package interfaces;

/**
 * This interface defines the basic functionality of a settings menu.
 * @author Brady 100%
 */
public interface SettingsIF {

    /**
     * Shows the settings menu and dialog
     */
    public void show();

    /**
     * Gets the color of the board
     * @return the color of the board
     */
    public String getBoardColor();

    /**
     * Gets the color of the pieces
     * @return the color of the pieces
     */
    public String getUndo();

    /**
     * Gets the color of the pieces
     * @return the color of the pieces
     */
    public String getShowMoves();
}
