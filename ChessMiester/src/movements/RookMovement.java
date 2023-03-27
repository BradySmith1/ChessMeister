/**
 * Class to define the movements of a rook in a game of chess. This class does so
 * by looking through and finding all possible moves above, below, left, and right
 * of a given piece.
 *
 * @author Zach Eanes 100%
 * @version 1.0
 */

package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import model.Piece;
import model.Position;
import model.Square;

import java.util.ArrayList;
import java.util.List;

public class RookMovement implements MovementIF{

    /* The color of the piece */
    private GameColor color;

    private int direction;

    /**
     * Constructor method for the RookMovement Class
     *
     * @param color the color of the piece
     */
    public RookMovement(GameColor color) {
        this.color = color;
        this.direction = color == GameColor.WHITE ? 1 : -1;
    }

    /**
     * Gets the valid moves for the piece
     * @param board the board the piece is on
     * @return the valid moves for the piece
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();

        // Get the vertical movements for the rook.
        List<Position> validVertical = getVerticalMoves(board, currentPosition);

        // Get the horizontal movements for the rook.
        List<Position> validHorizontal = getHorizontalMoves(board, currentPosition);

        // Add the vertical and horizontal moves to the valid moves list.
        validMoves.addAll(validVertical);
        validMoves.addAll(validHorizontal);

        return validMoves; // Return the valid moves.
    }

    /**
     * Gets the vertical moves for the rook from the current position
     *
     * @param board the board the piece is on
     * @return the valid vertical moves for the rook
     */
    private List<Position> getVerticalMoves(BoardIF board, Position currentPosition){
        List<Position> validVertical = new ArrayList<>();

        List<Position> validUp = getVerticalMovesAbove(board, currentPosition); // Get valid moves above piece
        List<Position> validDown = getVerticalMovesBelow(board, currentPosition); // Get valid moves below piece

        validVertical.addAll(validUp); // Add the valid moves above to the valid moves list
        validVertical.addAll(validDown);

        return validVertical;
    }

    /**
     * Gets the horizontal moves for the rook from the current position
     *
     * @param board the board the piece is on
     * @return the valid horizontal moves for the rook
     */
    private List<Position> getHorizontalMoves(BoardIF board, Position currentPosition){
        List<Position> validHorizontal = new ArrayList<>();

        List<Position> validLeft = getHorizontalMovesLeft(board, currentPosition); // Get valid moves left of piece
        List<Position> validRight = getHorizontalMovesRight(board, currentPosition); // Get valid moves right of piece

        validHorizontal.addAll(validLeft); // Add the valid moves left to the valid moves list
        validHorizontal.addAll(validRight);

        return validHorizontal;
    }

    /**
     * Gets the valid moves below the rook
     * @param board board the piece is on
     * @return valid set of moves below the rook
     */
    private List<Position> getVerticalMovesBelow(BoardIF board, Position currentPosition){
        List<Position> validUp = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex() + 1; // Get the current rank of the piece
        int currentFile = currentPosition.getFile().getFileNum(); // Get the current file of the piece

        boolean pieceHit = false; // Boolean to determine if a piece has been hit

        // Keep looping until the end of the board is reached or a piece is hit
        while(!pieceHit && currentRank < board.getHeight()){
            // Get the current square
            Square currSquare = (Square) board.getSquares()[currentRank][currentFile];

            Piece currPiece = (Piece) currSquare.getPiece(); // Get the piece on the square
            if(currSquare.getPiece() == null){ // Square is empty
                validUp.add(currSquare.getPosition());
            }
            else if(!currPiece.getColor().equals(this.color)){ // Square has an enemy piece
                validUp.add(currSquare.getPosition());
                pieceHit = true; // we hit a piece, so we can't move any further
            }
            else{
                pieceHit = true;
            }
            currentRank++; // Increment the rank of the piece
        }
        return validUp;
    }

    /**
     * Gets the valid moves above the rook
     * @param board board the piece is on
     * @return valid set of moves above the rook
     */
    private List<Position> getVerticalMovesAbove(BoardIF board, Position currentPosition){
        List<Position> validDown = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex() - 1; // Get current rank of piece
        int currentFile = currentPosition.getFile().getFileNum(); // Get current file of piece

        boolean pieceHit = false; // Boolean to determine if a piece has been hit

        // Keep looping until the end of the board is reached or a piece is hit
        while(!pieceHit && currentRank >= 0){
            // Get the current square
            Square currSquare = (Square) board.getSquares()[currentRank][currentFile];

            Piece currPiece = (Piece) currSquare.getPiece(); // Get the piece on the square
            if(currSquare.getPiece() == null){ // Square is empty
                validDown.add(currSquare.getPosition());
            }
            else if(!currPiece.getColor().equals(this.color)){ // Square has an enemy piece
                validDown.add(currSquare.getPosition());
                pieceHit = true; // we hit a piece, so we can't move any further
            }
            else{
                pieceHit = true;
            }

            currentRank--;
        }
        return validDown;
    }

    /**
     * Gets the valid moves left of the rook
     * @param board board the piece is on
     * @return valid set of moves left of the rook
     */
    private List<Position> getHorizontalMovesLeft(BoardIF board, Position currentPosition) {
        List<Position> validLeft = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex(); // Get current rank of piece
        int currentFile = currentPosition.getFile().getFileNum() - 1; // Get current file of piece

        boolean pieceHit = false; // Boolean to determine if a piece has been hit

        // Keep looping until the end of the board is reached or a piece is hit
        while(!pieceHit && currentFile >= 0){
            // get the current square
            Square currSquare = (Square) board.getSquares()[currentRank][currentFile];

            Piece currPiece = (Piece) currSquare.getPiece(); // Get the piece on the square

            if(currSquare.getPiece() == null){ // Square is empty
                validLeft.add(currSquare.getPosition());
            }
            else if(!currPiece.getColor().equals(this.color)){ // Square has an enemy piece
                validLeft.add(currSquare.getPosition());
                pieceHit = true; // we hit a piece, so we can't move any further
            }
            else{
                pieceHit = true;
            }

            currentFile--; // Decrement the file of the piece
        }
        return validLeft; // return the valid moves
    }

    /**
     * Gets the valid moves to the right of the rook
     * @param board board the piece is on
     * @return valid set of moves right of the rook
     */
    private List<Position> getHorizontalMovesRight(BoardIF board, Position currentPosition){
        List<Position> validRight = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex(); // Get current rank of piece
        int currentFile = currentPosition.getFile().getFileNum() + 1; // Get current file of piece

        boolean pieceHit = false; // Boolean to determine if a piece has been hit

        // Keep looping until the end of the board is reached or a piece is hit
        while(!pieceHit && currentFile < board.getWidth()){
            // get the current square
            Square currSquare = (Square) board.getSquares()[currentRank][currentFile];

            Piece currPiece = (Piece) currSquare.getPiece(); // Get the piece on the square

            if(currSquare.getPiece() == null){ // Square is empty
                validRight.add(currSquare.getPosition());
            }
            else if(!currPiece.getColor().equals(this.color)){ // Square has an enemy piece
                validRight.add(currSquare.getPosition());
                pieceHit = true; // we hit a piece, so we can't move any further
            }
            else{
                pieceHit = true;
            }
            currentFile++; // Increment the file of the piece
        }
        return validRight; // return the valid moves
    }
    /**
     * Setter method for the color of the rook
     * @param color color to set the rook to.
     */
    public void setColor(GameColor color) {
        this.color = color;
    }

    /**
     * Getter method for the color of the rook
     * @return color of the rook.
     */
    public GameColor getColor() {
        return this.color;
    }
}
