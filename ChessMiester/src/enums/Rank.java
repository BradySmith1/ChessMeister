/**
 * Enumeration file to represent the rank in chess.
 *
 * @author Zach Eanes (95%), Colton Brooks (5%)
 * @version 1.0
 */
package enums;

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

    public static Rank getRankFromIndex(int index) {
        Rank rank = null;
        switch (index) {
            case 0 :
                rank = R8;
                break;
            case 1 :
                rank = R7;
                break;
            case 2 :
                rank = R6;
                break;
            case 3 :
                rank = R5;
                break;
            case 4 :
                rank = R4;
                break;
            case 5 :
                rank = R3;
                break;
            case 6 :
                rank = R2;
                break;
            case 7 :
                rank = R1;
                break;
        }
        return rank;
    }
}
