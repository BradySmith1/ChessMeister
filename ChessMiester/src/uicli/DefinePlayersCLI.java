package uicli;

import enums.GameColor;
import interfaces.DefinePlayersIF;
import interfaces.PlayerIF;
import model.Player;
import java.util.Scanner;

/**
 * This class implements the main menu dialog for the command line interface.
 * @author Brady Smith 100%
 */
public class DefinePlayersCLI implements DefinePlayersIF {

    private Scanner scan; /*scanner for user input*/
    private String[] menuOptions; /*options for the menu*/

    private PlayerIF player1;  /*player 1*/

    private PlayerIF player2; /*player 2*/

    /**
     * Constructor for the main menu.
     */
    public DefinePlayersCLI(Scanner scan) {
        this.scan = scan;
        player1 = new Player(GameColor.WHITE);
        player1.setName("Player 1");    // Default name
        player2 = new Player(GameColor.BLACK);
        player2.setName("Player 2");    // Default name
        populateMenu();
    }

    /**
     * Populates the menu with the title and options.
     */
    private void populateMenu() {
        menuOptions = new String[2];
        menuOptions[0] = "Enter player name 1: ";
        menuOptions[1] = "Enter player name 2: ";
    }

    /**
     * Displays the main menu.
     */
    @Override
    public void show() {
        System.out.print("Players:\n--------------------------------------------------------" +
                "-------\n");
        promptUser(0);
        promptUser(1);
    }

    /**
     * Prompts the user for input.
     * @param num the number of which player is being prompted.
     */
    private void promptUser(int num){
        System.out.print(menuOptions[num]);
        String name = scan.next();
        if(num == 0){
            player1.setName(name);
            scan.nextLine();
        }else{
            player2.setName(name);
        }
    }

    /**
     * Gets player 1.
     * @return player 1.
     */
    @Override
    public PlayerIF getPlayer1() {
        return player1;
    }

    /**
     * Gets player 2.
     * @return player 2.
     */
    @Override
    public PlayerIF getPlayer2() {
        return player2;
    }
}
