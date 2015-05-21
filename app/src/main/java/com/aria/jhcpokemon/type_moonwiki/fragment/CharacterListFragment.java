package com.aria.jhcpokemon.type_moonwiki.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aria.jhcpokemon.type_moonwiki.R;
import com.aria.jhcpokemon.type_moonwiki.model.Character;
import com.aria.jhcpokemon.type_moonwiki.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jhcpokemon on 05/19/15.
 */
public class CharacterListFragment extends Fragment {
    public static final String TAG = "CHARACTER_LIST_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);
        final ListView characterList = (ListView) view.findViewById(R.id.character_list);

        String releaseId = getArguments().getString("releaseId");
        String charactersJson = Util.getStringFromAssets(getActivity(), "characters.json");
        List<Character> charactersList = parseCharacters(charactersJson);
        final ArrayList<String> characters = getCharacters(charactersList, releaseId);
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.list_item, characters);
        characterList.setAdapter(adapter);
        characterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity().getApplicationContext(),characters.get(i),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public ArrayList<String> getCharacters(List<Character> characterList, String releaseId) {
        ArrayList<String> characterNames = new ArrayList<>();
        for (Character character : characterList) {
            if (character.getReleaseId().equals(releaseId)) {
                characterNames.add(character.getCharacterName());
            }
        }
        return characterNames;
    }

    public List<com.aria.jhcpokemon.type_moonwiki.model.Character> parseCharacters(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, new TypeToken<List<Character>>() {
        }.getType());
    }

}
