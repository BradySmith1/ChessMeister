package uicli;

import controller.BoardMementoCaretaker;
import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SettingsIF;
import interfaces.ViewPlayedGamesIF;
import model.BoardSaverLoader;
import model.Board;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is responsible for allowing the user to view played games.
 */
public class PlayedGamesCLI implements ViewPlayedGamesIF {

    private Scanner scan; /* Scanner for user input */

    /* settings used to access the board color */
    final SettingsIF settings = new SettingsCLI(new Scanner(System.in));

    private BoardMementoCaretaker caretaker; /* caretaker for the board */

    /**
     * Constructor method for the played games class.
     *
     * @param scan Scanner for user input
     */
    public PlayedGamesCLI(Scanner scan) {
        this.scan = scan;
    }

    /**
     * Displays the played games dialog.
     */
    @Override
    public void showPlayedGames() {
        int choice = 999;
        while(choice != 0) {
            System.out.print("""
                    -----------------------------------------------------------------\s
                                    
                    Choose a game to view:
                      1: Scholars Mate Game (Checkmate in four moves!) \s
                      0: Return to Main Menu\s
                      
                      MORE COMING SOON!
                                    
                    -----------------------------------------------------------------\s
                    """);

            System.out.print("Enter your menu choice here -> ");
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException ignore) {
                // ignore
            }
            Board board; // create new board
            switch (choice) { // load game based on choice

                //we plan on adding following games later, ran out of time for now
                case 1:
                    board = this.loadBeginning("scholarsMateGame");
                    this.loopMoves(board);
                    break;
//                case 2:
//                    board = this.loadBeginning("checkmateGame");
//                    this.loopMoves(board);
//                    break;
//                case 3:
//                    board = this.loadBeginning("stalemateGame");
//                    this.loopMoves(board);
//                    break;
//                case 4:
//                    board = this.loadBeginning("scholarsMateGame");
//                    this.loopMoves(board);
//                    break;
                case 0: // return to menu
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    scan.nextLine();
            }
        }
    }

    /**
     * Loads a game from a file and sets it to the first state.
     *
     * @param fileName The name of the file to load.
     * @return The board loaded from the beginning of the file
     */
    private Board loadBeginning(String fileName) {
        Board board = new Board(); // create new board
        BoardSaverLoader loader = new BoardSaverLoader();
        BoardMementoCaretaker caretaker = loader.loadGameFromFile(fileName);
        if (caretaker != null) { // if file exists
            BoardStrategy strat;

            // set strat based on board color
            if (settings.getBoardColor().equals("Mono")) {
                strat = new BoardMonoCLI();
            } else {
                strat = new BoardColorCLI();
            }
            // create new board and set strat
            board.setDrawStrategy(strat);

            // load memento and go to beginning
            while (caretaker.down() != null) {
                caretaker.down();
            }
            board.loadFromMemento(caretaker.peek());
            this.setCaretaker(caretaker);
        }
        return board;
    }

    /**
     * Loops through the moves of a game for the player to view.
     *
     * @param board The board to loop through.
     */
    private void loopMoves(Board board){
        GameColor color = GameColor.WHITE;
        int choice = 1;
        board.draw(color);
        while(choice != 0){
            System.out.println("""
                    -----------------------------------------------------------------\s
                    Choose an option:
                      1: Next Move\s
                      2: Previous Move\s
                      0: Return to Main Menu\s
                    -----------------------------------------------------------------\s""");

            System.out.print("Enter your menu choice here -> ");
            try{
                choice = scan.nextInt();
            } catch (Exception e){
                // ignore
            }
            //choice = scan.nextInt(); // get user choice
            BoardIF.BoardMementoIF memento = this.caretaker.peek(); // establish memento
            switch(choice){
                case 1: // "redo" move
                    memento = this.caretaker.up(); // get next move
                    if(memento != null) { // if there is a next move
                        board.loadFromMemento(memento); // set move
                        board.draw(color); // display board
                    }else{
                        System.out.println("No next move available.");
                    }
                    break;
                case 2: // "undo" move
                    memento = this.caretaker.down(); // get previous move
                    if(memento != null) { // if there is a previous move
                        board.loadFromMemento(memento);  // set move
                        board.draw(color); // display board
                    }else{
                        System.out.println("No previous move available.");
                    }
                    break;
                case 0: // return to menu
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    scan.nextLine();
            }

        }
    }

    /**
     * Sets the caretaker for the board.
     *
     * @param caretaker The caretaker to set.
     */
    public void setCaretaker(BoardMementoCaretaker caretaker) { this.caretaker = caretaker; }
}
