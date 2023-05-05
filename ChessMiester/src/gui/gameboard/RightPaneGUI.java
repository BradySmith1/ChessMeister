package gui.gameboard;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RightPaneGUI {
    private VBox root;

    private Label player2;

    private Label capturedPieces;

    public RightPaneGUI(){
        root = new VBox();

        player2 = new Label("Player 2:");
        capturedPieces = new Label("Captured:");

        root.getChildren().add(player2);
        root.getChildren().add(capturedPieces);
    }

    public Pane getRoot(){
        return root;
    }
}
