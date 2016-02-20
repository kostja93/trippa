package com.trippa;

import com.trippa.ai.NeuralNet;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        NeuralNet net = new NeuralNet();
        LocalActivities localActivities = new LocalActivities();
        net.initFirstTimeUser(1);
    }
}
