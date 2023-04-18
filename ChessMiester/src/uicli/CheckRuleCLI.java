package uicli;

import enums.ChessPieceType;
import enums.GameColor;
import interfaces.RulesIF;
import java.util.Scanner;
import java.lang.StringBuilder;
import model.Board;
import model.BoardSaverLoader;
import model.Piece;

/**
 * This class is responsible for displaying the rules of check in chess.
 *
 * @author Kaushal Patel (60%), Zach Eanes (40%)
 * @version 1.0 (done in sprint 2)
 */
public class CheckRuleCLI implements RulesIF {
    /**
     * Displays the rules of check in chess.
     */
    @Override
    public void showRule() {
        StringBuilder str = new StringBuilder();

        /* create cool, fancy ascii art */
        str.append("""
                   _____ _               _   \s
                  / ____| |             | |  \s
                 | |    | |__   ___  ___| | __ \s
                 | |    | '_ \\ / _ \\/ __| |/ / \s
                 | |____| | | |  __/ (__|   <\s
                  \\_____|_| |_|\\___|\\___|_|\\_\\ \s
                                             
                Check is a condition in chess where a player's king is under attack by an \s
                opposing piece. If a player's king is in check, they must move their king to \s
                a square that is not under attack, capture the piece that is attacking \s
                their king, or place one of their own pieces in the way in order to defend \s
                the king. \s\s
                
                Whenever you are in check, that means there is a way of out the \s
                situation or else you would be in checkmate. To learn more about that, \s
                check out the tutorial for checkmate!\s\s
                """);

        /* display the check rules */
        System.out.println(str);

        /* wait for user to press any key to continue */
        System.out.print("When you're ready to try check, press 'ENTER' to continue ===> ");
        Scanner scanner = new Scanner(System.in); //create scanner object
        scanner.nextLine(); // read the next line of input

        System.out.println("""
                Let's try it out! We'll spawn in a board where there you only need to make \s
                one move for the opponent to be in check. Try to make that move!\s
                We'll let you know when you're right and wrong, so don't worry about making \s
                a mistake. Just try your best!\s
                """);

        /* Load in the board and go through game loop */
        BoardSaverLoader loader = new BoardSaverLoader(); // create loader to load board
        Board board = (Board) loader.loadGameFromFile("checkTutorial");
        board.setDrawStrategy(new BoardColorCLI()); // make it pretty :)

        String input = "1"; // basic string for user input
        board.draw(GameColor.WHITE); // draw board

        while(!input.equals("0")) { // loop game until user wants to quit
            System.out.println("Remember! Expected input is in the form of " +
                    "A1,A2 or A1, A2\nwith the first being the piece to move, and the second\n" +
                    "being the square to move to.\n");
            System.out.print("Enter a move (Enter 0 to quit) ===> "); // prompt and read input
            input = scanner.nextLine();
            input = input.toLowerCase().replaceAll("\\s", "");
            if(input.length() != 5){ // bad user
                System.out.println("Invalid input. Please try again.");
            }else if(input.equals("g2,e2")){ // user is right
                // place rook in right place and remove from old place
                board.getSquares()[6][4].setPiece(new Piece(ChessPieceType.Rook, GameColor.WHITE));
                board.getSquares()[6][6].setPiece(null);
                board.draw(GameColor.WHITE); // draw board
                System.out.println("You got it! The opponent is now in check!");
                System.out.print("Press 'ENTER' to return to the menu when ready ===> ");
                scanner.nextLine();
                break;
            }else{ // user is wrong
                System.out.println("Sorry, that's not the right move. Try again!");
            }
        }

    }
}
