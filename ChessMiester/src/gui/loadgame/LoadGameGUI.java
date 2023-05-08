/**
 * This class is responsible for creating the load game GUI.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */

package gui.loadgame;

import controller.BoardMementoCaretaker;
import enums.ToScreen;
import gui.gameboard.CenterPaneGUI;
import gui.gameboard.GameBoardGUI;
import gui.playernames.PlayerNamesGUI;
import gui_backend.DefinePlayersGUI;
import interfaces.DefinePlayersIF;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.BoardSaverLoader;
import model.Player;

import java.io.File;
import java.util.ArrayList;

public class LoadGameGUI extends VBox {
    /** The LoadGameGUI pane. */
    VBox LoadGamePane;

    /** Scene */
    private Scene scene;

    /** Reference to the implementation for the ScreenChangeHandlerIF. */
    ScreenChangeHandlerIF screenChanger;

    /** TextField for the menu. */
    TextField file;

    /** File selected in the menu. */
    File selectedFile;

    /** Buttons for the menu. */
    Button load, returnToMain, play;


    /**
     * Constructor for the load game GUI.
     */
    public LoadGameGUI() {
        // Create a VBox
        this.LoadGamePane = new VBox();

        // create title
        Label title = new Label("Enter File Name");
        title.setId("topLabel");
        title.setAlignment(Pos.CENTER);

        // create text field
        this.file = new TextField();
        this.file.setId("text-box");
        this.file.setEditable(false);
        //this.file.setAlignment(Pos.CENTER);

        // create button for loading
        this.load = new Button("Load");
        this.load.setId("menu-button");
        this.load.setAlignment(Pos.CENTER);
        this.load.setOnAction(buttonHandler);

        // create button for playing
        this.play = new Button("Play");
        this.play.setId("menu-button");
        this.play.setAlignment(Pos.CENTER);
        this.play.setOnAction(buttonHandler);

        // create button for returning to main menu
        this.returnToMain = new Button("Return to Main Menu");
        this.returnToMain.setId("bottom-button");
        this.returnToMain.setAlignment(Pos.CENTER);
        this.returnToMain.setOnAction(buttonHandler);

        // add all elements to the VBox
        this.LoadGamePane.getChildren().addAll(title, this.file, this.load, this.play, this.returnToMain);
        this.LoadGamePane.setSpacing(20);
        this.LoadGamePane.setAlignment(Pos.CENTER);
        this.LoadGamePane.setId("main-pane");

        // Get stylesheet
        this.LoadGamePane.getStylesheets().add(
                getClass().getResource("LoadGame.css").toExternalForm());
    }

    /**
     * Set the screen change handler for this GUI.
     *
     * @param sch The screen change handler.
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) {
        this.screenChanger = sch;
    }

    /**
     * Event handler for the buttons.
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        /**
         * Handles the button events.
         *
         * @param event The event that occurred.
         */
        @Override
        public void handle(ActionEvent event) {
            // Check if the screen change handler is set
            if (screenChanger != null) {
                // Get the source of the event
                Object source = event.getSource();

                // Check which button was pressed
                if (source == load) {
                    // Create a file chooser
                    FileChooser fileChooser = new FileChooser();

                    // Set the initial directory
                    fileChooser.setInitialDirectory(
                            new File(System.getProperty("user.dir") + "/src/SavedGames"));

                    // Set the extension filter
                    FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");

                    // Add the extension filter
                    fileChooser.getExtensionFilters().add(extFilter);

                    // Show the file chooser
                    selectedFile = fileChooser.showOpenDialog(load.getScene().getWindow());

                    // Check if a file was selected, if so, set the text field
                    if (selectedFile != null) {
                        System.out.println(selectedFile.getAbsolutePath());
                        file.setText(selectedFile.getName());

                    }
                } else if (source == returnToMain) {
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                } else if (source == play) {
                    BoardSaverLoader loader = new BoardSaverLoader(); // obj to load file
                    // caretaker that is a stack of all states of the game
                    if (selectedFile != null) {
                        ArrayList<Object> list = loader.loadGameFromFile(selectedFile);
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
                    }else{
                        file.setText(selectedFile.getName());
                    }
                }
            }
        }
    };

    /**
     * Returns the root of the load game GUI.
     *
     * @return the pane for the load game GUI
     */
    public Pane getRoot() { return this.LoadGamePane; }
}
