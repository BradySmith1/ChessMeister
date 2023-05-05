/**
 * This is an interface used to observe the game board and notify the
 * necessary components.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import javafx.event.Event;

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
}
