/**
 * This class represents the movement of a Queen.
 *
 * @authors Kaushal Patel (65%), Zach Eanes (20%), Colton Brooks (15%)
 * @version 1.0
 */
package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import model.BlackAndWhite;
import model.Piece;
import model.Position;
import model.Square;
import java.util.ArrayList;
import java.util.List;


public class QueenMovement extends BlackAndWhite implements MovementIF {// The color of the piece.

    /**
     * Constructor method for the QueenMovement class.
     *
     * @param color the color of the piece.
     */
    public QueenMovement(GameColor color) {
        super(color);
    }

    /**
     * Function that gets the valid moves for the Queen.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();

        // Get the vertical moves for the Queen.
        List<Position> validVerticalMoves = getVerticalMoves(board, currentPosition);

        // Get the horizontal moves for the Queen.
        List<Position> validHorizontalMoves = getHorizontalMoves(board, currentPosition);

        // Get the diagonal moves for the Queen.
        List<Position> validDiagonalMoves = getDiagonalMoves(board, currentPosition);

        // Add the vertical moves to the list of valid moves.
        validMoves.addAll(validVerticalMoves);

        // Add the horizontal moves to the list of valid moves.
        validMoves.addAll(validHorizontalMoves);

        // Add the diagonal moves to the list of valid moves.
        validMoves.addAll(validDiagonalMoves);

        return validMoves;  // Return the list of valid moves.
    }

    /**
     * Gets the vertical moves for the Queen from her current position.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the vertical moves for the Queen.
     */
    private List<Position> getVerticalMoves(BoardIF board, Position currentPosition) {
        List<Position> validVerticalMoves = new ArrayList<>();

        List<Position> validVerticalMovesAbove = getVerticalMovesAbove(board, currentPosition);
        List<Position> validVerticalMovesBelow = getVerticalMovesBelow(board, currentPosition);

        validVerticalMoves.addAll(validVerticalMovesAbove);
        validVerticalMoves.addAll(validVerticalMovesBelow);

        return validVerticalMoves;
    }

    /**
     * Gets the horizontal moves for the Queen from her current position.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the horizontal moves for the Queen.
     */
    private List<Position> getHorizontalMoves(BoardIF board, Position currentPosition) {
        List<Position> validHorizontalMoves = new ArrayList<>();

        List<Position> validHorizontalMovesLeft = getHorizontalMovesLeft(board, currentPosition);
        List<Position> validHorizontalMovesRight = getHorizontalMovesRight(board, currentPosition);

        validHorizontalMoves.addAll(validHorizontalMovesLeft);
        validHorizontalMoves.addAll(validHorizontalMovesRight);

        return validHorizontalMoves;
    }

    /**
     * Gets the diagonal moves for the Queen from her current position.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the diagonal moves for the Queen.
     */
    private List<Position> getDiagonalMoves(BoardIF board, Position currentPosition) {
        List<Position> validDiagonalMoves = new ArrayList<>();

        List<Position> validDiagonalMovesUpLeft =
                       getDiagonalMovesUpLeft(board, currentPosition);
        List<Position> validDiagonalMovesUpRight =
                       getDiagonalMovesUpRight(board, currentPosition);
        List<Position> validDiagonalMovesDownLeft =
                       getDiagonalMovesDownLeft(board, currentPosition);
        List<Position> validDiagonalMovesDownRight =
                       getDiagonalMovesDownRight(board, currentPosition);

        validDiagonalMoves.addAll(validDiagonalMovesUpLeft);
        validDiagonalMoves.addAll(validDiagonalMovesUpRight);
        validDiagonalMoves.addAll(validDiagonalMovesDownLeft);
        validDiagonalMoves.addAll(validDiagonalMovesDownRight);

        return validDiagonalMoves;
    }

    /**
     * Gets the diagonal moves for the Queen from her current position in the up-left direction.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the diagonal moves for the Queen in the up-left direction.
     */

