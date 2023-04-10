/**
 * This class implements the BoardStrategy interface for a black and white command line interface.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;
import model.Board;
import model.Piece;
import model.Square;

public class BoardMonoCLI implements BoardStrategy {

    /**
     * Draws the game board using the specified strategy.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board, GameColor color) {
        boolean first;
        // Get the squares from the board.
        SquareIF[][] squares = board.getSquares();
        if(color == GameColor.WHITE){
            System.out.println("   --------------------------------" +
                    "-----------------------------------------");
            // Print the board.
            for(int height = board.getHeight() - 1; height >= 0; height--) {
                printLine(height, 1);
                first = true;
                // Print the pieces.
                for (int width = board.getWidth() - 1; width >= 0; width--) {
                    if(first){
                        System.out.print((board.getHeight() - height) + " | ");
                        first = false;
                    }
                    printPiece(squares, height, width);
                }
                System.out.println("|");
                printLine(height, 1);
            }
        }else{
            System.out.println("   --------------------------------" +
                    "-----------------------------------------");
            // Print the board.
            for(int height = 0; height < board.getHeight(); height++) {
                printLine(height, 0);
                first = true;
                // Print the pieces.
                for (int width = 0; width < board.getWidth(); width++) {
                    if(first){
                        System.out.print((board.getHeight() - height) + " | ");
                        first = false;
                    }
                    printPiece(squares, height, width);
                }
                System.out.println("|");
                printLine(height, 0);
            }
        }
        System.out.println("   -------------------------------------------------------------------------");
        System.out.println("        A        B        C        D        E        F        G        H");
    }

    private void printLine(int height, int rotated){
        String line2 = "  | #########         #########         #########         #########         |";
        String line1 = "  |          #########         #########         #########         #########|";
        if (height % 2 == rotated) {
            System.out.println(line1);
        } else {
            System.out.println(line2);
        }
    }

    private void printPiece(SquareIF[][] squares, int height, int width){
        Square square = (Square) squares[height][width];
        // Print the rank numbers.
        if(square.getPiece() != null){
            String pieceString = String.valueOf(square.getPiece().getType().getLetter());
            if(((Piece) square.getPiece()).getColor() == GameColor.WHITE){
                pieceString += "_W";
            }else{
                pieceString += "_B";
            }
            if(square.isWhite()) {
                System.out.print("   " + pieceString + "   ");
            }else{
                System.out.print("###" + pieceString + "###");
            }
        } else {
            if(square.isWhite()) {
                System.out.print("         ");
            }else{
                System.out.print("#########");
            }
        }
    }
}
