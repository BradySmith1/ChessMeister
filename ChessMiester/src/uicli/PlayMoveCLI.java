package uicli;

import controller.BoardMementoCaretaker;
import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.*;
import model.Piece;
import model.Position;
import movements.KingMovement;
import movements.PawnMovement;
import movements.RookMovement;

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
     *
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
        //board.draw(currentPlayer.getColor());
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
        String prompt;
        while (choice != 0) { //while user has not quit
            System.out.println(menu);   //shows user menu options
            prompt = currentPlayer.getName() + ", enter your choice ===> ";
            System.out.print(prompt);   //ask user for this choice
            try {
                choice = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException ignore) {
                //ignore
            }
            switch (choice) {
                case 1:
                    this.display();
                    boolean successfulRun = this.gameLoop();
                    if (successfulRun) {
                        this.display();
                        switchPlayers();
                    }
//                    if (endGameCondition()) {
//                        // Show each player's stats at the end of the game
//                        player1.displayStats();
//                        player2.displayStats();
//                    }
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
                    if (agreementCondition() == true) {
                        System.out.println("Draw");
                        currentPlayer.increaseDraws();
                        getOtherPlayer(currentPlayer).increaseDraws();
                    } else {
                        System.out.println("No draw");
                    }
                    break;
                case 7:
                    System.out.println("Concede and Exit Game");
                    currentPlayer.increaseLosses();
                    getOtherPlayer(currentPlayer).increaseWins();
                    System.exit(1);
                    break;
                default:
                    System.out.println("Invalid input.");
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
                        Rank fromR, Files toF, Rank toR) {
        /**
         * This function is responsible for moving the pieces on the board.
         *
         * Algorithm:
         * 1. Get the piece at the from position.   TODO DONE
         * 2. Check if the piece at to is null. (If it is, then there is no piece at the from position)
         * 3. Check to see if the player owns the piece.
         * 4. Get all the possible moves for the piece.
         * 5. Two cases: The to position is in the list of possible moves, or it is not.
         * 6. If it is, then check to see if the to position is occupied.
         * 7. If it is, then check to see if the piece is the same color as the player.
         * 8. If it is, then the move is invalid. // Cannot move to a position that is occupied by a piece of the same color.
         * 9. If it is not, then the move is valid. // Can move to a position that is occupied by a piece of the opposite color.
         * 10. If the to position contains an enemy piece, then remove it from the board. and set our piece to the to position.
         * 11. Remove the piece from the from position.
         * 12. Add the enemy piece to the player's captured pieces.
         * 13. Remove the piece from the enemy player's pieces.
         */
        boolean validMove = false;
        Piece fromPiece = (Piece) board.getPiece(fromR, fromF);
        Piece toPiece = (Piece) board.getPiece(toR, toF);

        boolean isPlayersPiece = currentPlayer.getPieces().contains(fromPiece);

        if(this.board.getSquares()[fromR.getIndex()] // if it's a king
                [fromF.getFileNum()].getPiece().getType().equals(ChessPieceType.King) &&
           this.board.getSquares()[toR.getIndex()]
                        [toF.getFileNum()].getPiece().getType().equals(ChessPieceType.Rook)){

            if(canCastle(fromF, fromR, toF, toR)){ // see if it can castle
                Piece king = (Piece) this.board.getSquares()[fromR.getIndex()]
                        [fromF.getFileNum()].getPiece(); // get king from the board
                Piece rook = (Piece) this.board.getSquares()[toR.getIndex()]
                        [toF.getFileNum()].getPiece(); // get rook from the board

                if(fromF.getFileNum() < toF.getFileNum()){
                    this.board.getSquares()[toR.getIndex()] // set rook at new place
                            [toF.getFileNum() - 1].setPiece(rook);

                    this.board.getSquares()[fromR.getIndex()] // clear square from king
                            [fromF.getFileNum()].clear();
                    this.board.getSquares()[toR.getIndex()] // clear square from rook
                            [toF.getFileNum()].clear();

                    this.board.getSquares()[toR.getIndex()] // set king at new place
                            [toF.getFileNum()].setPiece(king);
                }else{
                    this.board.getSquares()[toR.getIndex()] // set rook at new place
                            [toF.getFileNum() + 1].setPiece(rook);

                    this.board.getSquares()[fromR.getIndex()] // clear square from king
                            [fromF.getFileNum()].clear();
                    this.board.getSquares()[toR.getIndex()] // clear square from rook
                            [toF.getFileNum()].clear();

                    this.board.getSquares()[toR.getIndex()] // set king at new place
                            [toF.getFileNum()].setPiece(king);
                }

                // set first move to false for both pieces
                if(king instanceof FirstMoveIF){
                    ((FirstMoveIF) king).setFirstMove(false);
                }
                if(rook instanceof FirstMoveIF){
                    ((FirstMoveIF) rook).setFirstMove(false);
                }

                this.display();
                return true;
            }
            System.out.println("Invalid castle attempt.");
            return false;
        }

        if (isPlayersPiece && (toPiece == null || toPiece.getColor() != currentPlayer.getColor())) {
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
            }
        } else if (toPiece.getColor() == currentPlayer.getColor()) {
            System.out.println("Cannot move to a position that is occupied by your own piece.");
        } else {
            System.out.println("Cannot move a piece that is not yours.");
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
        //Position kingPos = player.getKing().getPosition(); TODO Pass this line into checkCondition when checking for check.

        // Get the list of valid moves for all the enemy pieces on the board.
        for (PieceIF piece : player.getPieces()) {
            // Cast the piece to a Piece object.
            Piece p = (Piece) piece;
            // Get the list of valid moves for the piece.
            List<Position> validMoves = p.getValidMoves(board, piece.getPosition(board));

            // Check to see if the king's position is in the list of valid moves.
            if (validMoves.contains(position)) {
                isCheck = true;
            }
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
            Piece king = (Piece) player.getKing();

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
                //undo(); // TODO
                undoMoveFromCheck();
            }

            // Check to see if any of the pieces can block the checkmate.
            for (PieceIF piece : player.getPieces()) {
                // Cast the piece to a Piece object.
                Piece p = (Piece) piece;

                // Get the list of valid moves for the piece.
                List<Position> validMoves = p.getValidMoves(board, piece.getPosition(board));

                for (Position position : validMoves) {
                    // Emulate the move of the piece to each position in the list of valid moves.
                    // Check to see if there is a check.

                    this.move(player, playerOther, p.getPosition(board).getFile(), p.getPosition(board).getRank(), position.getFile(), position.getRank());

                    if (!this.checkCondition(player, king.getPosition(board))) {
                        canBlockCheck = true;
                    }
                    //undo(); // TODO
                    undoMoveFromCheck();
                }
            }
            checkmate = !canMoveOutOfCheck && !canBlockCheck;
        }

        return checkmate;
    }

    /**
     * This method checks to see if there is a draw by
     * 1) stalemate
     * 2) threefold repetition
     * 3) fifty move rule
     * 4) agreement condition.
     *
     * @return true if there is a draw, false otherwise.
     */
    private boolean drawCondition(PlayerIF player) {
        return stalemateCondition(player) || threefoldRepetitionCondition()
                || fiftyMoveRule();
    }

    /**
     * This method checks to see if there is a stalemate.
     *
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
     *
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
     *
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
     *
     * @return true if there is a agreement condition, false otherwise.
     */
    private boolean agreementCondition() {
        // Both players agree to a draw.
        boolean agreement = false;
        this.scan = new Scanner(System.in);
        System.out.println(getOtherPlayer(currentPlayer).getName() + ", do you agree to a draw? (y/n)");
        String input = scan.nextLine();
        if (input.equalsIgnoreCase("y")) {
            agreement = true;
        }
        return agreement;
    }

    /**
     * Sets the save game object.
     *
     * @param saveGame save game object
     */
    public void setSaveGame(LoadSaveGameIF saveGame) {
        this.saveGame = saveGame;
    }

    /**
     * Loop for the game to occur
     */
    private boolean gameLoop() {
        boolean success = false;

        // Logic
        /**
         * 1. Check to see if the game is over by checkmate or draw.
         * 2. If the game is not over by checkmate or draw, then check to see if the player is in check.
         * 3. If the player is in check, notify the player. (Player is able to move out of check since the game is not checkmate)
         * 4. If the player is not in check, then ask the player for a move.
         */

        // Check to see if the game is over by checkmate or draw.
        System.out.println("CHECKMATE CONDITION: " + checkmateCondition(currentPlayer, getOtherPlayer(currentPlayer)));
        if (checkmateCondition(currentPlayer, getOtherPlayer(currentPlayer)) || drawCondition(currentPlayer)) {
            // If the game is over, then notify the players and end the game.
            System.out.println("Game Over");
            System.out.println("The winner is " + getOtherPlayer(currentPlayer).getName());
        }
        // If the game is not over by checkmate or draw, then check to see if the player is in check.
        else if (checkCondition(getOtherPlayer(currentPlayer), currentPlayer.getKing().getPosition(board))) {
            // If the player is in check, then notify the player.
            System.out.println("You are in check!");
            // Ask the player for a move.

            boolean notInCheck = true;
            while (notInCheck) {
                success = startMove(currentPlayer);   // possibly return a boolean for valid move made
                if (!checkCondition(getOtherPlayer(currentPlayer), currentPlayer.getKing().getPosition(board))) {
                    notInCheck = false;
                } else {
                    System.out.println("Cannot move into check!");
                    undoMoveFromCheck();
                }
            }
        }
        // If the player is not in check, then ask the player for a move.
        else {
            boolean notInCheck = true;
            while (notInCheck) {
                success = startMove(currentPlayer);   // possibly return a boolean for valid move made
                if (!checkCondition(getOtherPlayer(currentPlayer), currentPlayer.getKing().getPosition(board))) {
                    notInCheck = false;
                } else {
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
    private void undoMoveFromCheck() {
        this.caretaker.pop();
        this.board.loadFromMemento(this.caretaker.peek());
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
     */
    private void undo() {
        BoardIF.BoardMementoIF memento = this.caretaker.down();
        if (memento != null) {
            this.board.loadFromMemento(memento);
        }
    }

    /**
     * This method redoes the move that just occured by viewing what is above in
     * the caretaker.
     */
    private void redo() {
        BoardIF.BoardMementoIF memento = this.caretaker.up();
        if (memento != null) {
            this.board.loadFromMemento(memento);
        }
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
        while (!validMove) { // loop until we get a valid move
            System.out.print("Make Move:"); // prompt for move
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
            if (fromFile == null || fromRank == null || toFile == null || toRank == null) {
                System.out.println("Invalid move. Please try again.");

                // reset validmove to ensure loop doesn't end
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
    private PlayerIF getOtherPlayer(PlayerIF player) {
        return player == player1 ? player2 : player1;
    }

    private void switchPlayers() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    /**
     * This method makes all necessary checks to see if a game has ended.
     *
     * @return true if an end condition has been met, false otherwise
     */
    private boolean endGameCondition() {
        boolean endGame = false;

        // game has come to an end, either because of checkmate or draw conditions
        if (checkmateCondition(currentPlayer, this.getOtherPlayer(currentPlayer))) {
            System.out.println(currentPlayer.getName() + "you're in checkmate! Better luck" +
                    "next time!");

            currentPlayer.increaseLosses(); //current has lost, they were placed in checkmate
            this.getOtherPlayer(currentPlayer).increaseWins();  //other has won, they placed other in checkmate

            endGame = true;

        } else if (drawCondition(currentPlayer)) {
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
        while (!validMove) { // loop until we get a valid move
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
            if (fromFile == null || fromRank == null) {
                System.out.println("Invalid piece. Please try again.");

                // reset valid move to ensure loop doesn't end
                validMove = false;
            }
            // check to see if there is a piece at the position
            if (this.board.getPiece(fromRank, fromFile) == null) {
                System.out.println("No piece at that position.");
                validMove = false;
            }
            // check to see if the piece is the current player's piece
            else if (!(currentPlayer.getPieces().contains(this.board.getPiece(fromRank, fromFile)))) {
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

    /*
     * This is the represent the possible logic that would be needed to implement castling.
     * I wanted to wait and see where to place it, but this method is essentially it.
     *
     * Outside of this function, if statement in game logic to see if the pieces
     * as "from" == king and "to" == rook, then call this method if true
     */

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
        if (tempF.getFileNum() < toF.getFileNum() && canCastle) {
            tempF = Files.values()[tempF.getFileNum() + 1];
            while (!tempF.equals(toF)) {
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
            }
        }
        // check if king is moving to the left
        else {
            while (!tempF.equals(toF)) {
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
            }
        }
        return canCastle; //true if castling is possible, false if not
    }
}