    private List<Position> getVerticalMovesAbove(BoardIF board, Position currentPosition) {
        // The valid moves for the Queen above her current position.
        List<Position> validVerticalMovesAbove = new ArrayList<>();

        // The rank above current square.
        int currentQueenRank = currentPosition.getRank().getIndex() - 1;
        int currentQueenFile = currentPosition.getFile().getFileNum();// file of current square

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while (!pieceEncountered && currentQueenRank >= 0) {
            // Get the square at the current rank and file.
            Square currentSquare = (Square) board.getSquares()[currentQueenRank]
                                                              [currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if (currentSquare.getPiece() == null) {
                validVerticalMovesAbove.add(currentSquare.getPosition());
            }
            // If square is occupied by a piece of opposite color, add it to list of valid moves
            else if (!currentPiece.getColor().equals(getColor())) { // if piece is opposite color
                validVerticalMovesAbove.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If square occupied by a piece of same color, do not add it to list of valid moves
            else {
                pieceEncountered = true;
            }
            // Increment the rank.
            currentQueenRank--;
        }
        return validVerticalMovesAbove; // Return the list of valid moves above the Queen.
    }

    /**
     * Gets the vertical moves for the Queen from her current position in the down direction.
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the vertical moves for the Queen in the down direction.
     */
    private List<Position> getVerticalMovesBelow(BoardIF board, Position currentPosition) {
        // The valid moves for the Queen below her current position.
        List<Position> validVerticalMovesBelow = new ArrayList<>();

        // The rank below the current square.
        int currentQueenRank = currentPosition.getRank().getIndex() + 1;
        int currentQueenFile = currentPosition.getFile().getFileNum();  //file of current square

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while (!pieceEncountered && currentQueenRank < board.getHeight()) {
            // Get the square at the current rank and file.
            Square currentSquare = (Square) board.getSquares()[currentQueenRank]
                                                              [currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if (currentSquare.getPiece() == null) {
                validVerticalMovesBelow.add(currentSquare.getPosition());
            }
            // If square is occupied by a piece of opposite color, add it to list of valid moves
            else if (!currentPiece.getColor().equals(getColor())) { // if piece is opposite color
                validVerticalMovesBelow.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            //If square occupied by a piece of same color, do not add it to list of valid moves
            else {
                pieceEncountered = true;
            }
            // Increment the rank.
            currentQueenRank++;
        }
        return validVerticalMovesBelow; // Return the list of valid moves above the Queen.

    }

    /**
     * Gets the horizontal moves for the Queen from her current position in the left direction.
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the horizontal moves for the Queen in the left direction.
     */
    private List<Position> getHorizontalMovesLeft(BoardIF board, Position currentPosition) {
        // The valid moves for the Queen to the left of her current position.
        List<Position> validHorizontalMovesLeft = new ArrayList<>();

        // The rank of the current square
        int currentQueenRank = currentPosition.getRank().getIndex();
        // The file left of current square
        int currentQueenFile = currentPosition.getFile().getFileNum() - 1;

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while (!pieceEncountered && currentQueenFile >= 0) {
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank]
                                                             [currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if (currentSquare.getPiece() == null) {
                validHorizontalMovesLeft.add(currentSquare.getPosition());
            }
            // If square is occupied by a piece of opposite color, add it to list of valid moves
            else if (!currentPiece.getColor().equals(getColor())) { // if piece is opposite color
                validHorizontalMovesLeft.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If square is occupied by a piece of same color, do not add it to list of valid moves
            else {
                pieceEncountered = true;
            }
            // Decrement the file.
            currentQueenFile--;
        }

        return validHorizontalMovesLeft; // Return list of valid moves to the left of the Queen
    }

    /**
     * Gets the horizontal moves for the Queen from her current position in the right direction.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the horizontal moves for the Queen in the right direction.
     */
    private List<Position> getHorizontalMovesRight(BoardIF board, Position currentPosition) {
        // The valid moves for the Queen to the right of her current position.
        List<Position> validHorizontalMovesRight = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex();//The rank of current square
        // The file right of the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() + 1;

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while (!pieceEncountered && currentQueenFile < board.getWidth()) {
            // Get the square at the current rank and file.
            Square currentSquare = (Square) board.getSquares()[currentQueenRank]
                                                              [currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if (currentSquare.getPiece() == null) {
                validHorizontalMovesRight.add(currentSquare.getPosition());
            }
            // If square is occupied by a piece of opposite color, add it to list of valid moves
            else if (!currentPiece.getColor().equals(getColor())) { // if piece is opposite color
                validHorizontalMovesRight.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            //If square occupied by a piece of same color, do not add it to list of valid moves
            else {
                pieceEncountered = true;
            }
            // Increment the file.
            currentQueenFile++;
        }

        return validHorizontalMovesRight; // Return the list of valid moves to right of Queen
    }

    /**
     * Gets the diagonal moves for the Queen from her current position in the up and
     * left direction.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the diagonal moves for the Queen in the up and left direction.
     */
    private List<Position> getDiagonalMovesUpLeft(BoardIF board, Position currentPosition) {
        // The valid moves for the Queen to the up and left of her current position.
        List<Position> validDiagonalMovesUpLeft = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex() + 1;//rank above current square
        //file left of current square
        int currentQueenFile = currentPosition.getFile().getFileNum() - 1;

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while (!pieceEncountered && currentQueenRank < board.getHeight() && currentQueenFile >= 0) {
            // Get the square at the current rank and file.
            Square currentSquare = (Square) board.getSquares()[currentQueenRank]
                                                              [currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if (currentSquare.getPiece() == null) {
                validDiagonalMovesUpLeft.add(currentSquare.getPosition());
            }
            // If square is occupied by a piece of opposite color, add it to list of valid moves
            else if (!currentPiece.getColor().equals(getColor())) { // if piece is opposite color
                validDiagonalMovesUpLeft.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If square is occupied by a piece of same color, do not add it to list of valid moves
            else {
                pieceEncountered = true;
            }
            // Increment the rank.
            currentQueenRank++;
            // Decrement the file.
            currentQueenFile--;
        }

        return validDiagonalMovesUpLeft; // Return list of valid moves up and left of Queen
    }

    /**
     * Gets the diagonal moves for the Queen from her current position in the up and
     * right direction.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the diagonal moves for the Queen in the up and right direction.
     */
    private List<Position> getDiagonalMovesUpRight(BoardIF board, Position currentPosition) {
        // The valid moves for the Queen to the up and right of her current position.
        List<Position> validDiagonalMovesUpRight = new ArrayList<>();

        // The rank above the current square.
        int currentQueenRank = currentPosition.getRank().getIndex() + 1;
        // The file right of the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() + 1;

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached
        while (!pieceEncountered && currentQueenRank < board.getHeight() &&
                currentQueenFile < board.getWidth()) {
            // Get the square at the current rank and file.
            Square currentSquare = (Square) board.getSquares()[currentQueenRank]
                                                              [currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if (currentSquare.getPiece() == null) {
                validDiagonalMovesUpRight.add(currentSquare.getPosition());
            }
            // If square is occupied by a piece of opposite color, add it to list of valid moves
            else if (!currentPiece.getColor().equals(getColor())) { // if piece is opposite color
                validDiagonalMovesUpRight.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            //If square occupied by a piece of same color, do not add it to list of valid moves
            else {
                pieceEncountered = true;
            }
            // Increment the rank.
            currentQueenRank++;
            // Increment the file.
            currentQueenFile++;
        }

        return validDiagonalMovesUpRight; // Return list of valid moves up and right of Queen
    }

    /**
     * Gets the diagonal moves for the Queen from her current position in the down
     * and left direction.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the diagonal moves for the Queen in the down and left direction.
     */
    private List<Position> getDiagonalMovesDownLeft(BoardIF board, Position currentPosition) {
        // The valid moves for the Queen to the down and left of her current position.
        List<Position> validDiagonalMovesDownLeft = new ArrayList<>();

        // The rank below the current square.
        int currentQueenRank = currentPosition.getRank().getIndex() - 1;
        // The file left of the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() - 1;

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while (!pieceEncountered && currentQueenRank >= 0 && currentQueenFile >= 0) {
            // Get the square at the current rank and file.
            Square currentSquare = (Square) board.getSquares()[currentQueenRank]
                                                              [currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if (currentSquare.getPiece() == null) {
                validDiagonalMovesDownLeft.add(currentSquare.getPosition());
            }
            // If square is occupied by a piece of opposite color, add it to list of valid moves
            else if (!currentPiece.getColor().equals(getColor())) { // if piece is opposite color
                validDiagonalMovesDownLeft.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If square occupied by a piece of same color, do not add it to list of valid moves
            else {
                pieceEncountered = true;
            }
            // Decrement the rank.
            currentQueenRank--;
            // Decrement the file.
            currentQueenFile--;
        }

        return validDiagonalMovesDownLeft; //return list of valid moves down and left of Queen
    }

    /**
     * Gets the diagonal moves for the Queen from her current position in the down and
     * to the right direction.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the diagonal moves for the Queen in the down and right direction.
     */
    private List<Position> getDiagonalMovesDownRight(BoardIF board, Position currentPosition) {
        // The valid moves for the Queen to the down and right of her current position.
        List<Position> validDiagonalMovesDownRight = new ArrayList<>();

        // The rank below the current square.
        int currentQueenRank = currentPosition.getRank().getIndex() - 1;
        // The file right of the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() + 1;

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while (!pieceEncountered && currentQueenRank >= 0 && currentQueenFile < board.getWidth()){
            // Get the square at the current rank and file.
            Square currentSquare = (Square) board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if (currentPiece == null) {
                validDiagonalMovesDownRight.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color,
            // add it to the list of valid moves.
            else if (!currentPiece.getColor().equals(getColor())) {
                validDiagonalMovesDownRight.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color,
            // do not add it to the list of valid moves.
            else {
                pieceEncountered = true;
            }
            // Decrement the rank.
            currentQueenRank--;
            // Increment the file.
            currentQueenFile++;
        }

        // Return the list of valid moves down and right of the Queen.
        return validDiagonalMovesDownRight;
    }
}