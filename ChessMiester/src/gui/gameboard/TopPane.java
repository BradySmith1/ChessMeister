/**
 * This class creates the top pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */

package gui.gameboard;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TopPane {
    /** The root pane for the right pane. */
    GridPane root;

    /** The buttons for the pane */
    Button save, undo, redo;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /**
     * Constructor for the right pane.
     */
    public TopPane(){

        //Creation of the grid pane.
        root = new GridPane();

        //Creation of the buttons.
        save = new Button("Save");
        undo = new Button("Undo");
        redo = new Button("Redo");

        // set id of the buttons
        save.setId("bottom-button");
        undo.setId("bottom-button");
        redo.setId("bottom-button");

        // Set the action for the buttons
        save.setOnAction(buttonHandler);
        undo.setOnAction(buttonHandler);
        redo.setOnAction(buttonHandler);

        //Sets the max size of the button to fill the grid space.
        save.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        undo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        redo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Adds the buttons to the grid.
        root.add(save, 0,  0, 1, 1);
        root.add(undo, 1, 0, 1, 1);
        root.add(redo, 2, 0,1,  1);

        //Attributes for the grid pane.
        root.setHgap(45);
        root.setAlignment(Pos.CENTER);
    }

    /**
     * Getter for the root pane.
     *
     * @return the root pane.
     */
    public Pane getRoot(){
        return root;
    }


    /**
     * Sets the screen changer for the main menu.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) {
        this.screenChanger = sch;
    }

    /** The button handler for the buttons. */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        /**
         * Handle the button clicks
         *
         * @param event the event
         */
        @Override
        public void handle(ActionEvent event) {
            if (screenChanger != null) {
                Object source = event.getSource();
                if (source == save) {
                    System.out.println("Save");
                } else if (source == undo) {
                    System.out.println("Undo");
                } else if (source == redo) {
                    System.out.println("Redo");
                }
            }
        }
    };


}
