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
import javafx.application.Platform;
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

    /** The left tile pane. */
    private TilePane captured = new TilePane();

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
        this.makeCaptured(player);
        this.setProperties();
        capturedPieces.setId("topLabel");

        root.getChildren().add(player1);
        root.getChildren().add(capturedPieces);
        root.getChildren().add(captured);
        root.setId("left");
        root.setSpacing(50);
        root.setMaxSize(300, 800);
    }

    /**
     * Makes the captured pieces pane.
     *
     * @param player the player
     */
    private void makeCaptured(PlayerIF player){
        ArrayList<PieceIF> pieces = player.getCapturedPieces();
        for (PieceIF piece : pieces) {
            System.out.println("------------------");
            System.out.println("About to add a piece to the captured pane");
            System.out.println("The piece is: " + piece.getColor() + " " + piece.getType());
            System.out.println("------------------");
            Image pieceToAdd = piece.getImage();
            ImageView imageView = new ImageView(pieceToAdd);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            captured.getChildren().add(imageView);
        }
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

    /**
     * Sets the label for player 1.
     *
     * @param name the name of the player
     */
    public void setLabel(String name){
        player1.setText(name);
    }

    /**
     * Sets the properties of the tile pane for visual aid
     */
    public void setProperties() {
        captured.setPrefColumns(2);
        captured.setPrefRows(8);
        captured.setMaxSize(300, 800);
        captured.setMinSize(300, 400);    //TODO this is a temporary fix for the resizing issue
        captured.setId("main-pane");
        // TODO Testing why it is not displaying the captured pieces
        captured.setStyle("-fx-border-color: red; -fx-border-width: 5px;");
    }
}
