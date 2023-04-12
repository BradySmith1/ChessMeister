package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import model.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to define the movements of a rook in a game of chess. This class does so
 * by looking through and finding all possible moves above, below, left, and right
 * of a given piece.
 *
 * @author Zach Eanes (85%), Colton Brooks (15%)
 * @version 1.0
 */
public class RookMovement extends QueenMovement implements MovementIF{

    /* Boolean to check if the rook has moved; needed for castling implementation */
    private boolean isFirstMove;
    /**
     * Constructor method for the RookMovement Class
     *
     * @param color the color of the piece
     */
    public RookMovement(GameColor color) {
        super(color);
        this.isFirstMove = true;
    }

    /**
     * Gets the valid moves for the piece
     *
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();

        // check all 4 possible move directions for a rook
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 1, 0));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, -1, 0));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 0, 1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 0, -1));

        validMoves.removeAll(Collections.singleton(null)); // remove all null values

        return validMoves; // Return the valid moves.
    }

    /**
     * Method to be called whenever a rook makes its first move, changes isFirstMove to false
     * to show a move has been made. Used for castling implementation.
     */
    public void setFirstMove() {this.isFirstMove = false;}

    /**
     * Method to be called to check if the rook has moved
     *
     * @return true if the rook has not moved, false if it has
     */
    public boolean getFirstMove(){return this.isFirstMove;}
}