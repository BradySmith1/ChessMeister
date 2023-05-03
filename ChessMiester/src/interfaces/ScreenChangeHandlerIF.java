package interfaces;

import enums.ToScreen;
import javafx.scene.Scene;

/**
 * Interface to be coded to for the functionality of changing screens
 */
public interface ScreenChangeHandlerIF {

    /**
     * Method that sets the scenes root to a new scene
     * @param screenChoice  the ToScreen enum reference
     */
    public void changeScreen(ToScreen screenChoice);

    /**
     * The getter for the singleton instance of this class
     * @param scene the scene to be used
     * @return the singleton instance
     */
    public ScreenChangeHandlerIF getInstance(Scene scene);

}
