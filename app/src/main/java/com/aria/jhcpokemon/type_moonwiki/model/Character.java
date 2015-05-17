package com.aria.jhcpokemon.type_moonwiki.model;

/**
 * Created by jhcpokemon on 04/29/15.
 */
public class Character {
    private String character_id;
    private String release_id;
    private String character_name;

    public String getCharacter_id(){
        return this.character_id;
    }
    public void setCharacterId(String character_id) {
        this.character_id = character_id;
    }
    public String getRelease_id(){
        return this.release_id;
    }
    public void setReleaseId(String release_id) {
        this.release_id = release_id;
    }
    public String getCharacter_name(){
        return this.character_name;
    }
    public void setCharacterName(String character_name) {
        this.character_name = character_name;
    }
}
