package uicli;

import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;

/**
 * This class implements the BoardStrategy interface for a black and white command line interface.
 */
public class BoardMonoCLI implements BoardStrategy {

    /**
     * Draws the game board using the specified strategy.
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board) {
        SquareIF[][] squares = board.getSquares();
        System.out.println("   A   B   C   D   E   F   G   H  ");
        for(int height = 0; height < board.getHeight(); height++){
            System.out.println("---------------------------------");
            for(int width = 0; width < board.getWidth(); width++){
                if(squares[width][height].getPiece() != null){
                    System.out.print("| " + squares[width][height].getPiece().getType().getLetter() + " ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
    }
}
