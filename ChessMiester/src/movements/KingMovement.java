package movements;

import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.*;
import model.BlackAndWhite;
import model.Position;
import uicli.PlayMoveCLI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to define the movements of a king in a game of chess. This class does so
 * by finding the valid moves for a king on a chess board in all diagonal directions.
 *
 * @author Colton Brooks (90%), Zach Eanes (10%)
 * @version 2.0
 */
public class KingMovement extends BlackAndWhite implements MovementIF, FirstMoveIF {

    /* Boolean to check if the king has moved; needed for castling implementation */
    private boolean isFirstMove;

    /** Access to PlayMoveCLI */
    private PlayMoveCLI playMoveCLI;

    /**
     * Constructor method for the KingMovement Class
     *
     * @param color the color of the piece
     */
    public KingMovement(GameColor color) {
        super(color);
        this.isFirstMove = true;
    }

    /**
     * Gets the valid moves for the piece.
     *
     * @param board           the board the piece is on.
     * @param currentPosition the current position of the piece.
     * @return the valid moves for the piece.
     */
    @Override
    public List<Position> getValidMoves(BoardIF board, Position currentPosition) {
        // Set of valid moves to be found for a king
        List<Position> validMoves = new ArrayList<>();


        // Check all 8 possible moves for a king
        validMoves.add(moveCheck(board, currentPosition, 1, 0, true));
        validMoves.add(moveCheck(board, currentPosition, 1, 1, true));
        validMoves.add(moveCheck(board, currentPosition, 0, 1, true));
        validMoves.add(moveCheck(board, currentPosition, -1, 1, true));
        validMoves.add(moveCheck(board, currentPosition, -1, 0, true));
        validMoves.add(moveCheck(board, currentPosition, -1, -1, true));
        validMoves.add(moveCheck(board, currentPosition, 0, -1, true));
        validMoves.add(moveCheck(board, currentPosition, 1, -1, true));


        // TODO pass all fields for castling into canCastle method
        validMoves.add(canCastle());

        validMoves.removeAll(Collections.singleton(null)); // remove all null values
        return validMoves;
    }

    /**
     * Method to be called whenever king makes its first move, sets isFirstMove false
     * to show a move with this piece has been made.
     *
     * @param isFirstMove boolean to set the first move of the king
     */
    public void setFirstMove(boolean isFirstMove){ this.isFirstMove = isFirstMove; }

    /**
     * Method to be called to check if the king has moved
     *
     * @return true if the king has not moved, false if it has
     */
    public boolean getFirstMove(){ return this.isFirstMove; }

    /**
     * Method to check if castling is possible
     *
     * @param fromF         the file the king is moving from
     * @param fromR         the rank the king is moving from
     * @param toF           the file the king is moving to
     * @param toR           the rank the king is moving to
     * @param squares       the board the king is on
     * @param currentPlayer the current player
     * @return true if castling is possible, false if not
     */
    public boolean canCastle(Files fromF, Rank fromR, Files toF, Rank toR,
                             SquareIF[][] squares, PlayerIF currentPlayer) {

        // grab the king and rook piece from positions to save keystrokes
        KingMovement king =
                (KingMovement) squares[fromR.getIndex()]
                        [fromF.getFileNum()].getPiece().getMoveType();
        RookMovement rook =
                (RookMovement) squares[toR.getIndex()]
                        [toF.getFileNum()].getPiece().getMoveType();

        //boolean to return at the end
        //boolean flag = true;
        boolean canCastle = true;

        // if either rook or king have moved, castling cannot occur
        if(!king.getFirstMove() || !rook.getFirstMove()) {
            canCastle = false;
            //flag = false;
        }

        // if king is in check or moves into check, castling cannot occur
        if (playMoveCLI.checkCondition(currentPlayer, new Position(fromR, fromF)) ||
                playMoveCLI.checkCondition(currentPlayer, new Position(toR, toF))) {
            canCastle = false;
            //flag = false;
        }

        // if king passes through check or a piece is there, castling cannot occur

        // check if king is moving to the right
        Files tempF = fromF;
        int cnt = 0;
        if (tempF.getFileNum() < toF.getFileNum() && canCastle) {
            tempF = Files.values()[tempF.getFileNum() + 1];
            while (cnt < 2) {
                //check if king is ever put into check
                if (!playMoveCLI.checkCondition(currentPlayer, new Position(fromR, tempF))) {
                    canCastle = false;
                    //flag = false;
                }
                //check if there is a piece in the way
                if (squares[fromR.getIndex()]
                        [tempF.getFileNum()].getPiece() != null) {
                    canCastle = false;
                    //flag = false;
                }
                tempF = Files.values()[tempF.getFileNum() + 1];
                cnt++;
            }
        }
        // check if king is moving to the left
        else {
            while (cnt < 2) {
                tempF = Files.values()[tempF.getFileNum() - 1];
                //check if king is ever put into check
                if (!playMoveCLI.checkCondition(currentPlayer, new Position(fromR, tempF))) {
                    canCastle = false;
                }
                //check if there is a piece in the way
                if (squares[fromR.getIndex()]
                        [tempF.getFileNum()].getPiece() != null) {
                    canCastle = false;
                }
                tempF = Files.values()[tempF.getFileNum() - 1];
                cnt++;
            }
        }
        return canCastle; //true if castling is possible, false if not
    }

}