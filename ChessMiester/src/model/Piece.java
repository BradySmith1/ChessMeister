package model;

import enums.ChessPieceType;
import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import interfaces.PieceIF;
import movements.*;

import java.util.List;

/**
 * This class represents a chess piece.
 * @author Brady Smith 100%
 */
public class Piece extends BlackAndWhite implements PieceIF{

    /**
     * The type of the piece.
     */
    private ChessPieceType type;


    private MovementIF moveType;

    /**
     * Creates a new piece of the specified type.
     * @param type the type of the piece.
     */
    public Piece(ChessPieceType type, GameColor color) {
        super(color);
        this.type = type;
        moveTypeFactory();
    }

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

    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        return this.moveType.getValidMoves(board, currentPosition);
    }

    @Override
    public boolean move(BoardIF board, Position movePosition) {
        return false;
    }

    @Override
    public boolean isCaptured() {
        return false;
    }
}
