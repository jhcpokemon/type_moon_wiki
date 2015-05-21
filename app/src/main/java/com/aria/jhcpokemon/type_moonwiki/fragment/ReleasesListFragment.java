package com.aria.jhcpokemon.type_moonwiki.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aria.jhcpokemon.type_moonwiki.R;
import com.aria.jhcpokemon.type_moonwiki.model.Release;
import com.aria.jhcpokemon.type_moonwiki.util.Util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by jhcpokemon on 04/29/15.
 */
public class ReleasesListFragment extends Fragment {
    public static final String TAG = "RELEASE_LIST_TAG";
    private static RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycle_view, container, false);
        String releaseJson = Util.getStringFromAssets(getActivity(), "releases.json");
        List<Release> releases = ParseReleases(releaseJson);
        recyclerView = (RecyclerView) v.findViewById(R.id.release_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        RecycleViewAdapter adapter = new RecycleViewAdapter(this.getActivity(), releases);
        recyclerView.setAdapter(adapter);
        return v;
    }

    /**
     * viewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.release_pic);
            textView = (TextView) v.findViewById(R.id.release_name);
        }
    }

    /**
     * Adapter
     */
    public class RecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Release> releases;
        private Context context;

        public RecycleViewAdapter(Context context, List<Release> releases) {
            this.context = context;
            this.releases = releases;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerView.getChildAdapterPosition(view);
                    Release release = releases.get(position);
                    String release_id = release.getReleaseId();
                    Fragment fragment = getActivity().getFragmentManager().findFragmentByTag(CharacterListFragment.TAG);
                    Bundle args = new Bundle();
                    args.putString("releaseId", release_id);
                    if (fragment == null) {
                        fragment = new CharacterListFragment();
                        fragment.setArguments(args);
                    }
                    getActivity().getFragmentManager().beginTransaction().replace(R.id.container, fragment, CharacterListFragment.TAG).addToBackStack(CharacterListFragment.TAG).commit();
                }
            });
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Release release = releases.get(i);
            viewHolder.imageView.setImageDrawable(context.getDrawable(release.getReleaseImgResourceId(context)));
            viewHolder.textView.setText(release.getReleaseName());
        }

        @Override
        public int getItemCount() {
            return releases.size();
        }
    }

    public static List<Release> ParseReleases(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, new TypeToken<List<Release>>() {
        }.getType());
    }
}
