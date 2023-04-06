package uicli;

import interfaces.RulesIF;

import java.util.Scanner;
import model.Board;
import uicli.RulesCLI;

public class BoardSetupCLI implements RulesIF {
    @Override
    public void show() {
        /* create a new board */
        Board board = new Board();
        board.initBoard();
        board.draw();

        /* explain the setup/layout of a chess board */
        System.out.println("""
                Above is how a board will look when it's loaded. The board is 8x8,and each square\s
                is represented by a file (letter) and a rank (number). The files represent the\s
                columns, and the ranks represent the rows. Moves are made by moving a piece from\s
                one square to another. The squares are labeled with a file and a rank, and the\s
                pieces are labeled with their color and type.\s\s
                
                Players each receive 16 pieces, and the pieces are divided into six categories:\s
                Pawns - 8, which are placed before all your other pieces.\s
                Rooks - 2, which are placed into the corners of the board.\s
                Knights - 2, which are placed next to the rooks. \s
                Bishops - 2, which are placed next to the knights.\s
                Queen - 1, which is placed on the respective color square between the bishops. \s
                           For example, white is played on the light and black on dark squares.\s
                King - 1, is placed on the last vacant square between all of the pieces. \s
                Each of these pieces have their own respective tutorial, so feel free to try them\s
                out and learn more about them.\s\s
                
                Board setup will always be the same at the beginning of a chess game, so don't\s
                worry about having to learn but so much!\s\s
                
             
                """);

        /* wait for user to press enter */
        System.out.println("Press enter to whenever you're ready to return to the tutorial menu.");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();

        /* return to rules menu */
        RulesCLI rules = new RulesCLI();
        rules.show();
    }
}
