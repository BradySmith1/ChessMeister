package uicli;

import interfaces.*;
import java.util.InputMismatchException;
import java.util.Scanner;
//TODO: add javadoc
public class MainMenuCLI implements MainMenuIF {

    private Scanner scan;

    private String menuTitle;

    private String[] menuOptions;

    private PlayIF play;

    private SettingsIF settings;

    private DefinePlayersIF definePlayers;

    private RulesIF rules;

    private LoadGameIF loadGame;


    public MainMenuCLI(){
        scan = new Scanner(System.in);
        rules = new RulesCLI(scan);//zach needs to do
        definePlayers = new DefinePlayersCLI(scan);//done
        settings = new SettingsCLI(scan);//done
        loadGame = new LoadGameCLI();
        play = new PlayChessCLI(scan, settings.getBoardColor(), definePlayers.getPlayer1(),
                definePlayers.getPlayer2());//need to finish
        this.menuOptions = new String[7];
        populateMenu();
    }

    private void populateMenu(){
        this.menuTitle = """
                   _____ _                   __  __      _     _           \s
                  / ____| |                 |  \\/  |    (_)   | |          \s
                 | |    | |__   ___  ___ ___| \\  / | ___ _ ___| |_ ___ _ __\s
                 | |    | '_ \\ / _ \\/ __/ __| |\\/| |/ _ \\ / __| __/ _ \\ '__|
                 | |____| | | |  __/\\__ \\__ \\ |  | |  __/ \\__ \\ ||  __/ |  \s
                  \\_____|_| |_|\\___||___/___/_|  |_|\\___|_|___/\\__\\___|_|  \s
                """;
        menuOptions[0] = "1: Play Chess\n";
        menuOptions[1] = "2: View Rules\n";
        menuOptions[2] = "3: Define Players\n";
        menuOptions[3] = "4: Settings\n";
        menuOptions[4] = "5: Load Game\n";
        menuOptions[5] = "0: Quit";
    }

    @Override
    public void show() {
        System.out.println(menuTitle);
        String menu = "---------------------------------------------------------------\n" +
                "Please make a selection as to what you would like to do:\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5] +
                "\n-----------------------------------------" +
                "---------------------\n";
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
            System.out.println();
            switch(choice) {
                case 1:
                    play.show();
                    break;
                case 2:
                    rules.show();
                    break;
                case 3:
                    definePlayers.show();
                    break;
                case 4:
                    settings.show();
                    break;
                case 5:
                    loadGame.show();
                    break;
                case 0:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid number (0 - 5)");
                    break;
            }
            System.out.println();
            scan.nextLine();//consumes a new line so nextInt throwing an exception will not loop
        }
    }
}
