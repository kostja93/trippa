package com.trippa.ai;

import com.intellij.openapi.ui.popup.Balloon;

/**
 * Created by fu on 20.02.16.
 */
public class NeuralNet {
    private Neuron[] inputNeurons;
    private Neuron[] hiddenNeurons;
    private Neuron outputNeuron;


    private static final NeuralNet thisObject = new NeuralNet();

    private NeuralNet(){
        if(thisObject != null){
            inputNeurons = new Neuron[2];
            hiddenNeurons = new Neuron[3];
            outputNeuron = new Neuron(false);
            Connection temp;

            // init Input-Layer
            for(int i = 0; i < inputNeurons.length; i++){
                inputNeurons[i] = new Neuron(true);
            }
            // init Hidden-Layer
            for(int i = 0; i < hiddenNeurons.length; i++){
                hiddenNeurons[i] = new Neuron(false);
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

    public static NeuralNet getNeuralNet(){
        return thisObject;
    }

    public double getOutput(int locationId){

    }

    public void initNNet(double[] weights){
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

    private void createInputPattern(int[] pattern){

    }

    public void safeWeigths(int userId){
        createWeightArray();
    }

    private void createWeightArray(){
        int arrayLength = 0;
        double[] weightArray;

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

    private void errorFunction(){

    }
}
