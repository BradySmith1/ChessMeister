package uicli;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.BoardIF;
import interfaces.LoadSaveGameIF;
import interfaces.PlayIF;
import interfaces.PlayerIF;
import model.Piece;
import model.Position;
import movements.PawnMovement;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TODO fix the move method

/**
 * This class implements the play move dialog.
 */
public class PlayMoveCLI implements PlayIF {

    private Scanner scan; /* Scanner for user input */

    private String[] menuOptions; /*options for the menu*/

    private LoadSaveGameIF saveGame; /*save game object*/

    private PlayerIF player1; /*player 1*/

    private PlayerIF player2; /*player 2*/

    private BoardIF board; /*board to play game on*/

    private String undo; /*undo move*/

    private String showMoves; /*show moves*/

    /**
     * Constructor for the play move dialog.
     * @param scan Scanner for user input
     */
    public PlayMoveCLI(Scanner scan, BoardIF board, String undo, String showMoves, PlayerIF player1, PlayerIF player2) {
        this.scan = scan;
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.undo = undo;
        this.showMoves = showMoves;
        setSaveGame(new SaveGameCLI(scan));
        populateMenu();
    }

    /**
     * Populates the menu options.
     */
    private void populateMenu() {
        menuOptions = new String[6];
        menuOptions[0] = "1: Move\n";
        menuOptions[1] = "2: Undo\n";
        menuOptions[2] = "3: Redo\n";
        menuOptions[3] = "4: Show Moves\n";
        menuOptions[4] = "5: Save Game\n";
        menuOptions[5] = "6: Concede and Exit Game\n";
    }

    /**
     * Displays the play move dialog.
     */
    public void show() {
        board.draw(GameColor.WHITE);
        String menu = "\nPlay Chess\n---------------------------------------------------------------\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5];
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String prompt = "Enter your menu choice here -> ";
        while (choice != 0) { //while user has not quit
            System.out.println(menu);   //shows user menu options
            System.out.print(prompt);
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
            switch(choice){
                case 1:
                    System.out.println("Move");
                    break;
                case 2:
                    System.out.println("Undo");
                    break;
                case 3:
                    System.out.println("Redo");
                    break;
                case 4:
                    System.out.println("Show Moves");
                    break;
                case 5:
                    this.saveGame.showLoadSave();
                    break;
                case 6:
                    System.out.println("Concede and Exit Game");
                    break;
                default:
                    System.out.println("Invalid input.");
            }
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


                // Remove the captured piece from the player's list of pieces.
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
        board.draw(GameColor.WHITE); // display the board
        System.out.println(); // line printed for formatting
        player2.displayCapturedPieces(); // display captured pieces for player 2
    }

    /**
     * Sets the save game object.
     * @param saveGame save game object
     */
    public void setSaveGame(LoadSaveGameIF saveGame){
        this.saveGame = saveGame;
    }
}
