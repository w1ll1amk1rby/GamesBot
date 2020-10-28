/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4.game;

/**
 * game master runs the game between two players for a game of connect4
 * @author William Kirby
 */
public class Connect4GameMaster {
    
    public final Connect4Player player1;
    public final Connect4Player player2;
    
    /**
     * create a new game master
     * @param player1: player one for the match
     * @param player2: player two for the match
     */
    public Connect4GameMaster(Connect4Player player1,Connect4Player player2) {
        this.player1 = player1;
        this.player1.setColour(Connect4Counter.RED);
        this.player2 = player2;
        this.player1.setColour(Connect4Counter.YELLOW);
    }
    
    /**
     * run a game between two players until there is a given winner
     * @return true if player1 won.
     */
    public boolean runGame() {
        Connect4Board board = new Connect4Board();
        //start the game
        while(true) {
            if(board.isBoardFull()) {
                System.out.println("game ended in a draw");
                this.printBoard(board);
                return false;
            }
            int column = this.player1.getNextMove(board);
            if(board.isColumnFull(column)) {
                System.out.println("made a wrong move");
                this.printBoard(board);
                return false;
            }
            else {
                board.playCounter(new Connect4Counter(this.player1.colour), column);
            }
            if(board.hasColourWon(this.player1.colour)) {
                System.out.println("not a draw");
                this.printBoard(board);
                return true;
            }
            
            if(board.isBoardFull()) {
                System.out.println("game ended in a draw");
                this.printBoard(board);
                return true;
            }
            column = this.player2.getNextMove(board);
            if(board.isColumnFull(column)) {
                System.out.println("made a wrong move");
                this.printBoard(board);
                return true;
            }
            else {
                board.playCounter(new Connect4Counter(this.player2.colour), column);
            }
            if(board.hasColourWon(this.player2.colour)) {
                System.out.println("not a draw");
                this.printBoard(board);
                return false;
            }
        }
    }
    
    
    public void printBoard(Connect4Board board) {
        for(int y = Connect4Board.BOARD_HEIGHT - 1;y >= 0;y--) {
            for(int x = 0;x < Connect4Board.BOARD_WIDTH; x++) {
                System.out.print("[");
                Connect4Counter counter = board.getCounterFromPoint(x, y);
                if(counter == null) {
                    System.out.print(" ");
                }
                else if(counter.getColour() == Connect4Counter.RED) {
                    System.out.print("X");
                }
                else {
                    System.out.print("O");
                }
                System.out.print("]");
            }
            System.out.print("\n");
        }
    }
}
