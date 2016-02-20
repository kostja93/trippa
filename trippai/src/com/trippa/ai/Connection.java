package com.trippa.ai;

/**
 * Created by fu on 20.02.16.
 */
public class Connection {
    private double weight = 0;
    private Neuron connectionFrom;

    public Connection(Neuron neuron){
        connectionFrom = neuron;
    }

    public void setWeight(double w){
        weight = w;
    }

    public double getWeight(){
        return weight;
    }

    public Neuron getNeuron(){
        return connectionFrom;
    }
}
