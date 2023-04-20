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
 * @version 1.0 (done in sprint 2)
 */
public class BoardSaverLoader implements BoardSaverLoaderIF {

    /**
     * Method to save a game to a file
     *
     * @param caretaker the stack of mementos you want to save
     * @param fileName  the name you want for the file
     */
    @Override
    public void saveGameToFile(BoardMementoCaretaker caretaker, String fileName) {
        String path = new java.io.File("").getAbsolutePath();
        if(System.getProperty("os.name").contains("Windows")) // check if windows
            path = path.concat("\\src\\model\\saves"); // windows
        else // linux and macos
            path = path.concat("/src/model/saves/"); // concat file path
        path = path.concat(fileName);
        File saveFile = createFile(path);
        writeGame(caretaker, saveFile);
    }

    /**
     * Method to load a game from a file
     *
     * @param fileName the name of the file to load from
     * @return         the board that you have loaded
     */
    @Override
    public BoardMementoCaretaker loadGameFromFile(String fileName) {
        BoardIF board = new Board();
        board.initBoard();
        FileReader reader; // initialize reader
        BoardMementoCaretaker caretaker = null;
        BoardIF.BoardMementoIF memento;
        //must be in chessmeister for this to work
        String file = new java.io.File( "").getAbsolutePath();
        if(System.getProperty("os.name").contains("Windows")) // check if windows
            file = file.concat("\\src\\model\\saves\\" + fileName + ".txt"); // windows
        else // linux and macos
            file = file.concat("/src/model/saves/" + fileName + ".txt");

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
        catch(FileNotFoundException ignore){
            System.out.println("\nFile could not be located. Please enter a different name.\n");

        }

        // establish memento and load from memento
        return caretaker;
    }

    /**
     * Method to create the file
     *
     * @param fileName Name of the file to create
     * @return         the file that has been created
     */
    private File createFile(String fileName) {
        File saveFile = null;
        try {
            fileName = fileName.concat(".txt");
            saveFile = new File(fileName);
            if (saveFile.createNewFile()) {
                System.out.println("\nSave File: " + fileName + " was Successfully Created!");
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
     *
     * @param caretaker the stack of mementos you want to save
     * @param saveFile  the file you want to save to
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
            System.out.println("The file could not be saved!");
        }
    }
}