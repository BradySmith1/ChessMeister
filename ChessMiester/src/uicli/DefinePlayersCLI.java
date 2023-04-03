package uicli;

import enums.GameColor;
import interfaces.DefinePlayersIF;
import interfaces.MainMenuIF;
import interfaces.PlayIF;
import interfaces.PlayerIF;
import player.Player;
import java.util.Scanner;

//TODO add javadoc

public class DefinePlayersCLI implements DefinePlayersIF {

    private Scanner scan;
    private String[] menuOptions;

    private PlayerIF player1;

    private PlayerIF player2;

    public DefinePlayersCLI(Scanner scan) {
        this.scan = scan;
        player1 = new Player(GameColor.WHITE);
        player2 = new Player(GameColor.BLACK);
        populateMenu();
    }

    private void populateMenu() {
        menuOptions = new String[2];
        menuOptions[0] = "Enter player name 1: ";
        menuOptions[1] = "Enter player name 2: ";
    }

    @Override
    public void show() {
        System.out.print("Players:\n--------------------------------------------------------" +
                "-------\n");
        System.out.print(menuOptions[0]);
        String name = scan.nextLine();
        player1.setName(name);
        System.out.print("\n" + menuOptions[1]);
        name = scan.nextLine();
        player2.setName(name);
    }

    @Override
    public PlayerIF getPlayer1() {
        return player1;
    }

    @Override
    public PlayerIF getPlayer2() {
        return player2;
    }
}
