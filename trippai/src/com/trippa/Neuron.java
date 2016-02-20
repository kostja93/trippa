package com.trippa;

/**
 * Created by fu on 20.02.16.
 */

public class Neuron {
    private double output;
    private double input;
    private double weight;

    private Neuron[] inputNeurons = new Neuron[3];

    private double getInput(){
        output = 0;
        for(int i = 0; i < inputNeurons.length; i++){
            if(inputNeurons[i] != null){
                input += inputNeurons[i].getOutput() * inputNeurons[i].getWeight();
            }
        }
        return input;
    }

    public static double getSigmoidOutput(double input){
        double temp = (1 / (1 + Math.exp(-input)));
        return temp;
    }

    public void setWeight(double i){
        weight = i;
    }
    public double getWeight(){
        return weight;
    }

    public void updateOutput(){
        output = getSigmoidOutput(getInput());
    }

    public double getOutput(){
        updateOutput();
        return output;
    }
}
