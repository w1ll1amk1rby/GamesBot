/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.game;

/**
 * 
 * @author William Kirby
 */
public class Connect4Counter {
    
    public final static int RED = 0;
    public final static int YELLOW = 1;
    
    private final int colour;
    
    /**
     * create a new counter with a given colour.
     * @param colour: colour of the counter
     */
    public Connect4Counter(int colour) {
        this.colour = colour;
    }
    
    public int getColour() {
        return this.colour;
    }
}
