/**
 * Enumeration class to represent the file types.
 *
 * @author Zach Eanes (100%)
 * @version 1.0
 */
package enums;
public enum Files {
    A('a', 0), /* Represents the different positions on a chess board. */
    B('b', 1),
    C('c', 2),
    D('d', 3),
    E('e', 4),
    F('f', 5),
    G('g', 6),
    H('h', 7);

    public final char fileChar; /* Represents the char for the file. */
    public final int  fileNum;  /* Represents the num for the file. */

    /**
     * Constructor method for the Files enumeration.
     *
     * @param fileChar character to represent file
     * @param fileNum  num to represent the file
     */
    private Files(char fileChar, int fileNum) {
        this.fileChar = fileChar;
        this.fileNum = fileNum;
    }

    /**
     * Getter method for the file character.
     *
     * @return character to represent the current file
     */
    public char getFileChar() {return fileChar;}

    /**
     * Getter method for the file number.
     *
     * @return number to represent the current file
     */

    public int getFileNum() {return fileNum;}
}
