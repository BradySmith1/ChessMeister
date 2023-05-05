package gui.gameboard;

import enums.Files;
import enums.Rank;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import model.Position;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sound.sampled.Clip;

public class SquareGUI extends StackPane{

    private final Position position;

    private ImageView imageView;

    private Image oldImage;

    public SquareGUI(int row, int col){
        super();
        position = new Position(Rank.getRankFromIndex(row), Files.getFileFromFileNum(col));
        oldImage = null;
        imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        this.getChildren().add(imageView);
        String color;
        color = findColor(row, col);
        this.setStyle("-fx-background-color: " + color + ";");
        this.setPrefHeight(50);
        this.setPrefWidth(50);
        this.setOnDragDetected(event -> {
            ImageView imageView = (ImageView) this.getChildren().get(0);
            Dragboard db = this.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putImage(imageView.getImage());
            db.setContent(content);
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
        this.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
            event.consume();
        });
        this.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasImage()){
                this.imageView.setImage(db.getImage());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
        this.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE){
                this.imageView.setImage(null);
            }
            event.consume();
        });
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

    public ImageView getImageView(){
        return imageView;
    }
}
