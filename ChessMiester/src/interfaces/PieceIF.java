package interfaces;

import enums.ChessPieceType;
import enums.GameColor;
import javafx.scene.image.Image;
import model.Position;
import model.Square;

import java.util.List;

/**
 * This interface represents a chess piece.
 *
 * @author Brady Smith (75%), Zach Eanes (25%)
 * @version 1.0
 */
public interface PieceIF extends BlackAndWhiteIF {

    /**
     * Gets the type of the piece.
     *
     * @return the type of the piece.
     */
    ChessPieceType getType();

    /**
     * method to get the movement type for the piece
     *
     * @return a class that implements MovementIF that represents the moves this piece can do
     */
    MovementIF getMoveType();

    /**
     * Gets the image of the piece.
     * @return the image of the piece.
     */
    Image getImage();

    /**
     * Sets the image of the piece.
     * @param image the image of the piece.
     */
    void setPieceImage(Image image);

    /**
     * Gets the color of the piece.
     * @return the color of the piece.
     */
    GameColor getColor();

    /**
     * Returns a list of MovePositions that are valid & legal on the board.
     *
     * @param board           The game board that the piece moves on.
     * @param currentPosition The current position to get the valid moves from.
     * @return A list of valid MovePositions.
     */
    List<Position> getValidMoves(BoardIF board, Position currentPosition);

    /**
     * Gets the position of the piece.
     *
     * @param board the board the piece is on.
     * @return the position of the piece.
     */
    default Position getPosition(BoardIF board){
        Position position = null;
        SquareIF[][] squares = board.getSquares();
        PieceIF temp;
        for(int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                temp = squares[i][j].getPiece();
                if (temp == this) {
                    Square square = (Square) squares[i][j];
                    position = square.getPosition();
                }
            }
        }
        return position;
    }
}
