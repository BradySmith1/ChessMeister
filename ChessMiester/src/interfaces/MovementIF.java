/**
 * This interface represents a piece that can move.
 *
 * @author Kaushal Patel (100%)
 * @version 1.0
 */
package interfaces;

import model.Piece;
import model.Position;
import model.Square;

import java.util.List;

public interface MovementIF extends BlackAndWhiteIF{
    /**
     * Returns a list of MovePositions that are valid & legal on the board.
     *
     * @param board The game board that the piece moves on.
     * @return A list of valid MovePositions.
     */
    List<Position> getValidMoves(BoardIF board, Position currentPosition);

    /**
     * Checks to see if the movement to the square is valid.
     *
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @param rank            the rank to move to
     * @param file            the file to move to
     * @return the position of the move if it is valid, null otherwise
     */
    default Position moveCheck(BoardIF board, Position currentPosition, int rank, int file, boolean capture) {

        Position movePossible = null; // move starts are nonexistent

        int currentRank = currentPosition.getRank().getIndex(); // current rank of piece
        int currentFile = currentPosition.getFile().getFileNum(); //current file of piece

        //check for the specified movement direction
        if (currentRank + rank < board.getHeight() && currentFile + file < board.getWidth() &&
                currentRank + rank >= 0 && currentFile + file >= 0){
            //get the square of the move and the piece in the square
            Square currentSquare = (Square) board.getSquares()[currentRank + rank]
                    [currentFile + file];
            Piece currentPiece = (Piece) currentSquare.getPiece();
            //check if there is an empty square or an enemy piece1
            if (currentPiece == null || capture && !currentPiece.getColor().equals(getColor())) {
                //the move is possible so add it
                movePossible = currentSquare.getPosition();
            }
        }
        return movePossible; // return the result of if a move is possible
    }
}
