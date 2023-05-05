/**
 * This class is the main file used to create our GUI implementation
 * of chess.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ScreenFactory;

public class Main extends Application {

    /** The current stage for the application **/
    Stage stage;

    /** The root scene for this app **/
    Scene rootScene;

    /**
     * This is our main driver that launches the GUI.
     *
     * @param args command line arguments passed in
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method is used to start the GUI.
     *
     * @param primaryStage the stage to be used
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // create and factor the stage
            this.stage = primaryStage;
            this.stage.setFullScreen(true);

            // create scene and get from screen factory
            Scene scene = new Scene(new Pane(),800,600);
            this.rootScene = scene;
            ScreenFactory.getInstance(scene);

            // set the scene
            this.stage.setScene(scene);
            this.stage.setTitle("ChessMeister");
            this.stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the root scene for the application
     *
     * @param root The root scene to set
     */
    private void setScreen(Pane root){
        this.rootScene.setRoot(root);
    }
}
