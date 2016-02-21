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

    Location[] loc;

    public LocalActivities(){
        if(thisObject == null){
            thisObject = this;
            try {
                statement = NeuralNet.getConnectionDB().createStatement();
                locations = statement.executeQuery("SELECT * FROM locations");
            } catch(Exception e){
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
        int counter = 1;

        try {
            while (locations.next()) {
                if (isCloseLocation(locations.getDouble("lon"), userLongitude, maxDistance)) {
                    if (isCloseLocation(locations.getDouble("lat"), userLatitude, maxDistance)) {
                        closeLocationsIds[counter] = locations.getInt("id");
                        counter++;
                        if (counter == closeLocationsIds.length) enlargeArray(closeLocationsIds);
                    }
                }
            }
        } catch(Exception e){
        }
        closeLocationsIds[0] = counter;
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
    private float getRating(int locationId, int userId){
        double temp = NeuralNet.getNeuralNet().getOutput(locationId, userId);
        temp *= 100;
        return (float)temp;
    }

    public int[] getSortedLocations(double lon, double lat, int userId, int dis){
        int[] temp = getCloseLocation(lon, lat, dis);
        int arrayLength = 0;

        loc = new Location[temp[0]-1];
        for(int i = 0; i < loc.length; i++){
           loc[i] = new Location(getRating(temp[i], userId), temp[i]);
        }
        sortLocations();
        int[] output = {0,0,0,0,0};
            for (int i = 0; i < loc.length; i++) {
                temp[i] = loc[i].getiD();
            }
        for(int i = 0; i < temp.length; i++){
            if(i >= 5 || output.length == 0) break;
            output[i] = temp[i];
        }
        return output;
    }
    private void sortLocations(){
        Location[] orderedLoc = new Location[5];
        Location temp = null;
        if(loc.length > 0) temp = loc[0];
        int counter = 0;
        for(int i = 1; i < loc.length; i++){
            for(int j = i + 1; j < loc.length; j++){
                if(j >= 5) break;
                if(loc[j].getRating() > temp.getRating()){
                    counter = j;
                }
            }
            temp = loc[counter];
            loc[counter] = loc[i];
            loc[i] = temp;
        }
        for(int i = 0; i < loc.length; i++) {
            System.out.println(loc[i].getRating());
        }
    }

}
