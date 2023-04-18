package interfaces;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import model.Board;

/**
 * This interface defines the basic functionality of a game board object.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public interface BoardIF{

    /**
     * Initializes the game board.
     */
    void initBoard();

    /**
     * Sets up the initial state of the game board.
     */
    void setup();

    /**
     * Draws the game board.
     */
    public void draw(GameColor playerColor);

    /**
     * Returns a 2D array of squares on the game board.
     *
     * @return a 2D array of SquareIF objects representing the squares on the board.
     */
    SquareIF[][] getSquares();

    /**
     * Sets the drawing strategy for the game board.
     *
     * @param d the BoardStrategy object that defines the drawing strategy to use.
     */
    void setDrawStrategy(BoardStrategy d);

    /**
     * Returns the width of the game board.
     *
     * @return the width of the game board in squares.
     */
    int getWidth();

    /**
     * Returns the height of the game board.
     *
     * @return the height of the game board in squares.
     */
    int getHeight();

    /**
     * Returns the piece at the specified rank and file on the game board.
     *
     * @param r the rank of the piece.
     * @param f the file of the piece.
     * @return the PieceIF object at the specified rank and file.
     */
    PieceIF getPiece(Rank r, Files f);

    /**
     * Returns the piece at the specified column and row on the game board.
     *
     * @param col the column of the piece.
     * @param row the row of the piece.
     * @return the PieceIF object at the specified column and row.
     */
    PieceIF getPiece(int col, char row);

    /**
     * Adds the move to the boards state that it holds in a field.
     * @param color color of the moving piece
     * @param fromF current file for the piece
     * @param fromR current rank for the piece
     * @param toF   the file to move to
     * @param toR   the rank to move to
     */
    void addMove(GameColor color, Files fromF, Rank fromR, Files toF, Rank toR);

    /**
     * Creates a memento for the current state of the board
     * @return the memento to be stored in a caretaker
     */
    Board.BoardMementoIF createMemento();

    /**
     * Getter for the state field of the Board
     * @return the String of the board state
     */
    String getState();

    /**
     * Method to load the board from a different memento / board state.
     * @param boardMemento  the memento to load in
     */
    void loadFromMemento(Board.BoardMemento boardMemento);

    /**
     * An interface to define the nested class to hold a memento of the board.
     */
    public interface BoardMementoIF {}
}