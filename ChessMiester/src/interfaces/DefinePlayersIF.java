package interfaces;

/**
 * This interface defines the basic functionality of a player definition dialog.
 */
public interface DefinePlayersIF {

    /**
     * Displays the player definition dialog.
     */
    public void show();

    /**
     * Returns the first player.
     *
     * @return the PlayerIF object representing the first player.
     */
    public PlayerIF getPlayer1();

    /**
     * Returns the second player.
     *
     * @return the PlayerIF object representing the second player.
     */
    public PlayerIF getPlayer2();
}
