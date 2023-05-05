package gui.gameboard;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TopPane {
    GridPane root;

    Button save, undo, redo, settings;

    public TopPane(){

        //Creation of the grid pane.
        root = new GridPane();

        //Creation of the buttons.
        save = new Button("Save");
        undo = new Button("Undo");
        redo = new Button("Redo");
        settings = new Button("Settings");

        //Sets the max size of the button to fill the grid space.
        save.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        undo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        redo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        settings.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Adds the buttons to the grid.
        root.add(save, 0,  0, 1, 1);
        root.add(undo, 1, 0, 1, 1);
        root.add(redo, 2, 0,1,  1);
        root.add(settings, 3, 0,1, 1);

        //Attributes for the grid pane.
        root.setHgap(45);
        root.setAlignment(Pos.CENTER);
    }

    public Pane getRoot(){
        return root;
    }
}
