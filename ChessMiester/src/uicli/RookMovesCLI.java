package uicli;

import interfaces.RulesIF;
import java.util.Scanner;
import java.lang.StringBuilder;
import model.Board;

/**
 * This class is responsible for displaying the rules of a rook in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class RookMovesCLI implements RulesIF {
    /**
     * Displays the rules of a rook in chess.
     */
    @Override
    public void show() {
        StringBuilder str = new StringBuilder();

        /* create cool, fancy ascii art */
        str.append("""
                 .@@@    %@@@*    @@@ \s
                 .@@@@@@@@@@@@@@@@@@@ \s
                  @@@@@@@@@@@@@@@@@@( \s
                    @@@@@@@@@@@@@@/ \s
                      @@@@@@@@@@& \s
                       @@@@@@@@# \s
                       @@@@@@@@& \s
                      .@@@@@@@@@  \s
                      (@@@@@@@@@       _____                _   \s
                      @@@@@@@@@@(     |  __ \\             | |  \s
                      @@@@@@@@@@@     | |__) | ___    ___  | | __\s
                     @@@@@@@@@@@@/    |  _   // _ \\ / _ \\| |/ /\s
                    ,@@@@@@@@@@@@@    | | \\ \\(_) | |(_) ||    <\s
                    @@@@@@@@@@@@@@@   |_|  \\_\\___/\\___/ |_|\\_\\\s
                   @@@@@@@@@@@@@@@@# \s
                  ..................  \s
                 @@@@@@@@@@@@@@@@@@@@% \s
                 ,*******************  \s
             /@@@@@@@@@@@@@@@@@@@@@@@@@@@ \s
            ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ \s
            #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,
                """);

        str.append("""
                Rooks are considered to be one of the better pieces in chess, and is why \s
                a player is only provided with two of them. Rooks can move horizontally or \s
                vertically in any direction, as long as there are no pieces in the way. Rooks \s
                can capture pieces by moving to the square that the opposing piece is on.\s\s
                
                Now that the rules have been explained, let's try it out!\s\s
                """);

        /* display the rook rules */
        System.out.println(str);

        /* wait for user to press any key to continue */
        System.out.println("When you're ready to try the rook, press 'ENTER' to continue >>> ");
        Scanner scan = new Scanner(System.in); // create scanner to read user input
        scan.nextLine(); // read line when user presses enter

        Board board = new Board(); // create new board
        board.initBoard(); // initialize board w just a pawn
        board.draw();

        /* TODO: draw board with a single rook for the user to interact with */

    }
}
