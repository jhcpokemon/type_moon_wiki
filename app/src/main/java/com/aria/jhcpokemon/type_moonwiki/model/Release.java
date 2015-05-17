package com.aria.jhcpokemon.type_moonwiki.model;

import android.content.Context;
import android.util.Log;

/**
 * Created by jhcpokemon on 04/29/15.
 */
public class Release {
    private String release_id;
    private String release_name;

    public Release(String release_id,String release_name){
        this.release_id = release_id;
        this.release_name = release_name;
    }
    public String getReleaseId(){
        return this.release_id;
    }
    public void setReleaseId(String release_id){
        this.release_id = release_id;
    }
    public String getReleaseName(){
        return this.release_name;
    }
    public void setReleaseName(String release_name){
        this.release_name = release_name;
    }
    public int getReleaseImgResourceId(Context context){
        try {
            return context.getResources().getIdentifier("img_"+release_id,"drawable",context.getPackageName());
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
