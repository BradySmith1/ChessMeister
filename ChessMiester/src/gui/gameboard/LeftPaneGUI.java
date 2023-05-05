/**
 * This class is responsible for creating the left pane of the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LeftPaneGUI {
    /** The root pane. */
    private VBox root;

    /** The label for player 1. */
    private Label player1;

    /** The label for the captured pieces. */
    private Label capturedPieces;

    /**
     * Constructor for the left pane.
     */
    public LeftPaneGUI(){
        root = new VBox();

        player1 = new Label("Player 1:");
        capturedPieces = new Label("Captured:");

        root.getChildren().add(player1);
        root.getChildren().add(capturedPieces);
    }

    /**
     * Returns the root pane.
     *
     * @return the root pane
     */
    public Pane getRoot(){ return root; }

    /**
     * Returns the label for player 1.
     *
     * @return the label for player 1
     */
    public Label getLabel(){ return player1; }

    /**
     * Returns the label for the captured pieces.
     *
     * @return the label for the captured pieces
     */
    public Label getCapturedPieces(){ return capturedPieces; }
}
