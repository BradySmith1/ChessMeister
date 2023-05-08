package interfaces;

import enums.ToScreen;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Interface to be coded to for the functionality of changing screens
 */
public interface ScreenChangeHandlerIF {

    /**
     * Method that sets the scenes root to a new scene
     * @param screenChoice  the ToScreen enum reference
     * @param previousScreen the ToScreen enum reference of the previous screen
     */
    public void changeScreen(ToScreen screenChoice, ToScreen previousScreen);

    /**
     * Method that sets the scenes root to a new scene
     * @param screenChoice  the ToScreen enum reference
     */
    public void changeScreen(ToScreen screenChoice);

    /**
     * Method that returns an enum representing the previous scene
     * @return the ToScreen enum reference of the previous screen
     */
    public ToScreen getPreviousScreen();

    /**
     * Method that returns a selected scene
     *
     * @return the selected scene
     */
    public Pane getGuiScene(ToScreen screenChoice);

    /**
     * Method that updates the board for settings
     */
    public void notifyBoard();

}
