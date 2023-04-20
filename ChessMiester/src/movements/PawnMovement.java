package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.FirstMoveIF;
import interfaces.MovementIF;
import interfaces.PieceIF;
import model.BlackAndWhite;
import model.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class represents the movement of a pawn.
 *
 * @author Kaushal Patel (60%), Colton Brooks (30%), Zach Eanes (10%)
 * @version 1.0
 */
public class PawnMovement extends BlackAndWhite implements MovementIF, FirstMoveIF {
    /* Fields */

    /** The color of the piece. */
    private GameColor color;
    /** If this is the first move of the piece. */
   private boolean isFirstMove;

    /** The direction the pawn is moving. */
    private final int direction;

    /**
     * Constructor method for the PawnMovement Class.
     *
     * @param color the color of the piece.
     */
    public PawnMovement(GameColor color){
        super(color);
        this.isFirstMove = true;

        /* The direction the pawn is moving. */
        this.direction = color == GameColor.BLACK ? 1 : -1;
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
        validMoves.add(moveCheck(board, currentPosition, direction, 0, false));
        // first move twice case
        if (isFirstMove) {
            validMoves.add(moveCheck(board, currentPosition, direction * 2, 0, false));
        }
        // call to check diagonal captures
        validMoves.add(getDirectionalCapture(board, currentPosition, 1));
        validMoves.add(getDirectionalCapture(board, currentPosition, -1));
        // call to check for en passant
        //validMoves.addAll(getEnPassant(board, currentPosition));
        //remove all nulls in the list
        validMoves.removeAll(Collections.singleton(null));
        return validMoves;
    }

    /**
     * Method to return a possible diagonal capture for a pawn
     * @param board     the board the piece is on
     * @param currentPosition   the current position of the piece
     * @param side      the side the pawn will move towards diagonally
     * @return  a position if the move is possible, a null if not
     */
    private Position getDirectionalCapture(BoardIF board, Position currentPosition, int side) {
        Position validMove = null;
        Position place;
        PieceIF pieceInPos;
        int moveRank;
        int moveFile;
        place = moveCheck(board, currentPosition, direction, side, true);
        if (place != null) {    // if we can move there in the first place
            moveRank = place.getRank().getIndex();
            moveFile = place.getFile().getFileNum();
            pieceInPos = board.getSquares()[moveRank][moveFile].getPiece(); // this will be null if there is no piece
            if (pieceInPos != null) {   // if there is a piece where we are going
                validMove = place;
            }
        }
        return validMove;
    }
/*
    private List<Position> getEnPassant(BoardIF board, Position currentPosition) {
        Position validMove = null;
        Position place = null;
        PieceIF pieceInPos = null;
        int moveRank;
        int moveFile;
        place = moveCheck(board, currentPosition, direction, 0, true);
        if(place != null) {
            moveRank = place.getRank().getIndex();
            moveFile = place.getFile().getFileNum();
            pieceInPos = board.getSquares()[moveRank][moveFile].getPiece(); // this will be null if there is no piece
            if(pieceInPos.getMoveType() instanceof PawnMovement) {
                // TODO
                // make a variable for getMoveType with a static type of FirstMoveIF
                // if(getFirstMove is false) {
                //      undo board
                //      check if that pawns firstMove is now true
                //      redo and add the diagonals to valid move
            }
        }
        return validMove;
    }
*/
    /**
     * Gets the game color of the piece.
     * @return  the game color of the piece.
     */
    public GameColor getColor() {
        return color;
    }

    /**
     * Sets the game color of the piece.
     * @param color the game color of the piece.
     */
    public void setColor(GameColor color) {
        this.color = color;
    }

    /**
     * Method returns whether the piece have moved or not.
     *
     * @return true if the piece has not moved, false otherwise.
     */
    @Override
    public boolean getFirstMove() { return this.isFirstMove; }

    /**
     * Changes boolean if this is the first move of the piece occurs.
     */
    @Override
    public void setFirstMove(boolean isFirstMove){ this.isFirstMove = isFirstMove; }
}