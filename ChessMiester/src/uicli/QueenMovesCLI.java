package uicli;

import interfaces.RulesIF;
import java.util.Scanner;
import java.lang.StringBuilder;
import model.Board;

/**
 * This class is responsible for displaying the rules of a queen in chess.
 *
 * @author Zach Eanes (100%)
 */
public class QueenMovesCLI implements RulesIF {
    /**
     * Displays the rules of a queen in chess.
     */
    @Override
    public void show() {
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
                            &&&&&&&&&&&     \\___\\\\__,_|\\___|\\___|_| |_|\s
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

        /* wait for user to press any key to continue */
        System.out.println("When you're ready to try the queen, press 'ENTER' to continue >>> ");
        Scanner scan = new Scanner(System.in); // create scanner to read user input
        scan.nextLine(); // read line when user presses enter

        Board board = new Board(); // create new board
        board.initBoard(); // initialize board w just a pawn
        board.draw();

    }
}
