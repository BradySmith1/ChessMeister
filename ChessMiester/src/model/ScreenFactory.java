package model;

import enums.ToScreen;
import gui.Tutorial.TutorialMenuGUI;
import gui.colourselector.ColourSelectorGUI;
import gui.mainmenu.MainMenuGUI;
import gui.playernames.PlayerNamesGUI;
import gui.settingsmenu.SettingsMenuGUI;
import gui.viewplayed.ViewPlayedGUI;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
/**
 * Class that implements ScreenChangeHandlerIF to be used to change screens, this is a singleton
 */
public final class ScreenFactory implements ScreenChangeHandlerIF {

    /** the scene to be passed in first through getInstance, then to be changed by changeScreen() */
    private Scene scene;

    /** the singleton instance */
    private static ScreenFactory singleton;

    /** The main menu screen **/
    private static MainMenuGUI mainMenuScreen;

    /** The player names screen **/
    private static PlayerNamesGUI definePlayerNamesScreen;

    /** The tutorial screen **/
    private static TutorialMenuGUI tutorialMenuScreen;

    /** The settings screen **/
    private static SettingsMenuGUI settingsMenuScreen;

    /** The colour selector screen **/
    private static ColourSelectorGUI colourSelectorScreen;

    /** The load game screen **/
    private static ViewPlayedGUI loadGameScreen;

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
    public static ScreenFactory getInstance(Scene scene) {
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
        Pane screen;
        switch (screenChoice){
            case MAIN_MENU :
                if (mainMenuScreen == null) {
                    mainMenuScreen = new MainMenuGUI();
                    mainMenuScreen.setScreenChangeHandler(this);
                }
                screen = mainMenuScreen.getRoot();
                break;
            case PLAYER_NAMES :
                if (definePlayerNamesScreen == null) {
                    definePlayerNamesScreen = new PlayerNamesGUI();
                    definePlayerNamesScreen.setScreenChangeHandler(this);
                }
                screen = definePlayerNamesScreen.getRoot();
                break;
            case SETTINGS_MENU :
                if (settingsMenuScreen == null) {
                    settingsMenuScreen = new SettingsMenuGUI();
                    settingsMenuScreen.setScreenChangeHandler(this);
                }
                screen = settingsMenuScreen.getRoot();
                break;
            case TUTORIAL_MENU :
                if (tutorialMenuScreen == null) {
                    tutorialMenuScreen = new TutorialMenuGUI();
                    tutorialMenuScreen.setScreenChangeHandler(this);
                }
                screen = tutorialMenuScreen.getRoot();
                break;
            case COLOUR_SELECTOR:
                if (colourSelectorScreen == null) {
                    colourSelectorScreen = new ColourSelectorGUI();
                }
                screen = colourSelectorScreen.getRoot();
                break;
            case VIEW_PLAYED:
                if (loadGameScreen == null) {
                    loadGameScreen = new ViewPlayedGUI();
                    loadGameScreen.setScreenChangeHandler(this);
                }
                screen = loadGameScreen.getRoot();
                break;
            default:
                screen = null;
                break;
        }

        // Apply the screen to the root scene
        scene.setRoot(screen);

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
