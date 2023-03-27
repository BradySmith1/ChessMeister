package controller;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.BoardIF;
import interfaces.PlayerIF;
import interfaces.SquareIF;
import model.Board;
import model.Piece;
import model.Position;
import player.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Chess {

    private BoardIF board;

    private Scanner scan;

    private Player player1;

    private Player player2;


    public Chess() {
        scan = new Scanner(System.in); /* scanner to read in player choice */
    }

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
                        "0 - Exit Game\n--------------------------------------------------------------\n";
        String prompt = "Enter your menu choice here -> ";
        int choice = 999;     //initialized to 5 so there is no option chosen or quitting the loop
        while (choice != 0) {   //while user has not quit
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
                    newGame();
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
                case 0:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid number (0 - 4)");
                    break;
            }
            System.out.println();
            scan.nextLine();    //consumes a new line so that nextInt throwing an exception will not loop
        }
    }


    public void newGame() {
        System.out.println("Player one color: (1) White or (2) Black");
        int color = scan.nextInt();
        if(color == 1){ // user selects white
            player1 = new Player(GameColor.WHITE);
            player2 = new Player(GameColor.BLACK);
            assignPieces();
        }
        else if(color == 2){ // user selects black
            player1= new Player(GameColor.BLACK);
            player2 = new Player(GameColor.WHITE);
            assignPieces();
        }
        else {
            System.out.println("Invalid input. Defaulting to white.");
            player1 = new Player(GameColor.WHITE);
            player2 = new Player(GameColor.BLACK);
        }
        //clears the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        board = new Board();
        board.setup();
        board.draw();
        //loop for move logic.
        boolean gameOver = false;
        while(!gameOver){
            //player1.move();
            //board.draw();
            //player2.move();
            //board.draw();
            //check if game is over
            //if(gameOver)
            endGame();
        }
    }

    public void play(PlayerIF playerWhite, PlayerIF playerBlack){
        //player1.move(); //allow player one to move
        //board.draw();
        //player2.move();
        //board.draw();
        //check if game is over
        //if(gameOver)
        endGame();
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
                    }
                    else{
                        player2.addPiece(squares[i][j].getPiece());
                    }
                }
            }
        }
    }

    public void endGame() {
        System.out.println("The game should be over now.\nreturning to main menu.");
        //clears the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        this.mainMenu();
    }

    public BoardIF loadGame(String file) {
        return null;
    }

    public void saveGame(String file, BoardIF game) {

    }

    //move needs to be moved into the player object. This is just the code for when it is created.
    public void move(Files fromF, Rank fromR, Files toF, Rank toR) {
        // Get the piece at the from position.
        Piece piece = (Piece) board.getPiece(fromR, fromF);

        // Get the piece at the to position if any.
        boolean hasPiece = board.getPiece(fromR, fromF) != null;   // True if there is a piece at the to position.

        /**
         * Create a copy of the board and attempt to move the piece to the to position on the copy.
         * if the move was successful, but it puts your king in check then prompt the user to enter a new move. Revert to the original board.
         *
         *
         * If the move was successful then set the board copy "to" position to the piece and clear the "from" position.
         * If the move was successful but there was a capturable piece at the to position then store the piece in the player record of captured pieces.
         *
         * If we made the move and it was successful and doesn't put the king in check, then we copy the copied board to the actual board.
         *
         */

        boolean success = piece.move(board, new Position(toR, toF));

        if (success && hasPiece){ // A piece was captured and move is valid
            // how do we want to store captured pieces in a record that can be displayed later on



            board.getSquares()[toF.getFileNum()][toR.getIndex()].setPiece(piece);  // add piece to new location
            board.getSquares()[fromF.getFileNum()][fromR.getIndex()].clear();  // remove piece from old location
        }
        else if (success){ // A piece was not captured and move is valid
            board.getSquares()[toF.getFileNum()][toR.getIndex()].setPiece(piece);  // add piece to new location
            board.getSquares()[fromF.getFileNum()][fromR.getIndex()].clear();  // remove piece from old location

        }
        else{ // Move is not valid
            // Prompt user again for a new move, telling them the move was invalid
            Scanner scan = new Scanner(System.in);
            System.out.println("Invalid move, please enter another >>>");


        }
    }
}


//  TODO GAME LOGIC IDEA
//  1. Create a new board
//  2. Create 2 new players : player 1 and player 2 (player 1 is white, player 2 is black)
//  4. Create a new game
//  5. Start the game (loop) : !gameOver
//  6. Prompt the user for a move   // Keep track of whose turn it is
//  7. Check if the move is valid
//  8. If the move is valid, make the move // next player's turn
//  9. Check if the move puts the king of the player who made the move in check
//  10. If the move puts the king in check, prompt the user for a new move
//  11. If the move does not put the king in check, check if the move puts the opponent's king in check
//  12. If the move puts the opponent's king in check, check if the opponent's king is in checkmate
//  13. If the opponent's king is in checkmate, end the game    gameOver = true
//  14. If the opponent's king is not in checkmate, continue the game
//  15. If the move does not put the opponent's king in check, continue the game
//  16. If the move is not valid, prompt the user for a new move
//  17. Repeat steps 6-16 until the game is over
//  18. End the game
