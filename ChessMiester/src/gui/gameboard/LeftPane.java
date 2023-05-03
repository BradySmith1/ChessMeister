package gui.gameboard;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LeftPane {
    VBox root;

    Label player1;

    Label capturedPieces;

    public LeftPane(){
        root = new VBox();

        player1 = new Label("Player 1:");
        capturedPieces = new Label("Captured:");

        root.getChildren().add(player1);
        root.getChildren().add(capturedPieces);
    }

    public Pane getRoot(){
        return root;
    }
}
