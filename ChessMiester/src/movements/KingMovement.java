package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.FirstMoveIF;
import interfaces.MovementIF;
import model.BlackAndWhite;
import model.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to define the movements of a king in a game of chess. This class does so
 * by finding the valid moves for a king on a chess board in all diagonal directions.
 *
 * @author Colton Brooks (80%), Zach Eanes (20%)
 * @version 2.0
 */
public class KingMovement extends BlackAndWhite implements MovementIF, FirstMoveIF {

    /* Boolean to check if the king has moved; needed for castling implementation */
    private boolean isFirstMove;

    /**
     * Constructor method for the KingMovement Class
     *
     * @param color the color of the piece
     */
    public KingMovement(GameColor color) {
        super(color);
        this.isFirstMove = true;
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

    /**
     * Method to be called whenever king makes its first move, sets isFirstMove false
     * to show a move with this piece has been made.
     *
     * @param isFirstMove boolean to set the first move of the king
     */
    public void setFirstMove(boolean isFirstMove){ this.isFirstMove = isFirstMove; }

    /**
     * Method to be called to check if the king has moved
     *
     * @return true if the king has not moved, false if it has
     */
    public boolean getFirstMove(){ return this.isFirstMove; }



}