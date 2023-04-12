/**
 * Class to define the movements of a king in a game of chess. This class does so
 * by finding the valid moves for a king on a chess board in all diagonal directions.
 *
 * @author Colton Brooks (100%)
 * @version 1.0
 */
package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import model.BlackAndWhite;
import model.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KingMovement extends BlackAndWhite implements MovementIF{

    /**
     * Constructor method for the KingMovement Class
     *
     * @param color the color of the piece
     */
    public KingMovement(GameColor color) {
        super(color);
    }

    /**
     * Gets the valid moves for the piece.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        // Set of valid moves to be found for a king
        List<Position> validMoves = new ArrayList<>();


        // Check all 8 possible moves for a king
        validMoves.add(moveCheck(board, currentPosition, 1, 0, true));
        validMoves.add(moveCheck(board, currentPosition, 1, 1, true));
        validMoves.add(moveCheck(board, currentPosition, 0, 1, true));
        validMoves.add(moveCheck(board, currentPosition, -1, 1, true));
        validMoves.add(moveCheck(board, currentPosition, -1, 0, true));
        validMoves.add(moveCheck(board, currentPosition, -1, -1, true));
        validMoves.add(moveCheck(board, currentPosition, 0, -1, true));
        validMoves.add(moveCheck(board, currentPosition, 1, -1, true));

        validMoves.removeAll(Collections.singleton(null)); // remove all null values
        return validMoves;
    }
}