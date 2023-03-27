package interfaces;

import enums.GameColor;
import model.Piece;

import java.util.ArrayList;

public interface PlayerIF {
    public GameColor getPieceColor();

    public ArrayList<PieceIF> getPieces();

    public ArrayList<PieceIF> getCapturedPieces();

    public void addCapturedPiece(PieceIF piece);

    public void addPiece(PieceIF piece);

}
