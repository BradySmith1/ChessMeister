package gui.viewplayed;

import controller.BoardMementoCaretaker;
import enums.ToScreen;
import gui.gameboard.CenterPaneGUI;
import gui.gameboard.GameBoardGUI;
import gui.playernames.PlayerNamesGUI;
import interfaces.ScreenChangeHandlerIF;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.BoardSaverLoader;

import java.io.File;
import java.util.ArrayList;

/**
 * This class is responsible for creating the view played GUI.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
public class ViewPlayedGUI extends VBox {
    /** Reference to the implementation for the ScreenChangeHandlerIF */
    ScreenChangeHandlerIF screenChanger;

    /** The load game pane. */
    VBox viewPlayedPane;

    /** Scene for the main menu. */
    private Scene scene;

    /** Buttons for the menu */
    Button game1, game2, mainMenu;

    /**
     * Constructor for the load games GUI.
     */
    public ViewPlayedGUI() {
        // Create a border pane
        this.viewPlayedPane = new VBox();

        // create title
        Label title = new Label("Select Game to Load");
        title.setId("topLabel");

        // Create the buttons
        this.createButtons();

        // Set button ids
        this.setButtonIds();

        // Set button actions
        this.setButtonActions();

        // Add components to the pane
        this.viewPlayedPane.getChildren().addAll(title, game1, game2, mainMenu);
        this.viewPlayedPane.setId("main-pane");
        this.viewPlayedPane.setAlignment(Pos.CENTER);
        this.viewPlayedPane.setSpacing(20);

        // Get stylesheet
        this.viewPlayedPane.getStylesheets().add(
                getClass().getResource("ViewPlayed.css").toExternalForm());

    }

    /**
     * Returns the root of the load game GUI.
     *
     * @return the pane for the load game GUI
     */
    public Pane getRoot() { return this.viewPlayedPane; }

    /**
     * Sets the screen change handler for the load game GUI.
     *
     * @param sch the screen change handler to use
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }


    /** Event Handler for the buttons */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        /**
         * Handles the button events.
         *
         * @param event the event to handle
         */
        @Override
        public void handle(ActionEvent event) {
            if( screenChanger != null){
                Object source = event.getSource();

                if(source == game1){
                    BoardSaverLoader loader = new BoardSaverLoader(); // obj to load file
                    ArrayList<Object> list = loader.loadGameFromFile(new File(
                            "./src/SavedGames/scholarsMateGame.txt"));
                    BoardMementoCaretaker caretaker = (BoardMementoCaretaker) list.get(0);
                    String player1 = (String) list.get(1);
                    String player2 = (String) list.get(2);
                    screenChanger.changeScreen(ToScreen.PLAYER_NAMES);
                    PlayerNamesGUI players = (PlayerNamesGUI) screenChanger.getGuiScene(ToScreen.PLAYER_NAMES);
                    players.getPlayer().setPlayer1Name(player1);
                    players.getPlayer().setPlayer2Name(player2);
                    screenChanger.changeScreen(ToScreen.GAME_BOARD);
                    CenterPaneGUI center =
                            ((CenterPaneGUI)((GameBoardGUI)screenChanger.getGuiScene(ToScreen.GAME_BOARD)).getCenter());
                    center.setBoardMementoCaretaker(caretaker);
                    center.loadFromMemento(caretaker.peek());
                }
                else if(source == game2){
                    BoardSaverLoader loader = new BoardSaverLoader(); // obj to load file
                    ArrayList<Object> list = loader.loadGameFromFile(new File(
                            "./src/SavedGames/scholarsMateGame.txt"));
                    BoardMementoCaretaker caretaker = (BoardMementoCaretaker) list.get(0);
                    String player1 = (String) list.get(1);
                    String player2 = (String) list.get(2);
                    screenChanger.changeScreen(ToScreen.PLAYER_NAMES);
                    PlayerNamesGUI players = (PlayerNamesGUI) screenChanger.getGuiScene(ToScreen.PLAYER_NAMES);
                    players.getPlayer().setPlayer1Name(player1);
                    players.getPlayer().setPlayer2Name(player2);
                    screenChanger.changeScreen(ToScreen.GAME_BOARD);
                    CenterPaneGUI center =
                            ((CenterPaneGUI)((GameBoardGUI)screenChanger.getGuiScene(ToScreen.GAME_BOARD)).getCenter());
                    center.setBoardMementoCaretaker(caretaker);
                    center.loadFromMemento(caretaker.peek());
                }
                else if(source == mainMenu){
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                }
            }

        }
    };

    /**
     * Creates the buttons for the load game GUI.
     */
    private void createButtons(){
        this.game1 = new Button("Scholar's Mate");
        this.game2 = new Button("Stalemate");
        this.mainMenu = new Button("Return to Main Menu");
    }

    /**
     * Sets the ids for the buttons.
     */
    private void setButtonIds(){
        this.game1.setId("menu-button");
        this.game2.setId("menu-button");
        this.mainMenu.setId("bottom-button");
    }

    /**
     * Sets the actions for the buttons (directed to the button handler).
     */
    private void setButtonActions(){
        this.game1.setOnAction(buttonHandler);
        this.game2.setOnAction(buttonHandler);
        this.mainMenu.setOnAction(buttonHandler);
    }

}
