package controller;

import enums.Files;
import enums.Rank;
import interfaces.BoardIF;
import model.Board;

public class Chess {

    private BoardIF boardGame;
    private static final int DEFAULT_GAME = 1;

    public Chess() {
        this(DEFAULT_GAME);
    }

    public Chess(int gameType) {
        System.out.println("Feature not yet finished");
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
