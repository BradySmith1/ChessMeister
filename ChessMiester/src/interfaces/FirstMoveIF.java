package interfaces;

/**
 * This interface is used to help represent whenever a piece makes a first move.
 * This interface is represented in pawn, rook, and king.
 *
 * @author Zach Eanes (100%)
 */
public interface FirstMoveIF{
    /**
     * Method to be called to check if the rook has moved
     *
     * @return true if the piece has not moved, false if it has
     */
    boolean getFirstMove();

    /**
     * Method to be called whenever a rook makes its first move, changes isFirstMove to false
     * to show a move has been made. Used for castling implementation.
     */
     void setFirstMove(boolean isFirstMove);

}
