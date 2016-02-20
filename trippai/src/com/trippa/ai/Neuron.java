package com.trippa.ai;

/**
 * Created by fu on 20.02.16.
 */

public class Neuron {
    private double output;
    private double input;
    private double weight;

    private boolean isInputLayer;
    private Connection[] connections;
    private int counter = 0;

    public Neuron(boolean isInput, int numberOfConnections){
        isInputLayer = isInput;
        connections = new Connection[numberOfConnections];
    }

    public void addConnection(Connection newConnection){
        if(counter < connections.length) {
            connections[counter] = newConnection;
            counter++;
        }
    }

    private double getInput(){
        input = 0;
        for(int i = 0; i < connections.length; i++){
            if(connections[i] != null){
                input += connections[i].getNeuron().getOutput() * connections[i].getWeight();
            }
        }
        return input;
    }

    public Connection[] getConnections(){
        return connections;
    }

    public static double getSigmoidOutput(double input){
        double temp = (1 / (1 + Math.exp(-input)));
        return temp;
    }

    public void updateOutput(){
        if(!isInputLayer) {
            output = getSigmoidOutput(getInput());
        }
    }

    public double getOutput(){
        updateOutput();
        return output;
    }

    public void setOutput(double a){
        output = a;
    }
}
