package interfaces;

/**
 * This interface defines the basic functionality of a settings menu.
 * @author Brady 100%
 */
public interface SettingsIF {

    /**
     * Shows the settings menu and dialog
     */
    public void showSettings();

    /**
     * Gets the color of the board
     * @return the color of the board
     */
    public String getBoardColor();

    /**
     * Gets whether undo is on or off
     * @return string of on or off
     */
    public String getUndo();

    /**
     * Gets whether highlighting is on of off
     * @return string of on or off
     */
    public String getShowMoves();
}
