package uicli;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.RulesIF;
import model.Piece;
import model.Position;
import tutorialuicli.TutorialCLI;

import java.lang.StringBuilder;

/**
 * This class is responsible for displaying the rules of a pawn in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class PawnMovesCLI extends TutorialCLI implements RulesIF {
    /**
     * Displays the rules of a pawn in chess.
     */
    @Override
    public void showRule() {
        StringBuilder str = new StringBuilder();
        /* create cool, fancy ascii art */
        str.append("""
                               @@@@@@&                                   \s
                            @@    (@@@@@@                                \s
                          @@  @@@@@@@@@@@@@                              \s
                          @@@@@@@@@@@@@@@@@                              \s
                           @@@@@@@@@@@@@@@                               \s
                                                                         \s
                          @@@@@@@@@@@@@@@@@@       _____                \s
                             ((((((((((.          |  __ \\              \s
                             @@@@@@@@@@@          | |__) |_ ___      ___ __ \s
                             @@@@@@@@@@@          |  ___/ _` \\ \\ /\\ / / '_ \\\s
                            @@@@@@@@@@@@@         | |  | (_| |\\ V  V /| | | |   \s
                           @@@@@@@@@@@@@@@,       |_|   \\__,_| \\_/\\_/ |_| |_|\s
                         @@@@@@@@@@@@@@@@@@@                             \s
                                                                         \s
                      @@@@@@@@@@@@@@@@@@@@@@@@@                          \s
                   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                       \s
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&                    \s
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ \s
                """);

        /* add all important rules of a pawn */
        str.append("""
                                
                Pawns are considered to be the least powerful piece in chess, and is why a \s
                player is provided with eight of them. Pawns can only move forward, and \s
                can only capture diagonally. Pawns can move two spaces on their first move, \s
                but only one space on all subsequent moves (unless blocked by another piece \s
                of course). Pawns can promote to a queen, rook, bishop, or knight if they \s
                reach the other players end of the board.\s

                Pawns can also perform an en passant capture, which is a special move that \s
                can only be performed if the pawn is on the 5th rank and the opposing pawn is \s
                on the 4th rank. The pawn can only capture the opposing pawn if it moves \s
                diagonally one space forward and lands on the square that the opposing pawn \s
                just moved to.\s

                Now that the rules have been explained, let's try it out!\s\s
                """);

        /* display the pawn rules */
        System.out.println(str);

        /* create piece and call tutorial loop */
        Piece pawn = new Piece(ChessPieceType.Pawn, GameColor.WHITE);
        this.tutorialLoop("pawnTutorial", pawn, new Position(Rank.R2, Files.D));
    }
}
