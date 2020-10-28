/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork;

import neuralNetwork.neurons.BiasNeuron;
import neuralNetwork.neurons.BranchNeuron;
import neuralNetwork.neurons.HiddenNeuron;
import neuralNetwork.neurons.InputNeuron;
import neuralNetwork.neurons.OutputNeuron;

/**
 * @author William Kirby
 */
public class GridNeuralNetwork {
    
    protected final BranchNeuron[][] hiddenLayers; // hiddenLayers making up the network
    protected final OutputNeuron[] outputLayer; // the output neurons
    protected final BranchNeuron[] firstLayer; // the firstLayer + the bias
    protected final InputNeuron[] inputs; // just inputs of first layer minus the bias
    protected final int numberOfInputs;
    protected final int numberOfOutputs;
    protected final int numberOfHiddenLayers;
    protected final int hiddenLayerDepth;
    
    /**
     * creates a random neuralNetwork of specified size
     * @param numberOfHiddenLayers: number of layers in the network
     * @param hiddenLayerDepth: hiddenLayers on each layer
     * @param numberOfInputs: number of firstLayer
     * @param numberOfOutputs: number of outputLayer
    */
    public GridNeuralNetwork(int numberOfHiddenLayers,int hiddenLayerDepth, int numberOfInputs,int numberOfOutputs) {    
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.numberOfHiddenLayers = numberOfHiddenLayers;
        this.hiddenLayerDepth = hiddenLayerDepth;
        //create the input neurons
        this.inputs = new InputNeuron[numberOfInputs];
        this.firstLayer = new BranchNeuron[numberOfInputs + 1];
        this.populateInputs();
        //create the output neurons
        this.outputLayer = new OutputNeuron[numberOfOutputs];     
        this.populateOutputs();
        //create the hiddenLayer
        this.hiddenLayers = new BranchNeuron[numberOfHiddenLayers][hiddenLayerDepth + 1];
        this.populateHiddenLayers();
        // establish connections with random weights
        this.establishRandomConnections();
    }
    
    /**
     * duplicate the provided network
     * @param nn: nn to duplicate.
     */
    private GridNeuralNetwork(GridNeuralNetwork nn) {
        this.numberOfInputs = nn.numberOfInputs;
        this.numberOfOutputs = nn.numberOfOutputs;
        this.numberOfHiddenLayers = nn.numberOfHiddenLayers;
        this.hiddenLayerDepth = nn.hiddenLayerDepth;
        //create the input neurons
        this.inputs = new InputNeuron[this.numberOfInputs];
        this.firstLayer = new BranchNeuron[this.numberOfInputs + 1];
        this.populateInputs();
        //create the output neurons
        this.outputLayer = new OutputNeuron[this.numberOfOutputs];     
        this.populateOutputs();
        //create the hiddenLayer
        this.hiddenLayers = new BranchNeuron[this.numberOfHiddenLayers][this.hiddenLayerDepth + 1];
        this.populateHiddenLayers();
        
        // copy given weights and connections
        // link firstLayer to the to hidden layer
        // start
        for(int start = 0;start < this.firstLayer.length;start++) {
            //destination
            for(int destination = 0;destination < (this.hiddenLayers[0].length - 1);destination++) {
                double weight = nn.firstLayer[start].getConnections().get(destination).getWeight();
                this.firstLayer[start].connect(this.hiddenLayers[0][destination],weight);
            }
        }
        //connect up the hidden layer
        // for every layer except the last
        for(int x = 0;x < (this.hiddenLayers.length - 1);x++) {
            //start
            for(int start = 0;start < this.hiddenLayers[x].length;start++) {
                //destination
                for(int destination = 0;destination < (this.hiddenLayers[x + 1].length - 1);destination++) {
                    double weight = nn.hiddenLayers[x][start].getConnections().get(destination).getWeight();
                    this.hiddenLayers[x][start].connect(this.hiddenLayers[x + 1][destination],weight);
                }
            }
        }
        
        // set correct functions types
        for(int x = 0;x < this.hiddenLayers.length;x++) {
            for(int i = 0;i < this.hiddenLayers[x].length - 1;i++) {
                HiddenNeuron thisHid = (HiddenNeuron) this.hiddenLayers[x][i];
                HiddenNeuron oldHid = (HiddenNeuron) nn.hiddenLayers[x][i];
                thisHid.setFunctionType(oldHid.getFunctionType());
            }
        }
        
        int lastLayer = this.hiddenLayers.length - 1;
        // start
        for(int start = 0;start < this.hiddenLayers[lastLayer].length;start++) {
            // destination
            for(int destination = 0;destination < this.outputLayer.length;destination++) {
                double weight = nn.hiddenLayers[lastLayer][start].getConnections().get(destination).getWeight();
                this.hiddenLayers[lastLayer][start].connect(this.outputLayer[destination], weight);
            }
        }

        
    }
    
