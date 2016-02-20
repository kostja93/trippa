package com.trippa.ai;

import java.sql.*;

/**
 * Created by fu on 20.02.16.
 */
public class NeuralNet {
    private Neuron[] inputNeurons;
    private Neuron[] hiddenNeurons;
    private Neuron outputNeuron;
    private double[] weightArray;

    // location rating
    private int priceAverage = 0;
    private int equipmentAverage = 0;
    private int atmosphereAverage = 0;
    private int starsAverage = 0;

    private static NeuralNet thisObject;

    private static java.sql.Connection connectionDB;
    private ResultSet weightingQuery;
    private ResultSet locationRating;

    private Statement stmt;

    public NeuralNet(){
        if(thisObject == null){
            thisObject = this;
            initDbConnection();
            /**
             * 7 input neurons:
             *      1+2 --> price (00 small, 01 medium, 10 large)
             *      3+4 --> equipment (00 small, 01 medium, 10 large)
             *      5+6 --> atmosphere (00 small, 01 medium, 10 large)
             *      7   --> average user rating (real number between [0,1]
             */
            inputNeurons = new Neuron[7];
            hiddenNeurons = new Neuron[3];
            outputNeuron = new Neuron(false, 3);
            Connection temp;

            // init Input-Layer
            for(int i = 0; i < inputNeurons.length; i++){
                inputNeurons[i] = new Neuron(true, 0);
            }
            // init Hidden-Layer
            for(int i = 0; i < hiddenNeurons.length; i++){
                hiddenNeurons[i] = new Neuron(false, 7);
            }

            // init Connections to Hidden-Layer
            for(int i = 0; i < hiddenNeurons.length; i++){
                for(int k = 0; k < inputNeurons.length; k++) {
                    temp = new Connection(inputNeurons[k]);
                    hiddenNeurons[i].addConnection(temp);
                }
            }

            // init Connections to Output-Layer
            for(int i = 0; i < hiddenNeurons.length; i++){
                temp = new Connection(hiddenNeurons[i]);
                outputNeuron.addConnection(temp);
            }

        } else{
            System.out.println("NeuralNet not created");
        }
    }

    public static java.sql.Connection getConnectionDB(){
        return connectionDB;
    }

    public static NeuralNet getNeuralNet(){
        return thisObject;
    }

    public double getOutput(int locationId, int userId){
        setLocationAverageRating(locationId);
        initNNetFromDB(userId);
        prepareNetInputFromLocation(locationId);
        return outputNeuron.getOutput();
    }

    private void setLocationAverageRating(int locationId){
        int counterTemp = 0;
        try{
            locationRating = stmt.executeQuery("SELECT * FROM ratings WHERE location_id = " + locationId);
            while(locationRating.next()){
                priceAverage += locationRating.getInt("price_id");
                equipmentAverage += locationRating.getInt("equipment_id");
                atmosphereAverage += locationRating.getInt("atmosphere_id");
                starsAverage += locationRating.getInt("stars");
                counterTemp++;
            }
            if(counterTemp > 0) {
                equipmentAverage = Math.round(equipmentAverage / counterTemp);
                atmosphereAverage = Math.round(atmosphereAverage / counterTemp);
                priceAverage = Math.round(priceAverage / counterTemp);
                starsAverage = Math.round(starsAverage / counterTemp);
            }else{
                equipmentAverage = 1;
                atmosphereAverage = 1;
                priceAverage = 1;
                starsAverage = 0;
            }

        }catch(Exception e){
            System.out.println("couldnt get locationRating");
        }
    }

    public void initNNetFromDB(int userId){
        getWeightArrayFromDB(userId);
        initWeightingFromDB(weightArray);
    }

    public void initFirstTimeUser(int userId){
            for(int i = 0; i < hiddenNeurons.length; i++){
                for(int k = 0; k < inputNeurons.length; k++){
                    hiddenNeurons[i].getConnections()[k].setWeight(Math.random() * 2 - 1);
                    System.out.println("Hidden Neuron Connection " + k + "." + i + " : " + hiddenNeurons[i].getConnections()[k].getWeight());
                }
            }
            for(int i = 0;  i < hiddenNeurons.length; i++){
                outputNeuron.getConnections()[i].setWeight(Math.random() * 2 - 1);
            }
            safeWeigths(userId);
    }

    private void initDbConnection(){
        try {
            connectionDB = DriverManager.getConnection("jdbc:sqlite:../database.db");
            stmt = connectionDB.createStatement();
            System.out.println("connection to DB established");
        }catch(Exception e){
            System.out.println("couldnt init DB Connection");
        }
    }

