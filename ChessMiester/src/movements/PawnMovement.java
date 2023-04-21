package movements;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import enums.Files;
import interfaces.BoardIF;
import interfaces.FirstMoveIF;
import interfaces.MovementIF;
import interfaces.PieceIF;
import javafx.geometry.Pos;
import model.BlackAndWhite;
import model.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class represents the movement of a pawn.
 *
 * @author Kaushal Patel (60%), Colton Brooks (30%), Zach Eanes (10%)
 * @version 2.0
 */
public class PawnMovement extends BlackAndWhite implements MovementIF, FirstMoveIF {
    /* Fields */

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
     * @param board           the board the piece is on
     * @param currentPosition the current position of the piece
     * @param side            the side the pawn will move towards diagonally
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

    public Position enPassant(BoardIF board, Position currentPosition) {
        Position validMove = null;
        validMove = enPassantHelper(board, currentPosition, 1);
        if(validMove == null) {
            validMove = enPassantHelper(board, currentPosition, -1);
        }
        return validMove;
    }

    private Position enPassantHelper(BoardIF board, Position currentPosition, int side) {
        Position validMove = null;
        Position possiblePiece = moveCheck(board, currentPosition, 0, side, true);
        if(possiblePiece != null) {
            int moveRank = possiblePiece.getRank().getIndex();
            int moveFile = possiblePiece.getFile().getFileNum();
            PieceIF pieceInPos = board.getSquares()[moveRank][moveFile].getPiece(); // this will be null if there is no piece

            if(pieceInPos != null && !pieceInPos.getColor().equals(this.getColor()) && pieceInPos.getMoveType() instanceof PawnMovement) {
                String[] moveArr = board.getState().split("#")[1].split(",");
                String lastMove = moveArr[moveArr.length - 1];
                Position pos = pieceInPos.getPosition(board);
                int rankOf = pos.getRank().getDisplayNum();
                char fileOf = pos.getFile().getFileChar();
                int rankToList = Character.getNumericValue(lastMove.charAt(6));
                char fileToList = Character.toLowerCase(lastMove.charAt(5));
                if(fileToList == fileOf && rankToList == rankOf) {
                    int previousRank = Character.getNumericValue(lastMove.charAt(3));
                    int possiblePreviousRank = rankToList - (direction * 2);
                    if(previousRank == possiblePreviousRank) {
                        validMove = board.getSquares()[currentPosition.getRank().index + direction][currentPosition.getFile().getFileNum() + side].getPosition();
                    }
                }
            }
        }
        return validMove;
    }

    public boolean getEnPassant(BoardIF board, Position posToCheck, Position currentPosition){
        boolean canEnPassant = false;
        Position enPosition = this.enPassant(board, currentPosition);
        if(enPosition != null && enPosition.equals(posToCheck)) {
            canEnPassant = true;
        }
        return canEnPassant;
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
     *
     * @param isFirstMove boolean to set the first move to
     */
    @Override
    public void setFirstMove(boolean isFirstMove){ this.isFirstMove = isFirstMove; }

    public int getDirection() {
        return this.direction;
    }
}