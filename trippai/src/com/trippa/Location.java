package com.trippa;

/**
 * Created by fu on 20.02.16.
 */
public class Location {
    private float rating;
    private int iD;

    public Location(float rat, int i){
        rating = rat;
        iD = i;
    }

    public float getRating(){
        return rating;
    }
    public int getiD(){
        return iD;
    }

}
