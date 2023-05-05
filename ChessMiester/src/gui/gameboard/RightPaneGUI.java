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
        player2.setId("topLabel");
        capturedPieces = new Label("Captured:");
        capturedPieces.setId("topLabel");

        root.getChildren().add(player2);
        root.getChildren().add(capturedPieces);
        root.setId("right");
        root.setSpacing(50);
        root.setMaxSize(300, 800);
    }

    /**
     * Getter for the root pane.
     *
     * @return the root pane.
     */
    public Pane getRoot(){ return root; }

    public Label getLabel(){return player2;}

    public Label getCapturedPieces(){return capturedPieces;}
}
