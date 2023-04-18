package uicli;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import model.Board;
import model.BoardSaverLoader;
import model.Piece;
import model.Position;

import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class TutorialCLI {
    /**
     * This method will be responsible for loading the game, and looping
     * the player through until the game is over.
     *
     * @param file the name of the file to be loaded.
     */

    BoardSaverLoader loader = new BoardSaverLoader(); // create loader to load board
    public void tutorialLoop(String file, Piece piece, Position pos) {
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
        Board board = (Board) this.loader.loadGameFromFile(file); // load board for bishop
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
    public Board spawnPiece(Board board, Position pos, Piece piece) {
        Board toReturn;
        String prompt = "Spawning a piece, go capture it!";
        // spawn a pawn near middle of the board
        if(!piece.getType().equals(ChessPieceType.Pawn) &&
           !piece.getType().equals(ChessPieceType.Bishop)){ // piece is not a pawn or bishop
            Files randFile = getRandomFile(); // get random file
            Rank randRank = getRandomRank(); // get random rank

            while(board.getSquares()[randRank.getIndex()] // loop until a different position
                    [randFile.getFileNum()].getPiece() != null){
                randFile = getRandomFile(); // ensure the files not equal
                randRank = getRandomRank(); // ensure the ranks not equal
            }
            board.getSquares()[randRank.getIndex()]
                    [randFile.getFileNum()].setPiece(new Piece(ChessPieceType.Pawn,
                    GameColor.BLACK)); // set piece on board

            System.out.println(prompt);
            toReturn = board; // return the new board
        }else if(piece.getType().equals(ChessPieceType.Pawn)){ // piece is a pawn
            board = this.spawnPieceForPawn();
            toReturn = board;
        }else{ // piece is a bishop
            System.out.println(prompt);
            board = this.spawnPieceForBishop(board);
            toReturn = board;
        }
        return toReturn;
    }

    /**
     * This method is responsible for spawning a piece for a bishop to capture.
     * Because bishops can only access squares of the same color, creating another
     * method and checking to ensure it's apart of the certain squares a bishop
     * can access was necessary.
     * Since in the tutorial the bishop is spawned on the black tiles, only black
     * tiles are valid in spawning a piece
     *
     * @param board the board to spawn the piece on
     * @return      the board with the piece spawned on it
     */
    public Board spawnPieceForBishop(Board board){
        boolean valid = false;
        while(!valid){
            Files randFile = getRandomFile(); // get random file
            Rank randRank = getRandomRank(); // get random rank
            // check to make sure it's a dark tile and it's empty
            if(board.getSquares()[randRank.getIndex()][randFile.getFileNum()].getPiece() == null){
                //wlog, a1, a3, a5, a7 are dark tiles
                if ((randFile.equals(Files.A) || randFile.equals(Files.C) ||
                        randFile.equals(Files.E) || randFile.equals(Files.G))){
                    if (randRank.equals(Rank.R1) || randRank.equals(Rank.R3) ||
                            randRank.equals(Rank.R5) || randRank.equals(Rank.R7)){
                        valid = true;
                        board.getSquares()[randRank.getIndex()]
                                [randFile.getFileNum()].setPiece(new Piece(ChessPieceType.Pawn,
                                GameColor.BLACK)); // set piece on board
                    }
                }else{ // a2, a4, a6, a8 are dark tiles
                    if(randRank.equals(Rank.R2) || randRank.equals(Rank.R4) ||
                       randRank.equals(Rank.R6) || randRank.equals(Rank.R8)){
                        valid = true;
                        board.getSquares()[randRank.getIndex()]
                                [randFile.getFileNum()].setPiece(new Piece(ChessPieceType.Pawn,
                                GameColor.BLACK)); // set piece on board
                    }
                }
            }
        }
        return board;
    }

    /**
     * This method is responsible for spawning a piece for a pawn to capture.
     * Because pawns can be advanced all the way forward and get stuck, we decided
     * it was best to create a new board for the player to practice on, where
     * they're forced back to the beginning, and it spawns a piece at a spot they can capture.
     *
     * @return the board with the piece spawned on it
     */
    public Board spawnPieceForPawn(){
        System.out.println("Since pawn's can be a little odd when it comes to capturing, " +
                "we'll create a new board for you to practice on.\n" +
                "This board will have a piece on it for you to capture, just gotta get there!\n");
        Board newBoard = (Board) this.loader.loadGameFromFile("pawnSpawn"); // load board for pawn
        newBoard.setDrawStrategy(new BoardColorCLI()); // make it pretty :)
        return newBoard;

    }
    /**
     * This method is responsible for getting a random file, which is used to
     * spawn a random piece in for players to capture.
     * @return a random file
     */
    public Files getRandomFile(){
        Random rand = new Random();
        int fileNum = rand.nextInt(Files.values().length);
        return Files.values()[fileNum];
    }

    /**
     * Method is responsible for getting a random rank, which is used
     * to spawn a random piece in for the players to capture.
     * @return a random rank
     */
    public Rank getRandomRank(){
        Random rand = new Random();
        int rankNum = rand.nextInt(Rank.values().length);
        return Rank.values()[rankNum];
    }
}
