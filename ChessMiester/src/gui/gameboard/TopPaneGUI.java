/**
 * This class creates the top pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */

package gui.gameboard;
import interfaces.PieceIF;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import model.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class TopPaneGUI extends GridPane implements GameBoardObserver{

    /** The buttons for the pane */
    Button save, undo, redo;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** Boolean for undo and redo*/
    private boolean undoRedo;

    GameBoardObserver observer;

    /**
     * Constructor for the right pane.
     */
    public TopPaneGUI(){

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
        this.add(save, 0,  0, 1, 1);
        this.add(undo, 1, 0, 1, 1);
        this.add(redo, 2, 0,1,  1);

        //Attributes for the grid pane.
        this.setHgap(45);
        this.setAlignment(Pos.CENTER);
    }

    /**
     * Getter for the root pane.
     *
     * @return the root pane.
     */
    public Pane getRoot(){
        return this;
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
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")  + "/src/SavedGames"));
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
                    fileChooser.getExtensionFilters().add(extFilter);
                    File selectedFile = fileChooser.showSaveDialog(save.getScene().getWindow());
                    if (selectedFile != null) {
                        // PrintWriter to write to the file
                        try {
                            PrintWriter writer = new PrintWriter(selectedFile);
                            notifySaveGame(writer);
                            writer.close();
                            System.out.println("File saved: " + selectedFile.getAbsolutePath());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (source == undo && undoRedo) {
                    notifyUndo();
                } else if (source == redo && undoRedo) {
                    notifyRedo();
                } else if (source == undo || source == redo && !undoRedo) {
                    createAlert("Undo/Redo");
                }
            }
        }
    };

    /**
     * Add a observer
     * @param observer the observer
     */
    public void addObserver(GameBoardObserver observer) {
        this.observer = observer;
    }

    /**
     * Sets the status of undo and redo.
     * @param undoRedo the status of undo and redo
     */
    public void setShowUndoRedo(boolean undoRedo) {
        this.undoRedo = undoRedo;
    }

    /**
     * Creates an alert for the user
     * @param title the title of the alert
     */
    private void createAlert(String title){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(title + " is not available");
        alert.setContentText(title + " is disable in settings");
        alert.showAndWait();
    }

    /**
     * Notify the observer to left click
     * @param event the event that occurred
     */
    @Override
    public void notifyLeftClick(Event event) {

    }

    /**
     * Notify the observer to right click
     * @param event the event that occurred
     */
    @Override
    public void notifyRightClick(Event event) {

    }

    /**
     * Notify the observer to move the piece
     * @param event the event that occurred
     * @return the list of positions
     */
    @Override
    public List<Position> notifyPieceMoving(Event event) {
        return null;
    }

    /**
     * Notify the observer to add a captured piece
     * @param piece the piece that was captured.
     */
    @Override
    public void notifyAddCapturedPiece(PieceIF piece) {

    }

    /**
     * Notify the observer to load the board
     * @param event the event
     */
    @Override
    public void notifyBoardLoader(Event event) {

    }

    /**
     * Notify the observer to undo the move
     */
    @Override
    public void notifyUndo() {
        observer.notifyUndo();
    }

    /**
     * Notify the observer to redo the move
     */
    @Override
    public void notifyRedo() {
        observer.notifyRedo();
    }

    public void notifySaveGame(PrintWriter writer) {
        observer.notifySaveGame(writer);
    }
}
