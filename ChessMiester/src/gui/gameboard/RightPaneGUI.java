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

/**
 * This class creates the right pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
public class RightPaneGUI extends VBox{

    /** The label for player 2. */
    Label player2;

    /** The label for the captured pieces. */
    Label capturedPieces;

    /** The captured pieces pane. */
    private TilePane captured;

    /**
     * Constructor for the right pane.
     *
     * @param player the player
     */
    public RightPaneGUI(PlayerIF player){
        player2 = new Label("Player 2: " + player.getName());
        player2.setId("topLabel");
        capturedPieces = new Label("Captured:");
        capturedPieces.setId("topLabel");
        this.captured = new TilePane();

        this.getChildren().addAll(player2, capturedPieces, captured);
        this.setId("right");
        this.setSpacing(50);
        this.setMaxSize(300, 800);
    }

    /**
     * Makes the captured pieces pane.
     *
     * @param player the player
     */
    public void makeCapturedRight(PlayerIF player){
        TilePane captured = (TilePane) this.getChildren().get(2);
        while(captured.getChildren().size() > 0){
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
     * Getter for the root pane.
     *
     * @return the root pane.
     */
    public Pane getRoot(){ return this; }

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
