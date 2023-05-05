package gui.gameboard;

import javafx.event.Event;

public interface GameBoardObserver {
    void notifyLeftClick(Event event);

    void notifyRightClick(Event event);
}
