package uicli;

import interfaces.LoadSaveGameIF;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class implements the play move dialog.
 */
public class PlayMoveCLI {

    private Scanner scan; /* Scanner for user input */

    private String[] menuOptions; /*options for the menu*/

    private LoadSaveGameIF saveGame; /*save game object*/

    /**
     * Constructor for the play move dialog.
     * @param scan Scanner for user input
     */
    public PlayMoveCLI(Scanner scan) {
        this.scan = scan;
        this.saveGame= new SaveGameCLI(scan);
        populateMenu();
    }

    /**
     * Populates the menu options.
     */
    private void populateMenu() {
        menuOptions = new String[6];
        menuOptions[0] = "1: Move\n";
        menuOptions[1] = "2: Undo\n";
        menuOptions[2] = "3: Redo\n";
        menuOptions[3] = "4: Show Moves\n";
        menuOptions[4] = "5: Save Game\n";
        menuOptions[5] = "6: Concede and Exit Game\n";
    }

    /**
     * Displays the play move dialog.
     */
    public void show() {
        String menu = "Play Chess\n---------------------------------------------------------------\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5];
        System.out.println(menu);
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String prompt = "Enter your menu choice here -> ";
        while (choice != 0) { //while user has not quit
            System.out.println(menu);   //shows user menu options
            System.out.print(prompt);
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
            switch(choice){
                case 1:
                    System.out.println("Move");
                    break;
                case 2:
                    System.out.println("Undo");
                    break;
                case 3:
                    System.out.println("Redo");
                    break;
                case 4:
                    System.out.println("Show Moves");
                    break;
                case 5:
                    this.saveGame.show();
                    break;
                case 6:
                    System.out.println("Concede and Exit Game");
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}