    private void initWeightingFromDB(double[] weights){
        int temp = 0;
        Connection[] tempConnections;
        for(int i = 0; i < hiddenNeurons.length; i++){
            tempConnections = hiddenNeurons[i].getConnections();
            for(int k = 0; k < tempConnections.length; k++){
                tempConnections[k].setWeight(weights[temp]);
                temp++;
            }
        }
        tempConnections = outputNeuron.getConnections();
        for(int i = 0; i < tempConnections.length; i++){
            tempConnections[i].setWeight(weights[temp]);
            temp++;
        }
    }

    private void applyPatternToNet(int[] pattern){
        for(int i = 0; i < inputNeurons.length; i++){
            inputNeurons[i].setOutput(pattern[i]);
        }
    }

    public void safeWeigths(int userId){
        createWeightArrayFromCurrent();

        try {
            for(int i = 0; i < weightArray.length; i++) {
                stmt.execute("INSERT INTO weighting (user_id, value, key) VALUES (" +
                        userId + ", " +
                        weightArray[i] + ", " +
                        i + ")");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("updating weighting failed");
        }
    }

    private void getWeightArrayFromDB(int userId){
        createWeightArrayFromCurrent();
        try {
            weightingQuery = stmt.executeQuery("SELECT * FROM weighting WHERE user_id = " + userId);
            while(weightingQuery.next()){
                weightArray[weightingQuery.getInt("key")] = weightingQuery.getDouble("value");
            }
        } catch(Exception e){
            System.out.println("couldnt get weighting  from DB");
        }
    }
    private void createWeightArrayFromCurrent(){
        int arrayLength = 0;

        // initializing the array
        for(int i = 0; i < hiddenNeurons.length; i++){
            arrayLength += hiddenNeurons[i].getConnections().length;
        }
        arrayLength += outputNeuron.getConnections().length;
        weightArray = new double[arrayLength];

        // filling the array
        int temp = 0;
        Connection[] tempConnections;
        for(int i = 0; i < hiddenNeurons.length; i++){
            tempConnections = hiddenNeurons[i].getConnections();
            for(int k = 0; k < tempConnections.length; k++){
                weightArray[temp] = tempConnections[k].getWeight();
                temp++;
            }
        }
        tempConnections = outputNeuron.getConnections();
        for(int i = 0; i < tempConnections.length; i++){
            weightArray[temp] = tempConnections[i].getWeight();
            temp++;
        }
    }

    private void prepareNetInputFromLocation(int locationId) {
        int[] inputPattern = new int[7];
        switch (priceAverage) {
            case 0:
                inputPattern[0] = 0;
                inputPattern[1] = 1;
                break;
            case 1:
                inputPattern[0] = 1;
                inputPattern[1] = 0;
                break;
            case 2:
                inputPattern[0] = 1;
                inputPattern[1] = 1;
                break;
            default:
                inputPattern[0] = 0;
                inputPattern[1] = 0;
                break;
        }
        switch (equipmentAverage) {
            case 0:
                inputPattern[2] = 0;
                inputPattern[3] = 1;
                break;
            case 1:
                inputPattern[2] = 1;
                inputPattern[3] = 0;
                break;
            case 2:
                inputPattern[2] = 1;
                inputPattern[3] = 1;
                break;
            default:
                inputPattern[2] = 0;
                inputPattern[3] = 0;
                break;
        }
        switch (atmosphereAverage) {
            case 0:
                inputPattern[4] = 0;
                inputPattern[5] = 1;
                break;
            case 1:
                inputPattern[4] = 1;
                inputPattern[5] = 0;
                break;
            case 2:
                inputPattern[4] = 1;
                inputPattern[5] = 1;
                break;
            default:
                inputPattern[4] = 0;
                inputPattern[5] = 0;
                break;
        }

        inputPattern[6] = starsAverage;
        applyPatternToNet(inputPattern);
        for (int i = 0; i < inputPattern.length; i++) {
            System.out.println("input Neuron: " + i);
            System.out.println("input value: " + inputPattern[i]);
        }

    }
    public int[] createTrainingPattern(int locationId, int liked){
        int[] temp = new int[8];
        setLocationAverageRating(locationId);
        prepareNetInputFromLocation(locationId);
        for(int i = 1; i < 8; i++){
            temp[i] = (int) inputNeurons[i].getOutput();
        }
        return temp;
    }

    public double parseTrainingPattern(int[] a){
        int[] temp = new int[7];
        for(int i = 1; i < a.length; i++){
            temp[i-1] = a[i];
        }
        applyPatternToNet(temp);
        return outputNeuron.getOutput();
    }

}
