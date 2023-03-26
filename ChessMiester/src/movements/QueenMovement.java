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

/**
 * This class represents the movement of a Queen.
 * @authors Kaushal Patel (70%), Zach Eanes (10%)
 */
public class QueenMovement implements MovementIF {

    /** Fields */
    private Position currentPosition;   // The current position of the piece.

    private GameColor color;   // The color of the piece.


    /** Constructor */
    public QueenMovement(Position currentPosition, GameColor color) {
        this.currentPosition = currentPosition;
        this.color = color;
    }

    /**
    * Gets the valid moves for the piece.
    *
    * @param board the board the piece is on.
    * @return the valid moves for the piece.
    */
    @Override
    public List<Position> getValidMoves(BoardIF board) {
        List<Position> validMoves = new ArrayList<>();

        // Get the vertical moves for the Queen.
        List<Position> validVerticalMoves = getVerticalMoves(board);

        // Get the horizontal moves for the Queen.
        List<Position> validHorizontalMoves = getHorizontalMoves(board);

        // Get the diagonal moves for the Queen.
        List<Position> validDiagonalMoves = getDiagonalMoves(board);

        // Add the vertical moves to the list of valid moves.
        validMoves.addAll(validVerticalMoves);

        // Add the horizontal moves to the list of valid moves.
        validMoves.addAll(validHorizontalMoves);

        // Add the diagonal moves to the list of valid moves.
        validMoves.addAll(validDiagonalMoves);

        return validMoves;  // Return the list of valid moves.
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

    /**
     * Gets the vertical moves for the Queen from her current position.
     * @param board the board the piece is on.
     * @return the vertical moves for the Queen.
     */
    private List<Position> getVerticalMoves(BoardIF board){
        List<Position> validVerticalMoves = new ArrayList<>();

        List<Position> validVerticalMovesAbove = getVerticalMovesAbove(board);
        List<Position> validVerticalMovesBelow = getVerticalMovesBelow(board);

        validVerticalMoves.addAll(validVerticalMovesAbove);
        validVerticalMoves.addAll(validVerticalMovesBelow);

        return validVerticalMoves;
    }

    private List<Position> getHorizontalMoves(BoardIF board){
        List<Position> validHorizontalMoves = new ArrayList<>();

        List<Position> validHorizontalMovesLeft = getHorizontalMovesLeft(board);
        List<Position> validHorizontalMovesRight = getHorizontalMovesRight(board);

        validHorizontalMoves.addAll(validHorizontalMovesLeft);
        validHorizontalMoves.addAll(validHorizontalMovesRight);

        return validHorizontalMoves;
    }

    private List<Position> getDiagonalMoves(BoardIF board){
        List<Position> validDiagonalMoves = new ArrayList<>();

        List<Position> validDiagonalMovesUpLeft = getDiagonalMovesUpLeft(board);
        List<Position> validDiagonalMovesUpRight = getDiagonalMovesUpRight(board);
        List<Position> validDiagonalMovesDownLeft = getDiagonalMovesDownLeft(board);
        List<Position> validDiagonalMovesDownRight = getDiagonalMovesDownRight(board);

        validDiagonalMoves.addAll(validDiagonalMovesUpLeft);
        validDiagonalMoves.addAll(validDiagonalMovesUpRight);
        validDiagonalMoves.addAll(validDiagonalMovesDownLeft);
        validDiagonalMoves.addAll(validDiagonalMovesDownRight);

        return validDiagonalMoves;
    }

    private List<Position> getVerticalMovesAbove(BoardIF board){
        // The valid moves for the Queen above her current position.
        List<Position> validVerticalMovesAbove = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex() + 1;    // The rank above the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum();  // The file of the current square.

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while(!pieceEncountered && currentQueenRank < board.getHeight()){
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if(currentSquare.getPiece() == null){
                validVerticalMovesAbove.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color, add it to the list of valid moves.
            else if(!currentPiece.getColor().equals(this.color)){ // if piece is opposite color
                validVerticalMovesAbove.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color, do not add it to the list of valid moves.
            else {
                pieceEncountered = true;
            }
            // Increment the rank.
            currentQueenRank++;
        }
        return validVerticalMovesAbove; // Return the list of valid moves above the Queen.
    }

    private List<Position> getVerticalMovesBelow(BoardIF board){
        // The valid moves for the Queen below her current position.
        List<Position> validVerticalMovesBelow = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex() - 1;    // The rank above the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum();  // The file of the current square.

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while(!pieceEncountered && currentQueenRank < board.getHeight()){
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if(currentSquare.getPiece() == null){
                validVerticalMovesBelow.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color, add it to the list of valid moves.
            else if(!currentPiece.getColor().equals(this.color)){ // if piece is opposite color
                validVerticalMovesBelow.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color, do not add it to the list of valid moves.
            else {
                pieceEncountered = true;
            }
            // Increment the rank.
            currentQueenRank++;
        }
        return validVerticalMovesBelow; // Return the list of valid moves above the Queen.

    }

    private List<Position> getHorizontalMovesLeft(BoardIF board){
        // The valid moves for the Queen to the left of her current position.
        List<Position> validHorizontalMovesLeft = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex();    // The rank of the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() - 1;  // The file left of the current square.

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while(!pieceEncountered && currentQueenFile >= 0){
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if(currentSquare.getPiece() == null){
                validHorizontalMovesLeft.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color, add it to the list of valid moves.
            else if(!currentPiece.getColor().equals(this.color)){ // if piece is opposite color
                validHorizontalMovesLeft.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color, do not add it to the list of valid moves.
            else {
                pieceEncountered = true;
            }
            // Decrement the file.
            currentQueenFile--;
        }

        return validHorizontalMovesLeft; // Return the list of valid moves to the left of the Queen.
    }

    private List<Position> getHorizontalMovesRight(BoardIF board){
        // The valid moves for the Queen to the right of her current position.
        List<Position> validHorizontalMovesRight = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex();    // The rank of the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() + 1;  // The file right of the current square.

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while(!pieceEncountered && currentQueenFile < board.getWidth()){
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if(currentSquare.getPiece() == null){
                validHorizontalMovesRight.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color, add it to the list of valid moves.
            else if(!currentPiece.getColor().equals(this.color)){ // if piece is opposite color
                validHorizontalMovesRight.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color, do not add it to the list of valid moves.
            else {
                pieceEncountered = true;
            }
            // Increment the file.
            currentQueenFile++;
        }

        return validHorizontalMovesRight; // Return the list of valid moves to the right of the Queen.
    }

    private List<Position> getDiagonalMovesUpLeft(BoardIF board){
        // The valid moves for the Queen to the up and left of her current position.
        List<Position> validDiagonalMovesUpLeft = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex() + 1;    // The rank above the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() - 1;  // The file left of the current square.

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while(!pieceEncountered && currentQueenRank < board.getHeight() && currentQueenFile >= 0){
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if(currentSquare.getPiece() == null){
                validDiagonalMovesUpLeft.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color, add it to the list of valid moves.
            else if(!currentPiece.getColor().equals(this.color)){ // if piece is opposite color
                validDiagonalMovesUpLeft.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color, do not add it to the list of valid moves.
            else {
                pieceEncountered = true;
            }
            // Increment the rank.
            currentQueenRank--;
            // Decrement the file.
            currentQueenFile++;
        }

        return validDiagonalMovesUpLeft; // Return the list of valid moves up and left of the Queen.
    }

    private List<Position> getDiagonalMovesUpRight(BoardIF board){
        // The valid moves for the Queen to the up and right of her current position.
        List<Position> validDiagonalMovesUpRight = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex() + 1;    // The rank above the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() + 1;  // The file right of the current square.

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while(!pieceEncountered && currentQueenRank < board.getHeight() && currentQueenFile < board.getWidth()){
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if(currentSquare.getPiece() == null){
                validDiagonalMovesUpRight.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color, add it to the list of valid moves.
            else if(!currentPiece.getColor().equals(this.color)){ // if piece is opposite color
                validDiagonalMovesUpRight.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color, do not add it to the list of valid moves.
            else {
                pieceEncountered = true;
            }
            // Increment the rank.
            currentQueenRank++;
            // Increment the file.
            currentQueenFile++;
        }

        return validDiagonalMovesUpRight; // Return the list of valid moves up and right of the Queen.
    }

    private List<Position> getDiagonalMovesDownLeft(BoardIF board){
        // The valid moves for the Queen to the down and left of her current position.
        List<Position> validDiagonalMovesDownLeft = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex() - 1;    // The rank below the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() - 1;  // The file left of the current square.

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while(!pieceEncountered && currentQueenRank >= 0 && currentQueenFile >= 0){
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if(currentSquare.getPiece() == null){
                validDiagonalMovesDownLeft.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color, add it to the list of valid moves.
            else if(!currentPiece.getColor().equals(this.color)){ // if piece is opposite color
                validDiagonalMovesDownLeft.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color, do not add it to the list of valid moves.
            else{
                pieceEncountered = true;
            }
            // Decrement the rank.
            currentQueenRank--;
            // Decrement the file.
            currentQueenFile--;
        }

        return validDiagonalMovesDownLeft; // Return the list of valid moves down and left of the Queen.
    }

    private List<Position> getDiagonalMovesDownRight(BoardIF board){
        // The valid moves for the Queen to the down and right of her current position.
        List<Position> validDiagonalMovesDownRight = new ArrayList<>();

        int currentQueenRank = currentPosition.getRank().getIndex() - 1;    // The rank below the current square.
        int currentQueenFile = currentPosition.getFile().getFileNum() + 1;  // The file right of the current square.

        // A boolean to determine if a piece has been encountered.
        boolean pieceEncountered = false;

        // Keep looping until a piece is encountered or the end of the board is reached.
        while(!pieceEncountered && currentQueenRank >= 0 && currentQueenFile < board.getWidth()){
            // Get the square at the current rank and file.
            Square currentSquare = (Square)board.getSquares()[currentQueenRank][currentQueenFile];

            // Get the piece at the current square and cast the PieceIF to Piece.
            Piece currentPiece = (Piece) currentSquare.getPiece();

            // If the square is empty, add it to the list of valid moves.
            if(currentPiece == null){
                validDiagonalMovesDownRight.add(currentSquare.getPosition());
            }
            // If the square is occupied by a piece of the opposite color, add it to the list of valid moves.
            else if(!currentPiece.getColor().equals(this.color)){ // if piece is opposite color
                validDiagonalMovesDownRight.add(currentSquare.getPosition());
                pieceEncountered = true;
            }
            // If the square is occupied by a piece of the same color, do not add it to the list of valid moves.
            else {
                pieceEncountered = true;
            }
            // Decrement the rank.
            currentQueenRank++;
            // Increment the file.
            currentQueenFile--;
        }

        return validDiagonalMovesDownRight; // Return the list of valid moves down and right of the Queen.
    }

    /**
     * Sets the color of the Queen.
     * @param color The color of the Queen.
     */
    public void setColor(GameColor color) {
        this.color = color;
    }

    /**
     * Gets the color of the Queen.
     * @return The color of the Queen.
     */
    public GameColor getColor() {
        return color;
    }

    /**
     * Sets the position of the Queen.
     * @param position The position of the Queen.
     */
    public void setPosition(Position position) {
        this.currentPosition = position;
    }

    /**
     * Gets the position of the Queen.
     * @return The position of the Queen.
     */
    public Position getPosition() {
        return currentPosition;
    }
}
