package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import interfaces.PieceIF;
import model.Piece;
import model.Position;
import model.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnightMovement implements MovementIF{

    private Position currentPosition; // The current position of the piece

    private GameColor color; // The color of the piece

    /**
     * Constructor method for the KnightMovement Class
     * @param currentPosition the current position of the piece
     * @param color the color of the piece
     */
    public KnightMovement(Position currentPosition, GameColor color) {
        this.currentPosition = currentPosition;
        this.color = color;
    }
    /**
    * Gets the valid moves for the piece.
    *
    * @param board the board the piece is on
    * @return the valid moves for the piece.
    */
    @Override
    public List<Position> getValidMoves(BoardIF board){
        // Set of valid moves to be found for a knight
        List<Position> validMoves = new ArrayList<>();

        // Check all 8 possible moves from a knight
        validMoves.add(moveCheck(board, 1, 2));
        validMoves.add(moveCheck(board, 2, 1));
        validMoves.add(moveCheck(board, -1, 2));
        validMoves.add(moveCheck(board, -2, 1));
        validMoves.add(moveCheck(board, 1, -2));
        validMoves.add(moveCheck(board, 2, -1));
        validMoves.add(moveCheck(board, -1, -2));
        validMoves.add(moveCheck(board, -2, -1));

        // Remove the instances of null from our list of valid moves
        validMoves.removeAll(Collections.singleton(null));

        return validMoves; // Return the set of valid moves
    }

    /**
     * Function to check if a specified move is possible for a knight.
     * @param board board the piece is on
     * @param rank  difference in rank for the piece to be changed
     * @param file  difference in file for the piece to be changed
     * @return position of a move if it's deemed valid
     */
    private Position moveCheck(BoardIF board, int rank, int file) {

        Position movePossible = null; // move starts are nonexistent

        int currentRank = currentPosition.getRank().getIndex(); // current rank of piece
        int currentFile = currentPosition.getFile().getFileNum(); //current file of piece

        //check for knight move up 1, right 2
        if(currentRank + rank < board.getHeight() && currentFile + file < board.getWidth()){
            //get the square of the move and the piece in the square
            Square currentSquare = (Square) board.getSquares()[currentRank + rank][currentFile + file];
            Piece currentPiece = (Piece) currentSquare.getPiece();
            //check if there is an empty square or an enemy piece
            if(currentPiece == null || !currentPiece.getColor().equals(this.color)) {
                //the move is possible so add it
                movePossible = currentSquare.getPosition();
            }
        }
        return movePossible; // return the result of if a move is possible
    }
    

    /**
    * Moves the piece to the specified position.
    * @param board        the board the piece is on.
    * @param movePosition the position to move the piece to.
    * @return true if the move was successful.
    */
    @Override
    public boolean move(BoardIF board, Position movePosition) {
        // Get the valid moves for the Queen.
        List<Position> validMoves = getValidMoves(board);

        // The move was not successful by default.
        boolean moveSuccessful = false;

        // Check if the move is valid.
        if(validMoves.contains(movePosition)){
            // Set the boards new piece to the piece that is moving.
            board.getSquares()[currentPosition.getRank().getIndex()]
                    [currentPosition.getFile().getFileNum()].setPiece((PieceIF) this); // TODO could be bad cast

            // Set the new local position of the piece.
            this.setPosition(movePosition);

            // The move was successful.
            moveSuccessful = true;
        }
        return moveSuccessful;  // Return the result of the move.
    }

    public void setPosition(Position position) {
        this.currentPosition = position;
    }
}
