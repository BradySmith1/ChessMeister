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

public class CenterPaneGUI {

    private GridPane root;

    private SquareGUI[][] squares;

    final int size = 8;

    public CenterPaneGUI(){
        root = new GridPane();

        squares = new SquareGUI[size][size];
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
                SquareGUI square = new SquareGUI(row, col);
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
            ImageView view = squares[1][i].getImageView();
            view.setImage(blackPawnImage);
            view.setId("pawn" + i);
        }
        for(int i = 0; i < size; i++){
            ImageView view = squares[6][i].getImageView();
            view.setImage(whitePawnImage);
            view.setId("pawn" + i);
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
        if(color == GameColor.BLACK){
            ImageView view = squares[offset][0].getImageView();
            view.setImage(whiteRookImage);
            view = squares[offset][size-2].getImageView();
            view.setImage(whiteKnightLeftImage);
            view = squares[offset][1].getImageView();
            view.setImage(whiteKnightRightImage);
            view = squares[offset][size-3].getImageView();
            view.setImage(whiteBishopImage);
            view = squares[offset][2].getImageView();
            view.setImage(whiteBishopImage);
            view = squares[offset][size-1].getImageView();
            view.setImage(whiteRookImage);
            view = squares[offset][size-4].getImageView();
            view.setImage(whiteQueenImage);
            view = squares[offset][3].getImageView();
            view.setImage(whiteKingImage);
        }else{
            ImageView view = squares[offset][0].getImageView();
            view.setImage(blackRookImage);
            view = squares[offset][size-2].getImageView();
            view.setImage(blackKnightLeftImage);
            view = squares[offset][1].getImageView();
            view.setImage(blackKnightRightImage);
            view = squares[offset][size-3].getImageView();
            view.setImage(blackBishopImage);
            view = squares[offset][2].getImageView();
            view.setImage(blackBishopImage);
            view = squares[offset][size-1].getImageView();
            view.setImage(blackRookImage);
            view = squares[offset][size-4].getImageView();
            view.setImage(blackQueenImage);
            view = squares[offset][3].getImageView();
            view.setImage(blackKingImage);
        }
    }

    public Pane getRoot(){
        return root;
    }
}
