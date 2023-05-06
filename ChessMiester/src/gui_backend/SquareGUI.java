package gui_backend;

import enums.Files;
import enums.Rank;
import gui.gameboard.GameBoardObserver;
import javafx.event.Event;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.Position;
import javafx.scene.image.ImageView;

public class SquareGUI extends StackPane implements GameBoardObserver {

    private final Position position;

    private PieceGUI piece;

    GameBoardObserver observer;

    public SquareGUI(int row, int col){
        super();
        position = new Position(Rank.getRankFromIndex(row), Files.getFileFromFileNum(col));
        piece = new PieceGUI();
        this.getChildren().add(piece);
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
                this.piece.setImage(db.getImage());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
        this.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE){
                this.piece.setImage(null);
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

    public String findColor(int row, int col){
        String color = "black";
        if((row + col) % 2 == 0){
            color = "white";
        }
        return color;
    }

    public Position getPosition(){
        return position;
    }

    public PieceGUI getPiece(){
        return piece;
    }

    public void setPiece(PieceGUI piece){
        this.piece = piece;
    }

    public void addObserver(GameBoardObserver observer){
        this.observer = observer;
    }

    @Override
    public void notifyLeftClick(Event event) {
        observer.notifyLeftClick(event);
    }

    @Override
    public void notifyRightClick(Event event) {
        observer.notifyRightClick(event);
    }

    public void setColor(Color color) {
        long alphaRGB = Long.parseLong(color.toString().substring(2), 16);
        this.setStyle("-fx-background-color: " + String.format("#%08X", alphaRGB) + ";");
    }

    public void highlight() {
        this.setColor(Color.rgb(0, 255, 0, 0.5));
    }
}
