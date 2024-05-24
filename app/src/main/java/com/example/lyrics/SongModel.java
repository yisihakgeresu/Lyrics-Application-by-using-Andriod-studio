package com.example.lyrics;

public class SongModel {
    private  String songName;
    private  String songNames;
    public SongModel(String songName){
        this.songName=songName;
//        this.songNames=songNames;
    }
    public String getSongName(){
        return songName;
    }
    public void setSongName(){
        this.songName=songName;
    }

    public String getTitle() {
        return getSongName();
    }

//    public static String getTitle() {
//        return getSongName();
//    }
}
