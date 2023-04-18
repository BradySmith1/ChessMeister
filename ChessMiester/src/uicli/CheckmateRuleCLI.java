package uicli;

import enums.ChessPieceType;
import enums.GameColor;
import interfaces.RulesIF;
import model.Board;
import model.BoardSaverLoader;
import model.Piece;

import java.util.Scanner;

/**
 * This class is responsible for displaying the rules of checkmate in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class CheckmateRuleCLI implements RulesIF {

    /**
     * This method is responsible for displaying the rules of checkmate in chess.
     * This includes a message explaining what checkmate is, and a practice
     * game for the user to play an opponent in checkmate.
     */
    @Override
    public void showRule() {

        StringBuilder str = new StringBuilder();

        str.append("""
                   _____ _               _                    _      \s
                  / ____| |             | |                  | |     \s
                 | |    | |__   ___  ___| | ___ __ ___   __ _| |_ ___\s
                 | |    | '_ \\ / _ \\/ __| |/ / '_ ` _ \\ / _` | __/ _ \\ \s
                 | |____| | | |  __/ (__|   <| | | | | | (_| | ||  __/ \s
                  \\_____|_| |_|\\___|\\___|_|\\_\\_| |_| |_|\\__,_|\\__\\___| \s
                                
                Checkmate is a condition in chess where a player's king is under attack by an \s
                opposing piece, and the player cannot move their king to a square that is not \s
                under attack, or capture the piece that is attacking their king. If a player's \s
                king is in checkmate, then the game is over. Simply put, the goal of chess is \s
                to put your opponent in checkmate, as that's the true win condition of \s
                the game.\s\s
                                
                To understand more, we suggest looking at the tutorial for check as well!\s
                """);

        System.out.println(str);

        System.out.print("When you're ready to try checkmate, press 'ENTER' to continue ===> ");
        Scanner scanner = new Scanner(System.in); //create scanner object
        scanner.nextLine(); // read the next line of input

        BoardSaverLoader loader = new BoardSaverLoader(); //create a new BoardSaverLoader object
        Board board = (Board) loader.loadGameFromFile("checkmateTutorial");
        board.setDrawStrategy(new BoardColorCLI()); // make it pretty :)
        board.draw(GameColor.WHITE); // draw the board

        System.out.println("Remember! Expected input is in the form of " +
                "A1,A2 or A1, A2\nwith the first being the piece to move, and the second\n" +
                "being the square to move to.\n");
        System.out.print("Enter a move (0 to quit) ===> ");
        String input = scanner.nextLine(); // read the next line of input
        input = input.toLowerCase().replaceAll("\\s", ""); // remove spaces and make lowercase

        if (input.length() != 5) { // bad user
            System.out.println("Invalid input. Please try again.");
        } else if (input.equals("d7,h5")) { // user is right
            // move queen to correct place and remove from old spot
            board.getSquares()[3][7].setPiece(new Piece(ChessPieceType.Queen, GameColor.WHITE));
            board.getSquares()[1][3].setPiece(null); // remove the pawn
            board.draw(GameColor.WHITE); // draw the board
            System.out.println("You got it! The opponent is now in checkmate!");
            System.out.print("Press 'ENTER' to return to the menu when ready ===> ");
            scanner.nextLine();
        }else{ // user is wrong
            System.out.println("Sorry, that's not the right move or an invalid input. Try again!");
        }
    }
}
