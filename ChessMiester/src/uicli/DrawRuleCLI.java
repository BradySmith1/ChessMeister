package uicli;

import controller.BoardMementoCaretaker;
import enums.ChessPieceType;
import enums.GameColor;
import interfaces.BoardIF;
import interfaces.RulesIF;
import model.Board;
import model.BoardSaverLoader;
import model.Piece;

import java.util.Scanner;

/**
 * This class is responsible for displaying the rules of draw in chess.
 */
public class DrawRuleCLI implements RulesIF {

    /**
     * This method creates the logic and display messages required for
     * explaining draw in chess.
     */
    @Override
    public void showRule() {
        StringBuilder str = new StringBuilder();

        str.append("""
                   _____                    \s
                  |  __ \\                   \s
                  | |  | |_ __ __ ___      __ \s
                  | |  | | '__/ _` \\ \\ /\\ / / \s
                  | |__| | | | (_| |\\ V  V /\s
                  |_____/|_|  \\__,_| \\_/\\_/ \s
                     
                     
                A draw is a result in chess where neither player wins or loses. \s
                There are many different ways of achieving the result of a draw, \s
                so let's explain them each: \s
                
                1. Stalemate: A stalemate is a result in chess where a player's king is \s
                              placed in a position where they are not currently under attack\s
                              but the only legal moves they have are to move into a square \s
                              that is under attack.\s
                              
                2. Threefold Repetition: A threefold repetition is a result in chess where \s
                                         a player has repeated the same position three times \s
                                         in a row. This can be achieved by moving the same \s
                                         pieces in the same order three times in a row.\s
                                         
                3. Fifty Move Rule: A fifty move rule is a result in chess where a player \s
                                    has not made a capture or a pawn move in the last 50 \s
                                    moves total, 25 for each player. \s
                                    
                4. Mutual Agreement: A mutual agreement is a result in chess where both \s
                                     players agree to end the game in a draw. \s
                                
                """);

        /* display the check rules */
        System.out.println(str);

        /* wait for user to press any key to continue */
        System.out.print("When you're ready to try check, press 'ENTER' to continue ===> ");
        Scanner scanner = new Scanner(System.in); //create scanner object
        scanner.nextLine(); // read the next line of input

        System.out.println("""
                Let's try it out! We'll spawn in a board where there you only need to make \s
                one move for the game to end in a draw. Try to make that move!\s
                We'll let you know when you're right and wrong, so don't worry about making \s
                a mistake. Just try your best!\s
                """);

        /* Load in the board and go through game loop */
        BoardSaverLoader loader = new BoardSaverLoader(); // create loader to load board
        BoardMementoCaretaker caretaker = loader.loadGameFromFile("drawTutorial");
        BoardIF board = new Board();
        board.loadFromMemento(caretaker.peek());// load board
        board.setDrawStrategy(new BoardColorCLI());

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
            }else if(input.equals("f2,b2")){ // user is right
                // place rook in correct spot and remove from old
                board.getSquares()[1][1].setPiece(new Piece(ChessPieceType.Rook, GameColor.WHITE));
                board.getSquares()[1][5].setPiece(null);
                board.draw(GameColor.WHITE); // draw board
                System.out.println("You got it! The game is now in a draw!");
                System.out.print("Press 'ENTER' to return to the menu when ready ===> ");
                scanner.nextLine();
                break;
            }else{ // user is wrong
                System.out.println("Sorry, that's not the right move. Try again!");
            }
        }
    }
}
