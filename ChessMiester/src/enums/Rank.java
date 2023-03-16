/**
 * Enumeration file to represent the rank in chess.
 *
 * @author Zach Eanes (100%)
 */
package enums;

public enum Rank {
    R1(1, 0),
    R2(2, 1),
    R3(3, 2),
    R4(4, 3),
    R5(5, 4),
    R6(6, 5),
    R7(7, 6),
    R8(8, 7);

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
