/**
 * This class is the main file used to create our GUI implementation
 * of chess.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.controller;

import enums.ToScreen;
import gui.Tutorial.TutorialMenuGUI;
import gui.colourselector.ColourSelectorGUI;
import gui.mainmenu.MainMenuGUI;
import gui.settingsmenu.SettingsMenuGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
//        MainMenuGUI mainMenu = MainMenuGUI.getInstance();
//        // Create ChessMeister Label
//        stage.setTitle("ChessMeister");
//
//        stage.setScene(mainMenu.getMenu());
//        stage.setMaximized(true);
//        stage.getScene().setFill(Color.GREY);
//        stage.show();

//        PlayerNamesGUI playerNameMenu = PlayerNamesGUI.getInstance();
//        // Create ChessMeister Label
//        stage.setTitle("ChessMeister");
//
//        stage.setScene(playerNameMenu.getMenu());
//        stage.setMaximized(true);
//        stage.getScene().setFill(Color.GREY);
//        stage.show();

//        TutorialMenuGUI tutorialMenu = TutorialMenuGUI.getInstance();
//        // Create ChessMeister Label
//        stage.setTitle("ChessMeister");
//
//        stage.setScene(tutorialMenu.getMenu());
//        stage.setMaximized(true);
//        stage.getScene().setFill(Color.GREY);
//        stage.show();

//        SettingsMenuGUI settingsMenu = SettingsMenuGUI.getInstance();
//        // Create ChessMeister Label
//        stage.setTitle("ChessMeister");
//
//        stage.setScene(settingsMenu.getMenu());
//        stage.setMaximized(true);
//        stage.getScene().setFill(Color.GREY);
//        stage.show();

//        ColourSelectorGUI colorSelectMenu = ColourSelectorGUI.getInstance();
//        // Create ChessMeister Label
//        stage.setTitle("ChessMeister");
//
//        stage.setScene(colorSelectMenu.getMenu());
//        stage.setMaximized(true);
//        stage.getScene().setFill(Color.GREY);
//        stage.show();

        try {
            this.stage = primaryStage;
            this.stage.setFullScreen(true);

            Scene scene = new Scene(new Pane(),800,600);
            this.rootScene = scene;

            ScreenFactory.getInstance(scene);

            ScreenFactory.getInstance(scene);

            this.stage.setScene(scene);
            this.stage.show();
            this.stage.setTitle("ChessMeister");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the root scene for the application
     * @param root The root scene to set
     */
    private void setScreen(Pane root){
        this.rootScene.setRoot(root);
    }
}
