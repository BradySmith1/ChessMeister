package gui.gameboard;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TopPane {
    GridPane root;

    Button load, save, undo, redo, settings;

    public TopPane(){

        //Creation of the grid pane.
        root = new GridPane();

        //Creation of the buttons.
        load = new Button("Load");
        save = new Button("Save");
        undo = new Button("Undo");
        redo = new Button("Redo");
        settings = new Button("Settings");

        //Sets the max size of the button to fill the grid space.
        load.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        save.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        undo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        redo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        settings.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Adds the buttons to the grid.
        root.add(load, 0, 0, 1, 1 );
        root.add(save, 1,  0, 1, 1);
        root.add(undo, 2, 0, 1, 1);
        root.add(redo, 3, 0,1,  1);
        root.add(settings, 4, 0,1, 1);

        //Attributes for the grid pane.
        root.setHgap(45);
        root.setAlignment(Pos.CENTER);
    }

    public Pane getRoot(){
        return root;
    }
}
