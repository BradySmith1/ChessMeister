/**
 * Class to define the movements of a knight in a game of chess. This class does so
 * by finding the valid moves for a knight on a chess board in all diagonal directions.
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

public class KnightMovement extends BlackAndWhite implements MovementIF {

    /**
     * Constructor method for the KnightMovement Class
     *
     * @param color the color of the piece
     */
    public KnightMovement(GameColor color) {
        super(color);
    }

    /**
     * Gets the valid moves for the piece.
     *
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        // Set of valid moves to be found for a knight
        List<Position> validMoves = new ArrayList<>();

        // Check all 8 possible moves from a knight
        validMoves.add(moveCheck(board, currentPosition,1, 2, true));
        validMoves.add(moveCheck(board, currentPosition, 2, 1, true));
        validMoves.add(moveCheck(board, currentPosition, -1, 2, true));
        validMoves.add(moveCheck(board, currentPosition, -2, 1, true));
        validMoves.add(moveCheck(board, currentPosition, 1, -2, true));
        validMoves.add(moveCheck(board, currentPosition, 2, -1, true));
        validMoves.add(moveCheck(board, currentPosition, -1, -2, true));
        validMoves.add(moveCheck(board, currentPosition, -2, -1, true));

        // Remove the instances of null from our list of valid moves
        validMoves.removeAll(Collections.singleton(null));

        return validMoves; // Return the set of valid moves
    }
}