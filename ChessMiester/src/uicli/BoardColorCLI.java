package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;
import javafx.geometry.Pos;
import model.Position;
import model.Square;

import java.util.ArrayList;

/**
 * This class implements the BoardStrategy interface for a color command line interface.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public class BoardColorCLI implements BoardStrategy {

    private final String BLACK = "\u001b[1m\u001b[33m"; /*actually white with bold*/
    private final String WHITE = "\u001b[1m\u001b[35m"; /*actually black with bold*/
    private final String BLACK_BACK = "\u001b[40m"; /*Black unicode*/
    private final String WHITE_BACK = "\u001b[47;1m"; /*White unicode*/
    private ArrayList<Position> highlighted; /*The array of highlighted positions*/
    private boolean highlight = false; /*Whether to highlight the board*/
    private final String SHOW_MOVES_PURPLE = "\u001b[1m\u001b[45m";/*Purple unicode for highlight*/

    /**
     * Draws the game board using the specified strategy.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board, GameColor playerColor) {
        String background = WHITE_BACK;
        //draws the board
        SquareIF[][] squares = board.getSquares();
        if(playerColor == GameColor.BLACK){
            //printWhite(board, squares, background);
            printBlack(board, squares, background);
        }else{
            printWhite(board, squares, background);
        }
    }

    /**
     * Function for printing a highlighted board.
     *
     * @param board       the BoardIF object representing the game board to be drawn.
     * @param highlighted the array of positions that are highlighted.
     * @param color       the color of the player.
     */
    @Override
    public void highlight(BoardIF board, ArrayList<Position> highlighted, GameColor color) {
        this.highlighted = highlighted;
        this.setHighlight(true);
        String background = WHITE_BACK;
        //draws the board
        SquareIF[][] squares = board.getSquares();
        if(color == GameColor.WHITE){
            printWhite(board, squares, background);
        }else{
            printBlack(board, squares, background);
        }
    }

    /**
     * Sets whether to highlight the board or not
     * @param boo true to highlight the board, false to not
     */
    @Override
    public void setHighlight(boolean boo) {
        this.highlight = boo;
    }

    /**
     * The function for printing the board for whites move.
     *
     * @param board      the BoardIF object representing the game board to be drawn.
     * @param squares    the array of squares on the board.
     * @param background the background color of the square.
     */
    private void printWhite(BoardIF board, SquareIF[][] squares, String background) {
        int number = 8;
        String temp_background = background;
        boolean squareHighlighted = false;
        //draws the board
        for(int height = 0; height < board.getHeight(); height++){
            System.out.print(number + " ");
            //draws the squares
            for(int width = 0; width < board.getWidth(); width++){
                Square square = (Square) squares[height][width];
                if(highlight){
                    squareHighlighted = checkHighlight(square);
                }
                if(squareHighlighted){
                    background = SHOW_MOVES_PURPLE;
                    printPiece(square, background);
                    background = temp_background;
                }
                else{
                    printPiece(square, background);
                }
                background = square.getColor() == GameColor.WHITE ? BLACK_BACK : WHITE_BACK;
            }
            background = background.equals(WHITE_BACK) ? BLACK_BACK : WHITE_BACK;
            System.out.print("\u001b[0m\n"); //ends the line. code is for reset
            number--;
        }
        System.out.print("     A      B      C      D      E      F      G      H\n");
    }

    /**
     * The function for printing the board when it is blacks move.
     *
     * @param board      the BoardIF object representing the game board to be drawn.
     * @param squares    the array of squares on the board.
     * @param background the background color of the square.
     */
    private void printBlack(BoardIF board, SquareIF[][] squares, String background) {
        int number = 1;
        String temp_background = background;
        boolean squareHighlighted = false;
        for(int height = board.getHeight() - 1; height >= 0; height--){
            System.out.print((board.getHeight() - height) + " "); // wrong
            //draws the squares
            for(int width = board.getWidth() - 1; width >= 0; width--){
                Square square = (Square) squares[height][width];
                if(highlight){
                    squareHighlighted = checkHighlight(square);
                }
                if(squareHighlighted){
                    background = SHOW_MOVES_PURPLE;
                    printPiece(square, background);
                    background = temp_background;
                }
                else{
                    printPiece(square, background);
                }
                background = square.getColor() == GameColor.WHITE ? BLACK_BACK : WHITE_BACK;
            }
            background = background.equals(WHITE_BACK) ? BLACK_BACK : WHITE_BACK;
            System.out.print("\u001b[0m\n"); //ends the line. code is for reset
            number++;
        }
        System.out.print("     H      G      F      E      D      C      B      A\n");
    }

    /**
     * The function for printing a piece.
     *
     * @param square     the square that the piece is on.
     * @param background the background color of the square.
     */
    private void printPiece(Square square, String background) {
        String unicode;
        //draws the piece
        if (square.getPiece() != null) {
            if ((square.getPiece()).isWhite()) {
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

    /**
     * The function for checking if a square is highlighted for valid moves.
     *
     * @param square the square to check.
     * @return true if the square is highlighted, false otherwise.
     */
    private boolean checkHighlight(Square square) {
        boolean result = false;
        for (Position position : highlighted) {
            if (square.getPosition().equals(position)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
