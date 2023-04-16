package model;


import controller.BoardMementoCaretaker;
import interfaces.BoardIF;
import interfaces.BoardSaverLoaderIF;

import java.io.*;
import java.util.Scanner;

public class BoardSaverLoader implements BoardSaverLoaderIF {

    @Override
    public void saveGameToFile(BoardMementoCaretaker caretaker, String fileName) {
        File saveFile = createFile(fileName);
        writeGame(caretaker, saveFile);
    }

    @Override
    public BoardMementoCaretaker loadGameFromFile(String fileName) {
        Scanner scan = new Scanner("../saves/" + fileName + ".txt");
        BoardIF.BoardMementoIF memento = new Board.BoardMemento(scan.nextLine());
        BoardMementoCaretaker caretaker = new BoardMementoCaretaker(memento);
        while(scan.hasNext()) {
            memento = new Board.BoardMemento(scan.nextLine());
            caretaker.push(memento);
        }
        scan.close();
        return caretaker;
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

    private void writeGame(BoardMementoCaretaker caretaker, File saveFile) {
        FileWriter writer;
        try {
            writer = new FileWriter(saveFile);
            writer.write(caretaker.topToBottom().state());
            while(caretaker.up() != null) {
                writer.write("\n" + caretaker.peek().state());
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
