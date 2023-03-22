package controller;

import model.Board;
import uicli.BoardColorCLI;
import uicli.BoardMonoCLI;

public class Driver {
    public static void main(String[] args){
        Board board = new Board(new BoardMonoCLI());
        board.initBoard();
        board.setup();
        board.draw();
    }
}
