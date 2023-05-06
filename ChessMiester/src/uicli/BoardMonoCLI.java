package uicli;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import model.Position;
import interfaces.SquareIF;
import model.Square;

import java.util.ArrayList;


/**
 * This class implements the BoardStrategy interface for a black and white command line interface.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public class BoardMonoCLI implements BoardStrategy {

    private String[] pieces = new String[8]; /*The array of pieces*/
    private String[] lines = new String[8]; /*The array of lines below and above the pieces*/
    private ArrayList<Position> highlighted; /*The array of highlighted positions*/
    private boolean highlight = false; /*Whether to highlight the board*/

    /**
     * Draws the game board using the specified strategy.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     * @param color the color of the player.
     */
    @Override
    public void draw(BoardIF board, GameColor color) {
        // Get the squares from the board.
        if (color == GameColor.BLACK) {
            printBlack(board);
        } else {
            printWhite(board);
        }
    }

    /**
     * Function for printing a highlighted board.
     *
     * @param board       the BoardIF object representing the game board to be drawn.
     * @param highlighted the array of positions that are highlighted.
     * @param color       the color of the player.
     */
    public void highlight(BoardIF board, ArrayList<Position> highlighted, GameColor color) {
        this.highlighted = highlighted;
        this.setHighlight(true);
    }

    /**
     * Sets the highlighted for the board
     *
     * @param boo whether to highlight the board
     */
    public void setHighlight(boolean boo){
        this.highlight = boo;
    }

    /**
     * Helper function for populating the pieces into the pieces array.
     *
     * @param board  the BoardIF object representing the game board to be drawn.
     * @param height the height of the board.
     */
    private void populateRow(BoardIF board, int height) {
        interfaces.SquareIF[][] squares = board.getSquares();
        for (int width = 0; width < board.getWidth(); width++) {
            SquareIF square = (SquareIF) squares[height][width];
            printPiece(square, width);
        }
        populateLine();
    }

    /**
     * Helper function for printing the lines into lines array.
     */
    private void populateLine() {
        String black = "#########";
        String white = "         ";
        String highlight = "---------";
        for (int width = 0; width < lines.length; width++) {
            lines[width] = "";
            if (pieces[width].substring(0, 1).equals(" ")) {
                lines[width] += white;
            } else if (pieces[width].substring(0, 1).equals("#")) {
                lines[width] += black;
            } else {
                lines[width] += highlight;
            }
        }
    }

    /**
     * Prints the board with the rotation oriented towards the white player.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     */
    private void printWhite(BoardIF board) {
        int number;
        number = 8;
        System.out.println("   --------------------------------" +
                "-----------------------------------------");
        // Print the board.
        for (int height = 0; height < board.getHeight(); height++) {
            populateRow(board, height);
            System.out.print("  | ");
            printLine(false);
            System.out.println("|");
            System.out.print(number + " | ");
            // Print the pieces.
            for (int width = 0; width < pieces.length; width++) {
                System.out.print(pieces[width]);
            }
            System.out.println("|");
            System.out.print("  | ");
            printLine(false);
            System.out.println("|");
            number--;
        }
        System.out.println("   -------------------------------------------------------------------------");
        System.out.println("        A        B        C        D        E        F        G        H");
    }

    /**
     * Prints the board with the rotation oriented towards the black player.
     *
     * @param board the BoardIF object representing the game board to be drawn.
     */
    private void printBlack(BoardIF board) {
        int number;
        number = 1;
        System.out.println("   --------------------------------" +
                "-----------------------------------------");
        // Print the board.
        for (int height = 0; height < board.getHeight(); height++) {
            populateRow(board, (board.getHeight() - height) - 1);
            System.out.print("  | ");
            printLine(true);
            System.out.println("|");
            System.out.print(height + 1 + " | ");
            for (int width = pieces.length - 1; width >= 0; width--) {
                System.out.print(pieces[width]);
            }
            System.out.println("|");
            System.out.print("  | ");
            printLine(true);
            System.out.println("|");
            number++;
        }
        System.out.println("   -------------------------------------------------------------------------");
        System.out.println("        H        G        F        E        D        C        B        A");
    }

    /**
     * Helper function for printing the lines in the array.
     *
     * @param reversed whether to print the lines in reverse.
     */
    private void printLine(boolean reversed) {
        if (reversed) {
            for (int width = lines.length - 1; width >= 0; width--) {
                System.out.print(lines[width]);
            }
        } else {
            for (String line : lines) {
                System.out.print(line);
            }
        }
    }

    /**
     * Helper function for printing the pieces in the array.
     *
     * @param square the square to print.
     * @param index  the index of the piece in the array.
     */
    private void printPiece(SquareIF square, int index) {
        boolean squareHighlighted = false;
        // Print the rank numbers.
        if (square.getPiece() != null) {
            String pieceString = String.valueOf(square.getPiece().getType().getLetter());
            if ((square.getPiece()).getColor() == GameColor.WHITE) {
                pieceString += "_W";
            } else {
                pieceString += "_B";
            }
            if (highlight) {
                squareHighlighted = checkHighlight(square);
            }
            if (squareHighlighted) {
                pieces[index] = ("---" + pieceString + "---");
            } else {
                if (((Square) square).isWhite()) {
                    pieces[index] = ("   " + pieceString + "   ");
                } else {
                    pieces[index] = ("###" + pieceString + "###");
                }
            }
        } else {
            if (highlight) {
                squareHighlighted = checkHighlight(square);
            }
            if (squareHighlighted) {
                pieces[index] = ("---------");
            } else {
                if (((Square) square).isWhite()) {
                    pieces[index] = ("         ");
                } else {
                    pieces[index] = ("#########");
                }
            }
        }
    }

    /**
     * Helper function for checking if a square is highlighted.
     *
     * @param square the square to check.
     * @return true if the square is highlighted, false otherwise.
     */
    private boolean checkHighlight(SquareIF square) {
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
