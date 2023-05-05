package gui.gameboard;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LeftPaneGUI {
    private VBox root;

    private Label player1;

    private Label capturedPieces;

    public LeftPaneGUI(){
        root = new VBox();

        player1 = new Label("Player 1:");
        capturedPieces = new Label("Captured:");

        root.getChildren().add(player1);
        root.getChildren().add(capturedPieces);
    }

    public Pane getRoot(){
        return root;
    }

    public Label getLabel(){return player1;}

    public Label getCapturedPieces(){return capturedPieces;}
}
