package interfaces;

import controller.BoardMementoCaretaker;

public interface BoardSaverLoaderIF {

    void saveGameToFile(BoardMementoCaretaker caretaker, String fileName);

    BoardIF loadGameFromFile(String fileName);
}
