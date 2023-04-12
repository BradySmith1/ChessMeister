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
import model.Piece;
import model.Position;
import model.Square;

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
        validMoves.add(moveCheck(board, currentPosition,1, 2));
        validMoves.add(moveCheck(board, currentPosition, 2, 1));
        validMoves.add(moveCheck(board, currentPosition, -1, 2));
        validMoves.add(moveCheck(board, currentPosition, -2, 1));
        validMoves.add(moveCheck(board, currentPosition, 1, -2));
        validMoves.add(moveCheck(board, currentPosition, 2, -1));
        validMoves.add(moveCheck(board, currentPosition, -1, -2));
        validMoves.add(moveCheck(board, currentPosition, -2, -1));

        // Remove the instances of null from our list of valid moves
        validMoves.removeAll(Collections.singleton(null));

        return validMoves; // Return the set of valid moves
    }

    /**
     * Function to check if a specified move is possible for a knight.
     *
     * @param board board the piece is on
     * @param currentPosition current position of the piece
     * @param rank  difference in rank for the piece to be changed
     * @param file  difference in file for the piece to be changed
     * @return position of a move if it's deemed valid
     */
    private Position moveCheck(BoardIF board, Position currentPosition, int rank, int file) {

        Position movePossible = null; // move starts are nonexistent

        int currentRank = currentPosition.getRank().getIndex(); // current rank of piece
        int currentFile = currentPosition.getFile().getFileNum(); //current file of piece

        //check for knight move up 1, right 2
        if (currentRank + rank < board.getHeight() && currentFile + file < board.getWidth() &&
            currentRank + rank >= 0 && currentFile + file >= 0) {
            //get the square of the move and the piece in the square
            Square currentSquare = (Square) board.getSquares()[currentRank + rank]
                                                              [currentFile + file];
            Piece currentPiece = (Piece) currentSquare.getPiece();
            //check if there is an empty square or an enemy piece
            if (currentPiece == null || !currentPiece.getColor().equals(getColor())) {
                //the move is possible so add it
                movePossible = currentSquare.getPosition();
            }
        }
        return movePossible; // return the result of if a move is possible
    }
}