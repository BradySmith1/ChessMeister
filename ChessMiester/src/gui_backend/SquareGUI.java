/**
 * This class is responsible for creating the squares of the chess board. This
 * is then in turn used to create the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui_backend;

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
        this.setOnMouseClicked(event-> {
            MouseButton temp = event.getButton();
            if(temp == MouseButton.PRIMARY){
                notifyLeftClick(event);
            }
            if(temp == MouseButton.SECONDARY){
                notifyRightClick(event);
            }
        });
        this.setOnDragDetected(event -> {
            ImageView imageView = (ImageView) this.getChildren().get(0);
            Dragboard db = this.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putImage(imageView.getImage());
            db.setContent(content);
            event.consume();
        });
        this.setOnDragOver(event -> { //TODO integrate with valid moves.
            if(event.getGestureSource() != this){
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });
        this.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasImage()){
                SquareGUI gui = (SquareGUI) event.getGestureSource();
                this.piece.setPieceImage(gui.getPiece().getImage());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
        this.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE){
                this.piece.setPieceImage(null);
            }
            event.consume();
        });
        /*this.setOnDragEntered(event -> {
            Dragboard db = event.getDragboard();
            this.oldImage = this.imageView.getImage();
            if (event.getGestureSource() != this ) {
                this.imageView.setImage(db.getImage());
            }
            event.consume();
        });*/
        /*this.setOnDragExited(event -> {
            this.imageView.setImage(this.oldImage);
            event.consume();
        });*/
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
        return piece;
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

    public void setColor(Color color) {
        long alphaRGB = Long.parseLong(color.toString().substring(2), 16);
        this.setStyle("-fx-background-color: " + String.format("#%08X", alphaRGB) + ";");
    }
}
