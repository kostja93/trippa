package com.trippa;

import com.trippa.ai.LearningAlgorithm;
import com.trippa.ai.NeuralNet;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        NeuralNet net = new NeuralNet();
        LearningAlgorithm learn = new LearningAlgorithm();
        LocalActivities localActivities = new LocalActivities();
        net.initNNetFromDB(3);
        System.out.println("Neural Net output: " + net.getOutput(1, 1));
        learn.trainNeuralNet(3);
        net.initNNetFromDB(3);

    }
}
