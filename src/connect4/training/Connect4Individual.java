/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.training;

import connect4.game.Connect4Board;
import connect4.game.Connect4GameMaster;
import connect4.game.Connect4Player;
import geneticAlgorithm.CompIndividual;
import neuralNetwork.GridNeuralNetwork;

/**
 * individual for competing in a game of connect4.
 * @author William Kirby
 */
public class Connect4Individual extends CompIndividual {
    
    protected final GridNeuralNetwork nn;
    
    /**
     * create an connect4Individual with a given nn.
     */
    private Connect4Individual(GridNeuralNetwork nn) {
        this.nn = nn;
    }
    
    /**
     * generate a new random connect4Individual
     */
    public Connect4Individual() {
        this.nn = new GridNeuralNetwork(3, // number of hidden layers
                40, // depth of hidden layer
                Connect4Board.BOARD_HEIGHT*Connect4Board.BOARD_WIDTH // number of inputs 
                ,Connect4Board.BOARD_WIDTH); // number of outputs
    }
    
    /**
     * create a child of the given individual.
     * @return the created child
     */
    @Override
    public CompIndividual createChild() {
        return new Connect4Individual(this.nn.duplicateNetwork());
    }
    
    /**
     * compete the two individuals against each other
     * @param individual: individual to compete against
     * @return if this individual won.
     */
    @Override
    public boolean doesIndBeatInd(CompIndividual individual) {
        Connect4Individual opponent = (Connect4Individual) individual;
        Connect4GameMaster gameMaster = new Connect4GameMaster(this.generatePlayer(),opponent.generatePlayer());
        return gameMaster.runGame();
    }
    
    public Connect4Player generatePlayer() {
        return new Connect4Player(this.nn);
    }
    
    @Override
    public void applyMutation(double mutationChance) {
        this.nn.applyMutation(mutationChance);
    }

    @Override
    public void crossOverIndividual(CompIndividual ind) {
        // just dont bother with nn
    }
}
