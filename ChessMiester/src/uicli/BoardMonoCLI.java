package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.SquareIF;
import model.Board;
import model.Piece;
import model.Position;
import model.Square;
//TODO documentation
/**
 * This class implements the BoardStrategy interface for a black and white command line interface.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public class BoardMonoCLI implements BoardStrategy {

    private String[] pieces = new String[8];
    private String[] lines = new String[8];
    private Position[] highlighted;
    private boolean highlight = false;

    /**
     * Draws the game board using the specified strategy.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board, GameColor color) {
        // Get the squares from the board.
        if(color == GameColor.WHITE){
            printWhite(board);
        }else{
            printBlack(board);
        }
    }

    public void highlight(BoardIF board, Position[] highlighted, GameColor color) {
        this.highlighted = highlighted;
        this.highlight = true;
        if(color == GameColor.WHITE){
            printWhite(board);
        }else{
            printBlack(board);
        }
    }

    private void populateRow(BoardIF board, int height){
        SquareIF[][] squares = board.getSquares();
        for(int width = 0; width < board.getWidth(); width++){
            Square square = (Square) squares[height][width];
            printPiece(square, width);
        }
        populateLine();
    }

    private void populateLine(){
        String black = "#########";
        String white = "         ";
        String highlight = "---------";
        for(int width = 0; width < lines.length; width++){
            lines[width] = "";
            String temp = pieces[width].substring(0, 1);
            if(pieces[width].substring(0, 1).equals(" ")){
                lines[width] += white;
            }else if(pieces[width].substring(0, 1).equals("#")){
                    lines[width] += black;
            }else{
                    lines[width] += highlight;
            }
        }
    }

    /**
     * Prints the board with the rotation oriented towards the white player.
     * @param board the BoardIF object representing the game board to be drawn.
     */
    private void printWhite(BoardIF board) {
        System.out.println("   --------------------------------" +
                "-----------------------------------------");
        // Print the board.
        for(int height = board.getHeight() - 1; height >= 0; height--) {
            populateRow(board, height);
            System.out.print("  | ");
            printLine(true);
            System.out.println("|");
            System.out.print(board.getHeight() - height + " | ");
            // Print the pieces.
            for (int width = pieces.length - 1; width >= 0; width--) {
                System.out.print(pieces[width]);
            }
            System.out.println("|");
            System.out.print("  | ");
            printLine(true);
            System.out.println("|");
        }
        System.out.println("   -------------------------------------------------------------------------");
        System.out.println("        H        G        F        E        D        C        B        A");
    }

    /**
     * Prints the board with the rotation oriented towards the black player.
     * @param board the BoardIF object representing the game board to be drawn.
     */
    private void printBlack(BoardIF board) {
        System.out.println("   --------------------------------" +
                "-----------------------------------------");
        // Print the board.
        for(int height = 0; height < board.getHeight(); height++) {
            populateRow(board, height);
            System.out.print("  | ");
            printLine(false);
            System.out.println("|");
            System.out.print(board.getHeight() - height + " | ");
            for (int width = 0; width < pieces.length; width++) {
                System.out.print(pieces[width]);
            }
            System.out.println("|");
            System.out.print("  | ");
            printLine(false);
            System.out.println("|");
        }
        System.out.println("   -------------------------------------------------------------------------");
        System.out.println("        A        B        C        D        E        F        G        H");
    }

    private void printLine(boolean reversed){
        if(reversed){
            for (int width = lines.length - 1; width >= 0; width--) {
                System.out.print(lines[width]);
            }
        }else{
            for(String line : lines){
                System.out.print(line);
            }
        }
    }

    private void printPiece(Square square, int index){
        boolean squareHighlighted = false;
        // Print the rank numbers.
        if(square.getPiece() != null){
            String pieceString = String.valueOf(square.getPiece().getType().getLetter());
            if((square.getPiece()).getColor() == GameColor.WHITE){
                pieceString += "_W";
            }else{
                pieceString += "_B";
            }
            if(highlight) {
                squareHighlighted = checkHighlight(square);
            }
            if(squareHighlighted) {
                pieces[index] = ("---" + pieceString + "---");
            }else {
                if (square.isWhite()) {
                    pieces[index] = ("   " + pieceString + "   ");
                } else {
                    pieces[index] = ("###" + pieceString + "###");
                }
            }
        } else {
            if(square.isWhite()) {
                pieces[index] = ("         ");
            }else{
                pieces[index] = ("#########");
            }
        }
    }

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
