package com.aria.jhcpokemon.type_moonwiki.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aria.jhcpokemon.type_moonwiki.R;
import com.aria.jhcpokemon.type_moonwiki.fragment.ReleasesListFragment;
import com.aria.jhcpokemon.type_moonwiki.model.Release;

import java.util.List;

/**
 * Created by jhcpokemon on 04/29/15.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<ReleasesListFragment.ViewHolder> {
    private List<Release> releases;
    private Context context;

    public RecycleViewAdapter(Context context, List<Release> releases) {
        this.context = context;
        this.releases = releases;
    }

    @Override
    public ReleasesListFragment.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        return new ReleasesListFragment.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReleasesListFragment.ViewHolder viewHolder, int i) {
        Release release = releases.get(i);
        viewHolder.imageView.setImageDrawable(context.getDrawable(release.getReleaseImgResourceId(context)));
        viewHolder.textView.setText(release.getReleaseName());
    }

    @Override
    public int getItemCount() {
        return releases.size();
    }
}