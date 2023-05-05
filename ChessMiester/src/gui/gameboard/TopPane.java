package gui.gameboard;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TopPane {
    GridPane root;

    Button load, save, undo, redo, settings;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    public TopPane(){

        //Creation of the grid pane.
        root = new GridPane();

        //Creation of the buttons.
        this.load = new Button("Load");
        this.save = new Button("Save");
        this.undo = new Button("Undo");
        this.redo = new Button("Redo");
        this.settings = new Button("Settings");

        // Set the action for the buttons
        this.load.setOnAction(buttonHandler);
        this.save.setOnAction(buttonHandler);
        this.undo.setOnAction(buttonHandler);
        this.redo.setOnAction(buttonHandler);
        this.settings.setOnAction(buttonHandler);

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


    /**
     * Sets the screen changer for the main menu.
     *
     * @param screenChanger the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) {
        this.screenChanger = sch;
    }

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
                if (source == load) {
                    System.out.println("Load");
                } else if (source == save) {
                    System.out.println("Save");
                } else if (source == undo) {
                    System.out.println("Undo");
                } else if (source == redo) {
                    System.out.println("Redo");
                } else if (source == settings) {
                    screenChanger.changeScreen(ToScreen.SETTINGS_MENU);
                }
            }
        }
    };


}
