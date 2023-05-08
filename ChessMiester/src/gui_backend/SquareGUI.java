package gui_backend;

import enums.ChessPieceType;
import enums.Files;
import enums.Rank;
import gui.gameboard.GameBoardObserver;
import interfaces.PieceIF;
import interfaces.SquareIF;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.Position;
import javafx.scene.image.ImageView;

import java.io.PrintWriter;
import java.util.List;

/**
 * This class is responsible for creating the squares of the chess board. This
 * is then in turn used to create the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
public class SquareGUI extends StackPane implements GameBoardObserver, SquareIF {

    /** The position of the square. */
    private final Position position;

    /** The piece on the square. */
    private PieceIF piece;

    /** The observer of the square. */
    GameBoardObserver observer;

    /**
     * Constructor for the square.
     *
     * @param row the row of the square
     * @param col the column of the square
     */
    public SquareGUI(int row, int col){
        super();
        position = new Position(Rank.getRankFromIndex(row), Files.getFileFromFileNum(col));
        piece = new PieceGUI();
        this.getChildren().add((Node) piece);
        String color;
        color = findColor(row, col);
        this.setStyle("-fx-background-color: " + color + ";");
        this.setPrefHeight(50);
        this.setPrefWidth(50);
        //Listener for mouse click.
        this.setOnMouseClicked(event-> {
            MouseButton temp = event.getButton();
            if(temp == MouseButton.PRIMARY){
                notifyLeftClick(event);
            }
            if(temp == MouseButton.SECONDARY){
                notifyRightClick(event);
            }
        });
        //Start of the listener for when a piece is being dragged.
        this.setOnDragDetected(event -> {
            ImageView imageView = (ImageView) this.getChildren().get(0);
            if(imageView.getImage() == null){
                return;
            }
            Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
            this.notifyLeftClick(event);
            ClipboardContent content = new ClipboardContent();
            content.putImage(imageView.getImage());
            db.setContent(content);
            event.consume();
        });
        this.setOnDragOver(event -> { //TODO integrate with valid moves.
            List<Position> validMoves = notifyPieceMoving(event);
            boolean valid = false;
            for (Position validMove : validMoves) {
                if (validMove == null) {
                    continue;
                }
                if (validMove.equals(this.position)) {
                    valid = true;
                    break;
                }
            }
            if(!valid){
                SquareGUI source = (SquareGUI) event.getGestureSource();
                SquareGUI target = (SquareGUI) event.getSource();
                if(source.getPiece().getType() == ChessPieceType.King && target.getPiece().getType() == ChessPieceType.Rook){
                    if(source.getPiece().getColor() == target.getPiece().getColor()){
                        valid = true;
                    }
                }
            }
            if(event.getGestureSource() != this && valid){
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });
        this.setOnDragDropped(event -> {
            this.notifyLeftClick(event);
            event.setDropCompleted(true);
            event.consume();
        });
        this.setOnDragDone(event -> {
            event.consume();
        });
    }

    /**
     * Finds the color of the square.
     *
     * @param row the row of the square
     * @param col the column of the square
     * @return the color of the square
     */
    public String findColor(int row, int col){
        String color = "black";
        if((row + col) % 2 == 0){
            color = "white";
        }
        return color;
    }

    /**
     * Getter method for the position of the square.
     *
     * @return the position of the square
     */
    public Position getPosition(){
        return position;
    }


    @Override
    public void clear() {
        this.piece = null;
    }

    /**
     * Setter method for the piece on the square.
     *
     * @param piece the piece to be set on the square
     */
    @Override
    public void setPiece(PieceIF piece) {
        this.piece = piece;
    }

    /**
     * Getter method for the piece on the square.
     *
     * @return the piece on the square
     */
    public PieceIF getPiece(){
        return (PieceIF) this.getChildren().get(0);
    }

    /**
     * Adds an observer to the square.
     *
     * @param observer the observer to be added
     */
    public void addObserver(GameBoardObserver observer){
        this.observer = observer;
    }

    /**
     * Notifies the observer that a left click has occurred.
     *
     * @param event the event that occurred
     */
    @Override
    public void notifyLeftClick(Event event) {
        observer.notifyLeftClick(event);
    }

    /**
     * Notifies the observer that a right click has occurred.
     *
     * @param event the event that occurred
     */
    @Override
    public void notifyRightClick(Event event) {
        observer.notifyRightClick(event);
    }

    /**
     * Notifies the observer that a piece has been captured.
     *
     * @param piece the piece that was captured
     */
    public void notifyAddCapturedPiece(PieceIF piece){
        observer.notifyAddCapturedPiece(piece);
    }

    /**
     * Notifies the observer that a drag has occurred.
     *
     * @param event the event that occurred
     */
    @Override
    public List<Position> notifyPieceMoving(Event event) {
        return observer.notifyPieceMoving(event);
    }

    /**
     * Notifies the observer that a piece has been dropped.
     *
     * @param event the event that occurred
     */
    public void notifyBoardLoader(Event event){
        observer.notifyBoardLoader(event);
    }

    /**
     * Notifies the observer that the undo button has been clicked.
     */
    @Override
    public void notifyUndo() {
        //Method not used.
    }

    /**
     * Notifies the observer that the redo button has been clicked.
     */
    @Override
    public void notifyRedo() {
        //Method not used.
    }

    /**
     * Notifies the observer that the save button has been clicked.
     *
     * @param writer the writer to be used to save the game
     */
    @Override
    public void notifySaveGame(PrintWriter writer) {
        //Method not used.
    }

    /**
     * The set color method for the square.
     * @param color the color to be set
     */
    public void setColor(Color color) {
        long alphaRGB = Long.parseLong(color.toString().substring(2), 16);
        this.setStyle("-fx-background-color: " + String.format("#%08X", alphaRGB) + ";");
    }
}
