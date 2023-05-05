package gui.gameboard;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class BottomPane {
    AnchorPane root;
    Label playerSelect;

    Button exitButton;

    public BottomPane(){
        root = new AnchorPane();
        playerSelect = new Label("Player 1 Selected D7 (Hardcoded)");
        root.getChildren().add(playerSelect);
        AnchorPane.setLeftAnchor(playerSelect, 505.0);

        exitButton = new Button("Exit");
        exitButton.setId("bottom-button");
        root.getChildren().add(exitButton);
        AnchorPane.setBottomAnchor(exitButton, 0.0);
        AnchorPane.setRightAnchor(exitButton, 0.0);
    }

    public Pane getRoot(){
        return root;
    }
}
