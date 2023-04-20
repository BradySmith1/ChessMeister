package model;

import enums.ChessPieceType;
import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import interfaces.PieceIF;
import interfaces.SquareIF;
import movements.*;

import java.util.List;

/**
 * This class represents a chess piece that can be placed on a game board.
 *
 * @author Brady Smith (80%), Colton Brooks (20%)
 * @version 1.0
 */
public class Piece extends BlackAndWhite implements PieceIF{

    /** The type of the piece. */
    private ChessPieceType type;

    /** The movement type of the piece. */
    private MovementIF moveType;

    /**
     * Creates a new piece of the specified type.
     *
     * @param type  the type of the piece.
     * @param color the color of the piece.
     */
    public Piece(ChessPieceType type, GameColor color) {
        super(color);
        this.type = type;
        moveTypeFactory();
    }

    /**
     * Defines the piece typing based on the type of the piece.
     */
    private void moveTypeFactory() {
        switch (type) {
            case King -> moveType = new KingMovement(getColor());
            case Pawn -> moveType = new PawnMovement(getColor());
            case Rook -> moveType = new RookMovement(getColor());
            case Queen -> moveType = new QueenMovement(getColor());
            case Bishop -> moveType = new BishopMovement(getColor());
            case Knight -> moveType = new KnightMovement(getColor());
        }
    }

    /**
     * Gets the type of the piece.
     *
     * @return the type of the piece.
     */
    @Override
    public ChessPieceType getType() {
        return type;
    }

    /**
     * Sets the type of the piece.
     *
     * @param type the type of the piece.
     */
    @Override
    public void setType(ChessPieceType type) {
        this.type = type;
    }

    /**
     * Method that gets the valid moves for the piece.
     *
     * @param board           The game board that the piece moves on.
     * @param currentPosition The current position of the piece.
     * @return a list of valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        return this.moveType.getValidMoves(board, currentPosition);
    }

    /**
     * Gets the position of the piece on the board.
     * @param board The board that the piece is on.
     * @return the position of the piece on the board.
     */
    public Position getPosition(BoardIF board){
        Position position = null;
        SquareIF[][] squares = board.getSquares();
        PieceIF temp;
        for(int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                temp = squares[i][j].getPiece();
                if (temp == this) {
                    Square square = (Square) squares[i][j];
                    position = square.getPosition();
                }
            }
        }
        return position;
    }

    /**
     * Gets the movement type of the piece.
     *
     * @return the movement type of the piece.
     */
    public MovementIF getMoveType() {
        return moveType;
    }
}
