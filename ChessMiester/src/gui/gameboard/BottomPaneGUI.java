package gui.gameboard;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
/**
 * This class creates the bottom pane for the chess board GUI.
 *
 * @author Brady Smith 100%
 * @version 1.0 (done in sprint 3)
 */

public class BottomPaneGUI extends AnchorPane {
    /** Label for the pane */
    Label playerSelect;

    /** The buttons to exit the game and go to settings */
    Button settings, exitButton;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /**
     * The bottom pane of the game board.
     * @param name the name of the player
     */
    public BottomPaneGUI(String name){
        this.playerSelect = new Label(name + "'s turn!");
        playerSelect.setId("topLabel");
        this.getChildren().add(playerSelect);
        AnchorPane.setLeftAnchor(playerSelect, 600.0);

        //Creation of the buttons.
        settings = new Button("Settings");
        settings.setId("bottom-button");
        exitButton = new Button("Exit");
        exitButton.setId("bottom-button");

        //Setting the values of the anchors.
        AnchorPane.setBottomAnchor(settings, 0.0);
        AnchorPane.setLeftAnchor(settings, 0.0);
        AnchorPane.setBottomAnchor(exitButton, 0.0);
        AnchorPane.setRightAnchor(exitButton, 0.0);

        // Set the action for the buttons
        this.settings.setOnAction(buttonHandler);
        this.exitButton.setOnAction(buttonHandler);

        this.getChildren().addAll(settings, exitButton);

    }

    /**
     * Gets the root pane for the bottom pane.
     *
     * @return the root pane
     */
    public Pane getRoot(){
        return this;
    }

    /**
     * Sets the screen changer for the main menu.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) {
        this.screenChanger = sch;
    }

    /**
     * The button handler for the bottom pane.
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        /**
         * Handle the button clicks
         *
         * @param event the event
         */
        @Override
        public void handle(ActionEvent event) {
            if (screenChanger != null) {
                Object source = event.getSource();
                if (source == exitButton){
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                } else if (source == settings) {
                    screenChanger.changeScreen(ToScreen.SETTINGS_MENU, ToScreen.GAME_BOARD);
                }
            }
        }
    };

    /**
     * Updates the bottom pane.
     *
     * @param currPlayer the current player
     */
    public void updateBottomPane(String currPlayer) {
        playerSelect.setText(currPlayer + "'s turn!");
    }
}
