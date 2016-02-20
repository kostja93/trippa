package com.trippa.ai;

import org.apache.sanselan.util.Debug;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by fu on 20.02.16.
 */
public class LearningAlgorithm {

    private LearningAlgorithm(){

    }

    private double error;
    private int[][] trainingPatterns;
    private int counter = 0;

    private Statement stmt;
    private ResultSet likes;

    public void trainNeuralNet(int userId){
        try {
            int tempLocation;
            int tempLike;
            stmt = NeuralNet.getConnectionDB().createStatement();
            likes = stmt.executeQuery("SELECT * FROM likes WHERE user_id = " + userId);
            while(likes.next()){
                tempLocation = likes.getInt("location_id");
                tempLike = likes.getInt("like");
                addLikeToPatternList(tempLocation, tempLike);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void enlargeTrainingPatterns(){
        int[][] temp = new int[trainingPatterns.length][8];
        for(int i = 0; i < trainingPatterns.length; i++){
            temp[i] = trainingPatterns[i];
        }
        trainingPatterns = new int[trainingPatterns.length*2][8];
        for(int i = 0; i < temp.length; i++){
            trainingPatterns[i] = temp[i];
        }
    }

    private void addLikeToPatternList(int loc, int like){
        if(counter == trainingPatterns.length) enlargeTrainingPatterns();
        trainingPatterns[counter] = NeuralNet.getNeuralNet().createTrainingPattern(loc, like);
        counter++;
    }

    private void errorFunction(){
        error = 0;
        for(int i = 0; i < trainingPatterns.length; i++){
            if(trainingPatterns[i] == null) break;
            error +=
        }
    }
}
