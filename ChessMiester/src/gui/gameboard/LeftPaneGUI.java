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

public class LeftPaneGUI extends VBox{

    /** The label for player 1. */
    private Label player1;

    /** The label for the captured pieces. */
    private Label capturedPieces;

    private TilePane captured;

    /**
     * Constructor for the left pane.
     *
     * @param player the player
     */
    public LeftPaneGUI(PlayerIF player){

        player1 = new Label("Player 1: " + player.getName());
        player1.setId("topLabel");
        capturedPieces = new Label("Captured:");
        this.captured = new TilePane();
        captured.setPrefColumns(2);
        captured.setPrefRows(8);
        captured.setMaxSize(300, 800);
        captured.setMinSize(300, 400); //TODO this is a temporary fix for the resizing issue
        captured.setId("main-pain");
        capturedPieces.setId("topLabel");

        this.getChildren().add(player1);
        this.getChildren().add(capturedPieces);
        this.getChildren().add(captured);
        this.setId("left");
        this.setSpacing(50);
        this.setMaxSize(300, 800);
    }

    /**
     * Makes the captured pieces pane.
     *
     * @param player the player
     */
    public void makeCaptured(PlayerIF player){
        TilePane captured = (TilePane) this.getChildren().get(2);
        for (int index = 0; index < captured.getChildren().size(); index++){
            captured.getChildren().remove(0);
        }

        ArrayList<PieceIF> pieces = player.getCapturedPieces();
        for(int index = 0; index < pieces.size(); index++){
            Image piece = pieces.get(index).getImage();
            // TOOD Testing why it is not displaying the captured pieces
            ImageView imageView = new ImageView(piece);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            captured.getChildren().add(imageView);
        }
    }

    /**
     * Returns the root pane.
     *
     * @return the root pane
     */
    public Pane getRoot(){ return this; }

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
}
