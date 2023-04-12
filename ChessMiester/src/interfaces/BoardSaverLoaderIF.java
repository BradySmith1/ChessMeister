package interfaces;

public interface BoardSaverLoaderIF {

    void saveGameToFile(BoardIF board, String fileName);

    BoardIF loadGameFromFile(String fileName);
}
