/**
 * This class represents a square on a chess board. Each square can be cleared,
 * and can hold a piece or be empty.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
package model;

import enums.GameColor;
import interfaces.PieceIF;
import interfaces.SquareIF;

public class Square extends BlackAndWhite implements SquareIF {

    /**
     * The piece that is currently on the square, if any.
     */
    private Position position;

    /**
     * The piece that is currently on the square, if any.
     */
    private PieceIF piece;

    /**
     * Creates a new square with no piece on it.
     */
    public Square(Position position, GameColor color){
        super(color);
        piece = null;
        this.position = position;
    }

    /**
     * Clears the square by removing any piece that may be on it.
     */
    @Override
    public void clear() {piece = null;}

    /**
     * Sets a piece on the square.
     * @return The piece that was previously on the square, if any.
     */
    @Override
    public PieceIF setPiece(PieceIF piece){
        this.piece = piece;
        return piece;
    }

    /**
     * Returns the piece that is currently on the square, if any.
     * @return The piece on the square, or null if the square is empty.
     */
    @Override
    public PieceIF getPiece() {return piece;}

    /**
     * Returns the position of the square.
     * @return The position of the square.
     */
    public Position getPosition(){return position;}

    /**
     * Sets the position of the square.
     * @param position The position of the square.
     */
    public void setPosition(Position position){this.position = position;}
}
