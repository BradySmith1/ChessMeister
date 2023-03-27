package movements;

import enums.GameColor;
import interfaces.BoardIF;
import interfaces.MovementIF;
import model.Position;

import java.util.List;

public class PawnMovement implements MovementIF {

    private GameColor color; // The color of the piece

    /**
     * Constructor method for the PawnMovement Class
     *
     * @param color           the color of the piece
     */
    public PawnMovement(GameColor color) {

        this.color = color;
    }

    /**
     * Gets the valid moves for the piece.
     *
     * @param board
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        return null;
    }
}