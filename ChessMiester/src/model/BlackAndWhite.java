/**
 * This class represents a piece that can be either black or white.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
package model;

import enums.GameColor;
import interfaces.BlackAndWhiteIF;

public class BlackAndWhite implements BlackAndWhiteIF {

    private GameColor color; /* The color of the piece. */

    /**
     * Constructor method for the BlackAndWhite class.
     *
     * @param color the color of the piece.
     */
    public BlackAndWhite(GameColor color) {
        this.color = color;
    }

    /**
     * Gets the color of the piece.
     *
     * @return the color of the piece.
     */
    public GameColor getColor() {
        return color;
    }

    /**
     * Sets the color of the piece.
     *
     * @param color the color of the piece.
     */
    public void setColor(GameColor color) {
        this.color = color;
    }

    /**
     * Returns true if the piece is black.
     *
     * @return true if the piece is black.
     */
    public boolean isBlack() {
        return color == GameColor.BLACK;
    }

    /**
     * Returns true if the piece is white.
     *
     * @return true if the piece is white.
     */
    public boolean isWhite() {
        return color == GameColor.WHITE;
    }

    /**
     * Sets the piece to black.
     */
    public void setBlack() {
        color = GameColor.BLACK;
    }

    /**
     * Sets the piece to white.
     */
    public void setWhite() {
        color = GameColor.WHITE;
    }

}
