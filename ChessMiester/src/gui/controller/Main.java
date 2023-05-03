/**
 * This class is the main file used to create our GUI implementation
 * of chess.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.controller;

import gui.Tutorial.TutorialMenuGUI;
import gui.colourselector.ColourSelectorGUI;
import gui.mainmenu.MainMenuGUI;
import gui.settingsmenu.SettingsMenuGUI;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

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
     * @param stage the stage to be used
     */
    @Override
    public void start(Stage stage) {
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

        ColourSelectorGUI colorSelectMenu = ColourSelectorGUI.getInstance();
        // Create ChessMeister Label
        stage.setTitle("ChessMeister");

        stage.setScene(colorSelectMenu.getMenu());
        stage.setMaximized(true);
        stage.getScene().setFill(Color.GREY);
        stage.show();
    }
}
