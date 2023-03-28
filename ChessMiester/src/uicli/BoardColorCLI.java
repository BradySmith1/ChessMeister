package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;
import model.Piece;
import model.Square;

/**
 * This class implements the BoardStrategy interface for a color command line interface.
 * @author Brady Smith %100
 */
public class BoardColorCLI implements BoardStrategy {


    /**
     * Draws the game board using the specified strategy.
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board) {
        String BLACK = "\u001b[1m\u001b[33m"; //actually purple with bold
        String WHITE = "\u001b[1m\u001b[35m"; //actually yellow with bold
        String BLACK_BACK = "\u001b[40m";
        String WHITE_BACK = "\u001b[47;1m";
        String unicode;
        String background = WHITE_BACK;
        String letters = "     A      B      C      D      E      F      G      H";
        int number = 8;
        //draws the board
        SquareIF[][] squares = board.getSquares();

        //draws the board
        for(int height = 0; height < board.getHeight(); height++){
            System.out.print(number + " ");
            //draws the squares
            for(int width = 0; width < board.getWidth(); width++){
                Square square = (Square) squares[height][width];
                //draws the piece
                if(square.getPiece() != null){
                    if(((Piece) square.getPiece()).isWhite()){
                        unicode = WHITE;
                    } else {
                        unicode = BLACK;
                    }
                    System.out.print(unicode + background + "   " +
                            square.getPiece().getType().getLetter() + "   ");
                }else{
                    System.out.print(background + "       ");
                }
                background = square.getColor() == GameColor.WHITE ? BLACK_BACK : WHITE_BACK;
            }
            background = background.equals(WHITE_BACK) ? BLACK_BACK : WHITE_BACK;
            System.out.print("\u001b[0m\n"); //ends the line. code is for reset
            number--;
        }
        System.out.print(letters);
    }
}
