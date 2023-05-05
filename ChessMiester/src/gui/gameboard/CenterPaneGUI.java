package gui.gameboard;

import enums.GameColor;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CenterPaneGUI implements GameBoardObserver, EventHandler<MouseEvent> {

    private GridPane root;

    private SquareGUI[][] squares;

    private SquareGUI clicked;

    private Stage popup;

    final int size = 8;

    public CenterPaneGUI(){
        root = new GridPane();
        clicked = null;
        popup = null;
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
                square.addObserver(this);
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
            SquareGUI newClicked = (SquareGUI) mouse.getSource();
            if(clicked.getImageView().getImage() != null){
                newClicked.getImageView().setImage(clicked.getImageView().getImage());
                clicked.getImageView().setImage(null);
            }
            popup.close();
            clicked = null;
            popup = null;
            root.removeEventFilter(MouseEvent.ANY, this);
        }
    }

    public Pane getRoot(){
        return root;
    }

    @Override
    public void notifyLeftClick(Event event) {
        clickMove((MouseEvent) event);
    }

    @Override
    public void notifyRightClick(Event event) {
        //highlight(); TODO
    }

    @Override
    public void handle(MouseEvent event) {
        popup.setX(event.getScreenX() + 10); //700
        popup.setY(event.getScreenY() + 10); //150
    }
}
