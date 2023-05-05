package gui_backend;

import enums.GameColor;
import interfaces.PlayerIF;
import model.Player;

public class DefinePlayersGUI {

    /** Player 1 */
    private PlayerIF player1;

    /** Player 2 */
    private PlayerIF player2;

    /**
     * Constructor for DefinePlayersGUI.
     */
    public DefinePlayersGUI() {
        player1 = new Player(GameColor.WHITE);
        player2 = new Player(GameColor.BLACK);

        player1.setName("Player 1");
        player2.setName("Player 2");
    }

    /**
     * Sets the name of player 1.
     * @param name the name of player 1
     */
    public void setPlayer1Name(String name) {
        player1.setName(name);
    }

    /**
     * Sets the name of player 2.
     * @param name the name of player 2
     */
    public void setPlayer2Name(String name) {
        player2.setName(name);
    }

    /**
     * Gets the name of player 1.
     * @return the name of player 1
     */
    public String getPlayer1Name() {
        return player1.getName();
    }

    /**
     * Gets the name of player 2.
     * @return the name of player 2
     */
    public String getPlayer2Name() {
        return player2.getName();
    }
}
