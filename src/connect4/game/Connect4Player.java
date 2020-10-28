/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.game;

import neuralNetwork.GridNeuralNetwork;

/**
 * an abstract player for connect 4
 * @author William Kirby
 */
public class Connect4Player {
    
    protected int colour;
    private final GridNeuralNetwork nn;
    
    /**
     * create a connect4player
     * @param nn
     */
    public Connect4Player(GridNeuralNetwork nn) {
        this.nn = nn;
    }
    
    /**
     * set the players counter colour.
     * @param colour: colour to set as player colour
     */
    public void setColour(int colour) {
        this.colour = colour;
    }
    
    public int getNextMove(Connect4Board board) {
        int numberOfInputs = Connect4Board.BOARD_HEIGHT*Connect4Board.BOARD_WIDTH;
        double[] inputs = new double[numberOfInputs];
        // get inputs for nn by checking counters on board
        for(int x = 0;x < Connect4Board.BOARD_WIDTH;x++) {
            for(int y = 0;y < Connect4Board.BOARD_HEIGHT;y++) {
                Connect4Counter counter = board.getCounterFromPoint(x , y);
                if(counter != null) { // if a counter exists
                    int colour = counter.getColour();
                    if(colour == this.colour) { // if own counter set as one
                        inputs[(x*y) + y] = 1;
                    }
                    else {
                        inputs[(x*y) + y] = -1;
                    }
                }
            }
        }
        // calculate 
        double[] outputs = this.nn.calculate(inputs);
        // get the given column
        int column = 0; // find the first free column
        for(int x = 0;x < outputs.length;x++) {
            if(!board.isColumnFull(x)) {
                column = x;
                break;
            }
        }
        
        // find a stronger answer in a non full column
        for(int x = column + 1;x < outputs.length;x++) {
            //find the largest answer
            if((outputs[x] > outputs[column]) && (!board.isColumnFull(x))) {
                column = x;
            }
        }
        
        return column;
    }
}
