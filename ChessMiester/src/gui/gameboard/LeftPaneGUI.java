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
        player1.setId("topLabel");
        capturedPieces = new Label("Captured:");
        capturedPieces.setId("topLabel");

        root.getChildren().add(player1);
        root.getChildren().add(capturedPieces);
        root.setId("left");
        root.setSpacing(50);
        root.setMaxSize(300, 800);
    }

    public Pane getRoot(){
        return root;
    }

    public Label getLabel(){return player1;}

    public Label getCapturedPieces(){return capturedPieces;}

    public void setLabel(String name){
        player1.setText(name);
    }
}
