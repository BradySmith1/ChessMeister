package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import interfaces.PieceIF;
import model.Piece;
import model.Position;
import model.Square;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents the movement of a pawn.
 * @author Kaushal Patel (100%)
 */
public class PawnMovement implements MovementIF {
    /** Fields */

    /** The current position of the piece. */
    private Position currentPosition;

    /** The color of the piece. */
    private GameColor color;

    /** If this is the first move of the piece. */
    private boolean isFirstMove;

    /** Constructors */
    public PawnMovement(Position currentPosition, GameColor color) {
        this.currentPosition = currentPosition;
        this.color = color;
        this.isFirstMove = true;
    }

    /**
     * Gets the valid moves for the piece.
     *
     * @param board
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board) {
        // Get the valid moves for the pawn.
        List<Position> validMoves = new ArrayList<>();

        // Get all the valid moves for the pawn if it is the first move.
        if (isFirstMove) {
            // Get the valid moves for the pawn if it is the first move.
            validMoves.addAll(getValidMovesForFirstMove(board));
        } else {
            // Get the valid moves for the pawn if it is not the first move.
            validMoves.addAll(getValidMovesForNonFirstMove(board));
        }

        return validMoves;
    }

    /**
     * Gets the valid moves for the pawn if it is not the first move.
     *
     * @param board the board the piece is on.
     * @return the valid moves for the pawn if it is not the first move.
     */
    private Collection<? extends Position> getValidMovesForNonFirstMove(BoardIF board) {
        // The valid moves for the pawn if it is not the first move.
        List<Position> validMoves = new ArrayList<>();

        // Get the rank of the pawn.
        int rankAbove = currentPosition.getRank().getIndex() + 1;

        // Get the file of the pawn.
        int currentFile = currentPosition.getFile().getFileNum();

        // Pawn above the board.
        if(rankAbove < board.getHeight()){
            // Get the square above the pawn.
            Square squareAbove = (Square) board.getSquares()[rankAbove][currentFile];

            // Get the piece on the square above the pawn.
            Piece pieceAbove = (Piece) squareAbove.getPiece();

            // If there is no piece on the square above the pawn.
            if (pieceAbove == null) {
                // Add the position of the square above the pawn to the valid moves.
                validMoves.add(squareAbove.getPosition());
            }
            // If there is an opposite piece on the square above the pawn.
            else if (!pieceAbove.getColor().equals(this.color)) {
                validMoves.add(squareAbove.getPosition());
            }
        }

        // Pawn top right of the board.
        if(rankAbove < board.getHeight() && currentFile + 1 < board.getWidth()){
            // Get the square top right of the pawn.
            Square squareTopRight = (Square) board.getSquares()[rankAbove][currentFile + 1];

            // Get the piece on the square top right of the pawn.
            Piece pieceTopRight = (Piece) squareTopRight.getPiece();

            // If the square is empty, add it to the valid moves.
            if (squareTopRight.getPiece() == null) {
                validMoves.add(squareTopRight.getPosition());
            }
            // If there is an opposite piece on the square top right of the pawn.
            else if (!pieceTopRight.getColor().equals(this.color)) {
                validMoves.add(squareTopRight.getPosition());
            }
        }

        // Pawn top left of the board.
        if(rankAbove < board.getHeight() && currentFile - 1 >= board.getWidth()){
            // Get the square top left of the pawn.
            Square squareTopLeft = (Square) board.getSquares()[rankAbove][currentFile - 1];

            // Get the piece on the square top left of the pawn.
            Piece pieceTopLeft = (Piece) squareTopLeft.getPiece();

            // If there is an opposite piece on the square top left of the pawn.
            if (pieceTopLeft != null && !pieceTopLeft.getColor().equals(this.color)) {
                validMoves.add(squareTopLeft.getPosition());
            }

            // If the square is empty, add it to the valid moves.
            if (squareTopLeft.getPiece() == null) {
                validMoves.add(squareTopLeft.getPosition());
            }
            // If there is an opposite piece on the square top right of the pawn.
            else if (!pieceTopLeft.getColor().equals(this.color)) {
                validMoves.add(squareTopLeft.getPosition());
            }
        }
        // Return the valid moves.
        return validMoves;
    }

