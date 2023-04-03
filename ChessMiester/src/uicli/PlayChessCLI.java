package uicli;

import enums.ChessPieceType;
import enums.Files;
import enums.Rank;
import interfaces.*;
import model.Board;
import model.Piece;
import model.Position;
import movements.PawnMovement;
import player.Player;
import java.util.List;
import java.util.Scanner;

//TODO add javadoc
public class PlayChessCLI implements PlayIF {

    private Scanner scan;

    private String[] menuOptions;

    private String boardColor;

    private PlayerIF player1;

    private PlayerIF player2;

    private Board board;

    public PlayChessCLI(Scanner scan, String boardColor, PlayerIF player1, PlayerIF player2) {
        this.scan = scan;
        this.boardColor = boardColor;
        this.player1 = player1;
        this.player2 = player2;
        initBoard();
        populateMenu();
    }

    private void initBoard() {
        BoardStrategy boardC;
        if(boardColor.equals("Mono")){
            boardC = new BoardMonoCLI();
        }else{
            boardC = new BoardColorCLI();
        }
        board = new Board(boardC); //create new board to play game on
        board.setup(); // initialize board
        assignPieces(); // assign pieces to player
    }

    private void populateMenu() {
        this.menuOptions = new String[6];
        menuOptions[0] = "1: Move\n";
        menuOptions[1] = "2: Undo\n";
        menuOptions[2] = "3: Redo\n";
        menuOptions[3] = "4: Show Moves\n";
        menuOptions[4] = "5: Save Game\n";
        menuOptions[5] = "0: Conceded and exit game\n";
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

    @Override
    public void show() {
        board.draw();  // printout the board for users to see
        String menu = "---------------------------------------------------------------\n" +
                "Please make a selection as to what you would like to do:\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5] +
                "\n-----------------------------------------" +
                "---------------------\n";
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
                moveValid = move(player1, player2, file1, rank1, file2, rank2);
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
                moveValid = move(player2, player1, file1, rank1, file2, rank2);
            }
            player1.displayCapturedPieces();
            board.draw();
            player2.displayCapturedPieces();
            //gameOver = true; //condition to end processing added later on
            //endGame();
        }

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
