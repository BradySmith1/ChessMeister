/**
 * This class creates the right pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

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
}
