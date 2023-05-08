package gui.gameboard;

import interfaces.PieceIF;
import javafx.event.Event;
import model.Position;

import java.io.PrintWriter;
import java.util.List;

/**
 * This is an interface used to observe the game board and notify the
 * necessary components.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
public interface GameBoardObserver {

    /**
     * Notifies the observer that a left click has occurred.
     *
     * @param event the event that occurred
     */
    void notifyLeftClick(Event event);

    /**
     * Notifies the observer that a right click has occurred.
     *
     * @param event the event that occurred
     */
    void notifyRightClick(Event event);

    /**
     * Notifies the observer that a piece is being moved.
     *
     * @param event the event that occurred
     */
    List<Position> notifyPieceMoving(Event event);

    /**
     * Notifies the observer that a piece has been captured.
     *
     * @param piece the piece that was captured.
     */
    void notifyAddCapturedPiece(PieceIF piece);

    /**
     * Notifies the observer that the board has been altered and needs to be saved.
     */
    void notifyBoardLoader(Event event);

    /**
     * Notifies the observer that the undo button has been pressed.
     */
    void notifyUndo();

    /**
     * Notifies the observer that the redo button has been pressed.
     */
    void notifyRedo();

    /**
     * Notifies the observer that the save button has been pressed.
     */
    void notifySaveGame(PrintWriter writer);
}
