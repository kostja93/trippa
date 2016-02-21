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
             * eg: <Programm> -l 22
             *
             * ~int ~double ~double ~int    get the 5 best location (input: userId latitude longitude maxDistance)
             * -t ~iD   train the weights
             * -r ~iD   reset the weights
             */
            case 1:
                if(args[0].equals("-h")){
                    System.out.println("[int] [double] [double] [int]    get the 5 best location (input: userId latitude longitude maxDistance) ");
                    System.out.println("[-t] [iD]   train the weights ");
                    System.out.println("[-r] [iD]   reset the weights");
                break;
                }
            case 2:
                if(args[0].equals("-t")){
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
                System.out.println("[" + temp[0] + ", " + temp[1] + ", "+ temp[2] + ", "+ temp[3] + ", " + temp[4] + "]");
                break;
        }
    }
}
