package controller;

import enums.Files;
import enums.Rank;
import interfaces.BoardIF;
import model.Board;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Chess {

    private BoardIF board;

    private Scanner scan;

    //private Player player1;

    //private Player player2;


    public Chess() {
        scan = new Scanner(System.in); /* scanner to read in player choice */
    }

    public void mainMenu() {
        System.out.println("   _____ _                   __  __      _     _            \n" +
                "  / ____| |                 |  \\/  |    (_)   | |           \n" +
                " | |    | |__   ___  ___ ___| \\  / | ___ _ ___| |_ ___ _ __ \n" +
                " | |    | '_ \\ / _ \\/ __/ __| |\\/| |/ _ \\ / __| __/ _ \\ '__|\n" +
                " | |____| | | |  __/\\__ \\__ \\ |  | |  __/ \\__ \\ ||  __/ |   \n" +
                "  \\_____|_| |_|\\___||___/___/_|  |_|\\___|_|___/\\__\\___|_|   \n");
        String menu =
                "---------------------------------------------------------------\n" +
                        "Please make a selection as to what you would like to do:\n" +
                        "1 - Play Local Game Against another Player\n" +
                        "(COMING SOON!) 2 - Play Local Game Against a Computer\n" +
                        "(COMING SOON!) 3 - Play Online Game Against another Player\n" +
                        "(COMING SOON!) 4 - 4-Player Chess!\n" +
                        "0 - Exit Game\n--------------------------------------------------------------\n";
        String prompt = "Enter your menu choice here -> ";
        int choice = 999;     //initialized to 5 so there is no option chosen or quitting the loop
        while (choice != 0) {   //while user has not quit
            System.out.println(menu);   //shows user menu options
            System.out.print(prompt);
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (0 - 4)");
            }
            System.out.println();
            switch(choice) {
                case 1:
                    newGame();
                    break;
                case 2:
                    System.out.println("This feature is coming soon!");
                    break;
                case 3:
                    System.out.println("This feature is coming soon!");
                    break;
                case 4:
                    System.out.println("This feature is coming soon!");
                    break;
                case 0:
                    endGame();
                    break;
                default:
                    System.out.println("Please enter a valid number (0 - 4)");
                    break;
            }
            System.out.println();
            scan.nextLine();    //consumes a new line so that nextInt throwing an exception will not loop
        }
    }


    public void newGame() {
        System.out.println("Player one color: (1) White or (2) Black");
        int color = scan.nextInt();
        if (color == 1) {
            //player1 = new Player(GameColor.WHITE);
            //player2 = new Player(GameColor.BLACK);
        }
        else if (color == 2) {
            //player1 = new Player(GameColor.BLACK);
            //player2 = new Player(GameColor.WHITE);
        }
        else {
            System.out.println("Invalid input. Defaulting to white.");
            //player1 = new Player(GameColor.WHITE);
            //player2 = new Player(GameColor.BLACK);
        }
        //clears the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        board = new Board();
        board.setup();
        board.draw();
        //loop for move logic.
        while(true){
            endGame();
        }
    }

    public void endGame() {
        System.out.println("The game should be over now.\nreturning to main menu.");
        //clears the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        this.mainMenu();
    }

    public BoardIF loadGame(String file) {
        return null;
    }

    public void saveGame(String file, BoardIF game) {

    }
}
