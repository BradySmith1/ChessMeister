package interfaces;

import model.Position;
import java.util.List;

/**
 * This interface represents a piece that can move.
 * @author Kaushal Patel (100%)
 * @version 1.0
 */
public interface MovementIF {
    /**
     * Gets the valid moves for the piece.
     * @return the valid moves for the piece.
     */
    List<Position> getValidMoves(BoardIF board);

    /**
     * Moves the piece to the specified position.
     * @param board the board the piece is on.
     * @param movePosition the position to move the piece to.
     * @return true if the move was successful.
     */
    boolean move(BoardIF board, Position movePosition);
}
