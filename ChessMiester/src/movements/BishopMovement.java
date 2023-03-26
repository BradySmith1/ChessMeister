/**
 * Class to define the movements of a bishop in a game of chess. This class does so
 * by finding the valid moves for a bishop on a chess board in all diagonal directions.
 *
 * @author Zach Eanes (100%)
 * @version 1.0
 */
package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import interfaces.PieceIF;
import model.Position;

import java.util.List;

public class BishopMovement implements MovementIF{

    private Position currentPosition; // The current position of the piece

    private GameColor color; // The color of the piece

    /**
     * Constructor method for the BishopMovement class
     *
     * @param currentPosition The current position of the piece
     * @param color The color of the piece
     */
    public BishopMovement(Position currentPosition, GameColor color) {
        this.currentPosition = currentPosition;
        this.color = color;
    }

    /**
     * Moves the piece to the specified position.
     *
     * @param board The board the piece is on
     * @param movePosition The position to move the piece toa
     */
    @Override
    public boolean move(BoardIF board, Position movePosition) {
        List<Position> validMoves = getValidMoves(board); // Gets the valid moves for the piece

        boolean moveSuccessful = false; // Whether the move was successful or not

        // Checks if the move is valid
        if(validMoves.contains(movePosition)){
            board.getSquares()[movePosition.getRank().getIndex()]
                              [movePosition.getFile().getFileNum()].setPiece((PieceIF) this);

            this.setPosition(movePosition); // Sets the position of the piece to the new position

            moveSuccessful = true; // Sets the move to successful
        }
        return moveSuccessful;
    }

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
