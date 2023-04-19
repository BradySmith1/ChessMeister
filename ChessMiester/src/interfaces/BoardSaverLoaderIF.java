package interfaces;

import controller.BoardMementoCaretaker;

public interface BoardSaverLoaderIF {

    void saveGameToFile(BoardMementoCaretaker caretaker, String fileName);

    BoardMementoCaretaker loadGameFromFile(String fileName);
}
