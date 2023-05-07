/**
 * This is an interface used to observe the game board and notify the
 * necessary components.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import gui.settingsmenu.SettingsMenuGUI;
import interfaces.PieceIF;

public interface CenterPaneObserver {

    /**
     * Notifies the observer that the pane has been updated.
     */
    void notifyPane(boolean notify);

    /**
     * Notifies the observer that a piece has been captured.
     *
     * @param piece the piece that was captured.
     */
    void notifyAddCapturedPiece(PieceIF piece);
}
