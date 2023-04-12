package uicli;

import enums.GameColor;
import interfaces.RulesIF;
import java.util.Scanner;
import java.lang.StringBuilder;
import model.Board;

/**
 * This class is responsible for displaying the rules of a king in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class KingMovesCLI implements RulesIF {
    /**
     * Displays the rules of a king in chess.
     */
    @Override
    public void showRule() {
        StringBuilder str = new StringBuilder();

    /* create cool, fancy ascii art */
    str.append("""
               #@@@,\s
             @@@@@@@@@\s
               @@@@%\s
             .@@@@@@@\s
         ,@@@@@@@@@@@@@@@*\s
          @@@@@@@@@@@@@@&\s
           (@@@@@@@@@@@,\s
            /@@@@@@@@@,\s
            @@@@@@@@@@#\s
         .@@@@@@@@@@@@@@&\s
          (@@@@@@@@@@@@@(\s
              @@@@@@@         _  ___            \s
              @@@@@@@        | |/ (_)           \s
              @@@@@@@        | ' / _ _ __   __ _\s
             .@@@@@@@        |  < | | '_ \\ / _` |\s
             #@@@@@@@.       | . \\| | | | | (_| |\s
             @@@@@@@@@       |_|\\_\\_|_| |_|\\__, |\s
           .@@@@@@@@@@@                     __/ |\s
          /@@@@@@@@@@@@@                   |___/\s
         /@@@@@@@@@@@@@@@\s
        *@@@@@@@@@@@@@@@@@\s
       @@@@@@@@@@@@@@@@@@@@#\s
      *@@@@@@@@@@@@@@@@@@@@@ \s
         /&@@@@@@@@@@@@@@@#. \s
               """);

    str.append("""
            Kings are the most important piece in chess, and is why a player is only \s
            provided with one of them. Kings can move one square in any direction, \s
            as long as there are no pieces in the way. Kings can capture pieces by \s
            moving to the square that the opposing piece is on.\s\s
            
            Kings are the life of the game, and if they are captured or placed into checkmate, \s
            which is explained in another section of the tutorial, the game is over.\s
            Kings can be put into check by an opposing piece, which another rule also explained \s
            in another section of the tutorial.\s\s
            
            Now that the rules have been explained, let's try it out!\s\s
            """);

        /* display the king rules */
        System.out.println(str);

        /* wait for user to press any key to continue */
        System.out.println("When you're ready to try the king, press 'ENTER' to continue >>> ");
        Scanner scan = new Scanner(System.in); // create scanner to read user input
        scan.nextLine(); // read line when user presses enter

        Board board = new Board(); // create new board
        board.initBoard(); // initialize board w just a pawn
        board.draw(GameColor.WHITE);

        /* TODO: draw board with a single king for the user to interact with */
    }
}
