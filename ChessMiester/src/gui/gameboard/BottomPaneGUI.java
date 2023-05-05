/**
 * This class is responsible for creating the bottom pane of the chess board.
 *
 * @author Brady Smith (80%), Zach Eanes (20%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class BottomPaneGUI {

    /** The root pane. */
    private FlowPane root;
    /** The label for the player select. */
    private Label playerSelect;

    /**
     * Constructor for the bottom pane.
     */
    public BottomPaneGUI(){
        root = new FlowPane();
        playerSelect = new Label("Player 1 Selected D7 (Hardcoded)");
        root.getChildren().add(playerSelect);
        root.setAlignment(Pos.CENTER);
    }

    /**
     * Returns the root pane.
     *
     * @return the root pane
     */
    public Pane getRoot(){ return root; }

    /**
     * Returns the label for the player select.
     *
     * @return the label for the player select
     */
    public Label getLabel(){ return playerSelect; }
}
