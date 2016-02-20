package com.trippa.ai;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by fu on 20.02.16.
 */
public class LearningAlgorithm {

    public LearningAlgorithm(){
    }

    private int[][] trainingPatterns;
    private int counterPattern = 0;

    private Statement stmt;
    private ResultSet likes;

    private static double learnRate = 0.01;
    private double[] deltaWeightArray = new double[24];
    private int currentPattern = 0;

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
            applyLearningAlgorithm();
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
        if(counterPattern == trainingPatterns.length) enlargeTrainingPatterns();
        trainingPatterns[counterPattern] = NeuralNet.getNeuralNet().createTrainingPattern(loc, like);
        counterPattern++;
    }

    private void applyLearningAlgorithm(){
        for(int i = 0; i < trainingPatterns.length; i++){
            if(trainingPatterns[i] == null) break;
            currentPattern = i;
            NeuralNet.getNeuralNet().parseTrainingPattern(trainingPatterns[i]);
            for(int j = 0; j < 3; j++) {
                for (int k = 0; k < 7; k++) {
                    deltaWeightArray[j*7 + k] = learnRate * calcDeltaForHidden(k) * trainingPatterns[i][k+1];
                }
            }
            deltaWeightArray[21] = learnRate * calcDeltaForOutput() * NeuralNet.getNeuralNet().getHiddenNeurons()[0].getOutput();
            deltaWeightArray[22] = learnRate * calcDeltaForOutput() * NeuralNet.getNeuralNet().getHiddenNeurons()[1].getOutput();
            deltaWeightArray[23] = learnRate * calcDeltaForOutput() * NeuralNet.getNeuralNet().getHiddenNeurons()[2].getOutput();

        }
    }

    private double calcDeltaForOutput(){
        double sigm;
        sigm = NeuralNet.getNeuralNet().getOutputForTraining();
        double output;
        output = sigm * (1-sigm) * (trainingPatterns[currentPattern][0] - sigm);
        return output;
    }

    private double calcDeltaForHidden(int i){
        double sigm;
        double output;
        sigm = NeuralNet.getNeuralNet().getHiddenForTraining(i);
        output = sigm * (1- sigm) * calcDeltaForOutput() * NeuralNet.getNeuralNet().getOutputNeuron().getConnections()[i].getWeight();
        return output;
    }


}
