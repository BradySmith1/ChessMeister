package model;

import interfaces.PlayerIF;
import enums.GameColor;
import interfaces.PieceIF;

import java.util.ArrayList;

/**
 * This class represents a player in the game which holds a list of their pieces,
 * list of pieces captured, and the color of the player themselves.
 *
 * @author Brady Smith (85%), Kaushal Patel (15%)
 * @version 1.0
 */
public class Player implements PlayerIF {

    /** The name of the player */
    private String name;

    /** The color of the player */
    private GameColor color;

    /** The pieces the player has */
    private ArrayList<PieceIF> pieces;

    /** The pieces the player has captured */
    private ArrayList<PieceIF> capturedPieces;

    /** The number of wins the player has */
    private int numberofWins;

    /** The number of losses the player has */
    private int numberofLosses;

    /** The number of draws the player has */
    private int numberofDraws;

    /** The number of moves the player has made */
    private int moveCount;

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
     * @return The list of player's pieces.
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
    @Override
    public void displayCapturedPieces(){
        System.out.print("\n" + this.getColor().toString() + " captured pieces: ");
        for(PieceIF piece : capturedPieces){
            System.out.print(piece.getType().getLetter() + " ");
        }
        System.out.print("\n");
    }

    /**
     * Getter method for the players game color.
     *
     * @return color of the current player
     */
    @Override
    public GameColor getColor(){
        return this.color;
    }

    /**
     * Getter method for the players name.
     * @return : name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method for the players name.
     * @param name : name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Increases the number of losses the player has.
     */
    @Override
    public void increaseLosses() {
        this.numberofLosses++;
    }

    /**
     * Increases the number of wins the player has.
     */
    @Override
    public void increaseWins() {
        this.numberofWins++;
    }

    /**
     * Increases the number of draws the player has.
     */
    @Override
    public void increaseDraws() {
        this.numberofDraws++;
    }

    /**
     * Displays the number of wins, losses, and draws the player has.
     */
    @Override
    public void displayStats() {
        System.out.println("--------------------");
        System.out.println("Stats for " + this.name + ":");
        System.out.println("--------------------");
        System.out.println("Wins: " + this.numberofWins);
        System.out.println("Losses: " + this.numberofLosses);
        System.out.println("Draws: " + this.numberofDraws);
    }

    /**
     * Increases the number of moves the player has made.
     */
    @Override
    public void increaseMoveCount() {
        this.moveCount++;
    }

    /**
     * Returns the number of moves the player has made.
     *
     * @return : The number of moves the player has made.
     */
    @Override
    public int getMoveCount() {
        return this.moveCount;
    }

    /**
     * Returns the king of the player.
     * @return The king of the player.
     */
    @Override
    public PieceIF getKing() {
        PieceIF p = null;
        for(PieceIF piece : pieces){
            if(piece.getType().getLetter() == 'K'){
                p = piece;
            }
        }
        return p;
    }

    /**
     * Returns the number of wins the player has.
     * @return The number of wins the player has.
     */
    public int getWins(){
        return this.numberofWins;
    }

    /**
     * Returns the number of losses the player has.
     * @return The number of losses the player has.
     */
    public int getLosses(){
        return this.numberofLosses;
    }

    /**
     * Returns the number of draws the player has.
     * @return The number of draws the player has.
     */
    public int getDraws(){
        return this.numberofDraws;
    }
}
