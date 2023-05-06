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

    /** The piece that is currently on the square, if any. */
    private Position position;

    /** The piece that is currently on the square, if any. */
    private PieceIF piece;

    /**
     * Constructor that creates a new square with no piece on it.
     *
     * @param position The position of the square
     * @param color    The color of the square
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
     *
     * @param piece The piece to set on the square.
     */
    @Override
    public void setPiece(PieceIF piece){
        this.piece = piece;
    }

    /**
     * Returns the piece that is currently on the square, if any.
     *
     * @return The piece on the square, or null if the square is empty.
     */
    @Override
    public PieceIF getPiece() {return piece;}

    /**
     * Returns the position of the square.
     *
     * @return The position of the square.
     */
    public Position getPosition(){return position;}

    /**
     * Sets the position of the square.
     *
     * @param position The position of the square.
     */
    public void setPosition(Position position){this.position = position;}
}
