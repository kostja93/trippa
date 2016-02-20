package com.trippa;

/**
 * Created by fu on 20.02.16.
 */
public class NeuralNet {
    private Neuron[] neurons;
    private static final NeuralNet thisObject = new NeuralNet();

    private NeuralNet(){
        if(thisObject != null){
            neurons = new Neuron[];
        } else{
            System.out.println("NeuralNet not created");
        }
    }
}
