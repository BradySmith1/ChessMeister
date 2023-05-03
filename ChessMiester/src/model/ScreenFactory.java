package model;

import enums.ToScreen;
import gui.Tutorial.TutorialMenuGUI;
import gui.colourselector.ColourSelectorGUI;
import gui.mainmenu.MainMenuGUI;
import gui.playernames.PlayerNamesGUI;
import gui.settingsmenu.SettingsMenuGUI;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

/**
 * Class that implements ScreenChangeHandlerIF to be used to change screens, this is a singleton
 */
public class ScreenFactory implements ScreenChangeHandlerIF {

    /** the scene to be passed in first through getInstance, then to be changed by changeScreen() */
    private Scene scene;

    /** the singleton instance */
    private static ScreenFactory singleton;

    /**
     * The constructor for ScreenFactory
     * @param scene the scene passed in to be used by ScreenFactory
     */
    private ScreenFactory(Scene scene) {
        this.scene = scene;
        setScreen(ToScreen.MAIN_MENU);
    }

    /**
     * The getter for the singleton instance of this class
     * @param scene the scene to be used
     * @return the singleton instance
     */
    public ScreenChangeHandlerIF getInstance(Scene scene) {
        if (singleton == null) {
            singleton = new ScreenFactory(scene);
        }
        return singleton;
    }

    /**
     * the helper method to change screens based on a ToScreen enum, edit this to add more screens as well as
     * the enum file ToScreen
     * @param screenChoice  the enum to be referenced for the changing screen
     * @return  a screen that the scene will now be using
     */
    private Pane setScreen(ToScreen screenChoice) {
        Pane screen = null;

        switch (screenChoice){
            case MAIN_MENU :
                screen = MainMenuGUI.getInstance();
                break;
            case PLAYER_NAMES :
                screen = PlayerNamesGUI.getInstance();
                break;
            case SETTINGS_MENU :
                screen = SettingsMenuGUI.getInstance();
                break;
            case TUTORIAL_MENU :
                screen = TutorialMenuGUI.getInstance();
                break;
            case COLOUR_SELECTOR:
                screen = ColourSelectorGUI.getInstance();
        }
        return screen;
    }

    /**
     * Method that sets the scenes root to a new scene
     * @param screenChoice  the ToScreen enum reference
     */
    @Override
    public void changeScreen(ToScreen screenChoice) {
        Pane root = setScreen(screenChoice);
        scene.setRoot(root);
    }
}
