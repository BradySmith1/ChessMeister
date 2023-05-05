package gui.gameboard;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class BottomPaneGUI {

    private FlowPane root;
    private Label playerSelect;

    public BottomPaneGUI(){
        root = new FlowPane();
        playerSelect = new Label("Player 1 Selected D7 (Hardcoded)");
        root.getChildren().add(playerSelect);
        root.setAlignment(Pos.CENTER);
    }

    public Pane getRoot(){
        return root;
    }
}
