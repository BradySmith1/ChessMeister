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


/**
 * This class represents the movement of a pawn.
 *
 * @author Kaushal Patel (60%), Colton Brooks (30%), Zach Eanes (10%)
 * @version 1.0
 */
public class PawnMovement extends BlackAndWhite implements MovementIF{
    /* Fields */

    /** The color of the piece. */
    private GameColor color;

    /** If this is the first move of the piece. */
    private boolean isFirstMove;

    /** The direction the pawn is moving. */
    private int direction;

    /**
     * Constructor method for the PawnMovement Class.
     *
     * @param color the color of the piece.
     */
    public PawnMovement(GameColor color){
        super(color);
        this.isFirstMove = true;

        /* The direction the pawn is moving. */
        this.direction = color == GameColor.WHITE ? 1 : -1;
    }

    /**
     * Function to check if a specified move is possible for a pawn.
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

        //check for pawn move forward 1
        if (currentRank + rank < board.getHeight() && currentFile + file < board.getWidth() &&
                currentRank + rank >= 0 && currentFile + file >= 0){
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

    /**
     * Gets the valid moves for the piece.
     *
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {

        List<Position> validMoves = new ArrayList<>();
        validMoves.add(moveCheck(board, currentPosition, direction, 0));
        // first move twice case
        if (isFirstMove) {
            validMoves.add(moveCheck(board, currentPosition, direction * 2, 0));
        }
        // call to check diagonal captures
        validMoves.addAll(pieceDiagonalCheck(board, currentPosition));
        //remove all nulls in the list
        validMoves.removeAll(Collections.singleton(null));
        return validMoves;
    }

    /**
     * Method to check a diagonal capture for a pawn
     *
     * @param board             the board the piece is on
     * @param currentPosition   the current position of the piece
     * @return valid moves for a capture
     */
    private List<Position> pieceDiagonalCheck(BoardIF board, Position currentPosition) {
        //new arraylist of possible moves
        List<Position> validMoves = new ArrayList<>();
        // forward movement for the pawn
        int forward = currentPosition.getRank().getIndex() + direction;
        //right and left movement for the pawn
        int right = currentPosition.getFile().getFileNum() + 1;
        int left = currentPosition.getFile().getFileNum() - 1;
        //checking a capture to the left
        if (left >= 0) {
            Square aheadLeft = (Square) board.getSquares()[forward][left];
            if (aheadLeft.getPiece() != null && !aheadLeft.getColor().equals(getColor())) {
                validMoves.add(moveCheck(board, currentPosition, direction, -1));
            }
        }
        //checking a capture to the right
        if (right < board.getWidth()) {
            Square aheadRight = (Square) board.getSquares()[forward][right];
            if (aheadRight.getPiece() != null && !aheadRight.getColor().equals(getColor())) {
                validMoves.add(moveCheck(board, currentPosition, direction, 1));
            }
        }
        return validMoves;
    }

    /**
     * Gets the game color of the piece.
     *
     * @return the game color of the piece.
     */
    public GameColor getColor() {
        return color;
    }

    /**
     * Sets the game color of the piece.
     *
     * @param color the game color of the piece.
     */
    public void setColor(GameColor color) {
        this.color = color;
    }

    /**
     * Changes boolean if this is the first move of the piece occurs.
     */
    public void setFirstMove(){
        this.isFirstMove = false;
    }
}