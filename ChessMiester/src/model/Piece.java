package model;

import enums.ChessPieceType;
import enums.GameColor;
import interfaces.PieceIF;

/**
 * This class represents a chess piece.
 */
public class Piece extends BlackAndWhite implements PieceIF {

    /**
     * The type of the piece.
     */
    private ChessPieceType type;

    /**
     * Creates a new piece of the specified type.
     * @param type the type of the piece.
     */
    public Piece(ChessPieceType type, GameColor color) {
        super(color);
        this.type = type;
    }

    /**
     * Gets the type of the piece.
     * @return the type of the piece.
     */
    @Override
    public ChessPieceType getType() {
        return type;
    }

    /**
     * Sets the type of the piece.
     * @param type the type of the piece.
     */
    @Override
    public void setType(ChessPieceType type) {
        this.type = type;
    }
}
