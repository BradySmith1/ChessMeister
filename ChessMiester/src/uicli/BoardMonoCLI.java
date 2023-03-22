package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;
import model.Square;

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
        //System.out.println("      A       B       C       D       E       F       G       H");
        String line2 = "  |-------|       |-------|       |-------|       |-------|       |";
        String line1 = "  |       |-------|       |-------|       |-------|       |-------|";
        for(int height = 0; height < board.getHeight(); height++){
            System.out.println("   ------- ------- ------- ------- ------- ------- ------- -------");
            if(height % 2 == 0){
                System.out.println(line1);
            } else {
                System.out.println(line2);
            }
            for(int width = 0; width < board.getWidth(); width++){
                Square square = (Square) squares[height][width];
                if(width == 0){
                    System.out.print((board.getHeight() - height) + " ");
                }
                if(square.getPiece() != null){
                    if(square.getColor() == GameColor.WHITE) {
                        System.out.print("|   " + square.getPiece().getType().getLetter() + "   ");
                    }else{
                        System.out.print("|---" + square.getPiece().getType().getLetter() + "---");
                    }
                } else {
                    if(square.getColor() == GameColor.WHITE) {
                        System.out.print("|       ");
                    }else{
                        System.out.print("|-------");
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
        System.out.println("   ------- ------- ------- ------- ------- ------- ------- -------");
        System.out.println("      A       B       C       D       E       F       G       H");
    }
}
