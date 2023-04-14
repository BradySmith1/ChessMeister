package enums;

/**
 * Enumeration class to represent the type of chess piece.
 *
 * @author Zach Eanes (100%)
 * @version 1.0
 */
public enum ChessPieceType {
    King('K', "King"), /* Used to represent the King piece. */
    Queen('Q', "Queen"), /* Used to represent the Queen piece. */
    Rook('R', "Rook"), /* Used to represent the Rook piece. */
    Bishop('B', "Bishop"), /* Used to represent the Bishop piece. */
    Knight('N', "Knight"), /* Used to represent the Knight piece. */
    Pawn('P', "Pawn"); /* Used to represent the Pawn piece. */

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

    /**
     * Used to identify the type of chess piece based on the letter.
     *
     * @param letter the letter to identify
     * @return the name for the piece type
     */
    public static String identify(String letter){
        return switch (letter) {
            case "K" -> "King";
            case "Q" -> "Queen";
            case "R" -> "Rook";
            case "B" -> "Bishop";
            case "N" -> "Knight";
            case "P" -> "Pawn";
            default -> "Invalid";
        };
    }
}
