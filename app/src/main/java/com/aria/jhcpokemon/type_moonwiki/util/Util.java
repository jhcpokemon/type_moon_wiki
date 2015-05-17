package com.aria.jhcpokemon.type_moonwiki.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jhcpokemon on 05/05/15.
 */
public class Util {
    public static String getStringFromAssets(Context context,String fileName){
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            InputStream is = context.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString().replaceAll("\\s","");
    }
}
