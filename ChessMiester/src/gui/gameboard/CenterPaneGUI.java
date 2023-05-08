package gui.gameboard;

import controller.BoardMementoCaretaker;
import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import gui_backend.PieceGUI;
import gui_backend.SquareGUI;
import gui_backend.StateValidation;
import interfaces.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.Board;
import model.Position;
import movements.KingMovement;
import movements.RookMovement;

/**
 * This class is responsible for creating the center pane of the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
public class CenterPaneGUI extends GridPane implements GameBoardObserver, EventHandler<MouseEvent>,
        BoardIF, CenterPaneObserver {

    /**
     * The squares on the board.
     */
    private SquareGUI[][] squares;

    /**
     * The square that was clicked.
     */
    private SquareGUI clicked;

    /**
     * The popup stage.
     */
    private Stage popup;

    /**
     * The size of the board.
     */
    final int size = 8;

    /**
     * Center pane observer.
     */
    private CenterPaneObserver observer;

    /**
     * The highlight color.
     */
    private Color highlightColor;

    /**
     * Players
     */
    private PlayerIF player1, player2;

    /**
     * Current player
     */
    private PlayerIF currentPlayer;

    private BoardMementoCaretaker caretaker;

    /**
     * The state of the board.
     */
    private String state;

    /**
     * If highlighting is turned on.
     */
    private boolean highlightOn;

    /**
     * Constructor for the center pane.
     */
    public CenterPaneGUI() {
        clicked = null;
        popup = null;
        //sets up the board.
        initBoard();
        setup();
        setConstraints();
        try {
            populateSquares();
        } catch (FileNotFoundException | MalformedURLException fnfe) {
            System.out.println("Error: File not found.");
        }
        //sets up the saving and loading of the board.
        this.state = "{}#[]#[]#[]";
        this.createState();
        this.caretaker = new BoardMementoCaretaker(this.createMemento());
    }

    /**
     * Initializes the game board by adding pieces to it.
     */
    @Override
    public void initBoard() {
        squares = new SquareGUI[size][size];
    }

    /**
     * Initializes the squares for the board
     */
    public void setup() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                SquareGUI square = new SquareGUI(row, col);
                square.addObserver(this);
                this.add(square, col, row, 1, 1);
                squares[row][col] = square;
            }
        }
    }

    /**
     * Sets the constraints for the board squares.
     */
    private void setConstraints() {
        for (int i = 0; i < size; i++) {
            this.getColumnConstraints().add(new ColumnConstraints(5,
                    Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    HPos.CENTER, true));
            this.getRowConstraints().add(new RowConstraints(5,
                    Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    VPos.CENTER, true));
        }
    }

    /**
     * Populates the pieces with pictures of the pieces.
     * @param type The type of piece.
     * @param color The color of the piece.
     * @param view The view of the piece.
     * @throws MalformedURLException If the URL is malformed.
     */
    private void PieceImageFactory(ChessPieceType type,  GameColor color, PieceGUI view) throws
            MalformedURLException {
        String url = new File("src/gui/gameboard/images/WhitePawn.png").toURI()
                .toURL().toExternalForm();
        Image whitePawnImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackPawn.png").toURI()
                .toURL().toExternalForm();
        Image blackPawnImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteRook.png").toURI()
                .toURL().toExternalForm();
        Image whiteRookImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteKnightLeft.png").toURI()
                .toURL().toExternalForm();
        Image whiteKnightLeftImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteBishop.png").toURI()
                .toURL().toExternalForm();
        Image whiteBishopImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteQueen.png").toURI()
                .toURL().toExternalForm();
        Image whiteQueenImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteKing.png").toURI()
                .toURL().toExternalForm();
        Image whiteKingImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackRook.png").toURI()
                .toURL().toExternalForm();
        Image blackRookImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackKnightLeft.png").toURI()
                .toURL().toExternalForm();
        Image blackKnightLeftImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackBishop.png").toURI()
                .toURL().toExternalForm();
        Image blackBishopImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackQueen.png").toURI()
                .toURL().toExternalForm();
        Image blackQueenImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackKing.png").toURI()
                .toURL().toExternalForm();
        Image blackKingImage = new Image(url);
        if (color == GameColor.BLACK) {
            switch(type){
                case Pawn -> {
                    view.setPieceImage(blackPawnImage);
                }
                case Rook -> {
                    view.setPieceImage(blackRookImage);
                }
                case Knight -> {
                    view.setPieceImage(blackKnightLeftImage);
                }
                case Bishop -> {
                    view.setPieceImage(blackBishopImage);
                }
                case Queen -> {
                    view.setPieceImage(blackQueenImage);
                }
                case King -> {
                    view.setPieceImage(blackKingImage);
                }
            }
        }else{
            switch(type){
                case Pawn -> {
                    view.setPieceImage(whitePawnImage);
                }
                case Rook -> {
                    view.setPieceImage(whiteRookImage);
                }
                case Knight -> {
                    view.setPieceImage(whiteKnightLeftImage);
                }
                case Bishop -> {
                    view.setPieceImage(whiteBishopImage);
                }
                case Queen -> {
                    view.setPieceImage(whiteQueenImage);
                }
                case King -> {
                    view.setPieceImage(whiteKingImage);
                }
            }
        }
    }

    /**
     * Sets all the pieces in their starting positions.
     *
     * @throws FileNotFoundException if file not found
     * @throws MalformedURLException if URL is malformed
     */
    private void populateSquares() throws FileNotFoundException, MalformedURLException {
        for (int i = 0; i < size; i++) {
            PieceGUI view = (PieceGUI) squares[1][i].getPiece();
            PieceImageFactory(ChessPieceType.Pawn, GameColor.BLACK, view);
        }
        for (int i = 0; i < size; i++) {
            PieceGUI view = (PieceGUI) squares[6][i].getPiece();
            PieceImageFactory(ChessPieceType.Pawn, GameColor.WHITE, view);
        }
        PieceGUI view = (PieceGUI) squares[0][0].getPiece();
        PieceImageFactory(ChessPieceType.Rook, GameColor.BLACK, view);
        view = (PieceGUI) squares[0][1].getPiece();
        PieceImageFactory(ChessPieceType.Knight, GameColor.BLACK, view);
        view = (PieceGUI) squares[0][2].getPiece();
        PieceImageFactory(ChessPieceType.Bishop, GameColor.BLACK, view);
        view = (PieceGUI) squares[0][3].getPiece();
        PieceImageFactory(ChessPieceType.Queen, GameColor.BLACK, view);
        view = (PieceGUI) squares[0][4].getPiece();
        PieceImageFactory(ChessPieceType.King, GameColor.BLACK, view);
        view = (PieceGUI) squares[0][5].getPiece();
        PieceImageFactory(ChessPieceType.Bishop, GameColor.BLACK, view);
        view = (PieceGUI) squares[0][6].getPiece();
        PieceImageFactory(ChessPieceType.Knight, GameColor.BLACK, view);
        view = (PieceGUI) squares[0][7].getPiece();
        PieceImageFactory(ChessPieceType.Rook, GameColor.BLACK, view);
        view = (PieceGUI) squares[7][0].getPiece();
        PieceImageFactory(ChessPieceType.Rook, GameColor.WHITE, view);
        view = (PieceGUI) squares[7][1].getPiece();
        PieceImageFactory(ChessPieceType.Knight, GameColor.WHITE, view);
        view = (PieceGUI) squares[7][2].getPiece();
        PieceImageFactory(ChessPieceType.Bishop, GameColor.WHITE, view);
        view = (PieceGUI) squares[7][3].getPiece();
        PieceImageFactory(ChessPieceType.Queen, GameColor.WHITE, view);
        view = (PieceGUI) squares[7][4].getPiece();
        PieceImageFactory(ChessPieceType.King, GameColor.WHITE, view);
        view = (PieceGUI) squares[7][5].getPiece();
        PieceImageFactory(ChessPieceType.Bishop, GameColor.WHITE, view);
        view = (PieceGUI) squares[7][6].getPiece();
        PieceImageFactory(ChessPieceType.Knight, GameColor.WHITE, view);
        view = (PieceGUI) squares[7][7].getPiece();
        PieceImageFactory(ChessPieceType.Rook, GameColor.WHITE, view);
    }

    /**
     * Enables user to click to move a piece on the chess board.
     *
     * @param event The mouse event
     */
    private void clickMove(Event event) {
        //First if statement is for if the user clicks on a square to move from.
        if(clicked == null){
            clicked = (SquareGUI) event.getSource();
            if(event instanceof MouseEvent mouse) {
                popup = new Stage();
                popup.initStyle(StageStyle.UNDECORATED);
                VBox box = new VBox();
                Label moveLabel = new Label("Move Piece");
                box.getChildren().add(moveLabel);
                Scene stageScene = new Scene(box, 70, 20);
                popup.setScene(stageScene);
                popup.setX(mouse.getScreenX() + 10);
                popup.setY(mouse.getScreenY() + 10);
                popup.show();
                this.addEventFilter(MouseEvent.ANY, this);
            }
        //Else statement is for if the user clicks on a square to move to.
        }else{
            //Basic checking if valid moves are possible.
            List<Position> validMoves;
            if (clicked.getPiece().getImage() != null) {
                PieceGUI piece = (PieceGUI) clicked.getPiece();
                validMoves = piece.getMoveType().getValidMoves(this,
                        clicked.getPosition());
                SquareGUI newClicked = (SquareGUI) event.getSource();
                boolean valid = false;
                for (Position validMove : validMoves) {
                    if (validMove == null) {
                        continue;
                    }
                    if (validMove.equals(newClicked.getPosition())) {
                        valid = true;
                        break;
                    }
                }
                //Checks for other possible moves like castling.
                if (valid) {
                    // TODO Temporary code to test switching players
                    this.alertPlayerSwitch(this.currentPlayer);
                    // TODO temporary testing purposes
                    if (this.movingOwnPiece()) {
                        Image image;

                        if (this.determineCapture(newClicked)) {this.capturePiece(newClicked);}
                        image = clicked.getPiece().getImage();
                        newClicked.getPiece().setPieceImage(clicked.getPiece().getImage());
                        clicked.getPiece().setPieceImage(null);

                        this.player1.assignPieces(this);
                        this.player2.assignPieces(this);

                        if (StateValidation.checkCondition(this.getOtherPlayer(this.currentPlayer),
                                this.currentPlayer.getKing().getPosition(this), this)) {
                            // Undo the move
                            newClicked.getPiece().setPieceImage(null);
                            clicked.getPiece().setPieceImage(image);

                            this.illegalMoveAlert("Cannot move into check!");
                        }
                        else{
                            //Saving to memento.
                            Position oldPos = clicked.getPosition();
                            Position newPos = ((SquareGUI) event.getSource()).getPosition();
                            this.addMove(currentPlayer.getColor(), oldPos.getFile(),
                                    oldPos.getRank(), newPos.getFile(), newPos.getRank());
                            caretaker.push(this.createMemento());

                            this.switchPlayers();
                            System.out.println(this.currentPlayer.getName());
                            this.notifyBottomPane(this.currentPlayer.getName());
                            this.gameStateCheck();
                        }
                    }
                }
                else{
                    this.castleLogic(newClicked);
                }
            }
            //cleanup of the popup and clicked square.
            if(popup != null){
                popup.close();
                popup = null;
                this.removeEventFilter(MouseEvent.ANY, this);
            };
            clicked = null;

        }
    }

    /**
     * Returns the root of the game board.
     *
     * @return The root of the game board
     */
    public Pane getRoot() {
        return this;
    }

    /**
     * Notifies the view that the left mouse button has been clicked.
     *
     * @param event The mouse event
     */
    @Override
    public void notifyLeftClick(Event event) {
        this.notifyPane(true);
        clickMove(event);
    }

    /**
     * Notifies the view that the right mouse button has been clicked.
     *
     * @param event The mouse event
     */
    @Override
    public void notifyRightClick(Event event) {
        this.notifyPane(true);
        //This is all the logic for highlighting valid moves.
        SquareGUI clickedSquare = (SquareGUI) event.getSource();
        if (clickedSquare.getPiece().getImage() != null && this.highlightOn) {
            PieceGUI piece = (PieceGUI) clickedSquare.getPiece();
            List<Position> validMoves = piece.getMoveType()
                    .getValidMoves(this, clickedSquare.getPosition());
            for (Position position : validMoves) {
               squares[position.getRank().getIndex()][position.getFile().getFileNum()]
                       .setColor(this.highlightColor);
            }

        }
    }

    /**
     * setter for highlight availability.
     *
     * @param highlight if highlighting is on or off.
     */
    public void setHighlight(boolean highlight){
        this.highlightOn = highlight;
    }

    /**
     * Notifies the view that the mouse has moved.
     *
     * @param event The mouse event
     */
    public List<Position> notifyPieceMoving(Event event) {
        //this is the logic for showing that a piece is being dragged from one square to another.
        DragEvent dragEvent = (DragEvent) event;
        List<Position> validMoves = null;
        SquareGUI clickedSquare = (SquareGUI) dragEvent.getGestureSource();
        if (clickedSquare.getPiece().getImage() != null) {
            PieceGUI piece = (PieceGUI) clickedSquare.getPiece();
            validMoves = piece.getMoveType().getValidMoves(this,
                    clickedSquare.getPosition());
        }
        return validMoves;
    }


    /**
     * Handles the mouse event.
     *
     * @param event The mouse event
     */
    @Override
    public void handle(MouseEvent event) {
        popup.setX(event.getScreenX() + 10); //700
        popup.setY(event.getScreenY() + 10); //150
    }

    /**
     * Returns the squares of the board.
     * @return The squares of the board
     */
    public SquareGUI[][] getSquares() {
        return squares;
    }

    /**
     * returns the width of the board
     * @return the width of the board
     */
    @Override
    public int getBoardWidth() {
        return this.size;
    }

    /**
     * returns the height of the board
     * @return the height of the board
     */
    @Override
    public int getBoardHeight() {
        return this.size;
    }

    /**
     * gets a piece from a certain position on the board
     * @param r the rank of the piece.
     * @param f the file of the piece.
     * @return the piece at the position.
     */
    @Override
    public PieceIF getPiece(Rank r, Files f) {
        return squares[r.getIndex()][f.getFileNum()].getPiece();
    }

    /**
     * gets a piece from a certain position on the board
     * @param row the row of the piece.
     * @param col the column of the piece.
     * @return the piece at the position.
     */
    @Override
    public PieceIF getPiece(int row, char col) {
        return squares[row][col].getPiece();
    }


    /**
     * Adds an observer to the center pane.
     *
     * @param observer the observer to be added
     */
    public void addObserver(CenterPaneObserver observer) {
        this.observer = observer;
    }

    /**
     * Notifies the observer that the pane has been updated.
     */
    @Override
    public void notifyPane(boolean notify) {
       this.observer.notifyPane(notify);
    }

    /**
     * Notifies the observer that the undo button has been pressed.
     */
    @Override
    public void notifyUndo() {
        this.undo();
    }

    /**
     * Notifies the observer that the redo button has been pressed.
     */
    @Override
    public void notifyRedo(){
        this.redo();
    }

    /**
     * Notifies the observer that the save button has been pressed.
     */
    @Override
    public void notifySaveGame(PrintWriter writer) {
        writer.write(caretaker.topToBottom().state());
        while(caretaker.up() != null) {
            writer.write("\n" + caretaker.peek().state());
        }
        writer.write("\n" + player1.getName() + "\n" + player2.getName());
    }

    /**
     * Notifies the observer that a piece has been captured.
     *
     * @param piece the piece that has been captured
     */
    public void notifyAddCapturedPiece(PieceIF piece) {
        observer.notifyAddCapturedPiece(piece);
    }

    /**
     * Notifies the observer that the board has been altered and needs to be saved.
     */
    public void notifyBoardLoader(Event event){
        Position oldPos = ((SquareGUI)((DragEvent) event).getGestureSource()).getPosition();
        Position newPos = ((SquareGUI) event.getSource()).getPosition();
        this.addMove(currentPlayer.getColor(), oldPos.getFile(), oldPos.getRank(), newPos.getFile(),
                newPos.getRank());
        caretaker.push(this.createMemento());
    }

    /**
     * sets the highlight color
     * @param color the color to be set
     */
    public void setHighlightColor(Color color) {
        this.highlightColor = color;
    }

    /**
     * Returns the other player based on the current player.
     * @param player the current player
     * @return the other player
     */
    private PlayerIF getOtherPlayer(PlayerIF player) {
        return player == player1 ? player2 : player1;
    }

    /**
     * Switches the current player to the other player.
     */
    private void switchPlayers(){
        this.currentPlayer = getOtherPlayer(this.currentPlayer);
    }

    /**
     * sets player 1
     * @param player1 the player to be set
     */
    public void setPlayer1(PlayerIF player1) {
        this.player1 = player1;
        this.currentPlayer = player1;
    }

    /**
     * sets player 2
     * @param player2 the player to be set
     */
    public void setPlayer2(PlayerIF player2) {
        this.player2 = player2;
    }

    /**
     * alerts system to switch players
     * @param player the player whose turn it is
     */
    public void alertPlayerSwitch(PlayerIF player){
        System.out.printf("Player %s's turn\n",
                    player.getColor() == GameColor.WHITE ? "White" : "Black");
    }

    /**
     * Checks to see if the game state is legal before making a move
     * @return true if a move can be made, false otherwise
     */
    private boolean gameStateCheck() {
        boolean legalState = true;
        System.out.println("Trying to move : " + clicked.getPiece().getColor()
                + " Piece --> Current player is : " + this.currentPlayer.getColor());
        // Check to see if the player wanting to make the move is in check -->
        // isInCheck = true if current player is in check, false otherwise
        boolean isInCheck = StateValidation.checkCondition(this.getOtherPlayer(this.currentPlayer),
                    this.currentPlayer.getKing().getPosition(this), this);

        // If the current player is in check, then check to
        // see if the current player is in checkmate
        if (isInCheck) {
            // Checkmate check: Inside IF STMT when current player is in checkmate
            if (StateValidation.checkMateCondition(this.currentPlayer,
                        this.getOtherPlayer(this.currentPlayer), this)) {
                legalState = false;
                this.currentPlayer.increaseLosses();
                this.getOtherPlayer(this.currentPlayer).increaseWins();
                gameEndAlert("The game has ended by Checkmate. "
                        + this.getOtherPlayer(this.currentPlayer).getName() + " has won!");

            }
            // If the current player is in check but not checkmate,
            // then the game state is semi-legal
            else{
                this.illegalMoveAlert("You are in check! You must move out of check!");
                legalState = StateValidation.canMoveOutOfCheck(this.currentPlayer,
                        this.getOtherPlayer(this.currentPlayer), this);
            }
        }
        // If the current player is not in check,
        // then it is possible that the game is stalemate.
        else if (StateValidation.stalemateCondition(this.currentPlayer,
                    this.getOtherPlayer(this.currentPlayer), this)) {
                legalState = false;
                this.currentPlayer.increaseDraws();
                this.getOtherPlayer(this.currentPlayer).increaseDraws();
                gameEndAlert("The game has ended by Stalemate. It is a draw!");
        }
        return legalState;
    }

    /**
     * Check to see if the piece we are trying to move is our piece
     * @return True if the piece we are trying to move is ours, false otherwise
     */
    private boolean movingOwnPiece(){
        // Check to see if the piece we are trying to move is the current player's
        // piece, if it is then do further checks
        return this.clicked.getPiece().getColor() == this.currentPlayer.getColor();
    }

    /**
     * determines whether a capture occurred or not
     * @param newClicked the square that was clicked
     * @return true if a capture occurred, false otherwise
     */
    private boolean determineCapture(SquareGUI newClicked){
        return newClicked.getPiece().getImage() != null;
    }

    /**
     * notifies that a piece has been captured
     * @param square the square that has been captured
     */
    private void capturePiece(SquareGUI square){
        System.out.println("Capturing piece at " + square.getPosition().toString());
        System.out.println("Captured piece is " + square.getPiece().getType() + " " +
                square.getPiece().getColor());
        notifyAddCapturedPiece(square.getPiece());
    }

    //Start of the memento implementation.

    /**
     * Creates the pieces placed part of the memento
     */
    public void createState() {
        StringBuilder stateBuilder = new StringBuilder("{");
        for (int i = 0; i < getBoardWidth(); i++) {
            for (int j = 0; j < getBoardHeight(); j++) {
                SquareIF square = (squares[i][j]);
                if (square.getPiece().getImage() != null) {
                    //Records the state of each piece to a string to be written to a file.
                    stateBuilder.append(
                            Character.toUpperCase(square.getPosition().getFile().getFileChar()));
                    stateBuilder.append(square.getPosition().getRank().displayNum);
                    stateBuilder.append(":");
                    stateBuilder.append(square.getPiece().getType().letter);
                    stateBuilder.append(square.getPiece().getColor().toString().charAt(0));
                    if (i != getBoardWidth() - 1 || j != getBoardHeight() - 1) {
                        stateBuilder.append(",");   // comma after every piece other than last
                    }
                }
            }
        }
        stateBuilder.append("}");
        this.state =
                stateBuilder.toString() + "#" + this.state.split("#")[1] + "#" +
                        this.state.split("#")[2] + "#" + this.state.split("#")[3];
    }

    /**
     * Adds the move to the boards state that it holds in a field.
     *
     * @param color color of the moving piece
     * @param fromF current file for the piece
     * @param fromR current rank for the piece
     * @param toF   the file to move to
     * @param toR   the rank to move to
     */
    @Override
    public void addMove(GameColor color, Files fromF, Rank fromR, Files toF, Rank toR) {
        //Adds the moves to the state string.
        this.createState();
        StringBuilder stateBuilder = new StringBuilder(this.state.split("#")[1]);
        stateBuilder.deleteCharAt(stateBuilder.length() - 1);
        if (stateBuilder.length() > 7) {
            stateBuilder.append(",");
        }
        stateBuilder.append(color.toString().charAt(0));
        stateBuilder.append(":");
        stateBuilder.append(Character.toUpperCase(fromF.getFileChar()));
        stateBuilder.append(fromR.displayNum);
        stateBuilder.append("-");
        stateBuilder.append(Character.toUpperCase(toF.getFileChar()));
        stateBuilder.append(toR.displayNum);
        stateBuilder.append("]");

        //Adds the captured pieces of player1 to the state string.
        StringBuilder stateBuilder2 = new StringBuilder(this.state.split("#")[2]);
        if (player1.getCapturedPieces().size() != 0){
            stateBuilder2.delete(1, stateBuilder2.length());
            for(int i = 0; i < player1.getCapturedPieces().size(); i++){
                stateBuilder2.append(player1.getCapturedPieces().get(i).getColor().toString()
                        .charAt(0));
                stateBuilder2.append("_");
                stateBuilder2.append(player1.getCapturedPieces().get(i).getType().letter);
                if(i != player1.getCapturedPieces().size() - 1){
                    stateBuilder2.append(",");
                }
            }
            stateBuilder2.append("]");
        }

        //Adds the captured pieces of player2 to the state string.
        StringBuilder stateBuilder3 = new StringBuilder(this.state.split("#")[3]);
        if (player2.getCapturedPieces().size() != 0){
            stateBuilder3.delete(1, stateBuilder3.length());
            for(int i = 0; i < player2.getCapturedPieces().size(); i++){
                stateBuilder3.append(player2.getCapturedPieces().get(i).getColor().toString()
                        .charAt(0));
                stateBuilder3.append("_");
                stateBuilder3.append(player2.getCapturedPieces().get(i).getType().letter);
                if(i != player2.getCapturedPieces().size() - 1){
                    stateBuilder3.append(",");
                }
            }
            stateBuilder3.append("]");
        }

        //Adds the moves and captured pieces to the state string.
        this.state = this.state.split("#")[0] + "#" + stateBuilder.toString() + "#" +
                stateBuilder2.toString() + "#" + stateBuilder3.toString();
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
            this.loadFromMemento(memento);
            player1.assignPieces(this);
            player2.assignPieces(this);
            success = true;
        }
        return success;
    }

    /**
     * This method redoes the move that just occurred by viewing what is above in
     * the caretaker.
     *
     * @return true if the redo was successful, false otherwise
     */
    private boolean redo() {
        boolean success = false;
        BoardIF.BoardMementoIF memento = this.caretaker.up();
        if(memento != null) {
            this.loadFromMemento(memento);
            player1.assignPieces(this);
            player2.assignPieces(this);
            success = true;
        }
        return success;
    }

    /**
     * Creates a memento for the current state of the board
     *
     * @return the memento to be stored in a caretaker
     */
    @Override
    public BoardMementoIF createMemento() {
        return new Board.BoardMemento(this.state);
    }

    @Override
    public String getState() {
        return this.state;
    }

    /**
     * Method to load the board from a different memento / board state.
     *
     * @param boardMemento the memento to load in
     */
    @Override
    public void loadFromMemento(BoardMementoIF boardMemento) {
        //parses the file and loads everything to the memento
        String[] contents = boardMemento.state().split("#");
        String[] pieces = contents[0].substring(1, contents[0].length() - 1).split(",");
        String[] movesForward = contents[1].substring(1, contents[1].length() - 1).split(",");
        String[] capturedPiecesPlayer1 = contents[2].substring(1, contents[2].length() - 1).split(
                ",");
        String[] capturedPiecesPlayer2 = contents[3].substring(1, contents[3].length() - 1).split(
                ",");
        ArrayList<String> movesAL = new ArrayList<>(Arrays.stream(movesForward).toList());
        Collections.reverse(movesAL);
        String[] moves = movesAL.toArray(new String[0]);
        setPiecesFromMemento(pieces);
        if (!moves[0].equals("")) {
            setFirstMovesFromMemento(moves);
        }
        //choosing what player is the current player to move.
        if(movesForward[movesForward.length - 1].substring(0, 1).equals("W")){
            this.currentPlayer = player2;
        }
        else{
            this.currentPlayer = player1;
        }
        //changes the bottom pane to the current player.
        this.notifyBottomPane(this.currentPlayer.getName());
        //sets the captured pieces of the players and shows them on the left and right panes.
        setCapturedPiecesFromMemento(capturedPiecesPlayer1, capturedPiecesPlayer2);
        this.notifyAddCapturedPiece(null);
        this.state = boardMemento.state();
    }

    public void setCapturedPiecesFromMemento(String[] player1Pieces, String[] player2Pieces){
        this.player1.getCapturedPieces().clear();
        this.player2.getCapturedPieces().clear();
        String[] empty = new String[1];
        empty[0] = "";
        //setting player 1 captured pieces
        if(!Arrays.equals(player1Pieces, empty)){
            for (String piece : player1Pieces){
                GameColor color = null;
                ChessPieceType type = null;
                char colorC = piece.charAt(0);
                char typeC = piece.charAt(2);
                if (colorC == 'W'){
                    color = GameColor.WHITE;
                }
                else{
                    color = GameColor.BLACK;
                }
                type = ChessPieceType.valueOf(ChessPieceType.identify(String.valueOf(typeC)));
                PieceGUI pieceGUI = new PieceGUI(null);
                try{
                    PieceImageFactory(type, color, pieceGUI);
                }catch (Exception e){
                    e.printStackTrace();
                }
                this.player1.addCapturedPiece(pieceGUI);
            }
        }
        //setting player 2 captured pieces
        if(!Arrays.equals(player2Pieces, empty)){
            for (String piece : player2Pieces){
                GameColor color = null;
                ChessPieceType type = null;
                char colorC = piece.charAt(0);
                char typeC = piece.charAt(2);
                if (colorC == 'W'){
                    color = GameColor.WHITE;
                }
                else{
                    color = GameColor.BLACK;
                }
                type = ChessPieceType.valueOf(ChessPieceType.identify(String.valueOf(typeC)));
                PieceGUI pieceGUI = new PieceGUI(null);
                try{
                    PieceImageFactory(type, color, pieceGUI);
                }catch (Exception e){
                    e.printStackTrace();
                }
                this.player2.addCapturedPiece(pieceGUI);
            }
        }

    }

    /**
     * Method to place the pieces depending on the String[] passed in from loadFromMemento()
     *
     * @param pieces An array in which each string describes a piece and its location
     */
    private void setPiecesFromMemento(String[] pieces) {
        this.initBoard();
        this.setup();
        for (String piece : pieces) {
            Files newFile = Files.valueOf(String.valueOf(piece.charAt(0))); // get file
            Rank newRank = Rank.valueOf("R" + piece.charAt(1)); // get rank
            // identify piece type from provided letter
            String type = ChessPieceType.identify(String.valueOf(piece.charAt(3)));
            // get piece type from returned string
            ChessPieceType pieceType = ChessPieceType.valueOf(type);
            String colorCase = String.valueOf(piece.charAt(4)); //get color
            GameColor color = null;
            switch (colorCase) {
                case "W" -> color = GameColor.WHITE;
                case "B" -> color = GameColor.BLACK;
            }
            PieceGUI view = (PieceGUI) squares[newRank.getIndex()][newFile.getFileNum()]
                    .getPiece();
            try{
                PieceImageFactory(pieceType, color, view);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the first moves for pieces that have certain characteristics that can
     * be determined by whether they're made a first move.
     *
     * @param moves the moves that have occurred in the game
     */
    private void setFirstMovesFromMemento(String[] moves) {
        for (String move : moves) {
            Files toF = Files.valueOf(String.valueOf(move.charAt(5)).toUpperCase());
            Rank toR = Rank.valueOf("R" + (String.valueOf(move.charAt(6))));

            PieceIF piece = squares[toR.getIndex()][toF.getFileNum()].getPiece();

            if (piece != null) {
                MovementIF movementType = piece.getMoveType();
                if (movementType instanceof FirstMoveIF movement) {
                    movement.setFirstMove(false);
                }
            }
        }
    }

    public BoardMementoCaretaker getBoardMementoCaretaker() {
        return caretaker;
    }

    public BoardMementoCaretaker setBoardMementoCaretaker(BoardMementoCaretaker caretaker) {
        return this.caretaker = caretaker;
    }

    /**
     * A memento nested class for the board object. It can hold a boards state.
     *
     * @param state A string representing the state the board is in
     */
    public record BoardMemento(String state) implements BoardMementoIF{}

    /**
     * Alerts users game has come to an end
     * @param message the message to be displayed
     */
    private void gameEndAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Game Over");

        // Size the alert window
        alert.getDialogPane().setMinHeight(800);
        alert.getDialogPane().setMinWidth(800);

        VBox dialogPaneContent = new VBox();
        dialogPaneContent.setSpacing(10);
        dialogPaneContent.setAlignment(Pos.CENTER);
        dialogPaneContent.setId("main-pane");

        // Add the message and player stats to the VBox
        Text messageText = new Text(message);
        Text returnMsg = new Text("Click OK to return to the main menu.");
        messageText.setId("text");
        returnMsg.setId("text");
        messageText.setTextAlignment(TextAlignment.CENTER);
        returnMsg.setTextAlignment(TextAlignment.CENTER);
        Text currentPlayerStats = new Text(this.currentPlayer.getName()
                + "'s Stats: " +
                "\n  Wins:    " + this.currentPlayer.getWins() +
                "\n  Losses: " + this.currentPlayer.getLosses() +
                "\n  Draws:  " + this.currentPlayer.getDraws());

        Text otherPlayerStats = new Text(this.getOtherPlayer(this.currentPlayer).getName()
                + "'s Stats: " +
                "\n  Wins:    " + this.getOtherPlayer(this.currentPlayer).getWins() +
                "\n  Losses: " + this.getOtherPlayer(this.currentPlayer).getLosses() +
                "\n  Draws:  " + this.getOtherPlayer(this.currentPlayer).getDraws());

        messageText.setId("text");
        currentPlayerStats.setId("text");
        otherPlayerStats.setId("text");

        // Add the message and player stats to the VBox
        dialogPaneContent.getChildren().addAll(messageText, returnMsg,
                currentPlayerStats, otherPlayerStats);

        // Create the OK button
        Button okButton = new Button("OK");
        okButton.setId("bottom-button");
        okButton.setMaxHeight(50);
        okButton.setMaxWidth(100);

        // Set the action of the OK button
        okButton.setOnAction(event -> {
            alert.close();
            this.notifyPane(false);
        });

        // Add the OK button to the VBox and style
        dialogPaneContent.getChildren().add(okButton);
        dialogPaneContent.getStylesheets().add(getClass().getResource(
                "gameBoard.css").toExternalForm());

        // Set the VBox as the content of the dialog pane
        alert.getDialogPane().setContent(dialogPaneContent);

        // Remove the default OK button
        alert.getButtonTypes().setAll(ButtonType.CLOSE);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CLOSE)).setVisible(false);

        // Add the cancel button
        alert.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        alert.showAndWait();
    }

    /**
     * Alert the user that the move they are trying to make is illegal
     * @param message The message to display to the user
     */
    private void illegalMoveAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alert");
        alert.setHeaderText("Board State");
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(getClass().getResource(
                "gameBoard.css").toExternalForm());
        alert.showAndWait();
    }

    /**
     * notifies bottom pane to update
     */
    public void notifyBottomPane(String currPlayer){
        this.observer.notifyBottomPane(currPlayer);
    }

    /**
     * Method to start checking for if castling is valid or not.
     * @param target the square that the piece is being moved to
     */
    private void castleLogic(SquareGUI target){
        Position clickedPos = this.clicked.getPosition();
        Position targetPos = target.getPosition();

        if (this.clicked.getPiece().getType() == ChessPieceType.King && target.getPiece()
                .getImage() != null){
            if (target.getPiece().getType() == ChessPieceType.Rook){
                if (canCastle(clickedPos.getFile(), clickedPos.getRank(), targetPos.getFile(),
                        targetPos.getRank())){
                    PieceIF king = this.clicked.getPiece();
                    PieceIF rook = target.getPiece();
                    Image rookImage = rook.getImage();
                    Image kingImage = king.getImage();
                    Position kingFrom = clicked.getPosition();
                    Position kingTo;
                    Position rookFrom = target.getPosition();
                    Position rookTo;

                    if (clickedPos.getFile().getFileNum() < targetPos.getFile().getFileNum()){
                        // Set rook at new place, guaranteed to have rook at file F
                        this.squares[targetPos.getRank().getIndex()][Files.F.getFileNum()]
                                .getPiece().setPieceImage(rookImage);
                        // Clear square from king
                        this.squares[clickedPos.getRank().getIndex()][clickedPos.getFile()
                                .getFileNum()].getPiece().setPieceImage(null);
                        // Clear square from rook
                        this.squares[targetPos.getRank().getIndex()][targetPos.getFile()
                                .getFileNum()].getPiece().setPieceImage(null);
                        // Set king at new place Guaranteed that the king will be at file G
                        this.squares[targetPos.getRank().getIndex()][Files.G.getFileNum()]
                                .getPiece().setPieceImage(kingImage);

                        kingTo = new Position(targetPos.getRank(), Files.G);
                        rookTo = new Position(targetPos.getRank(), Files.F);
                    }
                    else{
                        // Set rook at new place, guaranteed to have rook at file D
                        this.squares[targetPos.getRank().getIndex()][Files.D.getFileNum()]
                                .getPiece().setPieceImage(rookImage);
                        // Clear square from king
                        this.squares[clickedPos.getRank().getIndex()][clickedPos.getFile()
                                .getFileNum()].getPiece().setPieceImage(null);
                        // Clear square from rook
                        this.squares[targetPos.getRank().getIndex()][targetPos.getFile()
                                .getFileNum()].getPiece().setPieceImage(null);
                        // Set king at new place Guaranteed that the king will be at file C
                        this.squares[targetPos.getRank().getIndex()][Files.C.getFileNum()]
                                .getPiece().setPieceImage(kingImage);

                        kingTo = new Position(targetPos.getRank(), Files.G);
                        rookTo = new Position(targetPos.getRank(), Files.D);
                    }

                    // Set the first move of the king and rook to false
                    if (king instanceof FirstMoveIF){
                        ((FirstMoveIF) king).setFirstMove(false);
                    }
                    if (rook instanceof FirstMoveIF){
                        ((FirstMoveIF) rook).setFirstMove(false);
                    }

                    //Saving to memento.
                    this.addMove(currentPlayer.getColor(), kingFrom.getFile(), kingFrom.getRank(),
                            kingTo.getFile(), kingTo.getRank());
                    this.addMove(currentPlayer.getColor(), rookFrom.getFile(), rookFrom.getRank(),
                            rookTo.getFile(), rookTo.getRank());
                    caretaker.push(this.createMemento());

                    this.switchPlayers();
                    System.out.println(this.currentPlayer.getName());
                    this.notifyBottomPane(this.currentPlayer.getName());
                    //this.gameStateCheck();

                }
            }
        }
    }

    /**
     * Checks if the king and rook can castle
     * @param fromF the file the king is moving from
     * @param fromR the rank the king is moving from
     * @param toF the file the king is moving to
     * @param toR the rank the king is moving to
     * @return true if the king and rook can castle, false otherwise
     */
    private boolean canCastle(Files fromF, Rank fromR, Files toF, Rank toR){
        // grab the king and rook piece from positions to save keystrokes
        KingMovement king =
                (KingMovement) this.squares[fromR.getIndex()]
                        [fromF.getFileNum()].getPiece().getMoveType();
        RookMovement rook =
                (RookMovement) this.squares[toR.getIndex()]
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
        if (StateValidation.checkCondition(this.getOtherPlayer(this.currentPlayer),
                new Position(fromR, fromF), this) ||
                StateValidation.checkCondition(this.getOtherPlayer(this.currentPlayer),
                        new Position(toR, toF), this)) {
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

                if (StateValidation.checkCondition(this.getOtherPlayer(this.currentPlayer),
                        new Position(fromR, tempF), this)) {
                    canCastle = false;
                    //flag = false;
                }
                //check if there is a piece in the way
                if (this.squares[fromR.getIndex()][tempF.getFileNum()].getPiece().getImage()
                        != null) {
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
                if (StateValidation.checkCondition(this.getOtherPlayer(this.currentPlayer),
                        new Position(fromR, tempF), this)) {
                    canCastle = false;
                }
                //check if there is a piece in the way
                if (this.squares[fromR.getIndex()]
                        [tempF.getFileNum()].getPiece().getImage() != null) {
                    canCastle = false;
                }
                tempF = Files.values()[tempF.getFileNum() - 1];
                cnt++;
            }
        }
        return canCastle; //true if castling is possible, false if not
    }
}
