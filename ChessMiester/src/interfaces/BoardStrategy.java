package interfaces;

import enums.GameColor;
import model.Position;

import java.util.ArrayList;

/**
 * This interface defines the basic functionality of a drawing strategy for a game board.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public interface BoardStrategy {

    /**
     * Draws the game board using the specified strategy.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     * @param color the GameColor object representing the color of the game board to be drawn.
     */
    public void draw(BoardIF board, GameColor color);

    /**
     * Highlights the specified squares on the game board.
     *
     * @param board       the board to highlight
     * @param highlighted the squares to highlight
     * @param color       the color to orient the board
     */
    public void highlight(BoardIF board, ArrayList<Position> highlighted, GameColor color);

    /**
     * Returns the draw strategy.
     */
    public void setHighlight(boolean boo);
}
