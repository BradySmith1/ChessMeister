package interfaces;

import enums.ChessPieceType;
import model.Position;
import java.util.List;

/**
 * This interface represents a chess piece.
 *
 * @author Brady Smith (75%), Zach Eanes (25%)
 * @version 1.0
 */
public interface PieceIF extends BlackAndWhiteIF {

    /**
     * Gets the type of the piece.
     * @return the type of the piece.
     */
    ChessPieceType getType();

    /**
     * Sets the type of the piece.
     * @param type the type of the piece.
     */
    void setType(ChessPieceType type);

    /**
     * Returns a list of MovePositions that are valid & legal on the board.
     * @param board The game board that the piece moves on.
     * @return A list of valid MovePositions.
     */
    List<Position> getValidMoves(BoardIF board, Position currentPosition);

    /**
     * Gets the position of the piece.
     * @param board the board the piece is on.
     * @return the position of the piece.
     */
    Position getPosition(BoardIF board);
}
