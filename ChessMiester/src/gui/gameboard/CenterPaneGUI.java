/**
 * This class is responsible for creating the center pane of the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import enums.GameColor;
import gui_backend.PieceGUI;
import gui_backend.SquareGUI;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class CenterPaneGUI implements GameBoardObserver, EventHandler<MouseEvent> {
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

    /**
     * Constructor for the center pane.
     */
    public CenterPaneGUI(){
        root = new GridPane();
        clicked = null;
        popup = null;
        squares = new SquareGUI[size][size];
        initSquares();
        setConstraints();
        try{
            populateSquares();
        }catch(FileNotFoundException | MalformedURLException fnfe){
            System.out.println("Error: File not found.");
        }

    }

    /**
     * Initializes the squares for the board
     */
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
            PieceGUI view = squares[1][i].getPiece();
            view.setPieceImage(blackPawnImage);
            view.setId("pawn" + i);
        }
        for(int i = 0; i < size; i++){
            PieceGUI view = squares[6][i].getPiece();
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
        url = new File("src/gui/gameboard/images/WhiteQueen.png").toURI()
                .toURL().toExternalForm();
        Image whiteQueenImage = new Image(url);
        url = new File("src/gui/gameboard/images/WhiteKing.png").toURI()
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
        url = new File("src/gui/gameboard/images/BlackQueen.png").toURI()
                .toURL().toExternalForm();
        Image blackQueenImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackKing.png").toURI()
                .toURL().toExternalForm();
        Image blackKingImage = new Image(url);
        url = new File("src/gui/gameboard/images/BlackKnightRight.png").toURI()
                .toURL().toExternalForm();
        Image blackKnightRightImage = new Image(url);
        if(color == GameColor.BLACK){
            PieceGUI view = squares[offset][0].getPiece();
            view.setPieceImage(whiteRookImage);
            view = squares[offset][size-2].getPiece();
            view.setPieceImage(whiteKnightLeftImage);
            view = squares[offset][1].getPiece();
            view.setPieceImage(whiteKnightRightImage);
            view = squares[offset][size-3].getPiece();
            view.setPieceImage(whiteBishopImage);
            view = squares[offset][2].getPiece();
            view.setPieceImage(whiteBishopImage);
            view = squares[offset][size-1].getPiece();
            view.setPieceImage(whiteRookImage);
            view = squares[offset][size-4].getPiece();
            view.setPieceImage(whiteQueenImage);
            view = squares[offset][3].getPiece();
            view.setPieceImage(whiteKingImage);
        }else{
            PieceGUI view = squares[offset][0].getPiece();
            view.setPieceImage(blackRookImage);
            view = squares[offset][size-2].getPiece();
            view.setPieceImage(blackKnightLeftImage);
            view = squares[offset][1].getPiece();
            view.setPieceImage(blackKnightRightImage);
            view = squares[offset][size-3].getPiece();
            view.setPieceImage(blackBishopImage);
            view = squares[offset][2].getPiece();
            view.setPieceImage(blackBishopImage);
            view = squares[offset][size-1].getPiece();
            view.setPieceImage(blackRookImage);
            view = squares[offset][size-4].getPiece();
            view.setPieceImage(blackQueenImage);
            view = squares[offset][3].getPiece();
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
            SquareGUI newClicked = (SquareGUI) mouse.getSource(); //TODO need to integrate valid moves into here.
            if(clicked.getPiece().getPieceImage() != null && clicked != newClicked){
                newClicked.getPiece().setPieceImage(clicked.getPiece().getPieceImage());
                clicked.getPiece().setPieceImage(null);
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
        clickMove((MouseEvent) event);
    }

    /**
     * Notifies the view that the right mouse button has been clicked.
     *
     * @param event The mouse event
     */
    @Override
    public void notifyRightClick(Event event) {
        //highlight(); TODO integrate highlighting.
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
}
