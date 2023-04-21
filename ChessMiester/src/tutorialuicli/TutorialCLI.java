package tutorialuicli;

import controller.BoardMementoCaretaker;
import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.BoardIF;
import interfaces.FirstMoveIF;
import interfaces.SettingsIF;
import model.Board;
import model.BoardSaverLoader;
import model.Piece;
import model.Position;
import uicli.BoardColorCLI;
import uicli.BoardMonoCLI;
import uicli.SettingsCLI;

import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class TutorialCLI {

    private BoardSaverLoader loader = new BoardSaverLoader(); /* create loader to load board */
    private BoardIF board = new Board(new BoardColorCLI()); /* create board to load into */

    /* create settings to get board color */
    private SettingsIF settings = new SettingsCLI(new Scanner(System.in));

    /**
     * This method will be responsible for loading the game, and looping
     * the player through until the game is over.
     *
     * @param file  the name of the file to be loaded.
     * @param piece the piece to be used in the tutorial
     * @param pos   the position of the piece to be used in the tutorial
     * @param color the color of the draw strategy
     */
    public void tutorialLoop(String file, Piece piece, Position pos, String color) {
        /* wait for user to press any key to continue */
        System.out.println("""
         Let's start with some practice moves!
         Since you only have one piece, controls are adapted a little!\s
         Enter the file and rank of the square you want to move to, like a1 or A1.\s
         
         You also have the option to spawn in piece's! \s
         Make sure you're comfortable with the piece's movements before trying to capture!\s
         
         When you're ready to try the piece, press 'ENTER' to continue.
                 """);
        Scanner scan = new Scanner(System.in); // create scanner to read user input
        scan.nextLine(); // read line when user presses enter

        /* Load in the board and go through game loop */
        BoardMementoCaretaker caretaker = this.loader.loadGameFromFile(file);
        //BoardIF board = new Board();
        this.board.loadFromMemento(caretaker.peek());// load board for bishop

        // set the board color based on settings
        if(color.equals("Mono")){
            this.board.setDrawStrategy(new BoardMonoCLI());
        } else {
            this.board.setDrawStrategy(new BoardColorCLI());
        }

        String input = "1"; // basic string for user input

        while (!input.equals("0")) { // loop game until user wants to quit
            this.board.draw(GameColor.WHITE); // draw board

            List<Position> moves = piece.getValidMoves(this.board, pos); // get valid moves for bishop
            System.out.print("Enter a move (Enter 0 to quit, " +
                    "1 to try capturing) ===> "); // prompt and read input
            input = scan.nextLine();
            if(input.equals("1")){ // user wants to spawn a pawn
                pos = this.spawnPiece(pos, piece); // spawn a random pawn
                if(piece.getType().equals(ChessPieceType.Pawn)){
                    piece = new Piece(ChessPieceType.Pawn, GameColor.WHITE); // rebase pawn
                }
                //input = "2"; // break out of loop
            }
            Files toF = null;
            Rank toR = null;
            try {
                // file the file from the input
                toF = Files.valueOf(input.substring(0, 1).toUpperCase());

                // find the rank from the input
                Rank[] ranks = Rank.values();
                for (Rank r : ranks) {
                    if (r.getDisplayNum() == Integer.parseInt(input.substring(1, 2))) {
                        toR = r;
                    }
                }
            } catch (Exception e) {
                if(!input.equals("0") && !input.equals("1") && !input.equals("2"))
                    System.out.println("Invalid input. Expect a file and rank (EX: A1).");
            }

            // make the move
            if(toR != null && toF != null){;
                Position newPos = new Position(toR, toF);
                if(moves.contains(newPos)){
                    // remove piece from old position
                    this.board.getSquares()[pos.getRank().getIndex()]
                            [pos.getFile().getFileNum()].clear();
                    // set piece to new position
                    this.board.getSquares()[toR.getIndex()][toF.getFileNum()].setPiece(piece);
                    pos = newPos;
                    if(piece.getType().equals(ChessPieceType.Pawn)){
                        FirstMoveIF movement = (FirstMoveIF) piece.getMoveType();
                        movement.setFirstMove(false); // set first move to false
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } // end if
        } // end while
    } // end tutorialLoop


    /**
     * This method is responsible for spawning in a piece randomly on the board
     *
     * @param pos   the position of the piece to be spawned
     * @param piece the piece to be spawned
     * @return the position of the piece that was spawned
     */
    public Position spawnPiece(Position pos, Piece piece) {
        Position toReturn = pos;
        String prompt = "Spawning a piece, go capture it!";
        // spawn a pawn near middle of the board
        if(!piece.getType().equals(ChessPieceType.Pawn) &&
           !piece.getType().equals(ChessPieceType.Bishop)){ // piece is not a pawn or bishop
            Files randFile = getRandomFile(); // get random file
            Rank randRank = getRandomRank(); // get random rank

            while(this.board.getSquares()[randRank.getIndex()] // loop until a different position
                    [randFile.getFileNum()].getPiece() != null){
                randFile = getRandomFile(); // ensure the files not equal
                randRank = getRandomRank(); // ensure the ranks not equal
            }
            this.board.getSquares()[randRank.getIndex()]
                    [randFile.getFileNum()].setPiece(new Piece(ChessPieceType.Pawn,
                    GameColor.BLACK)); // set piece on board

            System.out.println(prompt);
        }else if(piece.getType().equals(ChessPieceType.Pawn)){ // piece is a pawn
            this.spawnPieceForPawn(pos); // pawn is loaded into a new position
            toReturn = new Position(Rank.R2, Files.D); //place the new spawn is located
        }else{ // piece is a bishop
            System.out.println(prompt);
            this.spawnPieceForBishop();
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
     */
    public void spawnPieceForBishop(){
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
    }

    /**
     * This method is responsible for spawning a piece for a pawn to capture.
     * Because pawns can be advanced all the way forward and get stuck, we decided
     * it was best to create a new board for the player to practice on, where
     * they're forced back to the beginning, and it spawns a piece at a spot they can capture.
     *
     * @param pos the position of the pawn
     */
    public void spawnPieceForPawn(Position pos){
        System.out.println("\nSince pawn's can be a little odd when it comes to capturing, " +
                "we'll create a new board for you to practice on.\n" +
                "This board will have a piece on it for you to capture, just gotta get there!\n");
        BoardMementoCaretaker caretaker = this.loader.loadGameFromFile("pawnSpawn");
        board.loadFromMemento(caretaker.peek()); // load board for pawn
        // remove from old position
    }

    /**
     * This method is responsible for getting a random file, which is used to
     * spawn a random piece in for players to capture.
     *
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
     *
     * @return a random rank
     */
    public Rank getRandomRank(){
        Random rand = new Random();
        int rankNum = rand.nextInt(Rank.values().length);
        return Rank.values()[rankNum];
    }
}
