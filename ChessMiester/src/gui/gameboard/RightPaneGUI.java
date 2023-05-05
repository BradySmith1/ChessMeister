/**
 * This class is responsible for creating the right pane of the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RightPaneGUI {
    /** The root pane. */
    private VBox root;

    /** The label for player 2. */
    private Label player2;

    /** The label for the captured pieces. */
    private Label capturedPieces;

    /**
     * Constructor for the right pane.
     */
    public RightPaneGUI(){
        root = new VBox();

        player2 = new Label("Player 2:");
        capturedPieces = new Label("Captured:");

        root.getChildren().add(player2);
        root.getChildren().add(capturedPieces);
    }

    /**
     * Returns the root pane.
     *
     * @return the root pane
     */
    public Pane getRoot(){
        return root;
    }

    /**
     * Returns the label for player 2.
     *
     * @return the label for player 2
     */
    public Label getLabel(){return player2;}

    /**
     * Returns the label for the captured pieces.
     *
     * @return the label for the captured pieces
     */
    public Label getCapturedPieces(){return capturedPieces;}
}
