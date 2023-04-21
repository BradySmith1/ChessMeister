/**
 * Class to define the movements of a bishop in a game of chess. This class does so
 * by finding the valid moves for a bishop on a chess board in all diagonal directions.
 *
 * @author Colton Brooks (100%)
 * @version 2.0
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
import java.util.List;

public class BishopMovement extends BlackAndWhite implements MovementIF{

    /**
     * Constructor method for the BishopMovement class
     *
     * @param color The color of the piece
     */
    public BishopMovement(GameColor color) {
        super(color);
    }

    /**
     * Gets the valid moves for the piece
     *
     * @param board the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>(); // The list of valid moves

        // Checks the diagonal up and to the right
        List<Position> validMovesUpRight = getValidMovesUpRight(board, currentPosition);

        // Checks the diagonal up and to the left
        List<Position> validMovesUpLeft = getValidMovesUpLeft(board, currentPosition);

        // Checks the diagonal down and to the right
        List<Position> validMovesDownRight = getValidMovesDownRight(board, currentPosition);

        // Checks the diagonal down and to the left
        List<Position> validMovesDownLeft = getValidMovesDownLeft(board, currentPosition);

        // Adds all the valid moves to the list of valid moves
        validMoves.addAll(validMovesUpRight);
        validMoves.addAll(validMovesUpLeft);
        validMoves.addAll(validMovesDownRight);
        validMoves.addAll(validMovesDownLeft);

        return validMoves;
    }

    /**
     * Gets the valid moves for the piece in the diagonal up and to the right
     *
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece in the diagonal up and to the right
     */
    private List<Position> getValidMovesUpRight(BoardIF board, Position currentPosition){
        // Valid moves for bishop to move up and to the right
        List<Position> validMovesUpRight = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex() + 1; // current rank of the piece
        int currentFile = currentPosition.getFile().getFileNum() + 1; // current file of the piece

        boolean pieceHit = false; // Whether a piece has been hit or not

        while(!pieceHit && currentRank < board.getHeight() && currentFile < board.getWidth()) {
            // get current square on board
            Square currSquare = (Square) board.getSquares()[currentRank][currentFile];

            Piece currPiece = (Piece) currSquare.getPiece(); // get piece on current square

            if(currSquare.getPiece() == null){ // if the square is empty
                validMovesUpRight.add(currSquare.getPosition());
            }
            else if(!currPiece.getColor().equals(getColor())){ // if the piece is an enemy piece
                validMovesUpRight.add(currSquare.getPosition());
                pieceHit = true; // set pieceHit to true
            }
            else{ // if the piece is a friendly piece
                pieceHit = true; // set pieceHit to true
            }
            currentRank++; // increment rank
            currentFile++; // increment file
        }
        return validMovesUpRight; // return valid moves
    }

    /**
     * Gets the valid moves for the piece in the diagonal up and to the left
     *
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece in the diagonal up and to the left
     */
    private List<Position> getValidMovesUpLeft(BoardIF board, Position currentPosition) {
        // Valid moves for bishop to move up and to the left
        List<Position> validMovesUpLeft = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex() + 1; // current rank of the piece
        int currentFile = currentPosition.getFile().getFileNum() - 1; // current file of the piece

        boolean pieceHit = false; // Whether a piece has been hit or not

        while (!pieceHit && currentRank < board.getHeight() && currentFile >= 0) {
            // get current square on board
            Square currSquare = (Square) board.getSquares()[currentRank][currentFile];

            Piece currPiece = (Piece) currSquare.getPiece(); // get piece on current square

            if (currSquare.getPiece() == null) { // if the square is empty
                validMovesUpLeft.add(currSquare.getPosition());
            } else if (!currPiece.getColor().equals(getColor())) {
                validMovesUpLeft.add(currSquare.getPosition());
                pieceHit = true; // set pieceHit to true
            } else { // if the piece is a friendly piece
                pieceHit = true; // set pieceHit to true
            }
            currentRank++; // increment rank
            currentFile--; // decrement file
        }
        return validMovesUpLeft; // return valid moves
    }

    /**
     * Gets the valid moves for the piece in the diagonal down and to the right
     *
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece in the diagonal down and to the right
     */
    private List<Position> getValidMovesDownRight(BoardIF board, Position currentPosition){
        // Valid moves for bishop to move down and to the right
        List<Position> validMovesDownRight = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex() - 1; // current rank of the piece
        int currentFile = currentPosition.getFile().getFileNum() + 1; // current file of the piece

        boolean pieceHit = false; // Whether a piece has been hit or not

        while (!pieceHit && currentRank >= 0 && currentFile < board.getWidth()) {
            // get current square on board
            Square currSquare = (Square) board.getSquares()[currentRank][currentFile];

            Piece currPiece = (Piece) currSquare.getPiece(); // get piece on current square

            if (currSquare.getPiece() == null) { // if the square is empty
                validMovesDownRight.add(currSquare.getPosition());
            } else if (!currPiece.getColor().equals(getColor())) { // if the piece is enemy piece
                validMovesDownRight.add(currSquare.getPosition());
                pieceHit = true; // set pieceHit to true
            } else { // if the piece is a friendly piece
                pieceHit = true; // set pieceHit to true
            }
            currentRank--; // decrement rank
            currentFile++; // increment file
        }
        return validMovesDownRight; // return valid moves
    }

    /**
     * Gets the valid moves for the piece in the diagonal down and to the left
     *
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece in the diagonal down and to the left
     */
    private List<Position> getValidMovesDownLeft(BoardIF board, Position currentPosition){
        // valid moves for the bishop to move down and to the left
        List<Position> validMovesDownLeft = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex() - 1; // current rank of piece
        int currentFile = currentPosition.getFile().getFileNum() - 1; // current file of piece

        boolean pieceHit = false; // whether a piece has been hit or not

        while(!pieceHit && currentRank >= 0 && currentFile >= 0){
            // get current square on board
            Square currSquare = (Square) board.getSquares()[currentRank][currentFile];

            Piece currPiece = (Piece) currSquare.getPiece(); // get piece on current square

            if(currSquare.getPiece() == null){ // if the square is empty
                validMovesDownLeft.add(currSquare.getPosition());
            }
            else if(!currPiece.getColor().equals(getColor())){ // if the piece is an enemy piece
                validMovesDownLeft.add(currSquare.getPosition());
                pieceHit = true; // set pieceHit to true
            }
            else{ // if the piece is a friendly piece
                pieceHit = true; // set pieceHit to true
            }
            currentRank--; // decrement rank
            currentFile--; // decrement file
        }
        return validMovesDownLeft; // return valid moves
    }
}
