package uicli;

import enums.GameColor;
import interfaces.RulesIF;
import java.util.Scanner;
import java.lang.StringBuilder;
import model.Board;

/**
 * This class is responsible for displaying the rules of check in chess.
 *
 * @author Kaushal Patel (100%)
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
                 | |    | |__   ___  ___| | __
                 | |    | '_ \\ / _ \\/ __| |/ /
                 | |____| | | |  __/ (__|   <\s
                  \\_____|_| |_|\\___|\\___|_|\\_\\
                                             \s
                                             \s
                """);

        str.append("""
                Check is a condition in chess where a player's king is under attack by an \s
                opposing piece. If a player's king is in check, they must move their king to \s
                a square that is not under attack, or capture the piece that is attacking \s
                their king. If a player's king is in check and they cannot move their king \s
                to a square that is not under attack, or capture the piece that is attacking \s
                their king, then they are in checkmate, and the game is over. Checkout the\s
                checkmate rules to learn more about it.\s\s
                """);

        /* display the check rules */
        System.out.println(str);

        /* wait for user to press any key to continue */
        System.out.println("When you're ready to try check, press 'ENTER' to continue >>> ");
        Scanner scanner = new Scanner(System.in); //create scanner object
        scanner.nextLine(); // read the next line of input

        Board board = new Board(); // create a new board
        board.setup(); // setup the board
        board.draw(GameColor.WHITE);

        /* TODO: add code to show the user how to check the king */
    }
}
