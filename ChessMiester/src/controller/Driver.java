/**
 * Simple driver for the Chess game.
 *
 * @author Zach Eanes (30%)
 * @author Colton Brooks (70%)
 */

package controller;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Board;
import uicli.BoardColorCLI;
import uicli.BoardMonoCLI;

public class Driver {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in); /* scanner to read in player choice */
        System.out.println("   _____ _                   __  __      _     _            \n" +
                           "  / ____| |                 |  \\/  |    (_)   | |           \n" +
                           " | |    | |__   ___  ___ ___| \\  / | ___ _ ___| |_ ___ _ __ \n" +
                           " | |    | '_ \\ / _ \\/ __/ __| |\\/| |/ _ \\ / __| __/ _ \\ '__|\n" +
                           " | |____| | | |  __/\\__ \\__ \\ |  | |  __/ \\__ \\ ||  __/ |   \n" +
                           "  \\_____|_| |_|\\___||___/___/_|  |_|\\___|_|___/\\__\\___|_|   \n");
        String menu =
                "Welcome to ChessMeister!\n---------------------------------------------------------------\n" +
                "Please make a selection as to what you would like to do:\n" +
                "1 - Play Local Game Against another Player\n" +
                "(COMING SOON!) 2 - Play Local Game Against a Computer\n" +
                "(COMING SOON!) 3 - Play Online Game Against another Player\n" +
                "(COMING SOON!) 4 - 4-Player Chess!\n" +
                "0 - Exit Game\n--------------------------------------------------------------\n";

        int choice = 5;     //initialized to 5 so there is no option chosen or quitting the loop
        Chess chess;    //initialization of the Chess class
        while (choice != 0) {   //while user has not quit
            System.out.println(menu);   //shows user menu options
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\n" + e);
            }
            if (choice >= 1 && choice <= 4) {
                chess = new Chess(choice);  //new game of chess based on the game type
            } else if (choice != 0) {
                System.out.println("\nPlease enter a valid number (0 - 4)\n");
                scan.nextLine();    //consumes a \n so the scanner can use nextInt properly next loop
            }
        }
    }
}
