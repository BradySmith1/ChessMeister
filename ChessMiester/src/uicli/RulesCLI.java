package uicli;

import interfaces.MainMenuIF;
import interfaces.RulePageIF;
import interfaces.RulesIF;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is responsible for displaying all the possible options for rules
 * in our implementation of chess.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 2)
 */
public class RulesCLI implements RulePageIF {

    private String[] menuOptions; /*options for the menu*/

    private RulesIF rule; /*rule object*/

    private Scanner scan; /*scanner for user input*/

    /**
     * Constructor for the rules page.
     */
    public RulesCLI(Scanner scan) {
        this.scan = scan;
        populateMenu();
    }

    /**
     * Populates the menu with the title and options.
     */
    private void populateMenu() {
        menuOptions = new String[9];
        menuOptions[0] = "1: Board Setup\n";
        menuOptions[1] = "2: Understanding Notation\n";
        menuOptions[2] = "3: King Moves\n";
        menuOptions[3] = "4: Queen Moves\n";
        menuOptions[4] = "5: Bishop Moves\n";
        menuOptions[5] = "6: Knight Moves\n";
        menuOptions[6] = "7: Rook Moves\n";
        menuOptions[7] = "8: Pawn Moves\n";
        menuOptions[8] = "0: Main Menu\n";
    }

    /**
     * Shows the rules page.
     */
    @Override
    public void showRulesPage() {
        String menu = "View Rules\n---------------------------------------------------------------\n" +
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
        while (choice != 0) {//while user has not quit
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
                case 1:
                    setRule(new BoardSetupCLI());
                    rule.showRule();
                    break;
                case 2:
                    setRule(new NotationCLI());
                    rule.showRule();
                    break;
                case 3:
                    setRule(new KingMovesCLI());
                    rule.showRule();
                    break;
                case 4:
                    setRule(new QueenMovesCLI());
                    rule.showRule();
                    break;
                case 5:
                    setRule(new BishopMovesCLI());
                    rule.showRule();
                    break;
                case 6:
                    setRule(new KnightMovesCLI());
                    rule.showRule();
                    break;
                case 7:
                    setRule(new RookMovesCLI());
                    rule.showRule();
                    break;
                case 8:
                    setRule(new PawnMovesCLI());
                    rule.showRule();
                    break;
                case 12:
                    setRule(new CheckRuleCLI());
                    rule.showRule();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid Number. Please enter a valid number (0 - 6)");
            }
            System.out.println();
            scan.nextLine();//consumes a new line so nextInt throwing an exception will not loop
        }
    }

    /**
     * Gets the rule object.
     * @return the rule object
     */
    public void setRule(RulesIF rule) {
        this.rule = rule;
    }
}