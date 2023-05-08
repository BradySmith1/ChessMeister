package gui_backend;

import javafx.scene.paint.Color;

/**
 * This class is responsible for defining the settings for the GUI
 * implementation of a chess game.
 *
 * @author Kaushal Patel (70%), Zach Eanes (30%)
 * @version 1.0 (done in sprint 3)
 */
public class SettingsGUI {
    /** The black square color. */
    private Color blackSquareColor;

    /** The white square color. */
    private Color whiteSquareColor;

    /** The highlight color. */
    private Color highlightColor;

    /** Undo/redo option. */
    private boolean undoRedo;

    /** Show moves option. */
    private boolean showMoves;

    /**
     * Constructor for the SettingsGUI class.
     */
    public SettingsGUI() {
        this.blackSquareColor = Color.BLACK;
        this.whiteSquareColor = Color.WHITE;
        this.highlightColor = Color.LIGHTPINK;
        this.undoRedo = false;
        this.showMoves = false;
    }

    /**
     * Gets the black square color.
     *
     * @return the black square color
     */
    public Color getBlackSquareColor() {
        return this.blackSquareColor;
    }

    /**
     * Sets the black square color.
     *
     * @param color the new black square color
     */
    public void setBlackSquareColor(Color color) {
        this.blackSquareColor = color;
    }

    /**
     * Gets the white square color.
     *
     * @return the white square color
     */

    public Color getWhiteSquareColor() {
        return this.whiteSquareColor;
    }

    /**
     * Sets the white square color.
     *
     * @param color the new white square color
     */
    public void setWhiteSquareColor(Color color) {
        this.whiteSquareColor = color;
    }

    /**
     * Gets the highlight color.
     *
     * @return the highlight color
     */
    public Color getHighlightColor() { return this.highlightColor; }

    /**
     * Sets the highlight color.
     *
     * @param color the new highlight color
     */
    public void setHighlightColor(Color color) { this.highlightColor = color; }

    /**
     * Gets the undo/redo option.
     *
     * @return the undo/redo option
     */
    public boolean getUndoRedo() {
        return this.undoRedo;
    }

    /**
     * Sets the undo/redo option.
     *
     * @param undoRedo the new undo/redo option
     */
    public void setUndoRedo(boolean undoRedo) {
        this.undoRedo = undoRedo;
    }

    /**
     * Gets the show moves option.
     *
     * @return the show moves option
     */
    public boolean getShowMoves() { return this.showMoves; }

    /**
     * Sets the show moves option.
     *
     * @param showMoves the new show moves option
     */
    public void setShowMoves(boolean showMoves) { this.showMoves = showMoves; }
}
