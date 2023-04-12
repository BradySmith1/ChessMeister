/**
 * This class represents the movement of a Queen.
 *
 * @authors Kaushal Patel (65%), Zach Eanes (20%), Colton Brooks (15%)
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


public class QueenMovement extends BlackAndWhite implements MovementIF {// The color of the piece.

    /**
     * Constructor method for the QueenMovement class.
     *
     * @param color the color of the piece.
     */
    public QueenMovement(GameColor color) {
        super(color);
    }

    /**
     * Function that gets the valid moves for the Queen.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();

        // Check all 8 possible move directions for a queen
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 1, 0));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, -1, 0));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 0, 1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 0, -1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 1, 1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, 1, -1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, -1, 1));
        validMoves.addAll(getDirectionalMoves(board, currentPosition, -1, -1));

        validMoves.removeAll(Collections.singleton(null)); // remove all null values

        return validMoves;  // Return the list of valid moves.
    }

    /**
     * This method also inherited by bishop and rook allows for continuous directional movement
     * @param board The board the piece is on
     * @param currentPosition   The current position of the piece
     * @param rank  The *direction* the piece is moving in for the rank
     * @param file  The *direction* the piece is moving in for the file
     * @return An ArrayList of the valid moves in the direction specified by the rank and file
     */
    List<Position> getDirectionalMoves(BoardIF board, Position currentPosition, int rank, int file){
        List<Position> validDirectional = new ArrayList<>();

        boolean movementStop = false;
        Position move;
        while (!movementStop) {
            move = moveCheck(board, currentPosition, rank, file, false);
            if(move == null) {
                movementStop = true;
                move = moveCheck(board, currentPosition, rank, file, true);
            }
            validDirectional.add(move);
            if(rank > 0) {
                rank++;
            }
            else if(rank < 0){
                rank--;
            }
            if(file > 0) {
                file++;
            }
            else if(file < 0){
                file--;
            }
        }
        return validDirectional; // return valid moves
    }
}