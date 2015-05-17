package com.aria.jhcpokemon.type_moonwiki.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aria.jhcpokemon.type_moonwiki.R;
import com.aria.jhcpokemon.type_moonwiki.adapter.RecycleViewAdapter;
import com.aria.jhcpokemon.type_moonwiki.model.Release;
import com.aria.jhcpokemon.type_moonwiki.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhcpokemon on 04/29/15.
 */
public class ReleasesListFragment extends Fragment {
    public static final String TAG = "RELEASE_LIST_TAG";
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    public List<Release> releases = new ArrayList<>();
    private String[] release_id = null;
    private String[] release_name = null;
    private View v = null;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        v = inflater.inflate(R.layout.recycle_view, container, false);
        String releaseJson = Util.getStringFromAssets(getActivity(),"releases.json");
        releases = ParseReleases(releaseJson);
        recyclerView = (RecyclerView)v.findViewById(R.id.release_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter = new RecycleViewAdapter(this.getActivity(),releases);
        recyclerView.setAdapter(adapter);
        return v;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.release_pic);
            textView = (TextView) v.findViewById(R.id.release_name);
        }
    }
    public static List<Release> ParseReleases(String jsonData){
        Gson gson = new Gson();
        return gson.fromJson(jsonData,new TypeToken<List<Release>>(){}.getType());
    }
}
