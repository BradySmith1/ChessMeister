package uicli;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.RulesIF;
import model.Piece;
import model.Position;

import java.lang.StringBuilder;

/**
 * This class is responsible for displaying the rules of a queen in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class QueenMovesCLI extends TutorialCLI implements RulesIF{
    /**
     * Displays the rules of a queen in chess.
     */
    @Override
    public void showRule() {
        StringBuilder str = new StringBuilder();

        /* create cool, fancy ascii art */
        str.append("""
                             *&  \s
                            &&&&& \s
                             ., \s
                             &&& \s
                      %&&&*%&&&&&,#&&&,\s
                       /&&&&&&&&&&&&& \s
                        &&&&&&&&&&&& \s
                         &&&&&&&&&&%  \s
                       %%%%%%%%%%%%%%% \s
                       *,,,,,,,,,,,,,, \s
                         &&&&&&&&&&& \s
                         ,&&&&&&&&&        ____                       \s
                         .&&&&&&&&&       / __ \\                      \s
                         ,&&&&&&&&&      | |  | |_   _  ___  ___ _ __ \s
                         %&&&&&&&&&      | |  | | | | |/ _ \\/ _ \\ '_ \\\s
                         &&&&&&&&&&%     | |__| | |_| |  __/  __/ | | | \s
                         &&&&&&&&&&&      \\___\\\\__,_|\\___|\\___|_|_| |_| \s
                        &&&&&&&&&&&&&  \s
                       &&&&&&&&&&&&&&&\s
                     &&&&&&&&&&&&&&&&&&/ \s
                   &&&&&&&&&&&&&&&&&&&&&&% \s
                 (&&&&&&&&&&&&&&&&&&&&&&&&&. \s
                .&&&&&&&&&&&&&&&&&&&&&&&&&&& \s
                 ,,,,,,,,,,,,,,,,,,,,,,,,,,,\s
                """);

        str.append("""
                                    
                The queen is the most powerful piece in chess. The queen can move \s
                in any direction, as long as there are no pieces in the way. The queen \s
                can capture pieces by moving to the square that the opposing piece is on.\s\s
                                    
                You can think of the Queen as an all-in-one piece! It combines the moves \s
                of the rook and bishop, and can move in any direction. Make sure that \s
                you're careful with this piece, as it can completely turn the tide of \s
                a game!\s\s
                                    
                Now that the rules have been explained, let's try it out!\s\s
                """);

        /* display the queen rules */
        System.out.println(str);

        /* create piece and call tutorial loop */
        Piece queen = new Piece(ChessPieceType.Queen, GameColor.WHITE);
        this.tutorialLoop("queenTutorial", queen, new Position(Rank.R1, Files.D));
    }

}
