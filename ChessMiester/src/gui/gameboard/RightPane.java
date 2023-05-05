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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RightPane {
    /** The root pane for the right pane. */
    VBox root;

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
        root = new VBox();

        player2 = new Label("Player 2:");
        capturedPieces = new Label("Captured:");

        root.getChildren().add(player2);
        root.getChildren().add(capturedPieces);
    }

    /**
     * Getter for the root pane.
     *
     * @return the root pane.
     */
    public Pane getRoot(){ return root; }

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
