package uicli;

import controller.BoardMementoCaretaker;
import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.*;
import model.BoardSaverLoader;
import model.Piece;
import model.Position;
import movements.KingMovement;
import movements.PawnMovement;
import movements.RookMovement;

import java.awt.*;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.ArrayList;
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

    private BoardMementoCaretaker caretaker;/*caretaker for the board*/

    private PlayerIF player1; /*player 1*/

    private PlayerIF player2; /*player 2*/

    private PlayerIF currentPlayer; /*current player*/

    private BoardIF board; /*board to play game on*/

    private String undo; /*undo move*/

    private String showMoves; /*show moves*/

    private SettingsIF settings = new SettingsCLI(new Scanner(System.in)); /* settings access */

    /**
     * Constructor for the play move dialog.
     *
     * @param scan      Scanner for user input
     * @param board     board to play game on
     * @param undo      string to undo to
     * @param showMoves string to show moves
     * @param player1   who to set to player 1
     * @param player2   who to set to player 2
     */
    public PlayMoveCLI(Scanner scan, BoardIF board, String undo, String showMoves, PlayerIF player1,
                       PlayerIF player2, BoardMementoCaretaker caretaker) {
        this.scan = scan;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = board;
        this.caretaker = caretaker;
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
    public void show(){
        String menu = "\nPlay Chess\n---------------------------------------------------------------\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5] +
                menuOptions[6] +
                "---------------------------------------------------------------";
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String prompt;
        while (choice != 0) { //while user has not quit
            this.display();
            System.out.println(menu);   //shows user menu options
            prompt = currentPlayer.getName() + ", enter your choice ===> ";
            System.out.print(prompt);   //ask user for this choice
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException ignore) {
                choice = 999; // issue found, default the switch statement
                //System.out.println("Invalid input.");
            }
            switch(choice){
                case 1:
                    boolean successfulRun = this.gameLoop(); // see if the move was successful
                    if (successfulRun){
                        this.board.getDrawStrategy().setHighlight(false);
                        switchPlayers(); // switch players
                        this.checkForCheckmate(); // check for checkmate
                    }
                    break;
                case 2:
                    if(this.undo.equals("on")){ // if settings allow undo
                        boolean undone = this.undo();
                        if (undone) {
                            this.switchPlayers();
                        } else {
                            System.out.println("\nThere is nothing to undo!");
                        }
                    }else{
                        System.out.println("\nUndo is not enabled!");
                    }
                    break;
                case 3:
                    if(this.undo.equals("on")) { // if settings allow redo
                        boolean redone = this.redo();
                        if (redone) {
                            this.switchPlayers();
                        } else {
                            System.out.println("\nThere is nothing to redo!");
                        }
                    }else{
                        System.out.println("\nRedo is not enabled!");
                    }
                    break;
                case 4: // show moves for a piece
                    if(this.showMoves.equals("on")) { // if settings allow showing moves
                        this.showMoves();
                    }else{
                        System.out.println("\nShow moves is not enabled!");
                    }
                    break;
                case 5: // save game
                    this.saveGame.showLoadSave(); // show the save game dialog
                    BoardSaverLoader loader = new BoardSaverLoader(); // create a loader to save
                    // save a game with a stack of mementos and the given URL to file
                    loader.saveGameToFile(this.caretaker, this.saveGame.getURL());
                    choice = 0; // end loop
                    break;
                case 6:
                    System.out.println("---------------------------------------");
                    if (agreementCondition()) { // if both players agree
                        System.out.println("Game ends in a draw!");
                        // increment stats and display them
                        currentPlayer.increaseDraws();
                        getOtherPlayer(currentPlayer).increaseDraws();
                        currentPlayer.displayStats();
                        getOtherPlayer(currentPlayer).displayStats();
                        System.out.println("---------------------------------------");
                        choice = 0; // end loop
                    } else {
                        System.out.println("\n" + getOtherPlayer(currentPlayer).getName()
                                + " declines the draw!");
                        System.out.println("---------------------------------------");
                    }
                    break;
                case 7:
                    System.out.println("---------------------------------------" +
                            "\n" + currentPlayer.getName() + " concedes the game!" +
                            "\n" + getOtherPlayer(currentPlayer).getName() + " wins!");

                    // increment stats and display them for each player
                    currentPlayer.increaseLosses();
                    getOtherPlayer(currentPlayer).increaseWins();
                    currentPlayer.displayStats();
                    getOtherPlayer(currentPlayer).displayStats();
                    System.out.print("---------------------------------------" +
                            "\n\nPress 'ENTER' whenever you're ready to return to the main menu. ");
                    scan.nextLine();
                    choice = 0; // end loop
                    break;
                default:
                    System.out.println("Invalid input.");
                    choice = 999; // preventative if enter 0
                    System.out.println(); // consume a line to fix scan
                    scan.nextLine();
            }
        }
    }

    /**
     * This is the function responsible for allowing the pieces to be moved.
     *
     * @param fromF File placement of where the piece is currently at
     * @param fromR Rank placement of where the piece is currently at
     * @param toF   File placement of where the piece will go
     * @param toR   Rank placement of where the piece will go
     */
    public boolean move(PlayerIF currentPlayer, PlayerIF otherPlayer, Files fromF,
                        Rank fromR, Files toF, Rank toR){

        boolean validMove = false;
        Piece fromPiece = (Piece)board.getPiece(fromR, fromF);
        Piece toPiece = (Piece)board.getPiece(toR, toF);

        if(fromPiece != null) { // user actually selects a square with a piece
            boolean isPlayersPiece = currentPlayer.getPieces().contains(fromPiece);
            boolean isEnPassant = false;
            // EN PASSANT
            if (fromPiece.getMoveType() instanceof PawnMovement pawn) {
                if (pawn.getEnPassant(board, board.getSquares()[toR.getIndex()][toF.getFileNum()].getPosition(), fromPiece.getPosition(board))) {

                    // Remove the to piece from the board
                    board.getSquares()[toR.getIndex()][toF.getFileNum()].clear();

                    Rank pieceToCapRank = Rank.getRankFromIndex(toR.index - pawn.getDirection());

                    SquareIF capturedSquare = board.getSquares()[pieceToCapRank.index][toF.getFileNum()];
                    PieceIF capturedPiece = capturedSquare.getPiece();

                    // Add the piece to the player's captured pieces
                    currentPlayer.addCapturedPiece(capturedPiece);

                    int toCapRankInt = capturedSquare.getPosition().getRank().getIndex();
                    int toCapFileInt = capturedSquare.getPosition().getFile().getFileNum();
                    // Remove the should_checked piece from the board
                    board.getSquares()[toCapRankInt][toCapFileInt].clear();
                    // Remove the piece from the enemy player's pieces
                    getOtherPlayer(currentPlayer).getPieces().remove(capturedPiece);

                    // Remove the from piece from the board
                    board.getSquares()[fromR.getIndex()][fromF.getFileNum()].clear();

                    // Move the piece to the to position
                    board.getSquares()[toR.getIndex()][toF.getFileNum()].setPiece(fromPiece);

                    validMove = true;
                    isEnPassant = true;
                }
            }
            if (!validMove && fromPiece.getType().equals(ChessPieceType.King) && toPiece != null) {
                if (toPiece.getType().equals(ChessPieceType.Rook)) {

                    if (canCastle(fromF, fromR, toF, toR)) { // see if it can castle
                        Piece king = (Piece) this.board.getSquares()[fromR.getIndex()]
                                [fromF.getFileNum()].getPiece(); // get king from the board
                        Piece rook = (Piece) this.board.getSquares()[toR.getIndex()]
                                [toF.getFileNum()].getPiece(); // get rook from the board

                        if (fromF.getFileNum() < toF.getFileNum()) {
                            // you can guarantee that the rook will be in F
                            this.board.getSquares()[toR.getIndex()] // set rook at new place
                                    [Files.F.getFileNum()].setPiece(rook);

                            this.board.getSquares()[fromR.getIndex()] // clear square from king
                                    [fromF.getFileNum()].clear();
                            this.board.getSquares()[toR.getIndex()] // clear square from rook
                                    [toF.getFileNum()].clear();

                            // you can guarantee that the king will be in G
                            this.board.getSquares()[toR.getIndex()] // set king at new place
                                    [Files.G.getFileNum()].setPiece(king);
                        } else {
                            // you can guarantee that the rook will be in D
                            this.board.getSquares()[toR.getIndex()] // set rook at new place
                                    [Files.D.getFileNum()].setPiece(rook);

                            this.board.getSquares()[fromR.getIndex()] // clear square from king
                                    [fromF.getFileNum()].clear();
                            this.board.getSquares()[toR.getIndex()] // clear square from rook
                                    [toF.getFileNum()].clear();

                            // you can guarantee that the king will be in C
                            this.board.getSquares()[toR.getIndex()] // set king at new place
                                    [Files.C.getFileNum()].setPiece(king);
                        }

                        // set first move to false for both pieces
                        if (king instanceof FirstMoveIF) {
                            ((FirstMoveIF) king).setFirstMove(false);
                        }
                        if (rook instanceof FirstMoveIF) {
                            ((FirstMoveIF) rook).setFirstMove(false);
                        }

                        //this.display();
                        System.out.println("\n" + currentPlayer.getName() + " has castled!");
                        return true;
                    }
                    System.out.println("Invalid castle attempt.");
                    return false;
                }
            }

            if (!validMove && isPlayersPiece && (toPiece == null || toPiece.getColor() != currentPlayer.getColor())) {
                // Is our piece and the to position is empty or the piece is the opposite color

                // Get all the possible moves for the piece
                List<Position> possibleMoves = fromPiece.getValidMoves(board, new Position(fromR, fromF));

                // Check to see if the to position is in the list of possible moves
                if (possibleMoves.contains(new Position(toR, toF))) {
                    // The to position is in the list of possible moves
                    if (toPiece != null) {
                        // The to position is occupied by an enemy piece

                        // Remove the to piece from the board
                        board.getSquares()[toR.getIndex()][toF.getFileNum()].clear();

                        // Add the piece to the player's captured pieces
                        currentPlayer.addCapturedPiece(toPiece);

                        // Remove the piece from the enemy player's pieces
                        getOtherPlayer(currentPlayer).getPieces().remove(toPiece);
                    }
                    // Remove the from piece from the board
                    board.getSquares()[fromR.getIndex()][fromF.getFileNum()].clear();

                    // Move the piece to the to position
                    board.getSquares()[toR.getIndex()][toF.getFileNum()].setPiece(fromPiece);
                    validMove = true;

                    // Add the move to the move history
                    board.addMove(currentPlayer.getColor(), fromF, fromR, toF, toR);
                    caretaker.push(board.createMemento());

                    if (fromPiece instanceof FirstMoveIF) {
                        FirstMoveIF movementPiece = (FirstMoveIF) fromPiece;
                        movementPiece.setFirstMove(false);
                    }
                } else {
                    System.out.println("Invalid move.");
                } //  && toPiece.getColor() == currentPlayer.getColor()
            } else if (!isEnPassant) {
                System.out.println("Cannot move a piece that is not yours.");

            } else{
                System.out.println("Cannot move to a position that is occupied by your own piece.");
            }
        }else{
            System.out.println("Square selected is empty.");
        }
        return validMove;
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
        try {
            newFile = Files.valueOf(file);
        } catch (IllegalArgumentException ignored) {
        } // prevent crashing
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
    public void display() {
        // display captured pieces for other
        this.getOtherPlayer(currentPlayer).displayCapturedPieces();
        System.out.println(); // line printed for formatting
        this.board.draw(currentPlayer.getColor());   // display the board
        System.out.println();
        //display captured pieces for current
        this.currentPlayer.displayCapturedPieces();
    }

    /**
     * This function checks to see if there is a check on the king.
     *
     * @param player player to check if their king is in check.
     * @return true if the king is in check, false otherwise.
     */
    public boolean checkCondition(PlayerIF player, Position position) {
        boolean isCheck = false;

        // Get the list of valid moves for all the enemy pieces on the board.
        for (PieceIF piece : player.getPieces()) {
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
     *
     * @param player player to check if their king is in checkmate.
     * @return true if the king is in checkmate, false otherwise.
     */
    private boolean checkmateCondition(PlayerIF player, PlayerIF playerOther) {
        /**
         * Steps:
         * Check to see if the king is in check.
         * If the king is in check, then check to see if there is checkmate.
         * If the king is not in check, then there is no checkmate.
         * If the king is in check, then check to see if the king can move to a position where it is not in check.
         * If the king can move to a position where it is not in check, then there is no checkmate.
         * If the king cannot move to a position where it is not in check, then check to see if any of the pieces can block the checkmate.
         * If any of the pieces can block the checkmate, then there is no checkmate.
         * If none of the pieces can block the checkmate, then there is a checkmate.
         */

        // Check to see if the king is in check.
        boolean checkmate = false;
        boolean canMoveOutOfCheck = false;
        boolean canBlockCheck = false;
        boolean inCheck = checkCondition(playerOther, player.getKing().getPosition(board));

        if (inCheck) {
            // Check to see if the king can move to a position where it is not in check.

            // Get the king of the player.
            PieceIF king = player.getKing();

            // Get the list of valid moves for the king.
            List<Position> kingValidMoves = king.getValidMoves(board, king.getPosition(board));

            // For each position in the list of valid moves, check to see if the king is in check.
            for (Position position : kingValidMoves) {
                // Emulate the move of the king to each position in the list of valid moves.
                this.move(player, playerOther, king.getPosition(board).getFile(), king.getPosition(board).getRank(), position.getFile(), position.getRank());

                // Check to see if there is a check.
                if (!this.checkCondition(player, king.getPosition(board))) {
                    canMoveOutOfCheck = true;
                }
                undo(); // TODO
                king = player.getKing();
                //undoMoveFromCheck();
                this.display();
            }

            ArrayList<PieceIF> pieces = player.getPieces();
            // Check to see if any of the pieces can block the checkmate.
            for (int i = 0; i < pieces.size(); i++){

            //for (PieceIF piece : player.getPieces()) {
                // Get the list of valid moves for the piece.
                List<Position> validMoves = pieces.get(i).getValidMoves(board, pieces.get(i).getPosition(board));

                for (Position position : validMoves) {
                    // Emulate the move of the piece to each position in the list of valid moves.
                    // Check to see if there is a check.
                    this.move(player, playerOther,pieces.get(i).getPosition(board).getFile(), pieces.get(i).getPosition(board).getRank(), position.getFile(), position.getRank());

                    if (!this.checkCondition(player, king.getPosition(board))) {
                        canBlockCheck = true;
                    }
                    undo(); // TODO
                    king = player.getKing();
                    //undoMoveFromCheck();

                }
            }
            System.out.println("Can move out of check : " + canMoveOutOfCheck + " | Can Block Check : " + canBlockCheck);
            checkmate = canMoveOutOfCheck && canBlockCheck;
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
                || fiftyMoveRule();
    }

    /**
     * This method checks to see if there is a stalemate.
     * @return true if there is a stalemate, false otherwise.
     */
    private boolean stalemateCondition(PlayerIF player) {
        // A draw should be declared if the king is not in check and there are no valid moves for the player
        boolean inCheck = checkCondition(getOtherPlayer(player), player.getKing().getPosition(board));
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
        // TODO
        return threefoldRepetition;
    }

    /**
     * This method checks to see if there is a check by fifty move rule.
     * @return true if there is a fifty move rule, false otherwise.
     */
    private boolean fiftyMoveRule() {
        // A draw should be declared if a total of 50 moves (25 per player) has occurred and no piece has been captures and no pawn has been moved
        boolean fiftyMoveRule = false;
        // TODO
        return fiftyMoveRule;
    }

    /**
     * This method checks to see if there is a check by agreement condition.
     * @return  true if there is a agreement condition, false otherwise.
     */
    private boolean agreementCondition() {
        // Both players agree to a draw.
        boolean agreement = false;
        this.scan = new Scanner(System.in);
        switchPlayers();    // Switch to the other player
        System.out.print(currentPlayer.getName() + ", do you agree to a draw? (y/n) ===> ");
        String input = scan.nextLine();
        if(input.equalsIgnoreCase("y")){
            agreement = true;
        }
        switchPlayers();    // Switch back to the original player
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
    private boolean gameLoop(){
        boolean success = false;

        // If the game is not over by checkmate or draw, then check to see if the player is in check.
        if(checkCondition(getOtherPlayer(currentPlayer), currentPlayer.getKing().getPosition(board))){
            // If the player is in check, then notify the player.
            System.out.println("You are in check!");
            // Ask the player for a move.

            boolean notInCheck = true;
            while(notInCheck){
                success = startMove(currentPlayer);   // possibly return a boolean for valid move made
                if(!checkCondition(getOtherPlayer(currentPlayer), currentPlayer.getKing().getPosition(board))){
                    notInCheck = false;
                }
                else{
                    System.out.println("Cannot move into check!");
                    undoMoveFromCheck();
                }
            }
        }
        // If the player is not in check, then ask the player for a move.
        else{
            boolean notInCheck = true;
            while(notInCheck){
                success = startMove(currentPlayer);   // possibly return a boolean for valid move made
                if(!checkCondition(getOtherPlayer(currentPlayer), currentPlayer.getKing().getPosition(board))){
                    notInCheck = false;
                }
                else{
                    System.out.println("Cannot move into check!");
                    undoMoveFromCheck();
                }
            }
        }
        return success;
    }


    /**
     * This method is used to see if a game is placed in check. This ensures
     * that the move from check will not be in the list of valid moves so
     * you cannot redo that move.
     */
    private void undoMoveFromCheck(){
        this.caretaker.pop();
        this.board.loadFromMemento(this.caretaker.peek());
        player1.assignPieces(board);
        player2.assignPieces(board);
    }

    /**
     * This method is responsible for pushing a board state onto the caretaker.
     *
     * @param memento the current memento to pushed pushed on
     */
    private void push(BoardIF.BoardMementoIF memento) {
        this.caretaker.push(memento);
    }

    /**
     * This method undoes the last move done on the board.
     *
     * @return true if the undo was successful, false otherwise
     */
    private boolean undo() {
        boolean success = false;
        BoardIF.BoardMementoIF memento = this.caretaker.down();
        if(memento != null) {
            this.board.loadFromMemento(memento);
            player1.assignPieces(this.board);
            player2.assignPieces(this.board);
            success = true;
        }
        return success;
    }

    /**
     * This method redoes the move that just occured by viewing what is above in
     * the caretaker.
     *
     * @return true if the redo was successful, false otherwise
     */
    private boolean redo() {
        boolean success = false;
        BoardIF.BoardMementoIF memento = this.caretaker.up();
        if(memento != null) {
            this.board.loadFromMemento(memento);
            player1.assignPieces(this.board);
            player2.assignPieces(this.board);
            success = true;
        }
        return success;
    }

    /**
     * This method is responsible for initiating a move given the player enters
     * a proper file and rank.
     *
     * @param player player being prompted for a move
     */
    private boolean startMove(PlayerIF player) {
        Files fromFile = null, toFile = null; // establish needed variables
        Rank fromRank = null, toRank = null;
        boolean successfulMove = false;

        // Prompt player for input
        boolean validMove = false;
        while(!validMove){ // loop until we get a valid move
            System.out.print("Make Move: "); // prompt for move
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
                //System.out.println("Invalid move. Please try again.");    // TODO uncomment this line

                // reset valid move to ensure loop doesn't end
                validMove = false;
            }
        }

        // make move for player
        successfulMove = this.move(player, getOtherPlayer(player), fromFile, fromRank, toFile, toRank);
        return successfulMove;
    }

    /**
     * Returns the other player based on a passed in player.
     *
     * @param player player to get the opposite player of
     * @return the other player
     */
    private PlayerIF getOtherPlayer(PlayerIF player){
        return player == player1 ? player2 : player1;
    }

    private void switchPlayers(){
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    /**
     * This method makes all necessary checks to see if a game has ended.
     *
     * @return true if an end condition has been met, false otherwise
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
            System.out.print("Show moves for what piece? "); // prompt for move
            scan = new Scanner(System.in);
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
                continue;
            }
            // check to see if any of the parts of the positions are null
            if(fromFile == null || fromRank == null) {
                System.out.println("Invalid piece. Please try again.");

                // reset valid move to ensure loop doesn't end
                validMove = false;
                continue;
            }
            // check to see if there is a piece at the position
            if(this.board.getPiece(fromRank, fromFile) == null){
                System.out.println("No piece at that position.");
                validMove = false;
                continue;
            }
            // check to see if the piece is the current player's piece
            else if (!(currentPlayer.getPieces().contains(this.board.getPiece(fromRank, fromFile)))){
                System.out.println("That is not your piece.");
                validMove = false;
                continue;
            }
        }

        if (validMove) {
            // Get valid moves for the piece
            PieceIF piece = this.board.getPiece(fromRank, fromFile);
            List<Position> validMoves = piece.getValidMoves(this.board, new Position(fromRank, fromFile));
            this.board.highlight(this.board, (ArrayList<Position>) validMoves, currentPlayer.getColor());
        }
    }

    /**
     * Method to check if castling is possible
     *
     * @param fromF the file the king is moving from
     * @param fromR the rank the king is moving from
     * @param toF   the file the king is moving to
     * @param toR   the rank the king is moving to
     * @return true if castling is possible, false if not
     */
    public boolean canCastle(Files fromF, Rank fromR, Files toF, Rank toR) {
        // grab the king and rook piece from positions to save keystrokes
        KingMovement king =
             (KingMovement) this.board.getSquares()[fromR.getIndex()]
                                                   [fromF.getFileNum()].getPiece().getMoveType();
        RookMovement rook =
             (RookMovement) this.board.getSquares()[toR.getIndex()]
                                                   [toF.getFileNum()].getPiece().getMoveType();

        //boolean to return at the end
        //boolean flag = true;
        boolean canCastle = true;

        // if either rook or king have moved, castling cannot occur
        if(!king.getFirstMove() || !rook.getFirstMove()) {
            canCastle = false;
            //flag = false;
        }

        // if king is in check or moves into check, castling cannot occur
        if (this.checkCondition(currentPlayer, new Position(fromR, fromF)) ||
            this.checkCondition(currentPlayer, new Position(toR, toF))) {
            canCastle = false;
            //flag = false;
        }

        // if king passes through check or a piece is there, castling cannot occur

        // check if king is moving to the right
        Files tempF = fromF;
        int cnt = 0;
        if (tempF.getFileNum() < toF.getFileNum() && canCastle) {
            tempF = Files.values()[tempF.getFileNum() + 1];
            while (cnt < 2) {
                //check if king is ever put into check
                if (!checkCondition(currentPlayer, new Position(fromR, tempF))) {
                    canCastle = false;
                    //flag = false;
                }
                //check if there is a piece in the way
                if (board.getSquares()[fromR.getIndex()]
                        [tempF.getFileNum()].getPiece() != null) {
                    canCastle = false;
                    //flag = false;
                }
                tempF = Files.values()[tempF.getFileNum() + 1];
                cnt++;
            }
        }
        // check if king is moving to the left
        else {
            while (cnt < 2) {
                tempF = Files.values()[tempF.getFileNum() - 1];
                //check if king is ever put into check
                if (!checkCondition(currentPlayer, new Position(fromR, tempF))) {
                    canCastle = false;
                }
                //check if there is a piece in the way
                if (board.getSquares()[fromR.getIndex()]
                        [tempF.getFileNum()].getPiece() != null) {
                    canCastle = false;
                }
                tempF = Files.values()[tempF.getFileNum() - 1];
                cnt++;
            }
        }
        return canCastle; //true if castling is possible, false if not
    }

    private void checkForCheckmate(){
        // Check to see if the game is over by checkmate or draw.
        boolean checkmate = checkmateCondition(currentPlayer, getOtherPlayer(currentPlayer));
        boolean draw = drawCondition(currentPlayer);

        if(checkmate || draw){
            // If the game is over, then notify the players and end the game.
            StringBuilder gameStatus = new StringBuilder("Game is over by ");
            gameStatus.append(checkmate ? "Checkmate" : "Draw");
            gameStatus.append(": The winner is ").append(getOtherPlayer(currentPlayer).getName());

            System.out.println("\n" + gameStatus);

            currentPlayer.increaseLosses();
            getOtherPlayer(currentPlayer).increaseWins();

            // Display the stats of the players.
            currentPlayer.displayStats();
            getOtherPlayer(currentPlayer).displayStats();

            // Save the game TODO
            System.out.println("\nDo you want to save the game? (y/n)");
            Scanner scan = new Scanner(System.in);
            String save = scan.nextLine();
            if(save.equalsIgnoreCase("y")){
                this.saveGame.showLoadSave(); // show the save game dialog
                BoardSaverLoader loader = new BoardSaverLoader(); // create a loader to save
                // save a game with a stack of mementos and the given URL to file
                loader.saveGameToFile(this.caretaker, this.saveGame.getURL());
            }

            // End the game.
            System.exit(1); // TODO change this to return something
        }
    }
}