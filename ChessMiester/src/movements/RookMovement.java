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
import interfaces.PieceIF;
import model.Piece;
import model.Position;
import model.Square;

import java.util.ArrayList;
import java.util.List;

public class RookMovement implements MovementIF{
    private Position currentPosition; // The current position of the piece

    private GameColor color; // The color of the piece

    /**
     * Constructor method for the RookMovement Class
     * @param currentPosition the current position of the piece
     * @param color the color of the piece
     */
    public RookMovement(Position currentPosition, GameColor color) {
        this.currentPosition = new Position();
        this.color = color;
    }

    /**
     * Moves the rook to a specified position on the board
     *
     * @param board the board the piece is on
     * @param movePosition the position to move the piece to
     * @return boolean where true if the move was successful, false otherwise
     */
    public boolean move(BoardIF board, Position movePosition) {
        List<Position> valid = getValidMoves(board); // Get the valid moves for the piece

        boolean success = false; // Move unsuccessful by default.

        if(valid.contains(movePosition)) { // If the move is valid
            // Set board square to rook.
            board.getSquares()[currentPosition.getRank().getIndex()]
                              [currentPosition.getFile().getFileNum()].setPiece((PieceIF) this);
            this.setPosition(movePosition); // Set the position of the piece
            success = true; // Move successful
        }
        return success; // Return the success of the move
    }

    /**
     * Gets the valid moves for the piece
     * @param board the board the piece is on
     * @return the valid moves for the piece
     */
    @Override
    public List<Position> getValidMoves(BoardIF board) {
        List<Position> validMoves = new ArrayList<>();

        // Get the vertical movements for the rook.
        List<Position> validVertical = getVerticalMoves(board);

        // Get the horizontal movements for the rook.
        List<Position> validHorizontal = getHorizontalMoves(board);

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
    private List<Position> getVerticalMoves(BoardIF board){
        List<Position> validVertical = new ArrayList<>();

        List<Position> validUp = getVerticalMovesAbove(board); // Get valid moves above piece
        List<Position> validDown = getVerticalMovesBelow(board); // Get valid moves below piece

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
    private List<Position> getHorizontalMoves(BoardIF board){
        List<Position> validHorizontal = new ArrayList<>();

        List<Position> validLeft = getHorizontalMovesLeft(board); // Get valid moves left of piece
        List<Position> validRight = getHorizontalMovesRight(board); // Get valid moves right of piece

        validHorizontal.addAll(validLeft); // Add the valid moves left to the valid moves list
        validHorizontal.addAll(validRight);

        return validHorizontal;
    }

    /**
     * Gets the valid moves above the rook
     * @param board board the piece is on
     * @return valid set of moves above the rook
     */
    private List<Position> getVerticalMovesAbove(BoardIF board){
        List<Position> validUp = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex(); // Get the current rank of the piece
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
     * Gets the valid moves below the rook
     * @param board board the piece is on
     * @return valid set of moves below the rook
     */
    private List<Position> getVerticalMovesBelow(BoardIF board){
        List<Position> validDown = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex(); // Get current rank of piece
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
            currentRank--; // Increment the rank of the piece
        }
        return validDown;
    }

    /**
     * Gets the valid moves left of the rook
     * @param board board the piece is on
     * @return valid set of moves left of the rook
     */
    private List<Position> getHorizontalMovesLeft(BoardIF board) {
        List<Position> validLeft = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex(); // Get current rank of piece
        int currentFile = currentPosition.getFile().getFileNum(); // Get current file of piece

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
        }
        return validLeft; // return the valid moves
    }

    /**
     * Gets the valid moves to the right of the rook
     * @param board board the piece is on
     * @return valid set of moves right of the rook
     */
    private List<Position> getHorizontalMovesRight(BoardIF board){
        List<Position> validRight = new ArrayList<>();

        int currentRank = currentPosition.getRank().getIndex(); // Get current rank of piece
        int currentFile = currentPosition.getFile().getFileNum(); // Get current file of piece

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


    /**
     * Setter method for the position of the rook
     * @param position position to set the rook to
     */
    public void setPosition(Position position) {
        this.currentPosition = position;
    }

    /**
     * Getter method for the position of the rook
     * @return position of the rook
     */
    public Position getPosition() {
        return this.currentPosition;
    }
}
