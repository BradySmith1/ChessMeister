/**
 * This class creates the right pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import interfaces.PieceIF;
import interfaces.PlayerIF;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class RightPaneGUI {
    /** The root pane for the right pane. */
    VBox root;

    /** The label for player 2. */
    Label player2;

    /** The label for the captured pieces. */
    Label capturedPieces;

    /**
     * Constructor for the right pane.
     *
     * @param player the player
     */
    public RightPaneGUI(PlayerIF player){
        root = new VBox();

        player2 = new Label("Player 2: " + player.getName());
        player2.setId("topLabel");
        capturedPieces = new Label("Captured:");
        capturedPieces.setId("topLabel");
        TilePane captured = makeCaptured(player);

        root.getChildren().addAll(player2, capturedPieces, captured);
        root.setId("right");
        root.setSpacing(50);
        root.setMaxSize(300, 800);
    }

    /**
     * Makes the captured pieces pane.
     *
     * @param player the player
     * @return the captured pieces pane
     */
    private TilePane makeCaptured(PlayerIF player){
        TilePane captured = new TilePane();
        captured.setPrefColumns(2);
        captured.setPrefRows(8);
        captured.setMaxSize(300, 800);
        captured.setMinSize(300, 800);
        captured.setId("main-pain");

        ArrayList<PieceIF> pieces = player.getCapturedPieces();
        for (PieceIF pieceIF : pieces) {
            Image piece = pieceIF.getImage();
            captured.getChildren().add(new ImageView(piece));
        }
        return captured;
    }

    /**
     * Getter for the root pane.
     *
     * @return the root pane.
     */
    public Pane getRoot(){ return root; }

    /**
     * Getter for the label.
     *
     * @return the label
     */
    public Label getLabel(){return player2;}

    /**
     * Getter for the captured pieces label.
     *
     * @return the captured pieces label
     */
    public Label getCapturedPieces(){return capturedPieces;}

    /**
     * Setter for the label.
     *
     * @param name the name
     */
    public void setLabel(String name){
        player2.setText(name);
    }
}
