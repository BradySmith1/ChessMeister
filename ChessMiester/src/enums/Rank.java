package enums;

/**
 * Enumeration file to represent the rank in chess.
 *
 * @author Zach Eanes (100%)
 * @version 1.0
 */
public enum Rank {
    R1(1, 7), /* Represents the different places on a chess board. */
    R2(2, 6),
    R3(3, 5),
    R4(4, 4),
    R5(5, 3),
    R6(6, 2),
    R7(7, 1),
    R8(8, 0);

    public final int displayNum; /* Number to be displayed to users. */
    public final int index; /* Number used to index throughout chess. */

    /**
     * Constructor method for the rank enumeration.
     *
     * @param displayNum number to be displayed to the users.
     * @param index      number to be used to index throughout program.
     */
    Rank(int displayNum, int index){
        this.displayNum = displayNum;
        this.index = index;
    }

    /**
     * Getter method for the displayNum.
     *
     * @return displayNum of the current rank.
     */
    public int getDisplayNum() {return displayNum;}

    /**
     * Getter method for the index.
     *
     * @return index of the current rank.
     */
    public int getIndex() {return index;}
}
