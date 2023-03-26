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
import model.Piece;
import model.Position;
import model.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BishopMovement implements MovementIF{

    /* The current position of the piece */
    private Position currentPosition;

    /* The color of the piece */
    private GameColor color;

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
    * Gets the valid moves for the piece
    * @param board the board the piece is on
    * @return the valid moves for the piece
    */
    @Override
    public List<Position> getValidMoves(BoardIF board) {
        List<Position> validMoves = new ArrayList<>(); // The list of valid moves

        // Checks the diagonal up and to the right
        List<Position> validMovesUpRight = getValidMovesUpRight(board);

        // Checks the diagonal up and to the left
        List<Position> validMovesUpLeft = getValidMovesUpLeft(board);

        // Checks the diagonal down and to the right
        List<Position> validMovesDownRight = getValidMovesDownRight(board);

        // Checks the diagonal down and to the left
        List<Position> validMovesDownLeft = getValidMovesDownLeft(board);

        // Adds all the valid moves to the list of valid moves
        validMoves.addAll(validMovesUpRight);
        validMoves.addAll(validMovesUpLeft);
        validMoves.addAll(validMovesDownRight);
        validMoves.addAll(validMovesDownLeft);

        return validMoves;
    }

    /**
     * Gets the valid moves for the piece in the diagonal up and to the right
     * @param board the board the piece is on
     * @return the valid moves for the piece in the diagonal up and to the right
     */
    private List<Position> getValidMovesUpRight(BoardIF board){
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
            else if(!currPiece.getColor().equals(this.color)){ // if the piece is an enemy piece
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
     * @param board the board the piece is on
     * @return the valid moves for the piece in the diagonal up and to the left
     */
    private List<Position> getValidMovesUpLeft(BoardIF board) {
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
            } else if (!currPiece.getColor().equals(this.color)) { // if the piece is an enemy piece
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
     * @param board the board the piece is on
     * @return the valid moves for the piece in the diagonal down and to the right
     */
    private List<Position> getValidMovesDownRight(BoardIF board){
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
            } else if (!currPiece.getColor().equals(this.color)) { // if the piece is an enemy piece
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
     * @param board the board the piece is on
     * @return the valid moves for the piece in the diagonal down and to the left
     */
    private List<Position> getValidMovesDownLeft(BoardIF board){
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
            else if(!currPiece.getColor().equals(this.color)){ // if the piece is an enemy piece
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

    /**
     * Getter method for the position of the piece
     * @return the position of the piece
     */
    public Position getPosition() {return currentPosition;}

    /**
     * Setter method for the position of the piece
     * @param position the position to set the piece to
     */
    public void setPosition(Position position) {currentPosition = position;}

    /**
     * Getter method for the color of the piece
     * @return the color of the piece
     */
    public GameColor getColor() {return color;}

    /**
     * Setter method for the color of the piece
     * @param color the color to set the piece to
     */
    public void setColor(GameColor color) {this.color = color;}
}