    /**
    * populate lists with neurons but no connections
    */
    private void populateInputs() {
        for(int x = 0;x < firstLayer.length - 1;x++) {
            InputNeuron inputNeuron = new InputNeuron();
            this.inputs[x] = inputNeuron;
            this.firstLayer[x] = inputNeuron;
        }
        this.firstLayer[firstLayer.length - 1] = new BiasNeuron();
    }
    private void populateOutputs() {
        for(int x = 0;x < this.outputLayer.length;x++) {
            this.outputLayer[x] = new OutputNeuron();
        }
    }
    private void populateHiddenLayers() {
        for(int x = 0;x < this.hiddenLayers.length;x++) {
            for(int i = 0;i < (this.hiddenLayers[x].length - 1);i++) {
                this.hiddenLayers[x][i] = new HiddenNeuron();
            }
            this.hiddenLayers[x][this.hiddenLayerDepth] = new BiasNeuron();
        }  
    }
    
    /**
    * create connections
    */
    private void establishRandomConnections() {
        // link firstLayer to the to hidden layer
        // start
        final int firstLayer = 0;
        for(int start = 0;start < this.firstLayer.length;start++) {
            //destination
            for(int destination = 0;destination < (this.hiddenLayers[firstLayer].length - 1);destination++) {
                double weight = (Math.random()*2) - 1;
                this.firstLayer[start].connect(this.hiddenLayers[firstLayer][destination],weight);
            }
        }
        //connect up the hidden layer
        // for every layer except the last
        for(int x = 0;x < (this.hiddenLayers.length - 1);x++) {
            //start
            for(int start = 0;start < this.hiddenLayers[x].length;start++) {
                //destination
                for(int destination = 0;destination < (this.hiddenLayers[x + 1].length - 1);destination++) {
                    double weight = (Math.random()*2) - 1;
                    this.hiddenLayers[x][start].connect(this.hiddenLayers[x + 1][destination],weight);
                }
            }
        }
        int lastLayer = this.hiddenLayers.length - 1;
        // start
        for(int start = 0;start < this.hiddenLayers[lastLayer].length;start++) {
            // destination
            for(int destination = 0;destination < this.outputLayer.length;destination++) {
                double weight = (Math.random()*2) - 1;
                this.hiddenLayers[lastLayer][start].connect(this.outputLayer[destination], weight);
            }
        }
    }
    
    /**
     * calculate the output of the neural network for a given input
     * @param inputValues: inputValues to the neuralNetwork
     * @return outputLayer of the neural network
    */
    public double[] calculate(double[] inputValues) {
        //set input layer
        for(int x = 0;x < this.inputs.length;x++) {
            inputs[x].setInput(inputValues[x]);
        }
        // flush firstLayer in to the network
        for(int x = 0;x < this.firstLayer.length;x++) {
            this.firstLayer[x].flush();
        }
        // flush through the hidden layers
        for(int x = 0;x < this.hiddenLayers.length;x++) { // each layer
            for(int i = 0;i < this.hiddenLayers[x].length;i++) {
                this.hiddenLayers[x][i].flush();
            }
        }
        
        double[] outputValues = new double[this.outputLayer.length];
        for(int x = 0;x < this.outputLayer.length;x++) {
            outputValues[x] = this.outputLayer[x].getAndResetValue();
        }
        
        return outputValues;
    }
    
    /**
     * create a child of the network
     * @return the new network
     */
    public final GridNeuralNetwork duplicateNetwork() {
        return new GridNeuralNetwork(this);
    }
    
    /**
     * apply mutation to the given network
     * @param mutationChance: chance for mutation to be applied.
     */
    public void applyMutation(double mutationChance) {
        for(int x = 0;x < this.firstLayer.length;x++) { // mutate input layer
            this.firstLayer[x].mutate(mutationChance);
        }
        for(int x = 0;x < this.hiddenLayers.length;x++) {
            for(int i = 0;i < this.hiddenLayers[x].length;i++) {
                this.hiddenLayers[x][i].mutate(mutationChance);
            }
        }
    }
}
