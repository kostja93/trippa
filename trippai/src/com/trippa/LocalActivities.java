package com.trippa;

/**
 * Created by fu on 20.02.16.
 */

import com.trippa.ai.NeuralNet;

import java.sql.*;

public class LocalActivities {

    private static LocalActivities thisObject;

    private final double kmPerDegree = 40075 / 360;
    private int[] closeLocationsIds;

    // GPS
    private double userLongitude;
    private double userLatitude;

    // DB
    private ResultSet locations;
    private ResultSet users;
    private Statement statement;


    public LocalActivities(){
        if(thisObject == null){
            thisObject = this;
            try {
                statement = NeuralNet.getConnectionDB().createStatement();
                locations = statement.executeQuery("SELECT * FROM locations");
            } catch(Exception e){
                System.out.print("DB error");
            }
        }

    }

    public static LocalActivities getLocalActivityObject(){
        return thisObject;
    }


    // user Input missing
    public int[] getCloseLocation(double longitude, double latitude, int maxDistance){
        userLongitude = longitude;
        userLatitude = latitude;
        closeLocationsIds = new int[4];
        int counter = 0;

        try {
            while (locations.next()) {
                if (isCloseLocation(locations.getDouble("lon"), userLongitude, maxDistance)) {
                    if (isCloseLocation(locations.getDouble("lat"), userLatitude, maxDistance)) {
                        closeLocationsIds[counter] = locations.getInt("id");
                        if (counter == closeLocationsIds.length) enlargeArray(closeLocationsIds);
                    }
                }
            }
        } catch(Exception e){
            System.out.print("Error in getCloseLocation");
        }

        return closeLocationsIds;
    }

    private void enlargeArray(int[] a){
        int[] temp = new int[a.length *2];
        for(int i = 0; i < a.length; i++){
            temp[i] = a[i];
        }
        closeLocationsIds = temp;
    }

    private boolean isCloseLocation(double locationDegree, double userDegree, int maxDistance){
        if((locationDegree - userDegree) * kmPerDegree < maxDistance) return true;
        else return false;
    }

    // ai rating
    public double getRating(int locationId){
        return 1;
    }

}
