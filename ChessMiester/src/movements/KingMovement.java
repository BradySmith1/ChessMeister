package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import model.Piece;
import model.Position;
import model.Square;

import java.util.ArrayList;
import java.util.List;

public class KingMovement implements MovementIF {

    private GameColor color;

    /**
     * Constructor method for the KingMovement Class
     *
     * @param color           the color of the piece
     */
    public KingMovement(GameColor color) {
        this.color = color;
    }

    /**
     * Gets the valid moves for the piece.
     *
     * @param board
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        // Set of valid moves to be found for a king
        List<Position> validMoves = new ArrayList<>();

        // Check all 8 possible moves for a king
        validMoves.add(moveCheck(board, currentPosition, 1, 0));
        validMoves.add(moveCheck(board, currentPosition, 1, 1));
        validMoves.add(moveCheck(board, currentPosition, 0, 1));
        validMoves.add(moveCheck(board, currentPosition, -1, 1));
        validMoves.add(moveCheck(board, currentPosition, -1, 0));
        validMoves.add(moveCheck(board, currentPosition, -1, -1));
        validMoves.add(moveCheck(board, currentPosition, 0, -1));
        validMoves.add(moveCheck(board, currentPosition, 1, -1));
        return null;
    }

    private Position moveCheck(BoardIF board, Position currentPosition, int rank, int file) {

        Position movePossible = null; // move starts are nonexistent

        int currentRank = currentPosition.getRank().getIndex(); // current rank of piece
        int currentFile = currentPosition.getFile().getFileNum(); //current file of piece

        //check for knight move up 1, right 2
        if (currentRank + rank < board.getHeight() && currentFile + file < board.getWidth() &&
            currentRank + rank >= 0 && currentFile + file >= 0){
            //get the square of the move and the piece in the square
            Square currentSquare = (Square) board.getSquares()[currentRank + rank][currentFile + file];
            Piece currentPiece = (Piece) currentSquare.getPiece();
            //check if there is an empty square or an enemy piece
            if (currentPiece == null || !currentPiece.getColor().equals(this.color)) {
                //the move is possible so add it
                movePossible = currentSquare.getPosition();
            }
        }
        return movePossible; // return the result of if a move is possible
    }

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
