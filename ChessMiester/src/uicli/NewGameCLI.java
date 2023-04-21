package uicli;

import controller.BoardMementoCaretaker;
import interfaces.*;
import model.Board;
import model.Piece;
import java.util.Scanner;

/**
 * This class implements the PlayIF interface for a command line interface.
 * @author Brady Smith (100%)
 */
public class NewGameCLI implements PlayIF {

    private Scanner scan; /*scanner for user input*/

    private String undo; /*undo move*/

    private String showMoves; /*show moves*/

    private String boardColor;  /*color of the board*/

    private PlayerIF player1; /*player 1*/

    private PlayerIF player2; /*player 2*/

    private Board board; /*board to play game on*/

    private PlayIF play; /*play object*/


    /**
     * Constructor for the main menu.
     *
     * @param scan       scanner for user input
     * @param boardColor color of the board
     * @param undo       moves that are undone
     * @param showMoves  way to show the moves occurred
     * @param player1    player 1
     * @param player2    player 2
     */
    public NewGameCLI(Scanner scan, String boardColor, String undo, String showMoves,
                      PlayerIF player1, PlayerIF player2) {
        this.scan = scan;
        this.boardColor = boardColor;
        this.undo = undo;
        this.showMoves = showMoves;
        setPlayer1(player1);
        setPlayer2(player2);
        initBoard();
        board.createState();
        BoardMementoCaretaker ct = new BoardMementoCaretaker(board.createMemento());
        setPlay(new PlayMoveCLI(scan, this.board, undo, showMoves, player1, player2, ct));
    }

    /**
     * Constructor for the game loop when a board is loaded in.
     *
     * @param scan       scanner for user input
     * @param boardColor color of the board
     * @param undo       undo move
     * @param showMoves  show moves
     * @param player1    player 1
     * @param player2    player 2
     * @param board      board to play game on
     * @param ct         caretaker for the board
     */
    public NewGameCLI(Scanner scan, String boardColor, String undo, String showMoves,
                      PlayerIF player1, PlayerIF player2, Board board, BoardMementoCaretaker ct) {
        this.scan = scan;
        this.boardColor = boardColor;
        this.undo = undo;
        this.showMoves = showMoves;
        setPlayer1(player1);
        setPlayer2(player2);
        this.board = board;
        setPlay(new PlayMoveCLI(scan, this.board, undo, showMoves, player1, player2, ct));
    }
    /**
     * This function is used to initialize the board.
     */
    private void initBoard() {
        BoardStrategy boardC;
        if(boardColor.equals("Mono")){
            boardC = new BoardMonoCLI();
        }else{
            boardC = new BoardColorCLI();
        }
        board = new Board(boardC); //create new board to play game on
        board.setup(); // initialize board
        player1.assignPieces(board); // assign pieces to player
        player2.assignPieces(board);
    }

    /**
     * Displays the main menu.
     */
    @Override
    public void show() {
        play.show();
    }

    /**
     * This function is used to set the move ui for the game.
     *
     * @param play play object to set
     */
    public void setPlay(PlayIF play){
        this.play = play;
    }

    /**
     * This function is used to set the player 1.
     *
     * @param player1 who to set to player one
     */
    public void setPlayer1(PlayerIF player1){
        this.player1 = player1;
    }

    /**
     * This function is used to set the player 2.
     *
     * @param player2 who to set to player 2
     */
    public void setPlayer2(PlayerIF player2) {
        this.player2 = player2;
    }
}
