/**
 * This interface represents a chess piece.
 *
 * @authors Brady Smith (75%), Zach Eanes (25%)
 */
package interfaces;

import enums.ChessPieceType;
import model.Position;
import java.util.List;

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

    /**
     * Returns a list of MovePositions that are valid & legal on the board.
     * @param board The game board that the piece moves on.
     * @return A list of valid MovePositions.
     */
    public List<Position> getValidMoves(BoardIF board, Position currentPosition);

    /**
     * Gets the status of the piece in the sense of if it is captured or not.
     * @return True if the piece has been captured, false otherwise.
     */
    public boolean isCaptured();
}
