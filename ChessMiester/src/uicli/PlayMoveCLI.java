package uicli;

import controller.BoardMementoCaretaker;
import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.*;
import model.Piece;
import model.Position;
import movements.PawnMovement;

import java.awt.*;
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

    private BoardMementoCaretaker caretaker;    /*caretaker for the board*/

    private PlayerIF player1; /*player 1*/

    private PlayerIF player2; /*player 2*/

    private PlayerIF currentPlayer; /*current player*/

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
        this.currentPlayer = player1;
        this.board = board;
        this.caretaker = new BoardMementoCaretaker(board.createMemento());
        this.undo = undo;
        this.showMoves = showMoves;
        setSaveGame(new SaveGameCLI(scan));
        populateMenu();
    }

    /**
     * Populates the menu options.
     */
    private void populateMenu() {
        menuOptions = new String[7];
        menuOptions[0] = "1: Move\n";
        menuOptions[1] = "2: Undo\n";
        menuOptions[2] = "3: Redo\n";
        menuOptions[3] = "4: Show Moves\n";
        menuOptions[4] = "5: Save Game\n";
        menuOptions[5] = "6: Propose Draw\n";
        menuOptions[6] = "7: Concede and Exit Game\n";
    }

    /**
     * Displays the play move dialog.
     */
    public void show() {
        //board.draw(GameColor.WHITE);
        this.display();
        String menu = "\nPlay Chess\n---------------------------------------------------------------\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5] +
                menuOptions[6];
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String prompt = currentPlayer.getName() + " Enter your choice ===> ";
        while (choice != 0) { //while user has not quit
            System.out.println(menu);   //shows user menu options
            System.out.print(prompt);   //ask user for this choice
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
            switch(choice){
                case 1:
                    System.out.println("Move");
                    this.gameLoop();
                    this.display();   //TODO TEMPORARY
                    this.switchPlayers();

                    if(endGameCondition()){
                        // Show each player's stats at the end of the game
                        player1.displayStats();
                        player2.displayStats();
                    }
                    break;
                case 2:
                    System.out.println("Undo");
                    break;
                case 3:
                    System.out.println("Redo");
                    break;
                case 4:
                    System.out.println("Show Moves");
                    this.showMoves();
                    break;
                case 5:
                    this.saveGame.showLoadSave();
                    break;
                case 6:
                    System.out.println("Propose draw");
                    break;
                case 7:
                    System.out.println("Concede and Exit Game");
                    // go to main menu? or end program?
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
     *
     * @param file string to be validated
     * @return a file if it's valid, null otherwise
     */
    public Files findValidFile(String file) {
        file = file.toUpperCase(); // make sure the input is uppercase
        Files newFile = null; // file to be returned
            try{
                newFile = Files.valueOf(file);
            }catch(IllegalArgumentException ignored){} // prevent crashing
        return newFile;
    }

    /**
     * This function checks to see if the user gave a valid rank for the piece movement.
     *
     * @param rank string to be validated
     * @return a rank if it's valid, null otherwise
     */
    public Rank findValidRank(String rank) {
        Rank newRank = null;
        Rank[] ranks = Rank.values(); // get all the ranks

        for (Rank r : ranks) {
            if (r.getDisplayNum() == Integer.parseInt(rank)) {
                newRank = r; // set new rank if it exists
                break;
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
     * This function checks to see if there is a check on the king.
     * @param player player to check if their king is in check.
     * @return true if the king is in check, false otherwise.
     */
    public boolean checkCondition(PlayerIF player, Position position){
        boolean isCheck = false;
        //Position kingPos = player.getKing().getPosition(); TODO Pass this line into checkCondition when checking for check.

        // Get the list of valid moves for all the enemy pieces on the board.
        for (PieceIF piece : player.getPieces()){
            // Cast the piece to a Piece object.
            Piece p = (Piece) piece;
            // Get the list of valid moves for the piece.
            List<Position> validMoves = p.getValidMoves(board, piece.getPosition(board));

            // Check to see if the king's position is in the list of valid moves.
            if (validMoves.contains(position)) {isCheck = true;}
        }
        return isCheck;
    }

    /**
     * This function checks to see if there is a checkmate on the king.
     * @param player player to check if their king is in checkmate.
     * @return true if the king is in checkmate, false otherwise.
     */
    private boolean checkmateCondition(PlayerIF player, PlayerIF playerOther){
        // If the king isn't in check, then there is no checkmate.
        boolean inCheck = checkCondition(player, player.getKing().getPosition(board)); // True if the king is in check, false otherwise.
        boolean checkmate = false; // True if the king is in checkmate, false otherwise.
        int checkCount = 0; // Number of checks on the king.

        // If the king is in check, then check to see if there is checkmate.
        if(inCheck){
            // TODO
            /**
             * Steps:
             * 1. Get the king of the player.
             * 2. Get the list of valid moves for the king.
             * 3. For each position in the list of valid moves, check to see if the king is in check.
             * 4. If the king is not in check for any of the positions, then there is no checkmate.
             */
            // Get the king of the player.
            Piece king = (Piece)player.getKing();

            // Get the list of valid moves for the king.
            List<Position> kingValidMoves = king.getValidMoves(board, king.getPosition(board));

            // For each position in the list of valid moves, check to see if the king is in check.
            for(Position pos : kingValidMoves) {
                // Call the checkCondition function to see if the king is in check if it is moved to the position.
                if (checkCondition(player, pos)) {
                    checkCount++;
                }
            }

            // If the king is not in check for any of the positions, then there is no checkmate.
            if (checkCount == kingValidMoves.size()) {
                checkmate = true;
            }

            // TODO Check to see if any of the pieces can block the checkmate.
            for (PieceIF piece : player.getPieces()) {
                // Cast the piece to a Piece object.
                Piece p = (Piece) piece;

                // Get the list of valid moves for the piece.
                List<Position> validMoves = p.getValidMoves(board, piece.getPosition(board));

                for (Position position : validMoves) {
                    // Emulate the move of the piece to each position in the list of valid moves.
                    // Check to see if there is a check.

                    this.move(player, playerOther,p.getPosition(board).getFile(), p.getPosition(board).getRank(), position.getFile(), position.getRank());

                    if (!this.checkCondition(player, king.getPosition(board))) {
                        //UndoMove(); //Down
                        checkmate = false;
                    }
                    //UndoMove(); //Down
                }
            }
        }
        return checkmate;
    }

    /**
     * This method checks to see if there is a draw by
     * 1) stalemate
     * 2) threefold repetition
     * 3) fifty move rule
     * 4) agreement condition.
     * @return true if there is a draw, false otherwise.
     */
    private boolean drawCondition(PlayerIF player){
        return stalemateCondition(player) || threefoldRepetitionCondition()
                || fiftyMoveRule() || agreementCondition();
    }

    /**
     * This method checks to see if there is a stalemate.
     * @return true if there is a stalemate, false otherwise.
     */
    private boolean stalemateCondition(PlayerIF player) {
        // A draw should be declared if the king is not in check and there are no valid moves for the player
        boolean inCheck = checkCondition(player1, player1.getKing().getPosition(board));
        boolean stalemate = true;

        // If the king is not in check, then check to see if there are any valid moves for the player.
        if (!inCheck) {
            for (PieceIF piece : player.getPieces()) {
                // Cast the piece to a Piece object.
                Piece p = (Piece) piece;

                // Get the list of valid moves for the piece.
                List<Position> validMoves = p.getValidMoves(board, piece.getPosition(board));

                if (validMoves.size() > 0) {
                    stalemate = false;
                }
            }
        }

        return !inCheck && stalemate;
    }

    /**
     * This method checks to see if there is a check by threefold repetition.
     * @return true if there is a threefold repetition, false otherwise.
     */
    private boolean threefoldRepetitionCondition() {
        // A draw should be declared if the same board presence has occurred three times in a row
        boolean threefoldRepetition = false;

        return threefoldRepetition;
    }

    /**
     * This method checks to see if there is a check by fifty move rule.
     * @return true if there is a fifty move rule, false otherwise.
     */
    private boolean fiftyMoveRule()
    {
        // A draw should be declared if a total of 50 moves (25 per player) has occurred and no piece has been captures and no pawn has been moved
        boolean fiftyMoveRule = false;

        return fiftyMoveRule;
    }

    /**
     * This method checks to see if there is a check by agreement condition.
     * @return  true if there is a agreement condition, false otherwise.
     */
    private boolean agreementCondition() {
        // Both players agree to a draw.
        boolean agreement = false;

        return agreement;
    }

    /**
     * Sets the save game object.
     * @param saveGame save game object
     */
    public void setSaveGame(LoadSaveGameIF saveGame){
        this.saveGame = saveGame;
    }

    /**
     * Loop for the game to occur
     */
    private void gameLoop(){
        this.populateMenu();
        if(!checkmateCondition(currentPlayer, this.getOtherPlayer(currentPlayer)) && !drawCondition(currentPlayer))
        {
            if(checkCondition(currentPlayer, currentPlayer.getKing().getPosition(this.board))){ // see if player is in check
                System.out.println(currentPlayer.getName() + ", you are in check! You must" +
                                   "defend your king!");
                //check = true;
            }
            else{
                // True if the king is in check, false otherwise.
                boolean checkPass = checkCondition(currentPlayer,
                                                   currentPlayer.getKing().getPosition(board));
                while(!checkPass) {
                    // Opponent did not place current player in check
                    startMove(currentPlayer);// start move by prompting for input and making move
                    if (checkCondition(currentPlayer, currentPlayer.getKing().getPosition(board))) {
                        System.out.println(currentPlayer.getName() + ", invalid move. Cannot " +
                                "place your own king in check");
                        undoMove();
                    } else {
                        checkPass = true;
                    }
                }
            }

        }
    }

    private void undoMove() {
        //this.board.loadFromMemento();
    }
    private void undoMoveFromCheck() {
        this.caretaker.pop();
        this.board.loadFromMemento(this.caretaker.peek());
    }

    private void push(BoardIF.BoardMementoIF memento) {
        this.caretaker.push(memento);
    }

    private void undo() {
        BoardIF.BoardMementoIF memento = this.caretaker.down();
        if(memento != null) {
            this.board.loadFromMemento(memento);
        }
    }

    private void redo() {
        BoardIF.BoardMementoIF memento = this.caretaker.up();
        if(memento != null) {
            this.board.loadFromMemento(memento);
        }
    }


    private void startMove(PlayerIF player) {
        Files fromFile = null, toFile = null; // establish needed variables
        Rank fromRank = null, toRank = null;

        // Prompt player for input
        boolean validMove = false;
        while(!validMove){ // loop until we get a valid move
            System.out.println("Make Move:"); // prompt for move
            Scanner scan = new Scanner(System.in);  // TODO remove this line later after testing and replace with field
            String move = scan.nextLine();
            move = move.replaceAll("\\s", ""); // remove white space

            try {
                // get and validate from position
                fromFile = this.findValidFile(String.valueOf(move.charAt(0)));
                fromRank = this.findValidRank(String.valueOf(move.charAt(1)));

                // get and validate to position
                toFile = this.findValidFile(String.valueOf(move.charAt(3)));
                toRank = this.findValidRank(String.valueOf(move.charAt(4)));

                // set valid move to true to end loop
                validMove = true;
            } catch (Exception e) {
                System.out.println("Invalid move. Please try again.");
            }
            // check to see if any of the parts of the positions are null
            if(fromFile == null || fromRank == null || toFile == null || toRank == null) {
                System.out.println("Invalid move. Please try again.");

                // reset validmove to ensure loop doesn't end
                validMove = false;
            }
        }

        // make move for player
        this.move(player, getOtherPlayer(player), fromFile, fromRank, toFile, toRank);
    }

    /**
     * Method that switches the active player in a game.
     */
    private void switchPlayers(){currentPlayer = currentPlayer == player1 ? player2 : player1;}

    /**
     * Returns the other player based on a passed in player.
     *
     * @param player player to get the opposite player of
     * @return the other player
     */
    private PlayerIF getOtherPlayer(PlayerIF player){
        return player == player1 ? player2 : player1;
    }

    /**
     * This method makes all necessary checks to see if a game has ended.
     * @return
     */
    private boolean endGameCondition(){
        boolean endGame = false;

        // game has come to an end, either because of checkmate or draw conditions
        if(checkmateCondition(currentPlayer, this.getOtherPlayer(currentPlayer))){
            System.out.println(currentPlayer.getName() + "you're in checkmate! Better luck" +
                    "next time!");

            currentPlayer.increaseLosses(); //current has lost, they were placed in checkmate
            this.getOtherPlayer(currentPlayer).increaseWins();  //other has won, they placed other in checkmate

            endGame = true;

        }else if(drawCondition(currentPlayer)){
            System.out.println("Game ends in a draw!");

            // Both players draw, increase their draw record
            currentPlayer.increaseDraws();
            this.getOtherPlayer(currentPlayer).increaseDraws();

            endGame = true;
        }
        return endGame;
    }


    /***** SHOW MOVES ******/
    /**
     * Method that is responsible for showing the moves of where to go.
     */
    public void showMoves() {
        Files fromFile = null; // establish needed variables
        Rank fromRank = null;

        // Prompt player for input
        boolean validMove = false;
        while(!validMove){ // loop until we get a valid move
            System.out.println("Show moves for what piece? "); // prompt for move
            String move = scan.nextLine();
            move = move.replaceAll("\\s", ""); // remove white space

            try {
                // get and validate from position
                fromFile = this.findValidFile(String.valueOf(move.charAt(0)));
                fromRank = this.findValidRank(String.valueOf(move.charAt(1)));

                // set valid move to true to end loop
                validMove = true;
            } catch (Exception e) {
                System.out.println("Invalid piece. Please try again.");
            }
            // check to see if any of the parts of the positions are null
            if(fromFile == null || fromRank == null) {
                System.out.println("Invalid piece. Please try again.");

                // reset valid move to ensure loop doesn't end
                validMove = false;
            }
            // check to see if there is a piece at the position
            if(this.board.getPiece(fromRank, fromFile) == null){
                System.out.println("No piece at that position.");
                validMove = false;
            }
            // check to see if the piece is the current player's piece
            else if (!(currentPlayer.getPieces().contains(this.board.getPiece(fromRank, fromFile)))){
                System.out.println("That is not your piece.");
                validMove = false;
            }
        }

        if (validMove) {
            // Get valid moves for the piece
            PieceIF piece = this.board.getPiece(fromRank, fromFile);
            List<Position> validMoves = piece.getValidMoves(this.board, new Position(fromRank, fromFile));
            //this.board.highlightMoves(validMoves);
        }
    }

}
