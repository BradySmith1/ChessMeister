package model;


import interfaces.BoardIF;
import interfaces.BoardSaverLoaderIF;

import java.io.*;
import java.util.Scanner;

public class BoardSaverLoader implements BoardSaverLoaderIF {

    @Override
    public void saveGameToFile(BoardIF board, String fileName) {
        File saveFile = createFile(fileName);
        writeGame(board, saveFile);
    }

    @Override
    public BoardIF loadGameFromFile(String fileName) {
        BoardIF board = new Board();
        board.initBoard();
        Scanner scan = new Scanner("../saves/" + fileName + ".txt");
        String contents = scan.nextLine();
        scan.close();
        Board.BoardMemento boardMemento = new Board.BoardMemento(contents);
        board.loadFromMemento(boardMemento);
        return board;
    }

    private File createFile(String fileName) {
        File saveFile = null;
        try {
            fileName = "../saves/" + fileName + ".txt";
            saveFile = new File(fileName);
            if (saveFile.createNewFile()) {
                System.out.println("\nSave File: " + fileName + "Successfully Created");
            } else {
                System.out.println("\nFile: " + fileName + "Already Exists");
            }
        }
        catch (IOException e) {
            System.out.println("There was an error creating your file:\n" + e);
        }
        return saveFile;
    }

    private void writeGame(BoardIF board, File saveFile) {
        FileWriter writer;
        try {
            writer = new FileWriter(saveFile);
            writer.write(board.getState());
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
