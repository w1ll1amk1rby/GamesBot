/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.game;

/**
 * board of a connect 4 game
 * @author William Kirby
 */
public class Connect4Board {
    
    public final static int BOARD_HEIGHT = 6;
    public final static int BOARD_WIDTH = 6;
    public final static int LINE_WIN_LENGTH = 4;
    
    private final Connect4Counter[][] board;
    
    /**
     * generate a fresh new board
     */
    public Connect4Board() {
        this.board = new Connect4Counter[BOARD_WIDTH][BOARD_HEIGHT];
    }
    
    /**
     * play a counter on to the board
     * @param counter: counter to place
     * @param column: column to put the counter in
     */
    public void playCounter(Connect4Counter counter, int column) {
        for(int x = 0;x < this.board[column].length;x++) {
            if(this.board[column][x] == null) {
                this.board[column][x] = counter;
                break;
            }
        }
    }
    
    /**
     * check if the board is full and no more can be added
     * @return is the board is full
     */
    public boolean isBoardFull() {
        for(int x = 0 ;x < this.board.length;x++) {
            if(!this.isColumnFull(x)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * find if a given column is full
     * @param column: column to check
     * @return true if the given column is full
     */
    public boolean isColumnFull(int column) {
        return this.board[column][BOARD_HEIGHT - 1] != null;
    }
    
    /**
     * get a counter from a specific point 
     * @param column: column to retrieve counter from
     * @param row: row to retrieve counter from
     * @return null if no counter or a counter from the selected space.
     */
    public Connect4Counter getCounterFromPoint(int column,int row) {
        if((column < BOARD_WIDTH) && (row < BOARD_HEIGHT)) return this.board[column][row];
        else return null;
    }   
    
    /**
     * check if a given colour has won the game.
     * @param colour: colour to check if won
     * @return true if colour has won.
     */
    public boolean hasColourWon(int colour) {
        //for each square
        for(int x = 0;x < this.board.length;x++) {
            for(int y = 0;y < this.board[x].length;y++) {
                if(this.checkIfColourHasWonFromPoint(x,y,colour)) {  
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * every point must be checked to effectively check if a user has won
     * @param x: point x to check
     * @param y: point y to check
     * @param colour: colour to check if won
     * @return true if the colour 
     */
    private boolean checkIfColourHasWonFromPoint(int x, int y, int colour) {
        
        boolean faultFound = false;
        // check if a line along the x
        for(int i = 0;i < LINE_WIN_LENGTH;i++) {
            if(x + i >= BOARD_WIDTH) {
                faultFound = true;
                break;
            }
            else if(this.board[x + i][y] == null) {
                faultFound = true;
                break;
            }
            else if(this.board[x + i][y].getColour() != colour) {
                faultFound = true;
                break;
            }
        }
        if(!faultFound) return true;
        //check if a line along the y
        faultFound = false;
        for(int i = 0;i < LINE_WIN_LENGTH;i++) {
            if(y + i >= BOARD_WIDTH) {
                faultFound = true;
                break;
            }
            else if(this.board[x][y + i] == null) {
                faultFound = true;
                break;
            }
            else if(this.board[x][y + i].getColour() != colour) {
                faultFound = true;
                break;
            }
        }
        if(!faultFound) return true;
        // check in posotive diagonal
        faultFound = false;
        for(int i = 0;i < LINE_WIN_LENGTH;i++) {
            // if off the board
            if((x+i >= BOARD_WIDTH) || (y+i >= BOARD_HEIGHT)) {
                faultFound = true;
                break;
            }
            else if(this.board[x + i][y + i] == null) {
                faultFound = true;
                break;
            }
            else if(this.board[x + i][y + i].getColour() != colour) {
                faultFound = true;
                break;
            }
        }
        if(!faultFound) return true;
        // check in pos neg diagonal
        faultFound = false;
        for(int i = 0;i < LINE_WIN_LENGTH;i++) {
            // if off the board
            if((x+i >= BOARD_WIDTH) || (y-i < 0)) {
                faultFound = true;
                break;
            }
            else if(this.board[x + i][y - i] == null) {
                faultFound = true;
                break;
            }
            else if(this.board[x + i][y - i].getColour() != colour) {
                faultFound = true;
                break;
            }
        }
        return !faultFound;
    }
}
