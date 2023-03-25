package movements;

import interfaces.BoardIF;
import interfaces.MovementIF;
import model.Position;

import java.util.List;

public class KingMovement implements MovementIF{

    /**
    * Gets the valid moves for the piece.
    *
    * @param board
    * @return the valid moves for the piece.
    */
    @Override
    public List<Position> getValidMoves(BoardIF board) {
        return null;
    }

    /**
    * Moves the piece to the specified position.
    *
    * @param board        the board the piece is on.
    * @param movePosition the position to move the piece to.
    * @return true if the move was successful.
    */
    @Override
    public boolean move(BoardIF board, Position movePosition) {
        return false;
    }
}
