package interfaces;

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
}
