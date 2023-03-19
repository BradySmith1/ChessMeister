package interfaces;

import enums.ChessPieceType;

/**
 * This interface represents a chess piece.
 *
 * @author Zach Eanes (100%)
 */
public interface PieceIF {

    /**
     * Gets the type of the piece.
     * @return the type of the piece.
     */
    public ChessPieceType getType();

    /**
     * Sets the type of the piece.
     * @param type the type of the piece.
     */
    public void setType(ChessPieceType type);
}
