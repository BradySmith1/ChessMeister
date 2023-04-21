package tutorialuicli;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.RulesIF;
import model.Piece;
import model.Position;

import java.lang.StringBuilder;

/**
 * This class is responsible for displaying the rules of a knight in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class KnightMovesCLI extends TutorialCLI implements RulesIF{
    /**
     * Displays the rules of a knight in chess.
     *
     * @param boardColor the color of the board
     */
    @Override
    public void showRule(String boardColor) {
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

        /* make piece and call tutorial loop */
        Piece knight = new Piece(ChessPieceType.Knight, GameColor.WHITE);
        this.tutorialLoop("knightTutorial", knight, new Position(Rank.R1, Files.B), boardColor);
    }
}
