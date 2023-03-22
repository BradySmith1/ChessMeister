package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;

/**
 * This class implements the BoardStrategy interface for a color command line interface.
 */
public class BoardColorCLI implements BoardStrategy {

    /**
     * Draws the game board using the specified strategy.
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board) {
        GameColor color = GameColor.WHITE;
        String uniCode = "\u001b[37m";
        char letter = 'A';
        int number = 8;
        SquareIF[][] squares = board.getSquares();
        for(int height = 0; height < board.getHeight(); height++){
            System.out.print(letter);
            for(int width = 0; width < board.getWidth(); width++){
                System.out.println(uniCode + squares[width][height].getPiece().getType().getLetter());
                color = color == GameColor.WHITE ? GameColor.BLACK : GameColor.WHITE;
                if(color == GameColor.WHITE){
                    uniCode = "\u001b[37m";
                } else {
                    uniCode = "\u001b[30m";
                }
            }
        }
    }
}
