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
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TopPane {
    GridPane root;

    Button save, undo, redo, settings;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    public TopPane(){

        //Creation of the grid pane.
        root = new GridPane();

        //Creation of the buttons.
        save = new Button("Save");
        undo = new Button("Undo");
        redo = new Button("Redo");
        settings = new Button("Settings");

        // Set the action for the buttons
        this.save.setOnAction(buttonHandler);
        this.undo.setOnAction(buttonHandler);
        this.redo.setOnAction(buttonHandler);
        this.settings.setOnAction(buttonHandler);

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


    /**
     * Sets the screen changer for the main menu.
     *
     * @param sch the screen changer
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
                if (source == save) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")  + "/src/SavedGames"));
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extFilter);
                    File selectedFile = fileChooser.showSaveDialog(save.getScene().getWindow());
                    if (selectedFile != null) {
                        // PrintWriter to write to the file
                        try {
                            PrintWriter writer = new PrintWriter(selectedFile);
                            writer.println("This is some text that will be written to the file.");
                            writer.close();
                            System.out.println("File saved: " + selectedFile.getAbsolutePath());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (source == undo) {
                    System.out.println("Undo");
                } else if (source == redo) {
                    System.out.println("Redo");
                } else if (source == settings) {
                    screenChanger.changeScreen(ToScreen.SETTINGS_MENU, ToScreen.GAME_BOARD);
                }
            }
        }
    };


}
