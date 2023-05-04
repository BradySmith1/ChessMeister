/**
 * This class is the GUI implementation for the main menu.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.settingsmenu;

import enums.ToScreen;
import gui.colourselector.ColourSelectorGUI;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SettingsMenuGUI extends GridPane {

    /** The main menu pane. */
    GridPane settingsMenuPane;

    /** Scene for the main menu. */
    private Scene scene;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** Buttons for the menu **/
    Button saveButton, exitButton;


    public SettingsMenuGUI(){
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
        // TODO: implement colorama and add it to colors and black/white squares

        // Buttons for the settings menu
        this.saveButton = new Button("Save");
        this.saveButton.setId("bottom-button");
        this.exitButton = new Button("Return to Main Menu");
        this.exitButton.setId("bottom-button2");

        // Create Rectangles
        Rectangle blackColorBox = new Rectangle(50, 20, Color.BLACK);
        Rectangle whiteColorBox = new Rectangle(50, 20, Color.WHITE);

        // Event Handler for the black square
        blackColorBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Instantiate the ColourSelectorGUI class
                ColourSelectorGUI colourSelectorGUI = new ColourSelectorGUI();

                // Create a new dialog
                Dialog<ColourSelectorGUI> colourSelectorDialog = new Dialog<>();
                colourSelectorDialog.setTitle("Colour Selector");

                // Set the content of the dialog to the ColourSelectorGUI
                colourSelectorDialog.getDialogPane().setContent(colourSelectorGUI.getRoot());

                // Show the dialog
                colourSelectorDialog.showAndWait();

                // Set the color of the black square to the selected color
                blackColorBox.setFill(colourSelectorGUI.getSelectedColor());

            }
        });

        // Event Handler for the white square
        whiteColorBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Instantiate the ColourSelectorGUI class
                ColourSelectorGUI colourSelectorGUI = new ColourSelectorGUI();

                // Create a new dialog
                Dialog<ColourSelectorGUI> colourSelectorDialog = new Dialog<>();
                colourSelectorDialog.setTitle("Colour Selector");

                // Set the content of the dialog to the ColourSelectorGUI
                colourSelectorDialog.getDialogPane().setContent(colourSelectorGUI.getRoot());

                // Show the dialog
                colourSelectorDialog.showAndWait();

                // Set the color of the white square to the selected color
                whiteColorBox.setFill(colourSelectorGUI.getSelectedColor());
            }
        });

        // Event Handlers for the settings menu
        this.saveButton.setOnAction(buttonHandler);
        this.exitButton.setOnAction(buttonHandler);

        // Checkboxes for the settings menu
        CheckBox showMoves = new CheckBox("Enabled");
        CheckBox enableUndo = new CheckBox("Enabled");

        // Set Alignment
        this.settingsMenuPane.setAlignment(Pos.CENTER); // Change to center and scale up
        this.settingsMenuPane.setId("main-pane");
        this.settingsMenuPane.setHgap(50);
        this.settingsMenuPane.setVgap(15);

        settingsMenuPane.add(titleLabel, 0, 0, 3, 1);
        settingsMenuPane.add(colorLabel, 0, 1, 1, 1);
        settingsMenuPane.add(blackSquares, 0, 2, 1, 1);
        settingsMenuPane.add(whiteSquares, 0, 3, 1, 1);
        settingsMenuPane.add(blackColorBox, 1, 2, 1, 1);
        settingsMenuPane.add(whiteColorBox, 1, 3, 1, 1);
        settingsMenuPane.add(undoLabel, 0, 4, 1, 1);
        settingsMenuPane.add(enableUndo, 0, 5, 1, 1);
        settingsMenuPane.add(showLabel, 1, 4, 1, 1);
        settingsMenuPane.add(showMoves, 1,5, 1, 1);
        settingsMenuPane.add(saveButton, 0, 6, 1, 1);
        settingsMenuPane.add(exitButton, 1, 6, 2, 1);

        GridPane.setHalignment(titleLabel, HPos.CENTER);

//        // Get stylesheet
        this.settingsMenuPane.getStylesheets().add(
                getClass().getResource("SettingsMenu.css").toExternalForm());
    }
    /**
     * Getter for the scene.
     * @return the scene
     */
    public Pane getRoot(){ return this.settingsMenuPane; }

    /**
     * Set the handler for screen changes
     * @param sch The ScreenChangeHandler
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch){
        this.screenChanger = sch;
    }

    /** Event Handler for buttons **/
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if (screenChanger != null){
                Object source = event.getSource();
                if (source == exitButton) {
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                }
            }
        }
    };

}
