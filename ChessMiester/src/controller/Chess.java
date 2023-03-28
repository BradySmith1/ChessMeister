/**
 * This file represents the necessary game logic loop needed for a game of chess to be played.
 * Includes methods to create a main menu and read player input, launch a new game, save/load
 * games (coming later) and many other crucial operations.
 *
 * @authors Brady Smith (25%), Zach Eanes (25%), Kaushal Patel (25%), and Colton Brooks (25%)
 * @version 1.0
 */
/* Package for the program. */
package controller;

/* Imports for our things in the program. */
import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.PlayerIF;
import interfaces.SquareIF;
import model.Board;
import model.Piece;
import model.Position;
import movements.PawnMovement;
import player.Player;
import uicli.BoardColorCLI;
import uicli.BoardMonoCLI;

/* Imports for the program. */
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Chess {

    /* Board the game will be played on. */
    private BoardIF board;

    /* Scanner used to read user input. */
    private Scanner scan;

    /* Player 1 object for the game. */
    private Player player1;

    /* Player 2 object for the game. */
    private Player player2;

    /**
     * Constructor method for the chess class. Initializes a scanner to be used.
     */
    public Chess() {
        scan = new Scanner(System.in); /* scanner to read in player choice */
    }

    /**
     * This function is used to display a main menu to the user. Here, we take user input
     * and decide what to be done next in processing.
     */
    public void mainMenu() {
        System.out.println("   _____ _                   __  __      _     _            \n" +
                "  / ____| |                 |  \\/  |    (_)   | |           \n" +
                " | |    | |__   ___  ___ ___| \\  / | ___ _ ___| |_ ___ _ __ \n" +
                " | |    | '_ \\ / _ \\/ __/ __| |\\/| |/ _ \\ / __| __/ _ \\ '__|\n" +
                " | |____| | | |  __/\\__ \\__ \\ |  | |  __/ \\__ \\ ||  __/ |   \n" +
                "  \\_____|_| |_|\\___||___/___/_|  |_|\\___|_|___/\\__\\___|_|   \n");
        String menu =
                "---------------------------------------------------------------\n" +
                        "Please make a selection as to what you would like to do:\n" +
                        "1 - Play Local Game Against another Player\n" +
                        "(COMING SOON!) 2 - Play Local Game Against a Computer\n" +
                        "(COMING SOON!) 3 - Play Online Game Against another Player\n" +
                        "(COMING SOON!) 4 - 4-Player Chess!\n" +
                        "5 - Switch to color/monochrome mode\n" +
                        "0 - Exit Game\n-----------------------------------------" +
                        "---------------------\n";
        String prompt = "Enter your menu choice here -> ";
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String boardC = "Mono";
        while (choice != 0) { //while user has not quit
            System.out.println(menu);   //shows user menu options
            System.out.print(prompt);
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (0 - 4)");
            }
            System.out.println();
            switch(choice) {
                case 1:
                    newGame(boardC);
                    break;
                case 2:
                    System.out.println("This feature is coming soon!");
                    break;
                case 3:
                    System.out.println("This feature is coming soon!");
                    break;
                case 4:
                    System.out.println("This feature is coming soon!");
                    break;
                case 5:
                    if(boardC.equals("Mono")){
                        boardC = "Color";
                    }else{
                        boardC = "Mono";
                    }
                    System.out.println("Switching to " + boardC + " mode.");
                    break;
                case 0:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid number (0 - 4)");
                    break;
            }
            System.out.println();
            scan.nextLine();//consumes a new line so nextInt throwing an exception will not loop
        }
    }

    /**
     * This function is used to begin a new game. Allows player to choose color to play,
     * and handle basic logic to loop through a game itself.
     */
    public void newGame(String boardColor) {
        System.out.println("Player one choose color: (1) White or (2) Black >>>");
        int color = scan.nextInt();
        if(color == 1){ // user selects white
            player1 = new Player(GameColor.WHITE);
            player2 = new Player(GameColor.BLACK);
        }
        else if(color == 2){ // user selects black
            player1 = new Player(GameColor.BLACK);
            player2 = new Player(GameColor.WHITE);
        }
        else{ // set default whenever user input is invalid
            System.out.println("Invalid input. Defaulting to white.");
            player1 = new Player(GameColor.WHITE);
            player2 = new Player(GameColor.BLACK);
        }
        scan.nextLine(); //consumes a new line so nextInt throwing an exception will not loop
        //clears the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        BoardStrategy boardC;
        if(boardColor.equals("Mono")){
            boardC = new BoardMonoCLI();
        }else{
            boardC = new BoardColorCLI();
        }
        board = new Board(boardC); //create new board to play game on
        board.setup(); // initialize board
        assignPieces(); // assign pieces to player
        board.draw();  // printout the board for users to see
        if(player1.getColor() == GameColor.WHITE){
            play(player1, player2);
        }else{
            play(player2, player1);
        }
    }

    /**
     * This function is the basic game loop used for a game of chess to actually happen.
     * @param playerWhite player playing as white
     * @param playerBlack player playing as black
     */
    public void play(PlayerIF playerWhite, PlayerIF playerBlack){
        boolean gameOver = false;
        Files file1, file2;
        Rank rank1, rank2;
        while(!gameOver){
            System.out.println("White's turn\nWhere would you like to move from?");
            boolean moveValid = false;
            while(!moveValid){
                file1 = findValidFile();
                rank1 = findValidRank();
                System.out.println("Where would you like to move to?");
                file2 = findValidFile();
                rank2 = findValidRank();
                moveValid = move(playerWhite, playerBlack, file1, rank1, file2, rank2);
            }
            this.display();
            System.out.println("\nBlack's turn\nWhere would you like to move from?");
            moveValid = false;
            while(!moveValid){
                file1 = findValidFile();
                rank1 = findValidRank();
                System.out.println("Where would you like to move to?");
                file2 = findValidFile();
                rank2 = findValidRank();
                moveValid = move(playerBlack, playerWhite, file1, rank1, file2, rank2);
            }
            player1.displayCapturedPieces();
            board.draw();
            player2.displayCapturedPieces();
            //gameOver = true; //condition to end processing added later on
            //endGame();
        }
    }

    /**
     * This function is used to assign pieces to each user.
     */
    private void assignPieces(){
        SquareIF[][] squares = board.getSquares();
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                if(squares[i][j].getPiece() != null){
                    if(((Piece) squares[i][j].getPiece()).getColor() == player1.getColor()){
                        player1.addPiece(squares[i][j].getPiece());
                    }else{
                        player2.addPiece(squares[i][j].getPiece());
                    }
                }
            }
        }
    }

    /**
     * This function is used to end processing and display to the user that the game
     * has come to an end.
     */
    public void endGame() {
        System.out.println("The game should be over now.\nReturning to main menu.");
        //clears the screen
        System.out.print("\033[H\033[2J");
        System.out.flush(); //clear the terminal
        this.mainMenu();
    }

    /**
     * This function allows the user to load in a game that was saved from earlier.
     * @param file name of the file that holds the game logic to be loaded in later
     * @return     the board to continue playing the chess game on
     */
    public BoardIF loadGame(String file) {
        return null;
    }

    /**
     * This function allows the user to save a game that can be resumed later.
     * @param file name of the file to save the game to
     * @param game board state to be saved in later
     */
    public void saveGame(String file, BoardIF game) {

    }

    /**
     * This is the function responsible for allowing the pieces to be moved.
     * @param fromF File placement of where the piece is currently at
     * @param fromR Rank placement of where the piece is currently at
     * @param toF   File placement of where the piece will go
     * @param toR   Rank placement of where the piece will go
     */
    public boolean move(PlayerIF currentPlayer, PlayerIF otherPlayer, Files fromF,
                        Rank fromR, Files toF, Rank toR){
        boolean moveMade = false; // initialize to false
        // Get the piece at the current/"from" position.
        Piece piece = (Piece) board.getPiece(fromR, fromF);

        if(currentPlayer.getPieces().contains(piece)){
            // True if there is a piece at the to position
            boolean hasPiece = board.getPiece(toR, toF) != null;

            // list of possible moves
            List<Position> moves = piece.getValidMoves(board, new Position(fromR, fromF));
            Position to = new Position(toR, toF); // position to move to
            boolean success = false; // initialize to false
            success = moves.contains(to); // check if where user wants to move is a valid move

            if(success && hasPiece){ // A piece was captured and move is valid
                // Add the captured piece to the player's list of captured pieces.
                currentPlayer.addCapturedPiece(board.getPiece(toR, toF));


                // Remove the captured piece from the player's list of pieces. TODO
                otherPlayer.getPieces().remove(piece);

                // Clear the piece at the "to" position.
                this.board.getSquares()[toR.getIndex()][toF.getFileNum()].clear();

                // Move the piece to the "to" position.
                this.board.getSquares()[toR.getIndex()][toF.getFileNum()].setPiece(piece);

                // Clear the "from" position.
                board.getSquares()[fromR.getIndex()][fromF.getFileNum()].clear();


                if((piece.getType() == ChessPieceType.Pawn)){ //check if piece is a pawn
                    PawnMovement pawn = (PawnMovement) piece.getMoveType();
                    pawn.setFirstMove(); // set first move for a pawn to false
                }
                moveMade = true; // move was successful
            }
            else if(success && !hasPiece){ // No piece was captured and move is valid
                // Move the piece to the "to" position.
                board.getSquares()[toR.getIndex()][toF.getFileNum()].setPiece(piece);

                // Clear the "from" position.
                board.getSquares()[fromR.getIndex()][fromF.getFileNum()].clear();

                if((piece.getType() == ChessPieceType.Pawn)){ //check if piece is a pawn
                    PawnMovement pawn = (PawnMovement) piece.getMoveType();
                    pawn.setFirstMove(); //set first move for a pawn to false
                }

                moveMade = true; // move was successful
            }
            else{
                System.out.println("Invalid move.");
            }
        }
        else{
            System.out.println("You cannot move that piece because it is not yours.");
        }

        return moveMade; // return whether or not the move was successful
    }


    /**
     * This function checks to see if the user gave a valid file for the piece movement.
     * @return new file for the piece to be moved to
     */
    public Files findValidFile() {
        System.out.print("Enter the file of the location (A-H) >>> ");
        String input = scan.nextLine();
        input = input.toUpperCase(); // make sure the input is uppercase

        Files newFile = null; // file to be returned

        boolean valid = false; // boolean to see if input is valid
        while(!valid){ // loop until a valid input is given be the user
            try {
                newFile = Files.valueOf(input);
                valid = true;
            }catch(IllegalArgumentException e) { //if a bad input is given, prompt for another
                System.out.print("Invalid File, enter another (A-H) >>> ");
                input = scan.nextLine();
            }
        }
        return newFile;
    }

    /**
     * This function checks to see if the user gave a valid rank for the piece movement.
     * @return new rank for the piece to be moved to
     */
    public Rank findValidRank() {
        System.out.print("Enter the rank of the location (1-8) >>> ");
        String input = scan.nextLine();
        Rank newRank = null;
        boolean valid = false; // boolean to see if input is valid
        while(!valid){
            try{
                Rank[] ranks = Rank.values(); // get all the ranks
                for(Rank r : ranks){
                    if(r.getDisplayNum() == Integer.parseInt(input)){
                        newRank = r;
                        break;
                    }
                }
                if(newRank == null){
                    throw new IllegalArgumentException();
                }
                valid = true;
            }catch(IllegalArgumentException e){
                System.out.print("Invalid rank, please enter another (1-8) >>> ");
                input = scan.nextLine();
            }
        }
        return newRank;
    }

    /**
     * Function used to display current state of the board and captured pieces.
     */
    public void display(){
        player1.displayCapturedPieces(); //display captured pieces for player 1
        System.out.println(); // line printed for formatting
        board.draw(); // display the board
        System.out.println(); // line printed for formatting
        player2.displayCapturedPieces(); // display captured pieces for player 2
    }
}
