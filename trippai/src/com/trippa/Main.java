package com.trippa;

import com.trippa.ai.LearningAlgorithm;
import com.trippa.ai.NeuralNet;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        int userId;
        double lat;
        double lon;
        int dis;

        NeuralNet net = new NeuralNet();
        LearningAlgorithm learn = new LearningAlgorithm();
        LocalActivities localActivities = new LocalActivities();

        switch(args.length) {
            /**
             * Bsp: <Programm> -l 22
             * es werden 50 Trainingszyklen durchgeführt beim User mit der ID 22
             */
            case 2:
                if(args[0].equals("-l")){
                    learn.trainNeuralNet(Integer.parseInt(args[1]));
                } else if(args[0].equals("-r")){
                    net.resetNeuralNet(Integer.parseInt(args[1]));
                }
                break;
            default:
                userId = Integer.parseInt(args[0]);
                lat = Double.parseDouble(args[1]);
                lon = Double.parseDouble(args[2]);
                dis = Integer.parseInt(args[3]);


                int[] temp = localActivities.getSortedLocations(lon, lat, userId, dis);
                System.out.print("[" + temp[0] + ", " + temp[1] + ", "+ temp[2] + ", "+ temp[3] + ", " + temp[4] + "]");
                break;
        }
    }

    private static void printLocalActivities(int userId, double lat, double lon, int dis){

    }
}
