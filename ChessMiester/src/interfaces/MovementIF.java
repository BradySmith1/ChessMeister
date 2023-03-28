/**
 * This interface represents a piece that can move.
 *
 * @author Kaushal Patel (100%)
 * @version 1.0
 */
package interfaces;

import model.Position;
import java.util.List;

public interface MovementIF {
    /**
     * Returns a list of MovePositions that are valid & legal on the board.
     *
     * @param board The game board that the piece moves on.
     * @return A list of valid MovePositions.
     */
    List<Position> getValidMoves(BoardIF board, Position currentPosition);

}
