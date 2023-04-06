package uicli;

import interfaces.RulesIF;
import java.util.Scanner;
import java.lang.StringBuilder;
import model.Board;

/**
 * This class is responsible for displaying the rules of a knight in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class KnightMovesCLI implements RulesIF {
    /**
     * Displays the rules of a knight in chess.
     */
    @Override
    public void show() {
        StringBuilder str = new StringBuilder();

        /* create cool, fancy ascii art */
        str.append("""
                               (@@@,,.                 \s
                           /@@,@@@@@@@,/@@#,           \s
                            ,,,@@@@@@@@@@,,@@@(,       \s
                         .@@@@@@@@@@@@@@@@@@%,@@@,     \s
                        ,@@/%@@@@@@@@@@@@@@@@@@,@@@,   \s
                       ,@@@@@@@@@@@@@@@@@@@@@@@@@,@@/  \s
                     ,@@@@@(//&@@@@@@@@@@@@@@@@@@@,@@/ \s
                   ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.@@,\s
                  %@@@@@@@@@@@@#/&@.@@@@@@@@@@@@@@@@@@@.    _  __      _       _     _  \s
                 ,@@@@@&&@.,,.     @@@@(@@@@@@@@@@@@@@@.   | |/ /     (_)     | |   | | \s
                   &@@@#          @@@/@@@@@@@@@@@@@@@@@.   | ' / _ __  _  __ _| |__ | |_\s
                                #@@/@@@@@@@@@@@@@@@@@@&    |  < | '_ \\| |/ _` | '_ \\| __|\s
                              %@((@@@@@@@@@@@@@@@@@@@@.    | . \\| | | | | (_| | | | | |_\s
                            @&/@@@@@@@@@@@@@@@@@@@@@@/     |_|\\_\\_| |_|_|\\__, |_| |_|\\__|\s
                          @@/@@@@@@@@@@@@@@@@@@@@@@@&                     __/ |         \s
                        #@//@@@@@@@@@@@@@@@@@@@@@@@@                     |___/         \s
                       @@//@@@@@@@@@@@@@@@@@@@@@@@@    \s
                       @@/@@@@@@@@@@@@@@@@@@@@@@@@/    \s
                     @@////(@@@@@@@@@@@@%/////////@,   \s
                    %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(   \s
                       @&/@@@@@@@@@@@@@@@@@@@@@@,.     \s
                    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,.  \s
                 @@&///////#@@@@@@@@@@@@@%///////////&@,
                %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*
                   """);

        str.append("""
                   Knights are considered to be one of the better piece in chess, and is why \s
                   a player is only provided with two of them. Knights can move in an L shape, \s
                   as long as there are no pieces in the way. Knights can capture pieces by \s
                   moving to the square that the opposing piece is on.\s\s
                   
                   Now that the rules have been explained, let's try it out!\s\s
                   """);

        /* print out the rules */
        System.out.println(str);

        /* wait for user to press any key to continue */
        System.out.println("When you're ready to try the knight, press 'ENTER' to continue >>> ");
        Scanner scan = new Scanner(System.in); // create scanner to read user input
        scan.nextLine(); // read line when user presses enter

        Board board = new Board(); // create new board
        board.initBoard(); // initialize board w just a pawn
        board.draw();

        /* TODO: draw board with a single knight for the user to interact with */

    }
}
