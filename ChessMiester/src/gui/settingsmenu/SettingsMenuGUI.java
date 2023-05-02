/**
 * This class is the GUI implementation for the main menu.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.settingsmenu;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;

public class SettingsMenuGUI extends GridPane {

    /** The main menu pane. */
    GridPane settingsMenuPane;

    /** Scene for the main menu. */
    private Scene scene;

    private static SettingsMenuGUI instance;

    /**
     * Constructor for the main menu GUI.
     */
    public static SettingsMenuGUI getInstance(){
        if(instance == null){
            instance = new SettingsMenuGUI();
        }
        return instance;
    }


    private SettingsMenuGUI(){
        // Create a border pane
        this.settingsMenuPane = new GridPane();

        // Labels for the settings menu
        Label titleLabel = new Label("Settings");
        titleLabel.setId("topLabel");
        titleLabel.setAlignment(Pos.CENTER);
        Label colorLabel = new Label("Colors:");
        colorLabel.setId("middleLabel");
        Label blackSquares = new Label("Black Squares:");
        blackSquares.setId("middleLabel");
        Label whiteSquares = new Label("White Squares:");
        whiteSquares.setId("middleLabel");
        Label undoLabel = new Label("Undo/Redo");
        undoLabel.setId("middleLabel");
        Label showLabel = new Label("Show Moves");
        showLabel.setId("middleLabel");

        // Buttons for the settings menu
        Button saveButton = new Button("Save");
        saveButton.setId("bottom-button");
        Button exitButton = new Button("Return to Main Menu");
        exitButton.setId("bottom-button");

        // Checkboxes for the settings menu
        CheckBox showMoves = new CheckBox("Enabled");
        CheckBox enableUndo = new CheckBox("Enabled");

        // Set Alignment
        this.settingsMenuPane.setAlignment(Pos.CENTER); // Change to center and scale up
        this.settingsMenuPane.setHgap(50);
        this.settingsMenuPane.setVgap(15);

        settingsMenuPane.add(titleLabel, 0, 0, 3, 1);
        settingsMenuPane.add(colorLabel, 0, 1, 1, 1);
        settingsMenuPane.add(blackSquares, 0, 2, 1, 1);
        settingsMenuPane.add(whiteSquares, 0, 3, 1, 1);
        settingsMenuPane.add(undoLabel, 0, 4, 1, 1);
        settingsMenuPane.add(enableUndo, 0, 5, 1, 1);
        settingsMenuPane.add(showLabel, 1, 4, 1, 1);
        settingsMenuPane.add(showMoves, 1,5, 1, 1);
        settingsMenuPane.add(saveButton, 0, 6, 1, 1);
        settingsMenuPane.add(exitButton, 1, 6, 2, 1);

        GridPane.setHalignment(titleLabel, HPos.CENTER);
        // Create a scene object
        this.scene = new Scene(settingsMenuPane);

        // Get stylesheet
        this.scene.getStylesheets().add(
                getClass().getResource("SettingsMenu.css").toExternalForm());
    }
    /**
     * Getter for the scene.
     * @return the scene
     */
    public Scene getMenu(){ return this.scene; }
}
