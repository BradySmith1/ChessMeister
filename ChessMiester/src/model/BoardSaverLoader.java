package model;


import controller.BoardMementoCaretaker;
import interfaces.BoardIF;
import interfaces.BoardSaverLoaderIF;

import java.io.*;
import java.util.Scanner;

/**
 * Class responsible for loading and saving chess games to and from .txt files.
 *
 * @author Colton Brooks (85%), Zach Eanes (15%)
 */
public class BoardSaverLoader implements BoardSaverLoaderIF {

    /**
     * Method to save a game to a file
     * @param caretaker the stack of mementos you want to save
     * @param fileName  the name you want for the file
     */
    @Override
    public void saveGameToFile(BoardMementoCaretaker caretaker, String fileName) {
        File saveFile = createFile(fileName);
        writeGame(caretaker, saveFile);
    }

    /**
     * Method to load a game from a file
     * @param fileName  the name of the file to load from
     * @return  the board that you have loaded
     */
    @Override
    public BoardMementoCaretaker loadGameFromFile(String fileName) {
        BoardIF board = new Board();
        board.initBoard();
        FileReader reader; // initialize reader
        BoardMementoCaretaker caretaker = null;
        BoardIF.BoardMementoIF memento;
        String file = new java.io.File( "").getAbsolutePath(); //must be in chessmeister for this to work
        if(System.getProperty("os.name").contains("Windows")) // check if windows
            file = file.concat("\\src\\model\\saves\\" + fileName + ".txt"); // windows
        else // linux and macos
            file = file.concat("/src/model/saves/" + fileName + ".txt"); // concat file path

        try {
            reader = new FileReader(file); // open reader from the file path
            Scanner scan = new Scanner(reader); // create scanner from reader
            memento = new Board.BoardMemento(scan.nextLine());
            caretaker = new BoardMementoCaretaker(memento);
            while(scan.hasNext()) {
                memento = new Board.BoardMemento(scan.nextLine());
                caretaker.push(memento);
            }
            scan.close(); // close scanner
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found: " + e);

        }

        // establish memento and load from memento
        return caretaker;
    }

    /**
     * Method to create the file
     * @param fileName  Name of the file to create
     * @return  the file that has been created
     */
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

    /**
     * Method to write the board state into a file.
     */
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
            System.out.println("The file could not be saved!!");
        }
    }
}