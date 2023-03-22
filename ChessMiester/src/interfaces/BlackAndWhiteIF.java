package interfaces;

import enums.GameColor;

/**
 * This interface represents a piece that can be black or white.
 * @author Brady Smith %100
 */
public interface BlackAndWhiteIF {

    /**
     * Gets the color of the piece.
     * @return the color of the piece.
     */
    public GameColor getColor();

    /**
     * Checks if the piece is black.
     * @return true if the piece is black.
     */
    public boolean isBlack();

    /**
     * Checks if the piece is white.
     * @return true if the piece is white.
     */
    public boolean isWhite();
}
