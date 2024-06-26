package gui_backend;

import interfaces.BoardIF;
import interfaces.PieceIF;
import interfaces.PlayerIF;
import model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the GUI implementation for the state validation
 * for the chess game.
 * @author Kaushal Patel 100%
 */
public class StateValidation {

    /**
     * Checks to see if the king is in check.
     * @param otherPlayer the other player
     * @param position the position of the king
     * @param board the board
     * @return true if the king is in check, false otherwise
     */
    public static boolean checkCondition(PlayerIF otherPlayer, Position position, BoardIF board) {
        boolean isCheck = false;

        // Get the list of valid moves for all the enemy pieces on the board.
        //for (PieceIF piece : otherPlayer.getPieces()) {
        for (int i = 0; i < otherPlayer.getPieces().size(); i++) {
            // Cast the piece to a Piece object.
            PieceGUI p = (PieceGUI) otherPlayer.getPieces().get(i);
            // Get the list of valid moves for the piece.
            List<Position> validMoves = p.getValidMoves(board, otherPlayer.getPieces().get(i).getPosition(board));

            // Check to see if the king's position is in the list of valid moves.
            if (validMoves.contains(position)) {
                isCheck = true;
            }
        }
        return isCheck;
    }


    /**
     * Checks to see if the king is in checkmate.
     * @param player the player
     * @param playerOther the other player
     * @param board the board
     * @return true if the king is in checkmate, false otherwise
     */
    public static boolean checkMateCondition(PlayerIF player, PlayerIF playerOther, BoardIF board) {
        // Check to see if the king is in check.
        boolean checkmate = false;
        boolean canMoveOutOfCheck = false;
        boolean canBlockCheck = false;
        boolean inCheck = checkCondition(playerOther, player.getKing().getPosition(board), board);

        if (inCheck) {
            canMoveOutOfCheck = canMoveOutOfCheck(player, playerOther, board);

            ArrayList<PieceIF> pieces = player.getPieces();
            // Check to see if any of the pieces can block the checkmate.
            for (int i = 0; i < pieces.size(); i++) {

                //for (PieceIF piece : player.getPieces()) {
                // Get the list of valid moves for the piece.
                List<Position> validMoves = pieces.get(i).getValidMoves(board,
                        pieces.get(i).getPosition(board));

                for (Position position : validMoves) {
                    if (!checkCondition(player, position, board)) {
                        canBlockCheck = true;
                    }
                }
            }
            checkmate = !canMoveOutOfCheck && !canBlockCheck;
        }
            return checkmate;
    }

    /**
     * Checks to see if the game is in stalemate.
     * @param currentPlayer the current player
     * @param otherPlayer the other player
     * @param board the board
     * @return true if the game is in stalemate, false otherwise
     */
    public static boolean stalemateCondition(PlayerIF currentPlayer, PlayerIF otherPlayer, BoardIF board) {
        // A draw should be declared if the king is not in check and there are no valid moves for the player
        boolean inCheck = checkCondition(otherPlayer, currentPlayer.getKing().getPosition(board), board);
        boolean stalemate = true;

        // If the king is not in check, then check to see if there are any valid moves for the player.
        if (!inCheck) {
            for (PieceIF piece : currentPlayer.getPieces()) {
                // Cast the piece to a Piece object.
                PieceGUI p = (PieceGUI) piece;

                // Get the list of valid moves for the piece.
                List<Position> validMoves = p.getValidMoves(board, piece.getPosition(board));

                if (validMoves.size() > 0) {
                    stalemate = false;
                }
            }
        }
        return !inCheck && stalemate;
    }

    /**
     * Checks to see if the king can move out of check.
     * @param player the player
     * @param playerOther the other player
     * @param board the board
     * @return true if the king can move out of check, false otherwise
     */
    public static boolean canMoveOutOfCheck(PlayerIF player, PlayerIF playerOther, BoardIF board) {
        // Check to see if the king can move to a position where it is not in check.
        boolean canMoveOutOfCheck = false;

        // Get the king of the player.
        PieceGUI king = (PieceGUI) player.getKing();

        // Get the list of valid moves for the king.
        List<Position> kingValidMoves = king.getValidMoves(board, king.getPosition(board));

        // For each position in the list of valid moves, check to see if the king is in check.
        for (Position position : kingValidMoves) {
            if (!checkCondition(playerOther, position, board)) {
                canMoveOutOfCheck = true;
            }
        }

        return canMoveOutOfCheck;
    }
}
