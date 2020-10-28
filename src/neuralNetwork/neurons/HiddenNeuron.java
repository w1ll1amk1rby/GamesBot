/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.neurons;

/**
 * @author William Kirby
 */
public class HiddenNeuron extends BranchNeuron {
    
    private int functionType;
    public static final int LINEAR = 0;
    public static final int STEP = 1;
    public static final int EXPONENTIAL = 2;
    public static final int SIGMOID = 3;
    
    public HiddenNeuron() {
        this.functionType = (int) (Math.random()*4);
    }
    
    @Override
    protected double getOutput() {
        switch(this.functionType) {
            case LINEAR:
                return this.storedCharge;
            case STEP:
                if(this.storedCharge > 0) {
                    return 1;
                } 
                else {
                    return 0;
                }
            case EXPONENTIAL:
                return this.storedCharge*this.storedCharge;
            case SIGMOID:
                return 1/(1 + Math.pow(Math.E,-1*this.storedCharge));
            default:
                return 0;
        }
    }
    
    /**
     * get the hidden neurons function type
     * @return the given function type
     */
    public int getFunctionType() {
        return this.functionType;
    } 
    /**
     * set the function type of the hidden neuron
     * @param functionType: the value to set the function as
     */
    public void setFunctionType(int functionType) {
        this.functionType = functionType;
    }
    
    @Override
    public void mutate(double mutationChance) {
        super.mutate(mutationChance);
        double rand = Math.random();
        if(rand < mutationChance) { // see if should mutate
             this.functionType = (int) (Math.random()*4);
        }
    }
}