/**
 * This class is responsible for creating the load game GUI.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */

package gui.loadgame;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class LoadGameGUI extends VBox {
    /** The LoadGameGUI pane. */
    VBox LoadGamePane;

    /** Scene */
    private Scene scene;

    /** Reference to the implementation for the ScreenChangeHandlerIF. */
    ScreenChangeHandlerIF screenChanger;

    /** TextField for the menu. */
    TextField file;

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
            if (screenChanger != null) {
                Object source = event.getSource();

                if (source == load) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/SavedGames"));
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extFilter);
                    File selectedFile = fileChooser.showOpenDialog(load.getScene().getWindow());
                    if (selectedFile != null) {
                        System.out.println(selectedFile.getAbsolutePath());
                        file.setText(selectedFile.getName());

                    }
                } else if (source == returnToMain) {
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                } else if (source == play) {
                    screenChanger.changeScreen(ToScreen.GAME_BOARD);
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
