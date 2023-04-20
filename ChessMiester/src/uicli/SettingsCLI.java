package uicli;
import interfaces.SettingsIF;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the CLI implementation of the SettingsIF interface.
 */
public class SettingsCLI implements SettingsIF {

    private Scanner scan; /* Scanner object to read user input */

    private String[] menuOptions; /* Menu options */

    private String boardColor; /* Board color */

    private String undo; /* Undo option */

    private String showMoves; /* Show moves option */

    /**
     * Constructor for the SettingsCLI class.
     * @param scan Scanner object to read user input
     */
    public SettingsCLI(Scanner scan) {
        this.boardColor = "Color"; //TODO change back to mono
        this.undo = "on";
        this.showMoves = "on";
        this.menuOptions = new String[7];
        this.scan = scan;
        populateMenu();
    }

    /**
     * Populates the menu with the title and options.
     */
    private void populateMenu() {
        menuOptions[0] = "1: Set Mono board\n";
        menuOptions[1] = "2: Set Color board\n";
        menuOptions[2] = "3: Enable Undo\n";
        menuOptions[3] = "4: Disable Undo\n";
        menuOptions[4] = "5: Enable Show Move\n";
        menuOptions[5] = "6: Disable Show Moves\n";
        menuOptions[6] = "0: Main Menu";
    }

    /**
     * Shows the settings page.
     */
    @Override
    public void showSettings() {
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String prompt = "Enter your menu choice here -> ";
        while (true) {//while user has not quit
            String menu = "Settings\n\n" +
                    "---------------------------------------------------------------\n" +
                    "Board: " + this.boardColor + "\n" +
                    "Undo is " + this.undo + "\n" +
                    "Show Moves is " + this.showMoves + "\n" +
                    menuOptions[0] +
                    menuOptions[1] +
                    menuOptions[2] +
                    menuOptions[3] +
                    menuOptions[4] +
                    menuOptions[5] +
                    menuOptions[6] +
                    "\n\n-----------------------------------------" +
                    "---------------------\n";
            System.out.println(menu);   //shows user menu options
            System.out.print(prompt);
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
            System.out.println();
            switch (choice) {
                case 1 -> boardColor = "Mono";
                case 2 -> boardColor = "Color";
                case 3 -> undo = "on";
                case 4 -> undo = "off";
                case 5 -> showMoves = "on";
                case 6 -> showMoves = "off";
                case 0 -> {
                    return;
                }
                default -> System.out.println("Please enter a valid number (0 - 6)");
            }
            System.out.println();
            scan.nextLine();//consumes a new line so nextInt throwing an exception will not loop
        }
    }

    /**
     * Gets the board color.
     * @return String board color
     */
    @Override
    public String getBoardColor() {
        return boardColor;
    }

    /**
     * Gets the undo option.
     * @return String undo option
     */
    @Override
    public String getUndo() {
        return undo;
    }

    /**
     * Gets the show moves option.
     * @return String show moves option
     */
    @Override
    public String getShowMoves() {
        return showMoves;
    }
}
