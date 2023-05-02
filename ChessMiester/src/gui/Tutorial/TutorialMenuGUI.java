/**
 * This class is the GUI implementation for the tutorial menu.
 *
 * @author Zach Eanes (100%)
 */
package gui.Tutorial;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class TutorialMenuGUI extends BorderPane {
    /** The main menu pane. */
    BorderPane tutorialPane;

    /** Scene */
    private Scene scene;


    private static TutorialMenuGUI instance;

    /**
     * Constructor for the main menu GUI.
     */
    public static TutorialMenuGUI getInstance(){
        if(instance == null){
            instance = new TutorialMenuGUI();
        }
        return instance;
    }


    private TutorialMenuGUI() {
        // Create a border pane and add title to top
        this.tutorialPane = new BorderPane();
        Label title = new Label("Tutorials");
        title.setId("topLabel");
        this.tutorialPane.setTop(title);

        // Create buttons for the center
        VBox center = makeCenter();
        this.tutorialPane.setCenter(center);
    }

    private VBox makeCenter(){
        return new VBox();
    }
}