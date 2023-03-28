package player;

import interfaces.PlayerIF;
import enums.GameColor;
import interfaces.PieceIF;
import model.Piece;

import java.util.ArrayList;

public class Player implements PlayerIF{
    private GameColor color;
    private ArrayList<PieceIF> pieces;
    private ArrayList<PieceIF> capturedPieces;

    public Player(GameColor color) {
        this.color = color;
        this.pieces = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
    }

    @Override
    public GameColor getPieceColor() {
        return this.color;
    }

    @Override
    public ArrayList<PieceIF> getPieces() {
        return this.pieces;
    }

    @Override
    public ArrayList<PieceIF> getCapturedPieces(){
        return this.capturedPieces;
    }

    @Override
    public void addPiece(PieceIF piece) {
        this.pieces.add(piece);
    }

    @Override
    public void addCapturedPiece(PieceIF piece){
        this.capturedPieces.add(piece);
    }

    /**
     * Method to display the pieces that the player has captured.
     */
    public void displayCapturedPieces(){
        System.out.println(this.getColor().toString() + " captured pieces: ");
        for(PieceIF piece : capturedPieces){
            System.out.print(piece.getType().getLetter() + " ");
        }
    }
    /**
     * Getter method for the players game color.
     * @return color of the current player
     */
    public GameColor getColor(){
        return this.color;
    }
}
