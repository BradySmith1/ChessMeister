package controller;

import enums.Files;
import enums.Rank;
import interfaces.BoardIF;
import model.Board;
import model.Piece;
import model.Position;
import movements.QueenMovement;

public class Chess {

    private BoardIF boardGame;

    public Chess() {
        this.boardGame = new Board();
    }

    public void newGame() {
        
    }

    public void endGame() {

    }

    public BoardIF loadGame(String file) {
        return null;
    }

    public void saveGame(String file, BoardIF game) {

    }

    public void move(Files fromF, Rank fromR, Files toF, Rank toR) {

    }
}
