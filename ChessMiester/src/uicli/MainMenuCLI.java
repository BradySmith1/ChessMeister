package uicli;

import interfaces.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the main menu for the command line version of the chess game.
 * @author Brady Smith 100%
 */
public class MainMenuCLI implements MainMenuIF {

    private Scanner scan; /*scanner for user input*/

    private String menuTitle; /*title of the menu*/

    private String[] menuOptions; /*options for the menu*/

    private PlayIF play; /*play object*/

    private SettingsIF settings; /*settings object*/

    private DefinePlayersIF definePlayers; /*define players object*/

    private RulePageIF rules; /*rules object*/
    private LoadSaveGameIF loadGame; /*load game object*/


    /**
     * Constructor for the main menu.
     */
    public MainMenuCLI(){
        scan = new Scanner(System.in);
        setRules(new RulesCLI(scan));//zach needs to do
        setDefinePlayers(new DefinePlayersCLI(scan));//done
        setSettings(new SettingsCLI(scan));//done
        setLoadGame(new LoadGameCLI(scan));//done
        this.menuOptions = new String[7];
        populateMenu();
    }

    /**
     * Populates the menu with the title and options.
     */
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

    /**
     * Shows the main menu.
     */
    @Override
    public void showMainMenu() {
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
                    play = new NewGameCLI(scan, settings.getBoardColor(), settings.getUndo(),
                            settings.getShowMoves(), definePlayers.getPlayer1(),
                            definePlayers.getPlayer2());//need to finish
                    play.show();
                    break;
                case 2:
                    rules.showRulesPage();
                    break;
                case 3:
                    definePlayers.show();
                    break;
                case 4:
                    settings.showSettings();
                    break;
                case 5:
                    loadGame.showLoadSave();
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

    /**
     * Sets the settings object.
     * @param settings the settings object
     */
    public void setSettings(SettingsIF settings) {
        this.settings = settings;
    }

    /**
     * Sets the define players object.
     * @param definePlayers the define players object
     */
    public void setDefinePlayers(DefinePlayersIF definePlayers) {
        this.definePlayers = definePlayers;
    }

    /**
     * Sets the rules object.
     * @param rules the rules object
     */
    public void setRules(RulePageIF rules) {
        this.rules = rules;
    }

    /**
     * Sets the load game object.
     * @param loadGame the load game object
     */
    public void setLoadGame(LoadSaveGameIF loadGame) {
        this.loadGame = loadGame;
    }

    /**
     * Sets the play object.
     * @param play the play object
     */
    public void setPlay(PlayIF play) {
        this.play = play;
    }


}
