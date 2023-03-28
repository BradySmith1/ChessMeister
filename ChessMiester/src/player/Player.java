/**
 * This class represents a player in the game which holds a list of their pieces,
 * list of pieces captured, and the color of the player themselves.
 *
 * @author Brady Smith (85%), Kaushal Patel (15%)
 * @version 1.0
 */
package player;

import interfaces.PlayerIF;
import enums.GameColor;
import interfaces.PieceIF;

import java.util.ArrayList;

public class Player implements PlayerIF{
    /** The color of the player */
    private GameColor color;

    /** The pieces the player has */
    private ArrayList<PieceIF> pieces;

    /** The pieces the player has captured */
    private ArrayList<PieceIF> capturedPieces;

    /**
     * Constructor method for the Player class.
     *
     * @param color The color of the player.
     */
    public Player(GameColor color) {
        this.color = color;
        this.pieces = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
    }

    /**
     * Class that returns the color of the player.
     *
     * @return The color of the player.
     */
    @Override
    public GameColor getPieceColor() {
        return this.color;
    }

    /**
     * Returns the list of pieces.
     *
     * @return
     */
    @Override
    public ArrayList<PieceIF> getPieces() {
        return this.pieces;
    }

    /**
     * Returns the list of captured pieces.
     *
     * @return The list of captured pieces.
     */
    @Override
    public ArrayList<PieceIF> getCapturedPieces(){
        return this.capturedPieces;
    }

    /**
     * Adds a piece to the list of user pieces.
     *
     * @param piece The piece to add.
     */
    @Override
    public void addPiece(PieceIF piece) {
        this.pieces.add(piece);
    }

    /**
     * Adds a piece to the list of captured pieces.
     *
     * @param piece The piece to add.
     */
    @Override
    public void addCapturedPiece(PieceIF piece){
        this.capturedPieces.add(piece);
    }

    /**
     * Method to display the pieces that the player has captured.
     */
    public void displayCapturedPieces(){
        System.out.print(this.getColor().toString() + " captured pieces: ");
        for(PieceIF piece : capturedPieces){
            System.out.print(piece.getType().getLetter() + " ");
        }
    }
    /**
     * Getter method for the players game color.
     *
     * @return color of the current player
     */
    public GameColor getColor(){
        return this.color;
    }
}
