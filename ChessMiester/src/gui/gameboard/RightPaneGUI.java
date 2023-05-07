/**
 * This class creates the right pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import interfaces.PieceIF;
import interfaces.PlayerIF;
import javafx.application.Platform;
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

    /** The right tile pane. */
    TilePane captured = new TilePane();

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

        this.setProperties();
        this.makeCaptured(player);

        root.getChildren().addAll(player2, capturedPieces, captured);
        root.setId("right");
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
