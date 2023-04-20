package uicli;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.RulesIF;
import model.Board;
import model.Position;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for explaining notation in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class NotationCLI extends TutorialCLI implements RulesIF{
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
                
                Press 'ENTER' to continue whenever you're ready.
                """);

        Scanner scan = new Scanner(System.in); // create a scanner to read user input
        String userInput = scan.nextLine(); // wait for user input

        while(!userInput.equals("0")) {
            Rank correctRank = getRandomRank(); // get a random rank
            Files correctFile = getRandomFile(); // get a random file
            ArrayList<Position> toHighlight = new ArrayList<Position>(); // create an array of positions to highlight
            toHighlight.add((new Position(correctRank, correctFile))); // add pos to array

            // highlight the random position
            board.getDrawStrategy().highlight(board, toHighlight, GameColor.WHITE);

            // ask the user to enter the square that was highlighted
            System.out.print("Enter the square that was highlighted (0 to quit): ");
            userInput = scan.nextLine(); // read the user input

            if(!userInput.equals("0")){ // if user doesn't quit, check if they're correct
                userInput = getResult(correctFile, correctRank, userInput, scan);
            }

        }
    } // end showRule()

    /**
     * This method is responsible for looping until the user makes the correct
     * guess as to what the highlighted square is.
     *
     * @param file  file of square highlighted
     * @param rank  rank of square highlighted
     * @param input input from the user
     * @param scan  scanner to read input from user if incorrect
     */
    private String getResult(Files file, Rank rank, String input, Scanner scan){
        boolean correct = false;
        while (!correct){ // loop until correct guess
            try {
                if (input.length() == 2 && //proper input and correct guess
                        Integer.parseInt(input.substring(1, 2)) == rank.getDisplayNum() && //right rank
                        input.charAt(0) == file.getFileChar()) { //right file
                    System.out.println("Correct!\n");
                    correct = true;
                } else if (input.length() == 2) { // wrong guess proper good input
                    System.out.println("Incorrect!");
                    System.out.print("Enter the square that was highlighted (0 to quit) ===> ");
                    input = scan.nextLine(); // read the user input
                    correct = checkQuit(input, scan); // check if user wants to quit
                }else{ // bad input (too short or too long)
                    System.out.println("Invalid input! EX of a valid input include 'a1' or 'A1'.");
                    System.out.print("Enter the square that was highlighted (EX: 0 to quit) ===> ");
                    input = scan.nextLine(); // read the user input
                    correct = checkQuit(input, scan); // check if user wants to quit
                }
            } catch(Exception e){ // something went wrong
                System.out.println("Invalid input! EX of a valid input include 'a1' or 'A1'.");
                System.out.print("Enter the square that was highlighted (0 to quit) ===> ");
                input = scan.nextLine(); // read the user input
                correct = checkQuit(input, scan); // check if user wants to quit
            }
        }
        return input;
    }

    /**
     * This method is responsible for checking if the user wants to quit.
     *
     * @param input user input string
     * @param scan  scanner to read input from user if incorrect
     * @return      true if user wants to quit, false otherwise
     */
    private boolean checkQuit(String input, Scanner scan){
        boolean quit = false;
        if(input.equals("0")){
            quit = true;
        }
        return quit;
    }
}