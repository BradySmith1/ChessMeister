package uicli;

import interfaces.RulePageIF;
import interfaces.RulesIF;
import tutorialuicli.*;

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
     *
     * @param scan Scanner for user input
     */
    public RulesCLI(Scanner scan) {
        this.scan = scan;
        populateMenu();
    }

    /**
     * Populates the menu with the title and options.
     */
    private void populateMenu() {
        menuOptions = new String[13];
        menuOptions[0] = " 1: Board Setup\n";
        menuOptions[1] = " 2: Understanding Notation\n";
        menuOptions[2] = " 3: Board Organization\n";
        menuOptions[3] = " 4: King Moves\n";
        menuOptions[4] = " 5: Queen Moves\n";
        menuOptions[5] = " 6: Bishop Moves\n";
        menuOptions[6] = " 7: Knight Moves\n";
        menuOptions[7] = " 8: Rook Moves\n";
        menuOptions[8] = " 9: Pawn Moves\n";
        menuOptions[9] = "10: Check\n";
        menuOptions[10] = "11: Checkmate\n";
        menuOptions[11] = "12: Draw\n";
        menuOptions[12] = " 0: Main Menu\n";
    }

    /**
     * Shows the rules page.
     *
     * @param boardColor the color of the board
     */
    @Override
    public void showRulesPage(String boardColor) {
        String menu = "View Rules\n------------------------------------------" +
                "---------------------\n" +
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
                menuOptions[9] +
                menuOptions[10] +
                menuOptions[11] +
                menuOptions[12] +
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
                    rule.showRule(boardColor);
                    break;
                case 2:
                    setRule(new NotationCLI());
                    rule.showRule(boardColor);
                    break;
                case 3:
                    setRule(new BoardOrganizationCLI());
                    rule.showRule(boardColor);
                    break;
                case 4:
                    setRule(new KingMovesCLI());
                    rule.showRule(boardColor);
                    break;
                case 5:
                    setRule(new QueenMovesCLI());
                    rule.showRule(boardColor);
                    break;
                case 6:
                    setRule(new BishopMovesCLI());
                    rule.showRule(boardColor);
                    break;
                case 7:
                    setRule(new KnightMovesCLI());
                    rule.showRule(boardColor);
                    break;
                case 8:
                    setRule(new RookMovesCLI());
                    rule.showRule(boardColor);
                    break;
                case 9:
                    setRule(new PawnMovesCLI());
                    rule.showRule(boardColor);
                    break;
                case 10:
                    setRule(new CheckRuleCLI());
                    rule.showRule(boardColor);
                    break;
                case 11:
                    setRule(new CheckmateRuleCLI());
                    rule.showRule(boardColor);
                    break;
                case 12:
                    setRule(new DrawRuleCLI());
                    rule.showRule(boardColor);
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
     * Sets the rule object.
     */
    public void setRule(RulesIF rule) {
        this.rule = rule;
    }
}
