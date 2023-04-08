package model;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.BoardIF;
import interfaces.BoardSaverLoaderIF;
import interfaces.SquareIF;

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
        SquareIF[][] squares = board.getSquares();
        Scanner scan = new Scanner("../saves/" + fileName + ".txt");
        String[] contents = scan.nextLine().split("#");
        scan.close();
        String piecesString = contents[0].substring(1, contents[0].length() - 2);
        String[] pieces = piecesString.split(",");
        setPieces(pieces, squares);

        return board;   //not finished
    }
    private void setPieces(String[] pieces, SquareIF[][] squares) {
        for (String piece : pieces) {
            Files newFile = Files.valueOf(String.valueOf(piece.charAt(0)).toLowerCase());
            Rank newRank = Rank.valueOf(String.valueOf(piece.charAt(1)));
            ChessPieceType type = ChessPieceType.valueOf(String.valueOf(piece.charAt(3)));
            String colorCase = String.valueOf(piece.charAt(4));
            GameColor color = null;
            switch(colorCase) {
                case "W" -> color = GameColor.WHITE;
                case "B" -> color = GameColor.BLACK;
            }
            squares[newFile.getFileNum()][newRank.getIndex()].setPiece(new Piece(type, color));
        }
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
            SquareIF[][] squares = board.getSquares();
            writePieces(board, writer, squares);
            writeMoves(board, writer);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private void writePieces(BoardIF board, FileWriter writer, SquareIF[][] squares) throws IOException {
        writer.write("{");
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                Square square = ((Square) squares[i][j]);
                if (square.getPiece() != null) {
                    writer.write(String.valueOf(square.getPosition().getFile()) + square.getPosition().getRank() +
                            ":" + square.getPiece().getType() + square.getPiece().getColor().toString());
                    if(i != board.getWidth() - 1) {
                        writer.write(",");
                    }
                }
            }
        }
        writer.write("}");
    }

    private void writeMoves(BoardIF board, FileWriter writer) throws IOException{
        writer.write("#[");
        for(String move : board.getMoves()) {
            writer.write(move);
            if(!move.equals(board.getMoves().get(board.getMoves().size() - 1))) {
                writer.write(",");
            }
        }
        writer.write("]");
    }
}
