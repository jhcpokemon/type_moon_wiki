package com.aria.jhcpokemon.type_moonwiki.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aria.jhcpokemon.type_moonwiki.R;

/**
 * Created by jhcpokemon on 04/28/15.
 */
public class AboutFragment extends Fragment {
    ImageView aboutImg;
    ImageView about_line;
    TextView aboutName;
    TextView aboutVersion;
    TextView aboutThanks;
    View v = null;
    public static final String TAG = "about_fragment_atg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_about, container, false);
        aboutImg = (ImageView) v.findViewById(R.id.about_img);
        about_line = (ImageView) v.findViewById(R.id.about_line);
        aboutName = (TextView) v.findViewById(R.id.about_name);
        aboutVersion = (TextView) v.findViewById(R.id.about_version);
        aboutThanks = (TextView) v.findViewById(R.id.about_thanks);
        return v;
    }
}
