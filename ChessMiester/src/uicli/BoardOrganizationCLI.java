package uicli;

import interfaces.RulesIF;
import java.util.Scanner;

/**
 * This class is responsible for explaining the organization of a chess
 * board and shown each pieces.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class BoardOrganizationCLI implements RulesIF {
    /**
     * Displays the rules of chess organization in chess.
     */
    @Override
    public void showRule() {
        System.out.println("""
                Players each receive 16 pieces, and the pieces are divided into six categories:\s
                        
                  P/P_W/P_B are Pawns - 8, which are placed before all your other pieces.\s
                  
                  R/R_W/R_B are Rooks - 2, which are placed into the corners of the board.\s
                  
                  N/N_W/N_B are Knights - 2, which are placed inside next to the rooks. \s
                  
                  B/B_W/B_B are Bishops - 2, which are placed inside next to the knights.\s
                  
                  Q/Q_W/Q_B is the Queen - 1, which is placed on the respective color square \s
                                              between the bishops. \s
                    For example, white is played on the light squares and black on dark squares.\s
                                              
                  K/K_W/K_B is the King - 1, is placed on the last vacant square between\s
                                             all of the pieces. \s\s
                        
                Each of these pieces have their own respective tutorial, so feel free to try them\s
                out and learn more about them.\s\s
                               """);

        /* wait for user to press enter */
        System.out.println("Press enter to whenever you're ready to return to the tutorial menu.");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
    }
}
