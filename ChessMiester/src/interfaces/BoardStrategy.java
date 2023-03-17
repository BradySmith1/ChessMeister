package interfaces;

/**
 * This interface defines the basic functionality of a drawing strategy for a game board.
 */
public interface BoardStrategy {

    /**
     * Draws the game board using the specified strategy.
     * @param board the BoardIF object representing the game board to be drawn.
     */
    public void draw(BoardIF board);
}
