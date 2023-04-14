package interfaces;

/**
 * Interface defining what methods a class that saves and loads boards from files should have
 */
public interface BoardSaverLoaderIF {

    /**
     * Method to save a game to a file
     * @param board the board / game to save
     * @param fileName  the name you want for the file
     */
    void saveGameToFile(BoardIF board, String fileName);

    /**
     * Method to load a game from a file
     * @param fileName  the name of the file to load from
     * @return  the board that you have loaded
     */
    BoardIF loadGameFromFile(String fileName);
}
