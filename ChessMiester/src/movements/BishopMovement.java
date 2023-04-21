package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import model.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to define the movements of a bishop in a game of chess. This class does so
 * by finding the valid moves for a bishop on a chess board in all diagonal directions.
 *
 * @author Colton Brooks (100%)
 * @version 2.0
 */
public class BishopMovement extends QueenMovement implements MovementIF{

    /**
     * Constructor method for the BishopMovement class
     *
     * @param color The color of the piece
     */
    public BishopMovement(GameColor color) {
        super(color);
    }

    /**
     * Gets the valid moves for the piece
     *
     * @param board the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>(); // The list of valid moves

        // check all 4 possible move directions for a bishop
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 1, 1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 1, -1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, -1, 1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, -1, -1));

        validMoves.removeAll(Collections.singleton(null)); // remove all null values
        return validMoves;
    }
}
