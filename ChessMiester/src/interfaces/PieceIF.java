package interfaces;

import enums.ChessPieceType;
import model.Position;
import java.util.List;

/**
 * This interface represents a chess piece.
 *
 * @author Zach Eanes (100%) TODO
 */
public interface PieceIF {

    /**
     * Gets the type of the piece.
     * @return the type of the piece.
     */
    public ChessPieceType getType();

    /**
     * Sets the type of the piece.
     * @param type the type of the piece.
     */
    public void setType(ChessPieceType type);

    /**
     * Gets the color of the piece. Either "white" or "black".
     * @return The color of the piece.
     */
    public String getColor();

    /**
     * Returns a list of MovePositions that are valid & legal on the board.
     * @param board The game board that the piece moves on.
     * @return A list of valid MovePositions.
     */
    public List<Position> getValidMoves(BoardIF board);

    /**
     * Moves the piece to the selected movePosition if it is a valid/legal move.
     * @param board The game board that the piece moves on.
     * @param movePosition The Rank and File position of the move to be made.
     * @return True if the move is valid and was completed, false otherwise.
     */
    public boolean move(BoardIF board, Position movePosition);

    /**
     * Gets the piece's location on the board.
     * @return The rank and file of the piece's position.
     */
    public Position getLocation();

    /**
     * Gets the status of the piece in the sense of if it is captured or not.
     * @return True if the piece has been captured, false otherwise.
     */
    public boolean isCaptured();
}
