package uicli;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.RulesIF;
import model.Board;
import interfaces.TutorialIF;
import model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class responsible for explaining notation in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class NotationCLI implements RulesIF, TutorialIF {
    /**
     * Displays the rules of chess notation.
     */
    @Override
    public void showRule() {
        Board board = new Board(); // create a new board
        board.setDrawStrategy(new BoardMonoCLI());
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
                
                Press ENTER to continue whenever you're ready.);
                """);

        Scanner scan = new Scanner(System.in); // create a scanner to read user input
        String input = scan.nextLine(); // wait for user input

        while(!input.equals("0")) {
            Rank rank = getRandomRank(); // get a random rank
            Files file = getRandomFile(); // get a random file
            Position[] toHighlight = new Position[1]; // create an array of positions to highlight
            toHighlight[0] = (new Position(rank, file)); // add the random position to the list
            // highlight the random position
            board.getDrawStrategy().highlight(board, toHighlight, GameColor.WHITE);

            // ask the user to enter the square that was highlighted
            System.out.println("Enter the square that was highlighted (EX: a1 or A1): ");
            input = scan.nextLine(); // read the user input
            System.out.println(input);

            boolean correct = false;
            while (!correct) {
                if (input.charAt(0) == (file.getFileChar()) &&
                        Integer.parseInt(input.substring(1, 1)) == rank.getIndex()) {
                    System.out.println("Correct!");
                    correct = true;
                } else {
                    System.out.println("Incorrect!");
                }
            }
        }
    } // end showRule()
}