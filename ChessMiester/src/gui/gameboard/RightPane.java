/**
 * This class creates the right pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RightPane {
    /** The root pane for the right pane. */
    AnchorPane root;

    /** The label for player 2. */
    Label player2;

    /** The label for the captured pieces. */
    Label capturedPieces;

    /** The button to exit the game. */
    Button exitButton;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /**
     * Constructor for the right pane.
     */
    public RightPane(){
        root = new AnchorPane();

        VBox vBox = new VBox();
        player2 = new Label("Player 2:");
        capturedPieces = new Label("Captured:");

        vBox.getChildren().add(player2);
        vBox.getChildren().add(capturedPieces);

        AnchorPane ap = new AnchorPane(vBox);
        AnchorPane.setTopAnchor(vBox, 0.0);

        exitButton = new Button("Return to Main Menu");
        exitButton.setId("bottom-button");

        // Set the action for the buttons
        exitButton.setOnAction(buttonHandler);

        //Adds the buttons to the anchor pane.
        ap.getChildren().add(exitButton);
        AnchorPane.setBottomAnchor(exitButton, 0.0);

        root.getChildren().add(ap);
    }

    public Pane getRoot(){
        return root;
    }

    /**
     * Sets the screen changer for the main menu.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) {
        this.screenChanger = sch;
    }

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
                }
            }
        }
    };
}
