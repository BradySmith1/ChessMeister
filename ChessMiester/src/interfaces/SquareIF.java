package interfaces;

/**
 * An interface for a square on a chess board. Each square can be cleared,
 * and can hold a piece or be empty.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public interface SquareIF {

    /**
     * Clears the square by removing any piece that may be on it.
     */
    public void clear();

    /**
     * Sets a piece on the square.
     *
     * @param piece The piece to set on the square.
     * @return The piece that was previously on the square, if any.
     */
    public PieceIF setPiece(PieceIF piece);

    /**
     * Returns the piece that is currently on the square, if any.
     *
     * @return The piece on the square, or null if the square is empty.
     */
    public PieceIF getPiece();
}
