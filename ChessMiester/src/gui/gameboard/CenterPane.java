/**
 * This creates the center pane for the chess board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import enums.GameColor;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CenterPane {
    /** The root pane for the center pane. */
    GridPane root;

    /** The squares for the chess board. */
    StackPane[][] squares;

    /** The size of the chess board. */
    final int size = 8;

    /**
     * Constructor for the center pane.
     */
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

    /**
     * This method initializes the squares for the chess board.
     */
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

    /**
     * This method sets the constraints for the panes in the chess board.
     */
    private void setConstraints() {
        for(int i = 0; i < size; i++){
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    /**
     * This method populates the squares with proper pieces to start the game.
     *
     * @throws FileNotFoundException if the file is not found.
     */
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
            ImageView view = createView();
            view.setId("blackPawn" + i);
            view.setImage(blackPawnImage);
            squares[6][i].getChildren().add(view);
        }
        setPieces(GameColor.WHITE, 0);
        setPieces(GameColor.BLACK, size-1);
    }

    /**
     * This method sets all pieces for the chess board.
     *
     * @param color the color of the pieces.
     * @param offset the offset for the pieces.
     * @throws FileNotFoundException
     */
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

    /**
     * This method creates a view for the chess board.
     *
     * @return the view for the chess board.
     */
    private ImageView createView(){
        ImageView view = new ImageView();
        view.setFitWidth(50);
        view.setFitHeight(50);
        return view;
    }

    private String findColor(int row, int col){
        String color = "black";
        if((row + col) % 2 == 0){
            color = "white";
        }
        return color;
    }

    /**
     * This method returns the root of the chess board.
     *
     * @return the root of the chess board.
     */
    public Pane getRoot(){ return root; }
}
