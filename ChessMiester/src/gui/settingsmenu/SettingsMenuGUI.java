/**
 * This class is the GUI implementation for the main menu.
 *
 * @author Kaushal Patel (70%), Zach Eanes (30%)
 */
package gui.settingsmenu;

import enums.ToScreen;
import gui.colourselector.ColourSelectorGUI;
import gui_backend.SettingsGUI;
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

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** Buttons for the menu **/
    Button exitButton;

    /** Checkboxes for the menu **/
    CheckBox enableUndo, showMoves;

    /** Settings for the board TODO **/
    SettingsGUI settings;

    /** Colored Rectangles for the settings menu **/
    Rectangle highlightColorBox, blackColorBox, whiteColorBox;


    /**
     * Constructor for the main menu GUI.
     */
    public SettingsMenuGUI(){
        // Instantiate the settings for the board
        this.settings = new SettingsGUI();

        // Create a border pane
        GridPane gp = new GridPane();

        // Labels for the settings menu
        Label titleLabel = createLabel("Settings", "topLabel");
        titleLabel.setAlignment(Pos.CENTER);
        Label colorLabel = createLabel("Highlight Color:", "middleLabel");
        Label blackSquares = createLabel("Black Squares:", "middleLabel");
        Label whiteSquares = createLabel("White Squares:", "middleLabel");
        Label undoLabel = createLabel("Undo/Redo", "middleLabel");
        Label showLabel = createLabel("Show Moves", "middleLabel");

        // Create exit button for the settings menu
        this.createExitButton();

        // Create Rectangles
        this.highlightColorBox = new Rectangle(50, 20, Color.LIGHTPINK);
        this.blackColorBox = new Rectangle(50, 20, Color.BLACK);
        this.whiteColorBox = new Rectangle(50, 20, Color.WHITE);

        // Handle Event for the black square
        this.handleBlackSquareEvent(highlightColorBox);
        this.handleBlackSquareEvent(blackColorBox);
        this.handleBlackSquareEvent(whiteColorBox);

        // Checkboxes for the settings menu
        this.showMoves = new CheckBox("Enabled");
        this.enableUndo = new CheckBox("Enabled");

        // Action Event Handler for the checkboxes
        showMoves.setOnAction(checkBoxHandler);
        enableUndo.setOnAction(checkBoxHandler);

        // Set Alignment
        gp.setAlignment(Pos.CENTER); // Change to center and scale up
        gp.setId("main-pane");
        gp.setHgap(50);
        gp.setVgap(15);

        // Add all the nodes to the grid pane
        gp.add(titleLabel, 0, 0, 3, 1);
        gp.add(colorLabel, 0, 1, 1, 1);
        gp.add(blackSquares, 0, 2, 1, 1);
        gp.add(whiteSquares, 0, 3, 1, 1);
        gp.add(highlightColorBox, 1, 1, 1, 1);
        gp.add(blackColorBox, 1, 2, 1, 1);
        gp.add(whiteColorBox, 1, 3, 1, 1);
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

        // Get stylesheet
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
                    if (screenChanger.getPreviousScreen() == ToScreen.MAIN_MENU) {
                        screenChanger.changeScreen(ToScreen.MAIN_MENU);
                    }
                    else if (screenChanger.getPreviousScreen() == ToScreen.GAME_BOARD){
                        screenChanger.changeScreen(ToScreen.GAME_BOARD);
                    }
                }
            }
        }
    };

    /**
     * Event Handler for the checkboxes
     */
    EventHandler<ActionEvent> checkBoxHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Object source = event.getSource();

            if (source == showMoves) {
                settings.setShowMoves(showMoves.isSelected());
            }
            else if (source == enableUndo){
                settings.setUndoRedo(enableUndo.isSelected());
            }

            System.out.println("Show Moves: " + settings.getShowMoves());
            System.out.println("Undo/Redo: " + settings.getUndoRedo());
        }
    };

    private void createExitButton(){
        // Buttons for the settings menu
        this.exitButton = new Button("Return");
        this.exitButton.setId("bottom-button2");
        this.exitButton.setAlignment(Pos.CENTER);

        // Event Handlers for the settings menu
        this.exitButton.setOnAction(buttonHandler);
    }

    private Label createLabel(String text, String id){
        Label label = new Label(text);
        label.setId(id);
        return label;
    }

    private void handleBlackSquareEvent(Rectangle colorBox){
        colorBox.setOnMouseClicked(event -> {
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
            colorBox.setFill(colourSelectorGUI.getSelectedColor());

            if (colorBox == blackColorBox){
                settings.setBlackSquareColor(colourSelectorGUI.getSelectedColor());
            }
            else if (colorBox == whiteColorBox){
                settings.setWhiteSquareColor(colourSelectorGUI.getSelectedColor());
            } else if(colorBox == highlightColorBox){
                settings.setHighlightColor(colourSelectorGUI.getSelectedColor());
            }
        });
    }

    public SettingsGUI getSettings() {
        return settings;
    }
}
