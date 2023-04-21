package interfaces;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import model.Board;
import model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines the basic functionality of a game board object.
 *
 * @author Brady Smith (50%), Colton Brooks (50%)
 * @version 2.0
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
     *
     * @param playerColor the color of the player.
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
     * Adds a move to the game board memento.
     *
     * @param color the color of the piece that is added
     * @param fromF the "from" file of the movement
     * @param fromR the "from" rank of the movement
     * @param toF   the "to" file of the movement
     * @param toR   the "to" rank of the movement
     */
    void addMove(GameColor color, Files fromF, Rank fromR, Files toF, Rank toR);

    /**
     * Creates a new memento.
     * @return the new memento
     */
    Board.BoardMementoIF createMemento();

    /**
     * Gets the state of the memento which includes the placement for pieces
     * and movements.
     *
     * @return the state of the memento
     */
    String getState();

    /**
     * Loads a game from a given memento.
     *
     * @param boardMemento the memento to load from
     */
    void loadFromMemento(Board.BoardMementoIF boardMemento);

    /**
     * Highlights the given arraylist of positions on the board.
     *
     * @param board the board to highlight
     * @param array the arraylist of positions to highlight
     * @param color the color of the orientation of the board
     */
    void highlight(BoardIF board, ArrayList<Position> array, GameColor color);

    /**
     * Returns the draw strategy.
     *
     * @return the draw strategy
     */
    BoardStrategy getDrawStrategy();
    /**
     * Interface for the memento.
     */
    public interface BoardMementoIF {
        String state();
    }
}