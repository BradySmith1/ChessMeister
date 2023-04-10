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
        promptUser(0);
        promptUser(1);
    }

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

    @Override
    public PlayerIF getPlayer1() {
        return player1;
    }

    @Override
    public PlayerIF getPlayer2() {
        return player2;
    }
}
