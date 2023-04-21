package interfaces;

import controller.BoardMementoCaretaker;

/**
 * This interface is responsible for saving and loading a game.
 *
 * @author Colton Brooks (100%)
 * @version 1.0 (done in sprint 2)
 */
public interface BoardSaverLoaderIF {

    /**
    * Saves the game to a file.
     *
     * @param caretaker caretaker that holds all states of a file
     * @param fileName  the name of the file to save to
     */
    void saveGameToFile(BoardMementoCaretaker caretaker, String fileName);

    /**
     * Loads a game from a file.
     *
     * @param fileName the name of the file to load from
     * @return the caretaker that holds all states of a file
     */
    BoardMementoCaretaker loadGameFromFile(String fileName);
}
