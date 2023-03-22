package model;

import enums.Files;
import enums.Rank;


/**
 * This class represents a position on the game board.
 * @author Brady Smith %100
 */
public class Position {

    /**
     * The rank of the position.
     */
    private Rank r;

    /**
     * The file of the position.
     */
    private Files f;

    /**
     * Creates a new position with the specified rank and file.
     * @param r the rank of the position.
     * @param f the file of the position.
     */
    public Position(Rank r, Files f) {
        this.r = r;
        this.f = f;
    }

    /**
     * Creates a new position with no rank or file.
     */
    public Position() {
        this.r = null;
        this.f = null;
    }

    /**
     * Gets the rank of the position.
     * @return the rank of the position.
     */
    public Rank getRank() {
        return r;
    }

    /**
     * Gets the file of the position.
     * @return the file of the position.
     */
    public Files getFile() {
        return f;
    }

    /**
     * Sets the rank of the position.
     * @param r the rank of the position.
     */
    public void setRank(Rank r) {
        this.r = r;
    }

    /**
     * Sets the file of the position.
     * @param f the file of the position.
     */
    public void setFile(Files f) {
        this.f = f;
    }
}
