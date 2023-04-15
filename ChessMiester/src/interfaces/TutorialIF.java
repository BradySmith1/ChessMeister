package interfaces;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import model.Board;
import model.BoardSaverLoader;
import model.Piece;
import model.Position;
import uicli.BoardColorCLI;

import java.util.List;
import java.util.Scanner;

public interface TutorialIF{
    /**
     * This method will be responsible for loading the game, and looping
     * the player through until the game is over.
     *
     * @param file the name of the file to be loaded.
     */
     default void tutorialLoop(String file, Piece piece, Position pos) {
         /* wait for user to press any key to continue */
         System.out.println("When you're ready to try the piece, press 'ENTER' to continue. ");
         Scanner scan = new Scanner(System.in); // create scanner to read user input
         scan.nextLine(); // read line when user presses enter

         BoardSaverLoader loader = new BoardSaverLoader(); // create loader to load board
         Board board = (Board) loader.loadGameFromFile(file); // load board for bishop
         board.setDrawStrategy(new BoardColorCLI()); // make it pretty :)
         String input = "1"; // basic string for user input
         while (!input.equals("0")) { // loop game until user wants to quit
             board.draw(GameColor.WHITE); // draw board
             List<Position> moves = piece.getValidMoves(board, pos); // get valid moves for bishop
             for(Position move : moves){
                 System.out.print(move.getFile().getFileChar());
                 System.out.println(move.getRank().getDisplayNum());
             }
             System.out.print("Enter a move (Enter 0 to quit) ===> "); // prompt and read input
             input = scan.nextLine();
             System.out.println(input);

             Files toF = null;
             Rank toR = null;
             try {
                 // file the file from the input
                 toF = Files.valueOf(input.substring(0, 1).toUpperCase());

                 // find the rank from the input
                 toR = null;
                 Rank[] ranks = Rank.values();
                 for (Rank r : ranks) {
                     if (r.getDisplayNum() == Integer.parseInt(input.substring(1, 2))) {
                         toR = r;
                     }
                 }
             } catch (Exception e) {
                 System.out.println("Invalid input. Expect a file and rank (EX: A1).");
             }

             // make the move
             if(toR != null && toF != null){;
                 Position newPos = new Position(toR, toF);
                 if(moves.contains(newPos)){
                     // remove piece from old position
                     board.getSquares()[pos.getRank().getIndex()]
                                       [pos.getFile().getFileNum()].clear();
                     // set piece to new position
                     board.getSquares()[toR.getIndex()][toF.getFileNum()].setPiece(piece);
                     pos = newPos;
                 } else {
                     System.out.println("Invalid move. Try again.");
                 }
             }
         }
     }
}
