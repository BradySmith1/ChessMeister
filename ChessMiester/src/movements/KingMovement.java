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

public class KingMovement implements MovementIF{

    private Position currentPosition;
    private GameColor color;

    /**
     * Constructor method for the KingMovement Class
     * @param currentPosition the current position of the piece
     * @param color the color of the piece
     */
    public KingMovement(Position currentPosition, GameColor color) {
        this.currentPosition = currentPosition;
        this.color = color;
    }

    /**
    * Gets the valid moves for the piece.
    *
    * @param board
    * @return the valid moves for the piece.
    */
    @Override
    public List<Position> getValidMoves(BoardIF board) {
        // Set of valid moves to be found for a king
        List<Position> validMoves = new ArrayList<>();

        // Check all 8 possible moves for a king
        validMoves.add(moveCheck(board, 1, 0));
        validMoves.add(moveCheck(board, 1, 1));
        validMoves.add(moveCheck(board, 0, 1));
        validMoves.add(moveCheck(board, -1, 1));
        validMoves.add(moveCheck(board, -1, 0));
        validMoves.add(moveCheck(board, -1, -1));
        validMoves.add(moveCheck(board, 0, -1));
        validMoves.add(moveCheck(board, 1, -1));
        return null;
    }

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
    *
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

    private void setPosition(Position position) {
        this.currentPosition = position;
    }
}
