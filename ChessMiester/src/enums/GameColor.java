package enums;

/**
 * Enumeration file to represent the color of the chess game.
 *
 * @author Zach Eanes (100%)
 * @version 1.0
 */

public enum GameColor {
    BLACK, /* Represents the color black. */
    WHITE; /* Represents the color white. */

    /**
     * Method to display the color of the current player as a string.
     * @return string representation of the current player's color
     */
    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
