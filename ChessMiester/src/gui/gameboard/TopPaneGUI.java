/**
 * This class is responsible for creating the top pane of the chess board.
 *
 * @author Brady Smith (50%), Zach Eanes (50%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TopPaneGUI {

    /** The root pane. */
    private GridPane root;

    /** The buttons used in the top pane. */
    private Button save, undo, redo;

    public TopPaneGUI(){

        //Creation of the grid pane.
        root = new GridPane();

        //Creation of the buttons.
        save = new Button("Save");
        undo = new Button("Undo");
        redo = new Button("Redo");

        //Sets the max size of the button to fill the grid space.
        save.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        undo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        redo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Adds the buttons to the grid.
        root.add(save, 1,  0, 1, 1);
        root.add(undo, 2, 0, 1, 1);
        root.add(redo, 3, 0,1,  1);

        //Attributes for the grid pane.
        root.setHgap(45);
        root.setAlignment(Pos.CENTER);
    }

    /**
     * Returns the root pane.
     *
     * @return the root pane
     */
    public Pane getRoot(){
        return root;
    }
}
