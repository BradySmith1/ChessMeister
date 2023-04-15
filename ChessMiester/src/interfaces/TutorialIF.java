package interfaces;

import enums.GameColor;
import model.Board;
import model.BoardSaverLoader;
import uicli.BoardColorCLI;

import java.util.Scanner;

public interface TutorialIF{

    /**
     * This method will return the name of the file necessary to be loaded for the
     * game loop.
     *
     * @return the name of the file necessary to be loaded for the game loop.
     */
    public String getFileName();


    /**
     * This method will be responsible for loading the game, and looping
     * the player through until the game is over.
     *
     * @param file the name of the file to be loaded.
     */
     default void tutorialLoop(String file) {
         /* wait for user to press any key to continue */
         System.out.println("When you're ready to try the bishop, press 'ENTER' to continue. ");
         Scanner scan = new Scanner(System.in); // create scanner to read user input
         scan.nextLine(); // read line when user presses enter

         BoardSaverLoader loader = new BoardSaverLoader(); // create loader to load board
         Board board = (Board) loader.loadGameFromFile(file); // load board for bishop
         board.setDrawStrategy(new BoardColorCLI()); // make it pretty :)
         board.draw(GameColor.WHITE);

         String input = "1"; // basic string for user input
         while (!input.equals("0")) { // loop game until user wants to quit
             System.out.print("Enter a move (Enter 0 to quit) ===> "); // prompt and read input
             input = scan.nextLine();
             System.out.println(input);
                if (!input.equals("0")) { // if user didn't quit
                   // board.move(input); // move the piece
                    board.draw(GameColor.WHITE); // draw the board
                }
         }
     }
}
