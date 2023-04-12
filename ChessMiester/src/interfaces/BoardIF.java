/**
 * This interface defines the basic functionality of a game board object.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
package interfaces;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import model.Board;

/**
 * This interface defines the basic functionality of a game board object.
 * @author Brady Smith (100%)
 */
public interface BoardIF {

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

    void addMove(GameColor color, Files fromF, Rank fromR, Files toF, Rank toR);

    Board.BoardMemento createMemento();

    String getState();

    void loadFromMemento(Board.BoardMemento boardMemento);

    public record BoardMemento(String state) {}
}