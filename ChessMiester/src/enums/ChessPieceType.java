/**
 * Enumeration class to represent the type of chess piece.
 *
 * @author Zach Eanes (100%)
 * @version 1.0
 */
package enums;

public enum ChessPieceType {
    King('K', "King"),
    Queen('Q', "Queen"),
    Rook('R', "Rook"),
    Bishop('B', "Bishop"),
    Knight('N', "Knight"),
    Pawn('P', "Pawn");

    public final char letter; /* Used to represent letter for piece. */
    public final String type; /* Used to represent type name of piece. */

    /**
     * Constructor method for the ChessPieceType enumeration.
     *
     * @param letter single letter used as shorthand for the piece itself
     * @param type   full name/type for the piece itself.
     */
    private ChessPieceType(char letter, String type){
        this.letter = letter;
        this.type = type;
    }

    /**
     * Used to get letter of current chess piece.
     *
     * @return letter to represent chess piece
     */
    public char getLetter(){return this.letter;}

    /**
     * Used to get type/name of current chess piece.
     *
     * @return string to represent chess piece.
     */
    public String getType(){return this.type;}
}
