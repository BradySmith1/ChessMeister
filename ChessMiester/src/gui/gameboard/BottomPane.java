package gui.gameboard;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class BottomPane {
    AnchorPane root;
    Label playerSelect;

    /** The button to exit the game. */
    Button exitButton;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    public BottomPane(){
        root = new AnchorPane();
        playerSelect = new Label("Player 1 Selected D7 (Hardcoded)");
        root.getChildren().add(playerSelect);
        AnchorPane.setLeftAnchor(playerSelect, 505.0);

        exitButton = new Button("Exit");
        exitButton.setId("bottom-button");

        // Set the action for the buttons
        this.exitButton.setOnAction(buttonHandler);

        root.getChildren().add(exitButton);
        AnchorPane.setBottomAnchor(exitButton, 0.0);
        AnchorPane.setRightAnchor(exitButton, 0.0);
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
