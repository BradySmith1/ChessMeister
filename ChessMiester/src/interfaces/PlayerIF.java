package interfaces;

import enums.GameColor;

import java.util.ArrayList;

/**
 * An interface for a player in a chess game. Each player has a color, a set of
 * pieces, and a set of captured pieces.
 *
 * @author Brady Smith 100%
 * @version 1.0
 */
public interface PlayerIF {
    /**
     * Returns the color of the player.
     *
     * @return The color of the player.
     */
    public GameColor getPieceColor();

    /**
     * Returns the pieces that the player has on the board.
     *
     * @return The pieces that the player has on the board.
     */
    public ArrayList<PieceIF> getPieces();

    /**
     * Returns the pieces that the player has captured.
     *
     * @return The pieces that the player has captured.
     */
    public ArrayList<PieceIF> getCapturedPieces();

    /**
     * Adds a piece to the player's set of captured pieces.
     *
     * @param piece The piece to add.
     */
    public void addCapturedPiece(PieceIF piece);

    /**
     * Adds a piece to the player's set of pieces.
     *
     * @param piece The piece to add.
     */
    public void addPiece(PieceIF piece);

    /**
     * Removes a piece from the player's set of pieces.
     * @return : The piece that was removed.
     */
    public GameColor getColor();

}
