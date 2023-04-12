package uicli;

import enums.GameColor;
import interfaces.RulesIF;
import model.Board;
/**
 * Class responsible for explaining notation in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class NotationCLI implements RulesIF {
    /**
     * Displays the rules of chess notation.
     */
    @Override
    public void showRule() {
        Board board = new Board(); // create a new board
        board.setDrawStrategy(new BoardColorCLI());
        board.draw(GameColor.WHITE); // draw the board for the player to see

        // display the rules of chess notation
        System.out.println("""
                Chess notation is a system that allows players to record and communicate the \s
                moves that have been made in a chess game. There are many different types of \s
                chess notation, but the most common is called algebraic notation. In algebraic \s
                notation, each square on the chess board is assigned a letter and a number. \s
                The letters are assigned from left to right, starting with the letter 'a'. \s
                The numbers are assigned from bottom to top, starting with the number '1'. \s
                For example, the square in the bottom left corner of the board is 'a1', and \s
                the square in the top right corner of the board is 'h8'.\s\s
                
                As seen in the above board, this implementation of chess follows the algebraic \s
                notation system. To make a move, simply type the square that the piece is \s
                currently on, followed by the square that the piece is moving to. The letters \s
                and numbers are displayed throughout the entire course of the game, so you \s
                should be able to easily identify the squares that you want to move to. \s\s
                
                Now, let's see if you have the right understanding! We're gonna highlight a \s
                few squares, and just enter what square they are. \s\s
                """);

        /* TODO: add a method to highlight squares on the board, and prompt user input */
    }
}