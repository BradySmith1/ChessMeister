package uicli;

import controller.BoardMementoCaretaker;
import enums.GameColor;
import interfaces.*;
import model.Board;
import model.BoardSaverLoader;

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

    private ViewPlayedGamesIF viewPlayed; /*view played games object*/


    /**
     * Constructor for the main menu.
     */
    public MainMenuCLI(){
        scan = new Scanner(System.in);
        setRules(new RulesCLI(scan));
        setDefinePlayers(new DefinePlayersCLI(scan));//done
        setSettings(new SettingsCLI(scan));//done
        setLoadGame(new LoadGameCLI(scan));//done
        setViewPlayed(new PlayedGamesCLI(scan));//done
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
        menuOptions[5] = "6: View Played Games\n";
        menuOptions[6] = "0: Quit";
    }

    /**
     * Shows the main menu.
     */
    @Override
    public void showMainMenu() {
        String menu = menuTitle;
        menu = menu.concat(
                "\n---------------------------------------------------------------\n\n" +
                "Please make a selection as to what you would like to do:\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5] +
                menuOptions[6] +
                "\n\n---------------------------------------------------------------\n");
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String prompt = "Enter your menu choice here -> ";
        while (choice != 0) { //while user has not quit
            //System.out.println(menuTitle);
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
                case 1: // new game
                    play = new NewGameCLI(scan, settings.getBoardColor(), settings.getUndo(),
                            settings.getShowMoves(), definePlayers.getPlayer1(),
                            definePlayers.getPlayer2());//need to finish
                    play.show();
                    break;
                case 2: // go into the rules menu
                    rules.showRulesPage(settings.getBoardColor());
                    break;
                case 3: // go into the define players menu
                    definePlayers.show();
                    break;
                case 4: // show the user settings
                    settings.showSettings();
                    break;
                case 5: // load a game from the name of a file
                    loadGame.showLoadSave(); // prompt and get file name
                    BoardSaverLoader loader = new BoardSaverLoader(); // obj to load file
                    // caretaker that is a stack of all states of the game
                    BoardMementoCaretaker caretaker = loader.loadGameFromFile(loadGame.getURL());
                    if(caretaker != null) { // file was loaded

                        BoardStrategy boardStrat;
                        // get the board strategy based on the settings
                        if (settings.getBoardColor().equals("Mono")) {
                            boardStrat = new BoardMonoCLI();
                        } else {
                            boardStrat = new BoardColorCLI();
                        }

                        // create a new board and load the state found settings
                        Board board = new Board();
                        board.setDrawStrategy(boardStrat);
                        board.loadFromMemento(caretaker.peek()); // load game state

                        // assign the pieces to the players after loading the game
                        definePlayers.getPlayer1().assignPieces(board);
                        definePlayers.getPlayer2().assignPieces(board);

                        // get the color of the last move
                        String color = board.getState().substring(
                                board.getState().length() - 8, board.getState().length() - 7);

                        // get who's turn it is a continue the game
                        if (color.equals("W")) { //blacks turn
                            play = new NewGameCLI(scan, settings.getBoardColor(), settings.getUndo(),
                                    settings.getShowMoves(), definePlayers.getPlayer2(),
                                    definePlayers.getPlayer1(), board, caretaker);
                            play.show();
                        } else { // whites turn
                            play = new NewGameCLI(scan, settings.getBoardColor(), settings.getUndo(),
                                    settings.getShowMoves(), definePlayers.getPlayer1(),
                                    definePlayers.getPlayer2(), board, caretaker);
                            play.show();
                        }
                    }
                    break;
                case 6:
                    viewPlayed.showPlayedGames();
                    break;
                case 0: // user quit
                    scan.close();
                    System.out.println("""
--------------------------------------------------------------------------------------------\s
 _______ _                 _           __                   _             _             _\s
|__   __| |               | |         / _|                 | |           (_)           | | \s
   | |  | |__   __ _ _ __ | | _____  | |_ ___  _ __   _ __ | | __ _ _   _ _ _ __   __ _| |\s
   | |  | '_ \\ / _` | '_ \\| |/ / __| |  _/ _ \\| '__| | '_ \\| |/ _` | | | | | '_ \\ / _` | |\s
   | |  | | | | (_| | | | |   <\\__ \\ | || (_) | |    | |_) | | (_| | |_| | | | | | (_| |_|\s
   |_|  |_| |_|\\__,_|_| |_|_|\\_\\___/ |_| \\___/|_|    | .__/|_|\\__,_|\\__, |_|_| |_|\\__, (_)\s
                                                     | |             __/ |         __/ | \s
                                                     |_|            |___/         |___/ \s
                            
                            """);
                    System.exit(0);
                    break;
                default: // user entered an invalid number
                    System.out.println("Please enter a valid number (0 - 5)");
                    break;
            }
            System.out.println();
            scan.nextLine();//consumes a new line so nextInt throwing an exception will not loop
        }
    }

    /**
     * Sets the settings object.
     *
     * @param settings the settings object
     */
    public void setSettings(SettingsIF settings) {
        this.settings = settings;
    }

    /**
     * Sets the define players object.
     *
     * @param definePlayers the define players object
     */
    public void setDefinePlayers(DefinePlayersIF definePlayers) {
        this.definePlayers = definePlayers;
    }

    /**
     * Sets the rules object.
     *
     * @param rules the rules object
     */
    public void setRules(RulePageIF rules) {
        this.rules = rules;
    }

    /**
     * Sets the load game object.
     *
     * @param loadGame the load game object
     */
    public void setLoadGame(LoadSaveGameIF loadGame) {
        this.loadGame = loadGame;
    }

    /**
     * Sets the play object.
     *
     * @param play the play object
     */
    public void setPlay(PlayIF play) {
        this.play = play;
    }

    /**
     * Sets the view played object.
     *
     * @param viewPlayed the view played object
     */
    public void setViewPlayed(ViewPlayedGamesIF viewPlayed) { this.viewPlayed = viewPlayed; }


}
