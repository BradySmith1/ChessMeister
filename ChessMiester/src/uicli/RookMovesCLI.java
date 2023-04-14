package uicli;

import interfaces.RulesIF;
import interfaces.TutorialIF;
import java.lang.StringBuilder;


/**
 * This class is responsible for displaying the rules of a rook in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class RookMovesCLI implements RulesIF, TutorialIF {
    /**
     * Displays the rules of a rook in chess.
     */
    @Override
    public void showRule() {
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
    }

    /**
     * Method returns the name of the file used to load in a tutorial game for a rook.
     *
     * @return name of file used to load rook tutorial
     */
    @Override
    public String getFileName() { return "rookTutorial"; }
}
