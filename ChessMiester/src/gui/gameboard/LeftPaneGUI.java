/**
 * This class is responsible for creating the left pane of the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import gui.playernames.PlayerNamesGUI;
import interfaces.PieceIF;
import interfaces.PlayerIF;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.Piece;

import java.util.ArrayList;

public class LeftPaneGUI {
    /** The root pane. */
    private VBox root;

    /** The label for player 1. */
    private Label player1;

    /** The label for the captured pieces. */
    private Label capturedPieces;

    /**
     * Constructor for the left pane.
     *
     * @param player the player
     */
    public LeftPaneGUI(PlayerIF player){
        root = new VBox();

        player1 = new Label("Player 1: " + player.getName());
        player1.setId("topLabel");
        capturedPieces = new Label("Captured:");
        TilePane captured = makeCaptured();
        capturedPieces.setId("topLabel");

        root.getChildren().add(player1);
        root.getChildren().add(capturedPieces);
        root.setId("left");
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
    public Label getCapturedPieces(){return capturedPieces;}


    public void setLabel(String name){
        player1.setText(name);
    }
}
