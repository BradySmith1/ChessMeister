package interfaces;

/**
 * This interface defines the basic functionality of a load game dialog.
 */
public interface LoadSaveGameIF {

    /**
     * Displays the load game dialog.
     */
    public void showLoadSave();

    /**
     * Gets the URL of the file to load.
     */
    public String getURL();
}
