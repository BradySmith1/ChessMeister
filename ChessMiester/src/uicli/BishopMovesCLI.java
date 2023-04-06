package uicli;

import interfaces.RulesIF;
import java.util.Scanner;
import java.lang.StringBuilder;
import model.Board;

/**
 * This class is responsible for displaying the rules of a bishop in chess.
 *
 * @author Zach Eanes (100%)
 */
public class BishopMovesCLI implements RulesIF {
    /**
     * Displays the rules of a bishop in chess.
     */
    @Override
    public void show() {
        StringBuilder str = new StringBuilder();
        /* create cool, fancy ascii art */
        str.append("""
                      &@@@@\s
                       @@@ \s
                     #@@@@@(\s
                    (&@@@@@@@ \s
                  @@@&/@@@@@@@% \s
                 @@@@@@@@@@@@@@& \s
                 @@@@@@@@@@@@@@@ \s
                  @@@@@@@@@@@@@  \s
                  /@@@@@@@@@@@, \s
                   .*********   \s
                   #@@@@@@@@@,  \s
                 (@@@@@@@@@@@@@@  \s
                 &@@@@@@@@@@@@@@      ____  _     _                \s
                    @@@@@@@@@        |  _ \\(_)   | |               \s
                   /@@@@@@@@@,       | |_) |_ ___| |__   ___  _ __ \s
                   @@@@@@@@@@@       |  _ <| / __| '_ \\ / _ \\| '_ \\\s
                  @@@@@@@@@@@@@      | |_) | \\__ \\ | | | (_) | |_) |\s
                 @@@@@@@@@@@@@@@     |____/|_|___/_| |_|\\___/ | .__/\s
               *@@@@@@@@@@@@@@@@@/                             | |   \s
             &@@@@@@@@@@@@@@@@@@@@@*                           |_|\s
              @@@@@@@@@@@@@@@@@@@@@ \s
            *@@@@@@@@@@@@@@@@@@@@@@@  \s
           *@@@@@@@@@@@@@@@@@@@@@@@@@ \s
           @@@@@@@@@@@@@@@@@@@@@@@@@@. \s
           *@@@@@@@@@@@@@@@@@@@@@@@@@ \s
            #@@@@@@@@@@@@@@@@@@@@@@@ \s
            @@@@@@@@@@@@@@@@@@@@@@@@ \s
            """);

        str.append("""
                Bishops are considered to be one of the better piece in chess, and is why \s
                a player is only provided with two of them. Bishops can move diagonally in any \s
                direction, as long as there are no pieces in the way. Bishops can capture \s
                pieces by moving to the square that the opposing piece is on.\s\s
                
                Now that the rules have been explained, let's try it out!\s\s
                """);
        /* display the bishop rules */
        System.out.println(str);

        /* wait for user to press any key to continue */
        System.out.println("When you're ready to try the bishop, press 'ENTER' to continue >>> ");
        Scanner scan = new Scanner(System.in); // create scanner to read user input
        scan.nextLine(); // read line when user presses enter

        Board board = new Board(); // create new board
        board.initBoard(); // initialize board w just a pawn
        board.draw();
    }
}
