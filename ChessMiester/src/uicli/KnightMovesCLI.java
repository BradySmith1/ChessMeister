package uicli;

import interfaces.RulesIF;
import interfaces.TutorialIF;
import java.lang.StringBuilder;

/**
 * This class is responsible for displaying the rules of a knight in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class KnightMovesCLI implements RulesIF, TutorialIF {
    /**
     * Displays the rules of a knight in chess.
     */
    @Override
    public void showRule() {
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
    }

    /**
     * This method returns the name of the file used to load in a game for knights.
     *
     * @return the name of the file used to load in a game for knights
     */
    @Override
    public String getFileName() { return "knightTutorial"; }
}
