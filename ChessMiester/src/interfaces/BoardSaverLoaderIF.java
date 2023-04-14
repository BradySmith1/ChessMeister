package interfaces;

/**
 * Interface defining what methods a class that saves and loads boards from files should have
 */
public interface BoardSaverLoaderIF {

    void saveGameToFile(BoardIF board, String fileName);

    BoardIF loadGameFromFile(String fileName);
}
