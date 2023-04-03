package uicli;

import interfaces.MainMenuIF;
import interfaces.RulePageIF;
import interfaces.RulesIF;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RulesCLI implements RulePageIF {

    private String[] menuOptions;

    private RulesIF rule;

    private Scanner scan;

    public RulesCLI(Scanner scan) {
        this.scan = scan;
        populateMenu();
    }

    private void populateMenu() {
        menuOptions = new String[9];
        menuOptions[0] = "1: Board Setup\n";
        menuOptions[1] = "2: King Moves\n";
        menuOptions[2] = "3: Queen Moves\n";
        menuOptions[3] = "4: Bishop Moves\n";
        menuOptions[4] = "5: Knight Moves\n";
        menuOptions[5] = "6: Rook Moves\n";
        menuOptions[6] = "7: Pawn Moves\n";
        menuOptions[7] = "8: Overview\n";
        menuOptions[8] = "0: Main Menu\n";
    }

    @Override
    public void show() {
        String menu = "View Rules:---------------------------------------------------------------\n" +
                "Please make a selection as to what you would like to do:\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5] +
                menuOptions[6] +
                menuOptions[7] +
                menuOptions[8] +
                "\n-----------------------------------------" +
                "---------------------\n";
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String prompt = "Enter your menu choice here -> ";
        while (true) {//while user has not quit
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
                case 1 -> rule = new BoardSetupCLI();
                case 2 -> rule = new KingMovesCLI();
                case 3 -> rule = new QueenMovesCLI();
                case 4 -> rule = new BishopMovesCLI();
                case 5 -> rule = new KnightMovesCLI();
                case 6 -> rule = new RookMovesCLI();
                case 7 -> rule = new PawnMovesCLI();
                case 8 -> rule = new OverviewCLI();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Please enter a valid number (0 - 6)");
            }
            System.out.println();
            scan.nextLine();//consumes a new line so nextInt throwing an exception will not loop
        }
    }
}
