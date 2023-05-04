/**
 * This class is the GUI implementation for the main menu.
 *
 * @author Kaushal Patel (70%), Zach Eanes (30%)
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

public class SettingsMenuGUI extends VBox {

    /** The main menu pane. */
    VBox settingsMenuPane;

    /** Scene for the main menu. */
    private Scene scene;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** Buttons for the menu **/
    Button exitButton;

    /**
     * Constructor for the main menu GUI.
     */
    public SettingsMenuGUI(){
        // Create a border pane
        GridPane gp = new GridPane();

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
        this.exitButton = new Button("Return to Main Menu");
        this.exitButton.setId("bottom-button2");
        this.exitButton.setAlignment(Pos.CENTER);

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
        this.exitButton.setOnAction(buttonHandler);

        // Checkboxes for the settings menu
        CheckBox showMoves = new CheckBox("Enabled");
        CheckBox enableUndo = new CheckBox("Enabled");

        // Set Alignment
        gp.setAlignment(Pos.CENTER); // Change to center and scale up
        gp.setId("main-pane");
        gp.setHgap(50);
        gp.setVgap(15);

        gp.add(titleLabel, 0, 0, 3, 1);
        gp.add(colorLabel, 0, 1, 1, 1);
        gp.add(blackSquares, 0, 2, 1, 1);
        gp.add(whiteSquares, 0, 3, 1, 1);
        gp.add(undoLabel, 0, 4, 1, 1);
        gp.add(enableUndo, 0, 5, 1, 1);
        gp.add(showLabel, 1, 4, 1, 1);
        gp.add(showMoves, 1,5, 1, 1);

        // create vbox and add grid pane and return button
        this.settingsMenuPane = new VBox();
        this.settingsMenuPane.getChildren().addAll(gp, exitButton);
        this.settingsMenuPane.setAlignment(Pos.CENTER);
        this.settingsMenuPane.setSpacing(20);
        this.settingsMenuPane.setId("main-pane");

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

        /**
         * Handle the button clicks
         *
         * @param event The event that triggered the handler
         */
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
