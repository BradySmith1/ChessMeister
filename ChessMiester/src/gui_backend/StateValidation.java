package gui_backend;

import interfaces.BoardIF;
import interfaces.PieceIF;
import interfaces.PlayerIF;
import model.Board;
import model.Piece;
import model.Position;

import java.util.ArrayList;
import java.util.List;

public class StateValidation {

    public static boolean checkCondition(PlayerIF otherPlayer, Position position, BoardIF board) {
        boolean isCheck = false;

        // Get the list of valid moves for all the enemy pieces on the board.
        for (PieceIF piece : otherPlayer.getPieces()) {
            // Cast the piece to a Piece object.
            Piece p = (Piece) piece;
            // Get the list of valid moves for the piece.
            List<Position> validMoves = p.getValidMoves(board, piece.getPosition(board));

            // Check to see if the king's position is in the list of valid moves.
            if (validMoves.contains(position)) {
                isCheck = true;
            }
        }
        return isCheck;
    }

    public static boolean checkMateCondition(PlayerIF player, PlayerIF playerOther, BoardIF board) {
        // Check to see if the king is in check.
        boolean checkmate = false;
        boolean canMoveOutOfCheck = false;
        boolean canBlockCheck = false;
        boolean inCheck = checkCondition(playerOther, player.getKing().getPosition(board), board);

        if (inCheck) {
            // Check to see if the king can move to a position where it is not in check.
            // Get the king of the player.
            PieceIF king = player.getKing();

            // Get the list of valid moves for the king.
            List<Position> kingValidMoves = king.getValidMoves(board, king.getPosition(board));

            // For each position in the list of valid moves, check to see if the king is in check.
            for (Position position : kingValidMoves) {
                // Emulate the move of the king to each position in the list of valid moves.
                /*this.move(player, king.getPosition(board).getFile(),
                        king.getPosition(board).getRank(), position.getFile(), position.getRank());

                // Check to see if there is a check.
                if (!checkCondition(player, king.getPosition(board), board)) {
                    canMoveOutOfCheck = true;
                }
                undo(); // TODO
                king = player.getKing();*/ //TODO instead of refering to kings actual position, refer to simulated position.

                //undoMoveFromCheck();
            }

            ArrayList<PieceIF> pieces = player.getPieces();
            // Check to see if any of the pieces can block the checkmate.
            for (int i = 0; i < pieces.size(); i++) {

                //for (PieceIF piece : player.getPieces()) {
                // Get the list of valid moves for the piece.
                List<Position> validMoves = pieces.get(i).getValidMoves(board,
                        pieces.get(i).getPosition(board));

                for (Position position : validMoves) {
                    /*// Emulate the move of the piece to each position in the list of valid moves.
                    // Check to see if there is a check.
                    this.move(player,pieces.get(i).getPosition(board).getFile(),
                            pieces.get(i).getPosition(board).getRank(), position.getFile(),
                            position.getRank());

                    if (checkCondition(player, king.getPosition(board))) {
                        canBlockCheck = true; //TODO instead of refering to kings actual position, refer to simulated position.
                    }
                    undo();
                    king = player.getKing();*/
                }
                }
                checkmate = canMoveOutOfCheck && canBlockCheck;
            }
            return checkmate;
        }
    }
