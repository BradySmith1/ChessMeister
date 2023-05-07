/**
 * This class is responsible for creating the center pane of the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import gui_backend.PieceGUI;
import gui_backend.SquareGUI;
import gui_backend.StateValidation;
import interfaces.BoardIF;
import interfaces.PieceIF;
import interfaces.PlayerIF;
import javafx.event.ActionEvent;
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
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import model.Position;

public class CenterPaneGUI implements GameBoardObserver, EventHandler<MouseEvent>,
                                      BoardIF, CenterPaneObserver {
    /** The root pane. */
    private GridPane root;

    /** The squares on the board. */
    private SquareGUI[][] squares;

    /** The square that was clicked. */
    private SquareGUI clicked;

    /** The popup stage. */
    private Stage popup;

    /** The size of the board. */
    final int size = 8;

    /** Center pane observer. */
    private CenterPaneObserver observer;

    /** The highlight color. */
    private Color highlightColor;

    /** Players */
    private PlayerIF player1, player2;

    /** Current player */
    private PlayerIF currentPlayer;

    /**

    /**
     * Constructor for the center pane.
     */
    public CenterPaneGUI(){
        root = new GridPane();
        clicked = null;
        popup = null;
        initBoard();
        setup();
        setConstraints();
        try{
            populateSquares();
        }catch(FileNotFoundException | MalformedURLException fnfe){
            System.out.println("Error: File not found.");
        }

    }

    @Override
    public void initBoard(){
        squares = new SquareGUI[size][size];
    }

    /**
     * Initializes the squares for the board
     */
    public void setup(){
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
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
        for(int i = 0; i < size; i++){
            root.getColumnConstraints().add(new ColumnConstraints(5,
                    Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5,
                    Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS,
                    VPos.CENTER, true));
        }
    }

    /**
     * Sets all the pieces in their starting positions.
     *
     * @throws FileNotFoundException if file not found
     * @throws MalformedURLException if URL is malformed
     */
    private void populateSquares() throws FileNotFoundException, MalformedURLException {
        String url = new File("src/gui/gameboard/images/WhitePawn.png").toURI()
                .toURL().toExternalForm();
        Image whitePawnImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackPawn.png").toURI()
                .toURL().toExternalForm();
        Image blackPawnImage = new Image(url);
        for(int i = 0; i < size; i++) {
            PieceGUI view = (PieceGUI) squares[1][i].getPiece();
            view.setPieceImage(blackPawnImage);
            view.setId("pawn" + i);
        }
        for(int i = 0; i < size; i++){
            PieceGUI view = (PieceGUI) squares[6][i].getPiece();
            view.setPieceImage(whitePawnImage);
            view.setId("pawn" + i);
        }
        setPieces(GameColor.WHITE, 0);
        setPieces(GameColor.BLACK, size-1);
    }

    /**
     * Helper method to set pieces on the board.
     *
     * @param color The color of the pieces
     * @param offset The offset for the pieces
     * @throws MalformedURLException if URL is malformed
     */
    private void setPieces(GameColor color, int offset) throws MalformedURLException {
        String url = new File("src/gui/gameboard/images/WhiteRook.png").toURI()
                .toURL().toExternalForm();
        Image whiteRookImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteKnightLeft.png").toURI()
                .toURL().toExternalForm();
        Image whiteKnightLeftImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteBishop.png").toURI()
                .toURL().toExternalForm();
        Image whiteBishopImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteKing.png").toURI()
                .toURL().toExternalForm();
        Image whiteQueenImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteQueen.png").toURI()
                .toURL().toExternalForm();
        Image whiteKingImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteKnightRight.png").toURI()
                .toURL().toExternalForm();
        Image whiteKnightRightImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackRook.png").toURI()
                .toURL().toExternalForm();
        Image blackRookImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackKnightLeft.png").toURI()
                .toURL().toExternalForm();
        Image blackKnightLeftImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackBishop.png").toURI()
                .toURL().toExternalForm();
        Image blackBishopImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackKing.png").toURI()
                .toURL().toExternalForm();
        Image blackQueenImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackQueen.png").toURI()
                .toURL().toExternalForm();
        Image blackKingImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackKnightRight.png").toURI()
                .toURL().toExternalForm();
        Image blackKnightRightImage = new Image(url);
        if(color == GameColor.BLACK){
            PieceGUI view = (PieceGUI) squares[offset][0].getPiece();
            view.setPieceImage(whiteRookImage);
            view = (PieceGUI) squares[offset][size-2].getPiece();
            view.setPieceImage(whiteKnightLeftImage);
            view = (PieceGUI) squares[offset][1].getPiece();
            view.setPieceImage(whiteKnightRightImage);
            view = (PieceGUI) squares[offset][size-3].getPiece();
            view.setPieceImage(whiteBishopImage);
            view = (PieceGUI) squares[offset][2].getPiece();
            view.setPieceImage(whiteBishopImage);
            view = (PieceGUI) squares[offset][size-1].getPiece();
            view.setPieceImage(whiteRookImage);
            view = (PieceGUI) squares[offset][size-4].getPiece();
            view.setPieceImage(whiteQueenImage);
            view = (PieceGUI) squares[offset][3].getPiece();
            view.setPieceImage(whiteKingImage);
        }else{
            PieceGUI view = (PieceGUI) squares[offset][0].getPiece();
            view.setPieceImage(blackRookImage);
            view = (PieceGUI) squares[offset][size-2].getPiece();
            view.setPieceImage(blackKnightLeftImage);
            view = (PieceGUI) squares[offset][1].getPiece();
            view.setPieceImage(blackKnightRightImage);
            view = (PieceGUI) squares[offset][size-3].getPiece();
            view.setPieceImage(blackBishopImage);
            view = (PieceGUI) squares[offset][2].getPiece();
            view.setPieceImage(blackBishopImage);
            view = (PieceGUI) squares[offset][size-1].getPiece();
            view.setPieceImage(blackRookImage);
            view = (PieceGUI) squares[offset][size-4].getPiece();
            view.setPieceImage(blackQueenImage);
            view = (PieceGUI) squares[offset][3].getPiece();
            view.setPieceImage(blackKingImage);
        }
    }

    /**
     * Enables user to click to move a piece on the chess board.
     *
     * @param mouse The mouse event
     */
    private void clickMove(MouseEvent mouse) {
        if(clicked == null){
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
        }else{
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
                if(valid){
                    // TODO Temporary code to test switching players
                    this.alertPlayerSwitch(this.currentPlayer);
                    // TODO temporary testing purposes
                    if (this.movingOwnPiece()) {
                        boolean legalState = this.gameStateCheck();
                        if (legalState) {
                            if (this.determineCapture(newClicked)) {this.capturePiece(newClicked);}
                            newClicked.getPiece().setPieceImage(clicked.getPiece().getImage());
                            clicked.getPiece().setPieceImage(null);

                            // FIXME Reassign player pieces b/c after a move is made, the players list needs to update with the new piece
                            this.player1.assignPieces(this);
                            this.player2.assignPieces(this);

                            this.switchPlayers();   // TODO This is called here when a confirmed move is made
                            // TODO call bottom pane and update player display message
                            this.notifyBottomPane(this.currentPlayer.getName());
                            System.out.println(this.currentPlayer.getName());
                            this.gameStateCheck();
                        }
                        else{
                            this.illegalMoveAlert("Illegal move. Try again.");
                        }
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
    public Pane getRoot(){ return root; }

    /**
     * Notifies the view that the left mouse button has been clicked.
     *
     * @param event The mouse event
     */
    @Override
    public void notifyLeftClick(Event event) {
        this.notifyPane(true);
        clickMove((MouseEvent) event);
    }

    /**
     * Notifies the view that the right mouse button has been clicked.
     *
     * @param event The mouse event
     */
    @Override
    public void notifyRightClick(Event event) {
        this.notifyPane(true);
        SquareGUI clickedSquare = (SquareGUI) event.getSource(); //TODO integrate highlighting.
        if (clickedSquare.getPiece().getImage() != null) {
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
     * Notifies the view that the mouse has moved.
     *
     * @param event The mouse event
     */
    public List<Position> notifyPieceMoving(Event event){
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
    public int getWidth() {
        return this.size;
    }

    /**
     * returns the height of the board
     * @return the height of the board
     */
    @Override
    public int getHeight() {
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

    //All memento stuff
    @Override
    public void addMove(GameColor color, Files fromF, Rank fromR, Files toF, Rank toR) {

    }

    @Override
    public BoardMementoIF createMemento() {
        return null;
    }

    @Override
    public String getState() {
        return null;
    }

    @Override
    public void loadFromMemento(BoardMementoIF boardMemento) {

    }

    /**
     * Adds an observer to the center pane.
     *
     * @param observer the observer to be added
     */
    public void addObserver(CenterPaneObserver observer){
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
     * Notifies the observer that a piece has been captured.
     *
     * @param piece the piece that has been captured
     */
    public void notifyAddCapturedPiece(PieceIF piece){
        observer.notifyAddCapturedPiece(piece);
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
    private PlayerIF getOtherPlayer(PlayerIF player){
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
    private boolean gameStateCheck(){
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
        System.out.println("Captured piece is " + square.getPiece().getType() + " " + square.getPiece().getColor());
        notifyAddCapturedPiece(square.getPiece());
    }

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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alert");
        alert.setHeaderText("Board State Error");
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(getClass().getResource(
                "gameBoard.css").toExternalForm());
    }

    /**
     * notifies bottom pane to update
     */
    public void notifyBottomPane(String currPlayer){
        this.observer.notifyBottomPane(currPlayer);
    }
}
