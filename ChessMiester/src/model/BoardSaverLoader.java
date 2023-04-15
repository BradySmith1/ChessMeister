package model;


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
     * @param board the board / game to save
     * @param fileName  the name you want for the file
     */
    @Override
    public void saveGameToFile(BoardIF board, String fileName) {
        File saveFile = createFile(fileName);
        writeGame(board, saveFile);
    }

    /**
     * Method to load a game from a file
     * @param fileName  the name of the file to load from
     * @return  the board that you have loaded
     */
    @Override
    public BoardIF loadGameFromFile(String fileName) {
        BoardIF board = new Board();
        board.initBoard();
        FileReader reader = null; // initialize reader
        String contents = ""; // initialize string for contents
        String file = new File( "").getAbsolutePath(); //must be in chessmeister for this to work
        if(System.getProperty("os.name").contains("Windows")) // check if windows
            file = file.concat("\\src\\model\\saves\\" + fileName + ".txt"); // windows
        else // linux and macos
            file = file.concat("/src/model/saves/" + fileName + ".txt"); // concat file path

        try {
            reader = new FileReader(file); // open reader from the file path
            Scanner scan = new Scanner(reader); // create scanner from reader
            contents = scan.nextLine(); // grab contents
            scan.close(); // close scanner
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found: " + e);

        }

        // establish memento and load from memento
        Board.BoardMemento boardMemento = new Board.BoardMemento(contents);
        board.loadFromMemento(boardMemento);
        return board;
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
     * @param board the board to write
     * @param saveFile the file to write to
     */
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
