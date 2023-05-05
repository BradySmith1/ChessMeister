/**
 * This class creates the right pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

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
        //exitButton.setOnAction(buttonHandle);
        ap.getChildren().add(exitButton);
        AnchorPane.setBottomAnchor(exitButton, 0.0);

        root.getChildren().add(ap);
    }

    public Pane getRoot(){
        return root;
    }
}