    /**
     * Gets the valid moves for the pawn if it is the first move.
     *
     * @param board the board the piece is on.
     * @return the valid moves for the pawn if it is the first move.
     */
    private Collection<? extends Position> getValidMovesForFirstMove(BoardIF board) {
        // The valid moves for the pawn if it is not the first move.
        List<Position> validMoves = new ArrayList<>();

        // Get the rank of the pawn.
        int rankAbove = currentPosition.getRank().getIndex() + 2;

        // Get the file of the pawn.
        int currentFile = currentPosition.getFile().getFileNum();

        // Pawn above the board.
        if(rankAbove < board.getHeight()){
            // Get the square above the pawn.
            Square squareAbove = (Square) board.getSquares()[rankAbove][currentFile];

            // Get the piece on the square above the pawn.
            Piece pieceAbove = (Piece) squareAbove.getPiece();

            // If there is no piece on the square above the pawn.
            if (pieceAbove == null) {
                // Add the position of the square above the pawn to the valid moves.
                validMoves.add(squareAbove.getPosition());
            }
            // If there is an opposite piece on the square above the pawn.
            else if (!pieceAbove.getColor().equals(this.color)) {
                validMoves.add(squareAbove.getPosition());
            }
        }

        // Pawn top right of the board.
        if(rankAbove - 1 < board.getHeight() && currentFile + 1 < board.getWidth()){
            // Get the square top right of the pawn.
            Square squareTopRight = (Square) board.getSquares()[rankAbove - 1][currentFile + 1];

            // Get the piece on the square top right of the pawn.
            Piece pieceTopRight = (Piece) squareTopRight.getPiece();

            // If the square is empty, add it to the valid moves.
            if (squareTopRight.getPiece() == null) {
                validMoves.add(squareTopRight.getPosition());
            }
            // If there is an opposite piece on the square top right of the pawn.
            else if (!pieceTopRight.getColor().equals(this.color)) {
                validMoves.add(squareTopRight.getPosition());
            }
        }

        // Pawn top left of the board.
        if(rankAbove - 1 < board.getHeight() && currentFile - 1 >= board.getWidth()){
            // Get the square top left of the pawn.
            Square squareTopLeft = (Square) board.getSquares()[rankAbove - 1][currentFile -1];

            // Get the piece on the square top left of the pawn.
            Piece pieceTopLeft = (Piece) squareTopLeft.getPiece();

            // If there is an opposite piece on the square top left of the pawn.
            if (pieceTopLeft != null && !pieceTopLeft.getColor().equals(this.color)) {
                validMoves.add(squareTopLeft.getPosition());
            }

            // If the square is empty, add it to the valid moves.
            if (squareTopLeft.getPiece() == null) {
                validMoves.add(squareTopLeft.getPosition());
            }
            // If there is an opposite piece on the square top right of the pawn.
            else if (!pieceTopLeft.getColor().equals(this.color)) {
                validMoves.add(squareTopLeft.getPosition());
            }
        }
        // Return the valid moves.
        return validMoves;
    }

    /**
     * Moves the piece to the specified position.
     *
     * @param board the board the piece is on.
     * @param movePosition the position to move the piece to.
     * @return true if the move was successful.
     */
    @Override
    public boolean move(BoardIF board, Position movePosition) {
        // Get the valid moves for the Pawn.
        List<Position> validMoves = getValidMoves(board);

        // The move was not successful by default
        boolean moveSuccessful = false;

        // Check if the move is valid.
        if (validMoves.contains(movePosition)){
            board.getSquares()[currentPosition.getRank().getIndex()]
                    [currentPosition.getFile().getFileNum()].setPiece((PieceIF) this);

            // Set the new local position of the piece.
            this.setPosition(movePosition);

            // The move was successful.
            moveSuccessful = true;
        }
        return moveSuccessful;
    }

    private void setPosition(Position position) {
        this.currentPosition = position;
    }
}
