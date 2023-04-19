/**
 * Class to define the movements of a king in a game of chess. This class does so
 * by finding the valid moves for a king on a chess board in all diagonal directions.
 *
 * @author Colton Brooks (100%)
 * @version 1.0
 */
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
 * @version 1.0
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

    /*
     * Rules for castling:
     * 1) King cannot have moved beforehand
     * 2) Rook cannot have moved beforehand
     * 3) King cannot be in check or pass through check
     * 4) No piece between king and rook
     */

    /**
     * Method to be called whenever king makes its first move, sets isFirstMove false
     * to show a move with this piece has been made.
     */
    public void setFirstMove(boolean isFirstMove){ this.isFirstMove = isFirstMove; }

    /**
     * Method to be called to check if the king has moved
     *
     * @return true if the king has not moved, false if it has
     */
    public boolean getFirstMove(){ return this.isFirstMove; }

    /*
     * This is the represent the possible logic that would be needed to implement castling.
     * I wanted to wait and see where to place it, but this method is essentially it.
     *
     * Outside of this function, if statement in game logic to see if the pieces
     * as "from" == king and "to" == rook, then call this method if true
     */
    /**
     * Method to check if castling is possible
     *
     * @param fromF the file the king is moving from
     * @param fromR the rank the king is moving from
     * @param toF the file the king is moving to
     * @param toR the rank the king is moving to
     * @return true if castling is possible, false if not
     */
/*
    public boolean canCastle(Files fromF, Rank fromR, Files toF, Ranks toR){
        // grab the king and rook piece from positions to save keystrokes
        KingMovement king = (KingMovement) board.getPiece(fromF, fromR);
        RookMovement rook = (RookMovement) board.getPiece(toF, toR);

        //boolean to return at the end
        boolean canCastle = true;

        while(canCastle){
            // if either rook or king have moved, castling cannot occur
            if(!king.getFirstMove() || !rook.getFirstMove()){
                canCastle = false;
            }

            // if king is in check or moves into check, castling cannot occur
            if(check(new Position(fromF, fromR)) || check(new Position(toF, toR))
                canCastle = false;
            }

            // if king passes through check or a piece is there, castling cannot occur
            // check if king is moving to the right
            if(fromF < toF){
                while(fromF != toF){
                    //check if king is ever put into check
                    if(check(new Position(fromF, fromR))){
                        canCastle = false;
                    }
                    //check if there is a piece in the way
                    if(board.getPiece(fromF, fromR) != null){
                        canCastle = false;
                    }
                    fromF++;
                }
            }
            // check if king is moving to the left
            else{
                while(fromF != toF){
                    //check if king is ever put into check
                    if(check(new Position(fromF, fromR))){
                        canCastle = false;
                    }
                    //check if there is a piece in the way
                    if(board.getPiece(fromF, fromR) != null){
                        canCastle = false;
                    }
                    fromF--;
                }
            }
        return canCastle; //true if castling is possible, false if not
        }
*/

}