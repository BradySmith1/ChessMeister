package gui.gameboard;

import enums.ChessPieceType;
import enums.GameColor;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import model.Piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CenterPane {

    GridPane root;

    StackPane[][] squares;

    final int size = 8;

    public CenterPane(){
        root = new GridPane();

        squares = new StackPane[size][size];
        initSquares();
        setConstraints();
        try{
            populateSquares();
        }catch(FileNotFoundException fnfe){
            System.out.println("Error: File not found.");
        }

    }

    private void initSquares(){
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                StackPane square = new StackPane();
                String color;
                color = findColor(row, col);
                square.setStyle("-fx-background-color: " + color + ";");
                square.setPrefHeight(50);
                square.setPrefWidth(50);
                root.add(square, col, row, 1, 1);
                squares[row][col] = square;
            }
        }
    }

    private void setConstraints() {
        for(int i = 0; i < size; i++){
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    private void populateSquares() throws FileNotFoundException {
        Image whitePawnImage = new Image(new FileInputStream("src/gui/gameboard/images/WhitePawn.png"));
        Image blackPawnImage = new Image(new FileInputStream("src/gui/gameboard/images/BlackPawn.png"));
        for(int i = 0; i < size; i++) {
            ImageView view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setId("pawn" + i);
            view.setImage(whitePawnImage);
            squares[1][i].getChildren().add(view);
        }
        for(int i = 0; i < size; i++){
            ImageView view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setId("blackPawn" + i);
            view.setImage(blackPawnImage);
            squares[6][i].getChildren().add(view);
        }
        setPieces(GameColor.WHITE, 0);
        setPieces(GameColor.BLACK, size-1);
    }

    private void setPieces(GameColor color, int offset) throws FileNotFoundException {
        Image whiteRookImage = new Image(new FileInputStream("src/gui/gameboard/images/WhiteRook.png"));
        Image whiteKnightLeftImage = new Image(new FileInputStream("src/gui/gameboard/images/WhiteKnightLeft.png"));
        Image whiteBishopImage = new Image(new FileInputStream("src/gui/gameboard/images/WhiteBishop.png"));
        Image whiteQueenImage = new Image(new FileInputStream("src/gui/gameboard/images/WhiteQueen.png"));
        Image whiteKingImage = new Image(new FileInputStream("src/gui/gameboard/images/WhiteKing.png"));
        Image whiteKnightRightImage = new Image(new FileInputStream("src/gui/gameboard/images/WhiteKnightRight.png"));
        Image blackRookImage = new Image(new FileInputStream("src/gui/gameboard/images/BlackRook.png"));
        Image blackKnightLeftImage = new Image(new FileInputStream("src/gui/gameboard/images/BlackKnightLeft.png"));
        Image blackBishopImage = new Image(new FileInputStream("src/gui/gameboard/images/BlackBishop.png"));
        Image blackQueenImage = new Image(new FileInputStream("src/gui/gameboard/images/BlackQueen.png"));
        Image blackKingImage = new Image(new FileInputStream("src/gui/gameboard/images/BlackKing.png"));
        Image blackKnightRightImage = new Image(new FileInputStream("src/gui/gameboard/images/BlackKnightRight.png"));
        ImageView view = new ImageView();
        view.setFitHeight(50);
        view.setFitWidth(50);
        if(color == GameColor.WHITE){
            view.setImage(whiteRookImage);
            squares[offset][0].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(whiteRookImage);
            squares[offset][size-1].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(whiteKnightLeftImage);
            squares[offset][1].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(whiteKnightRightImage);
            squares[offset][size-2].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(whiteBishopImage);
            squares[offset][2].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(whiteRookImage);
            squares[offset][size-3].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(whiteQueenImage);
            squares[offset][3].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(whiteKingImage);
        }else{
            view.setImage(blackRookImage);
            squares[offset][0].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(blackRookImage);
            squares[offset][size-1].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(blackKnightLeftImage);
            squares[offset][1].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(blackKnightRightImage);
            squares[offset][size-2].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(blackBishopImage);
            squares[offset][2].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(blackRookImage);
            squares[offset][size-3].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(blackQueenImage);
            squares[offset][3].getChildren().add(view);
            view = new ImageView();
            view.setFitHeight(50);
            view.setFitWidth(50);
            view.setImage(blackKingImage);
        }
        squares[offset][4].getChildren().add(view);
    }

    private String findColor(int row, int col){
        String color = "black";
        if((row + col) % 2 == 0){
            color = "white";
        }
        return color;
    }

    public Pane getRoot(){
        return root;
    }
}
