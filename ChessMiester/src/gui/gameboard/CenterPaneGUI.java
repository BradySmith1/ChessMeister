/**
 * This class is responsible for creating the center pane of the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import controller.Chess;
import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import gui_backend.PieceGUI;
import gui_backend.SquareGUI;
import gui_backend.StateValidation;
import controller.BoardMementoCaretaker;
import interfaces.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;

import model.Board;
import model.Piece;
import model.Position;

public class CenterPaneGUI implements GameBoardObserver, EventHandler<MouseEvent>, BoardIF, CenterPaneObserver {
    /**
     * The root pane.
     */
    private GridPane root;

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

    private final BoardMementoCaretaker caretaker;

    /**
     * The state of the board.
     */
    private String state;

    /**
     * /**
     * Constructor for the center pane.
     */
    public CenterPaneGUI() {
        root = new GridPane();
        clicked = null;
        popup = null;
        initBoard();
        setup();
        setConstraints();
        try {
            populateSquares();
        } catch (FileNotFoundException | MalformedURLException fnfe) {
            System.out.println("Error: File not found.");
        }
        this.state = "{}#[]#[]#[]";
        this.createState();
        this.caretaker = new BoardMementoCaretaker(this.createMemento());
    }

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
                root.add(square, col, row, 1, 1);
                squares[row][col] = square;
            }
        }
    }

    /**
     * Sets the constraints for the board squares.
     */
    private void setConstraints() {
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5,
                    Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5,
                    Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    VPos.CENTER, true));
        }
    }

    private void PieceImageFactory(ChessPieceType type,  GameColor color, PieceGUI view) throws MalformedURLException {
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
     * @param mouse The mouse event
     */
    private void clickMove(MouseEvent mouse) {
        if (clicked == null) {
            clicked = (SquareGUI) mouse.getSource();
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
            root.addEventFilter(MouseEvent.ANY, this);
        } else {
            List<Position> validMoves;
            if (clicked.getPiece().getImage() != null) {
                PieceGUI piece = (PieceGUI) clicked.getPiece();
                validMoves = piece.getMoveType().getValidMoves(this,
                        clicked.getPosition());
                SquareGUI newClicked = (SquareGUI) mouse.getSource(); //TODO need to integrate valid moves into here.
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
                if (valid) {
                    // TODO Temporary code to test switching players
                    this.alertPlayerSwitch(this.currentPlayer);
                    // TODO temporary testing purposes
                    if (this.movingOwnPiece()) {
                        //boolean legalState = this.gameStateCheck();
                        //if (legalState) {
                        if (this.determineCapture(newClicked)) {
                            this.capturePiece(newClicked);
                        }
                        newClicked.getPiece().setPieceImage(clicked.getPiece().getImage());
                        clicked.getPiece().setPieceImage(null);

                        // FIXME Reassign player pieces b/c after a move is made, the players list needs to update with the new piece
                        this.player1.assignPieces(this);
                        this.player2.assignPieces(this);

                        //Saving to memento.
                        Position oldPos = clicked.getPosition();
                        Position newPos = ((SquareGUI) mouse.getSource()).getPosition();
                        this.addMove(currentPlayer.getColor(), oldPos.getFile(), oldPos.getRank(), newPos.getFile(), newPos.getRank());
                        caretaker.push(this.createMemento());

                        this.switchPlayers();   // TODO This is called here when a confirmed move is made
                        System.out.println(this.currentPlayer.getName());
                        this.gameStateCheck();
                        //}
                    }
                }
            }
            popup.close();
            clicked = null;
            popup = null;
            root.removeEventFilter(MouseEvent.ANY, this);
        }
    }

    /**
     * Returns the root of the game board.
     *
     * @return The root of the game board
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Notifies the view that the left mouse button has been clicked.
     *
     * @param event The mouse event
     */
    @Override
    public void notifyLeftClick(Event event) {
        this.notifyPane();
        clickMove((MouseEvent) event);
    }

    /**
     * Notifies the view that the right mouse button has been clicked.
     *
     * @param event The mouse event
     */
    @Override
    public void notifyRightClick(Event event) {
        this.notifyPane();
        SquareGUI clickedSquare = (SquareGUI) event.getSource(); //TODO integrate highlighting.
        if (clickedSquare.getPiece().getImage() != null) {
            PieceGUI piece = (PieceGUI) clickedSquare.getPiece();
            List<Position> validMoves = piece.getMoveType().getValidMoves(this, clickedSquare.getPosition());
            for (Position position : validMoves) {
                squares[position.getRank().getIndex()][position.getFile().getFileNum()].setColor(this.highlightColor);
            }

        }
    }

    /**
     * Notifies the view that the mouse has moved.
     *
     * @param event The mouse event
     */
    public List<Position> notifyPieceMoving(Event event) {
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

    // TODO Kaushal: This is the method that will return the squares from the center
    public SquareGUI[][] getSquares() {
        return squares;
    }

    @Override
    public int getWidth() {
        return this.size;
    }

    @Override
    public int getHeight() {
        return this.size;
    }

    @Override
    public PieceIF getPiece(Rank r, Files f) {
        return squares[r.getIndex()][f.getFileNum()].getPiece();
    }

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
    public void notifyPane() {
        this.observer.notifyPane();
    }

    /**
     * Notifies the observer that the undo button has been pressed.
     */
    @Override
    public void notifyUndo() {
        this.undo();
    }

    @Override
    public void notifyRedo(){
        this.redo();
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
        this.addMove(currentPlayer.getColor(), oldPos.getFile(), oldPos.getRank(), newPos.getFile(), newPos.getRank());
        caretaker.push(this.createMemento());
    }

    public void setHighlightColor(Color color) {
        this.highlightColor = color;
    }

    /**
     * Returns the other player based on the current player.
     *
     * @param player the current player
     * @return the other player
     */
    private PlayerIF getOtherPlayer(PlayerIF player) {
        return player == player1 ? player2 : player1;
    }

    private void switchPlayers() {
        this.currentPlayer = getOtherPlayer(this.currentPlayer);
    }

    public void setPlayer1(PlayerIF player1) {
        this.player1 = player1;
        this.currentPlayer = player1;
    }

    public void setPlayer2(PlayerIF player2) {
        this.player2 = player2;
    }

    public void alertPlayerSwitch(PlayerIF player) {
        System.out.printf("Player %s's turn\n", player.getColor() == GameColor.WHITE ? "White" : "Black");
    }

    /**
     * Checks to see if the game state is legal before making a move
     *
     * @return true if a move can be made, false otherwise
     */
    private boolean gameStateCheck() {
        boolean legalState = true;
        System.out.println("Trying to move : " + clicked.getPiece().getColor() + " Piece --> Current player is : " + this.currentPlayer.getColor());
        // Check to see if the player wanting to make the move is in check --> isInCheck = true if current player is in check, false otherwise
        boolean isInCheck = StateValidation.checkCondition(this.getOtherPlayer(this.currentPlayer), this.currentPlayer.getKing().getPosition(this), this);

        // If the current player is in check, then check to see if the current player is in checkmate
        if (isInCheck) {
            // Checkmate check: Inside IF STMT when current player is in checkmate
            if (StateValidation.checkMateCondition(this.currentPlayer, this.getOtherPlayer(this.currentPlayer), this)) {
                legalState = false;
                System.out.println("Checkmate");
            }
            // If the current player is in check but not checkmate, then the game state is semi-legal
            else {
                System.out.println("Check");
            }
        }
        // If the current player is not in check, then it is possible that the game is stalemate.
        else if (StateValidation.stalemateCondition(this.currentPlayer, this.getOtherPlayer(this.currentPlayer), this)) {
            System.out.println("Stalemate");
            legalState = false;
        }
        return legalState;
    }

    /**
     * Check to see if the piece we are trying to move is our piece
     *
     * @return True if the piece we are trying to move is ours, false otherwise
     */
    private boolean movingOwnPiece() {
        // Check to see if the piece we are trying to move is the current player's piece, if it is then do further checks
        return this.clicked.getPiece().getColor() == this.currentPlayer.getColor();
    }

    private boolean determineCapture(SquareGUI newClicked) {
        return newClicked.getPiece().getImage() != null;
    }

    private void capturePiece(SquareGUI square) {
        notifyAddCapturedPiece(square.getPiece());
    }

    //Start of the memento implementation.

    /**
     * Creates the pieces placed part of the memento
     */
    public void createState() {
        StringBuilder stateBuilder = new StringBuilder("{");
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                SquareIF square = (squares[i][j]);
                if (square.getPiece().getImage() != null) {

                    stateBuilder.append(
                            Character.toUpperCase(square.getPosition().getFile().getFileChar()));
                    stateBuilder.append(square.getPosition().getRank().displayNum);
                    stateBuilder.append(":");
                    stateBuilder.append(square.getPiece().getType().letter);
                    stateBuilder.append(square.getPiece().getColor().toString().charAt(0));
                    if (i != getWidth() - 1 || j != getHeight() - 1) {
                        stateBuilder.append(",");   // comma after every piece other than last
                    }
                }
            }
        }
        stateBuilder.append("}");
        this.state =
                stateBuilder.toString() + "#" + this.state.split("#")[1] + "#" + this.state.split("#")[2] + "#" + this.state.split("#")[3];
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

        StringBuilder stateBuilder2 = new StringBuilder(this.state.split("#")[2]);
        if (player1.getCapturedPieces().size() != 0){
            stateBuilder2.delete(1, stateBuilder2.length());
            for(int i = 0; i < player1.getCapturedPieces().size(); i++){
                stateBuilder2.append(player1.getCapturedPieces().get(i).getColor().toString().charAt(0));
                stateBuilder2.append("_");
                stateBuilder2.append(player1.getCapturedPieces().get(i).getType().letter);
                if(i != player1.getCapturedPieces().size() - 1){
                    stateBuilder2.append(",");
                }
            }
            stateBuilder2.append("]");
        }
        StringBuilder stateBuilder3 = new StringBuilder(this.state.split("#")[3]);
        if (player2.getCapturedPieces().size() != 0){
            stateBuilder3.delete(1, stateBuilder3.length());
            for(int i = 0; i < player1.getCapturedPieces().size(); i++){
                stateBuilder3.append(player1.getCapturedPieces().get(i).getColor().toString().charAt(0));
                stateBuilder3.append("_");
                stateBuilder3.append(player1.getCapturedPieces().get(i).getType().letter);
                if(i != player1.getCapturedPieces().size() - 1){
                    stateBuilder3.append(",");
                }
            }
            stateBuilder3.append("]");
        }

        this.state = this.state.split("#")[0] + "#" + stateBuilder.toString() + "#" + stateBuilder2.toString() + "#" + stateBuilder3.toString();
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
        setCapturedPiecesFromMemento(capturedPiecesPlayer1, capturedPiecesPlayer2);
        this.notifyAddCapturedPiece(null);
        this.state = boardMemento.state();
    }

    public void setCapturedPiecesFromMemento(String[] player1Pieces, String[] player2Pieces){
        this.player1.getCapturedPieces().clear();
        this.player2.getCapturedPieces().clear();
        String[] empty = new String[1];
        empty[0] = "";
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
                this.player1.addCapturedPiece(pieceGUI);
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
            PieceGUI view = (PieceGUI) squares[newRank.getIndex()][newFile.getFileNum()].getPiece();
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

    /**
     * A memento nested class for the board object. It can hold a boards state.
     *
     * @param state A string representing the state the board is in
     */
    public record BoardMemento(String state) implements BoardMementoIF{}
}
