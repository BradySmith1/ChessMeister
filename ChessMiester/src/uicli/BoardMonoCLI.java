package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;
import model.Piece;
import model.Square;

/**
 * This class implements the BoardStrategy interface for a black and white command line interface.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public class BoardMonoCLI implements BoardStrategy {

    /**
     * Draws the game board using the specified strategy.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board) {
        // Get the squares from the board.
        SquareIF[][] squares = board.getSquares();
        String line2 = "  | #######         #######         #######         #######         |";
        String line1 = "  |         #######         #######         #######         ####### |";

        System.out.println("   --------------------------------" +
                "---------------------------------");
        // Print the board.
        for(int height = 0; height < board.getHeight(); height++){
            if(height % 2 == 0){
                System.out.println(line1);
            } else {
                System.out.println(line2);
            }
            // Print the pieces.
            for(int width = 0; width < board.getWidth(); width++){
                Square square = (Square) squares[height][width];
                // Print the rank numbers.
                if(width == 0){
                    System.out.print((board.getHeight() - height) + " | ");
                }
                if(square.getPiece() != null){
                    String pieceString = String.valueOf(square.getPiece().getType().getLetter());
                    if(((Piece) square.getPiece()).getColor() == GameColor.WHITE){
                        pieceString += "_W";
                    }else{
                        pieceString += "_B";
                    }
                    if(square.isWhite()) {
                        System.out.print("  " + pieceString + "   ");
                    }else{
                        System.out.print("##" + pieceString + "## ");
                    }
                } else {
                    if(square.isWhite()) {
                        System.out.print("        ");
                    }else{
                        System.out.print("####### ");
                    }
                }
            }
            System.out.println("|");
            if(height % 2 == 0){
                System.out.println(line1);
            } else {
                System.out.println(line2);
            }
        }
        System.out.println("   -----------------------------------------------------------------");
        System.out.println("      A       B       C       D       E       F       G       H");
    }
}
