/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.connections;

import neuralNetwork.neurons.Neuron;

/**
 * @author William Kirby
 */
public class NeuralConnection {
    
    protected double weight; // weight of the connection
    protected Neuron targetNeuron; // target targetNeuron
    private static final double MAX_WEIGHT_CHANGE = 0.05;
    
    /**
    * Constructor: creates a new connection with given weight and target.
     * @param targetNeuron:
     * @param weight: 
    */
    public NeuralConnection(Neuron targetNeuron, double weight) {
        this.weight = weight;
        this.targetNeuron = targetNeuron;
    }
    
    /**
     * pass the charge through the connection (gets multiplied by weight)
     * @param charge: charge to pass through
    */
    public void passCharge(double charge) {
        this.targetNeuron.addCharge(charge*weight);
    }
    
    /**
     * get the weight
     * @return double, weight stored
    */
    public double getWeight() {
        return this.weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void mutate(double mutationChance) {
        double rand = Math.random();
        if(rand < mutationChance) { // see if should mutate
            rand = (Math.random()*(MAX_WEIGHT_CHANGE*2)) - MAX_WEIGHT_CHANGE; // get change amount
            this.weight = this.weight + rand;
            if(this.weight > 1) this.weight = 1;
            else if(this.weight < -1) this.weight = -1;  
        }
    }
    
}
