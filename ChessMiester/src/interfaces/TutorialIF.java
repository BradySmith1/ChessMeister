package interfaces;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import model.Board;
import model.BoardSaverLoader;
import model.Piece;
import model.Position;
import uicli.BoardColorCLI;
import uicli.BoardMonoCLI;

import java.util.Random;
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
         System.out.println("""
         Let's start with some practice moves!
         Enter the file and rank of the square you want to move to, like a1 or A1.\s
         
         After a while, we'll spawn in a piece for you to go and capture! \s
         Make sure you're comfortable with the piece's movements before trying to capture!\s
         
         When you're ready to try the piece, press 'ENTER' to continue.
                 """);
         Scanner scan = new Scanner(System.in); // create scanner to read user input
         scan.nextLine(); // read line when user presses enter

         /* Load in the board and go through game loop */
         BoardSaverLoader loader = new BoardSaverLoader(); // create loader to load board
         Board board = (Board) loader.loadGameFromFile(file); // load board for bishop
         board.setDrawStrategy(new BoardColorCLI()); // make it pretty :)
         String input = "1"; // basic string for user input

         while (!input.equals("0")) { // loop game until user wants to quit
             board.draw(GameColor.WHITE); // draw board
             List<Position> moves = piece.getValidMoves(board, pos); // get valid moves for bishop
             System.out.print("Enter a move (Enter 0 to quit, " +
                              "1 to try capturing) ===> "); // prompt and read input
             input = scan.nextLine();
             if(input.equals("1")){ // user wants to spawn a pawn
                 board = this.spawnPiece(board, pos, piece); // spawn a random pawn
                 board.draw(GameColor.WHITE); // draw board
                 System.out.print("Enter a move ===> ");
                 input = scan.nextLine();
             }
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
                 if(!input.equals("0") && !input.equals("1"))
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
             } // end if
         } // end while
     } // end tutorialLoop


    /**
     * This method is responsible for spawning in a piece randomly on the board
     *
     * @param board the board to spawn the piece on
     * @param pos   the position of the piece to be spawned
     * @param piece the piece to be spawned
     * @return      the board with the piece spawned on it
     */
    public default Board spawnPiece(Board board, Position pos, Piece piece) {
         Board toReturn;
        System.out.println("Spawning a pawn, go capture it!");
        // spawn a pawn near middle of the board
        if(!piece.getType().equals(ChessPieceType.Pawn)){
            Files randFile = getRandomFile(); // get random file
//            while (pos.getFile().getFileNum() == randFile.getFileNum()){
//                randFile = getRandomFile(); // ensure the files not equal
//            }
            Rank randRank = getRandomRank(); // get random rank
//            while (pos.getRank().getIndex() == randRank.getIndex()) {
//                randRank = getRandomRank(); // ensure the ranks not equal
//            }

            while(board.getSquares()[randRank.getIndex()]
                    [randFile.getFileNum()].getPiece() != null){
                randFile = getRandomFile(); // ensure the files not equal
                randRank = getRandomRank(); // ensure the ranks not equal
            }
            if(piece.getType().equals(ChessPieceType.Bishop)){
                //TODO logic for a spawning a piece for a bishop to capture
            }
            board.getSquares()[randRank.getIndex()]
                    [randFile.getFileNum()].setPiece(new Piece(ChessPieceType.Pawn,
                    GameColor.BLACK));

            toReturn = board;
        }else { // piece is a pawn
            // TODO logic for a spawning a piece for a pawn to capture
            toReturn = board;

        }
        return toReturn;
    }
    /**
     * This method is responsible for getting a random file, which is used to
     * spawn a random piece in for players to capture.
     * @return a random file
     */
    public default Files getRandomFile(){
        Random rand = new Random();
        int fileNum = rand.nextInt(Files.values().length);
        return Files.values()[fileNum];
    }

    /**
     * Method is responsible for getting a random rank, which is used
     * to spawn a random piece in for the players to capture.
     * @return a random rank
     */
    public default Rank getRandomRank(){
        Random rand = new Random();
        int rankNum = rand.nextInt(Rank.values().length);
        return Rank.values()[rankNum];
    }
}
