package uicli;

import enums.GameColor;
import interfaces.RulesIF;
import java.util.Scanner;
import model.Board;

/**
 * This class is responsible for explaining the setup of a chess board.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class BoardSetupCLI implements RulesIF {
    /**
     * Displays the rules of board setup in chess.
     */
    @Override
    public void showRule() {
        Board board = new Board(); // make a new board
        board.setDrawStrategy(new BoardColorCLI()); // make the board pretty :)
        board.setup(); // initialize the board with pieces
        board.draw(GameColor.WHITE); // display the board

        /* explain the setup/layout of a chess board */
        System.out.println("""
                Above is how a board will look when it's loaded. The board is 8x8,and each square\s
                is represented by a file (letter) and a rank (number). The files represent the\s
                columns, and the ranks represent the rows. Moves are made by moving a piece from\s
                one square to another. The squares are labeled with a file and a rank, and the\s
                pieces are labeled with their color and type.\s\s
                
                Board setup will always be the same at the beginning of a chess game, so don't\s
                worry about having to learn but so much!\s\s
                """);

        /* wait for user to press enter */
        System.out.println("Press 'ENTER' whenever you're ready to return to the tutorial menu.");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
}
