package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;
import model.Piece;
import model.Position;
import model.Square;

/**
 * This class implements the BoardStrategy interface for a color command line interface.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public class BoardColorCLI implements BoardStrategy {

    private final String BLACK = "\u001b[1m\u001b[33m"; //actually purple with bold
    private final String WHITE = "\u001b[1m\u001b[35m"; //actually yellow with bold
    private final String BLACK_BACK = "\u001b[40m";
    private final String WHITE_BACK = "\u001b[47;1m";

    /**
     * Draws the game board using the specified strategy.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board, GameColor playerColor) {
        String unicode;
        String background = WHITE_BACK;
        //draws the board
        SquareIF[][] squares = board.getSquares();
        if(playerColor == GameColor.WHITE){
            printWhite(board, squares, background);
        }else{
            printBlack(board, squares, background);
        }
    }

    @Override
    public void highlight(BoardIF board, Position[] highlighted, GameColor color) {

    }

    private void printWhite(BoardIF board, SquareIF[][] squares, String background) {
        int number;
        number = 1;
        //draws the board
        for(int height = 0; height < board.getHeight(); height++){
            System.out.print(number + " ");
            //draws the squares
            for(int width = 0; width < board.getWidth(); width++){
                Square square = (Square) squares[height][width];
                printPiece(square, background);
                background = square.getColor() == GameColor.WHITE ? BLACK_BACK : WHITE_BACK;
            }
            background = background.equals(WHITE_BACK) ? BLACK_BACK : WHITE_BACK;
            System.out.print("\u001b[0m\n"); //ends the line. code is for reset
            number++;
        }
        System.out.print("     H      G      F      E      D      C      B      A\n");
    }

    private void printBlack(BoardIF board, SquareIF[][] squares, String background) {
        int number;
        number = 8;
        for(int height = board.getHeight() - 1; height >= 0; height--){
            System.out.print(number + " ");
            //draws the squares
            for(int width = board.getWidth() - 1; width >= 0; width--){
                Square square = (Square) squares[height][width];
                printPiece(square, background);
                background = square.getColor() == GameColor.WHITE ? BLACK_BACK : WHITE_BACK;
            }
            background = background.equals(WHITE_BACK) ? BLACK_BACK : WHITE_BACK;
            System.out.print("\u001b[0m\n"); //ends the line. code is for reset
            number--;
        }
        System.out.print("     A      B      C      D      E      F      G      H\n");
    }

    private void printPiece(Square square, String background) {
        String unicode;
        //draws the piece
        if (square.getPiece() != null) {
            if (((Piece) square.getPiece()).isWhite()) {
                unicode = WHITE;
            } else {
                unicode = BLACK;
            }
            System.out.print(unicode + background + "   " +
                    square.getPiece().getType().getLetter() + "   ");
        } else {
            System.out.print(background + "       ");
        }
    }
}
