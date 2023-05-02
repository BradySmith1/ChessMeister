/**
 * This class is the GUI implementation for the main menu.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.settingsmenu;

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
        Label colorLabel = new Label("Colors:");
        Label blackSquares = new Label("Black Squares:");
        Label whiteSquares = new Label("White Squares:");
        Label undoLabel = new Label("Undo");
        Label maxUndosLabel = new Label("Max Undos");

        // Buttons for the settings menu
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");

        // Text fields for the settings menu
        TextField maxUndosField = new TextField();

        // Checkboxes for the settings menu
        CheckBox showMoves = new CheckBox("Show Moves");
        CheckBox enableUndo = new CheckBox("Enabled");
        CheckBox unlimitedUndo = new CheckBox("Unlimited Undos");

        // Set Alignment
        this.settingsMenuPane.setAlignment(Pos.CENTER);

        settingsMenuPane.add(titleLabel, 0, 0, 2, 1);
        settingsMenuPane.add(colorLabel, 0, 1, 1, 1);
        settingsMenuPane.add(blackSquares, 0, 2, 1, 1);
        settingsMenuPane.add(whiteSquares, 0, 3, 1, 1);
        settingsMenuPane.add(undoLabel, 0, 4, 1, 1);
        settingsMenuPane.add(enableUndo, 0, 5, 1, 1);
        settingsMenuPane.add(unlimitedUndo, 0, 6, 1, 1);
        settingsMenuPane.add(maxUndosField, 0, 7, 1, 1);
        settingsMenuPane.add(maxUndosLabel, 1, 7, 1, 1);
        settingsMenuPane.add(showMoves, 2,2, 1, 1);
        settingsMenuPane.add(saveButton, 2, 6, 1, 1);
        settingsMenuPane.add(exitButton, 2, 7, 1, 1);



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
