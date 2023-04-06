package uicli;

import interfaces.RulesIF;
import java.lang.StringBuilder;
import java.util.Scanner;

import model.Board;

/**
 * This class is responsible for displaying the rules of a pawn in chess.
 *
 * @author Zach Eanes (100%)
 */
public class PawnMovesCLI implements RulesIF {
    @Override
    public void show() {
        StringBuilder str = new StringBuilder();
        /* create cool, fancy ascii art */
        str.append("""
                               @@@@@@&                                   \s
                            @@    (@@@@@@                                \s
                          @@  @@@@@@@@@@@@@                              \s
                          @@@@@@@@@@@@@@@@@                              \s
                           @@@@@@@@@@@@@@@                               \s
                                                                         \s
                          @@@@@@@@@@@@@@@@@@        _____                \s
                             ((((((((((.           |  __ \\              \s
                             @@@@@@@@@@@           | |__) |_ ___      ___ __ \s
                             @@@@@@@@@@@           |  ___/ _` \\ \\ /\\ / / '_ \\\s
                            @@@@@@@@@@@@@          | |  | (_| |\\ V  V /| | | |   \s
                           @@@@@@@@@@@@@@@,        |_|   \\__,_| \\_/\\_/ |_| |_|\s
                         @@@@@@@@@@@@@@@@@@@                             \s
                                                                         \s
                      @@@@@@@@@@@@@@@@@@@@@@@@@                          \s
                   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                       \s
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&                    \s
                @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ \
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

        /* wait for user to press any key to continue */
        System.out.println("Whenever you're ready to try it out, press 'ENTER' to continue >>> ");
        Scanner scan = new Scanner(System.in); // create scanner to read user input
        scan.nextLine(); // read line when user presses enter

        Board board = new Board(); // create new board
        board.initBoard(); // initialize board w just a pawn
        board.draw();

    }
}
