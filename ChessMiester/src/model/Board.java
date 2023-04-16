package model;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.*;
import uicli.BoardMonoCLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class represents a game board that can be used for a chess game.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */
public class Board implements BoardIF {

    /** The squares that make up the game board. */
    private SquareIF[][] squares;

    /** The width of the board in squares. */
    private int width;

    /** The height of the board in squares. */
    private int height;

    /** The strategy used to draw the board. */
    private BoardStrategy drawStrategy;

    private String state;

    /**
     * Constructor method that creates a new game board with the specified
     * width and height.
     */
    public Board() {
        this(new BoardMonoCLI());
    }

    /**
     * Constructor method that creates a new game board with the specified strategy.
     *
     * @param strategy : the strategy to use to draw the board
     */
    public Board(BoardStrategy strategy){
        this.width = 8;
        this.height = 8;
        squares = new Square[height][width];
        drawStrategy = strategy;
        this.initBoard();
    }

    /**
    * Method that initializes the game board.
    */
    @Override
    public void initBoard() {
        GameColor color = GameColor.WHITE;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                squares[i][j] = new Square(new Position(Rank.values()
                                    [(Rank.values().length - 1) - i], Files.values()[j]), color);
                color = color == GameColor.WHITE ? GameColor.BLACK : GameColor.WHITE;
            }
            color = color == GameColor.WHITE ? GameColor.BLACK : GameColor.WHITE;
        }
    }

    /**
    * Method that sets up the initial state of the game board.
    */
    @Override
    public void setup() {
        //need 8 pawns
        //need 2 rooks
        //need 2 knights
        //need 2 bishops
        //need 1 queen
        //need 1 king

        for(int i = 0; i < width; i++) {
            squares[1][i].setPiece(new Piece(ChessPieceType.Pawn, GameColor.WHITE));
            squares[6][i].setPiece(new Piece(ChessPieceType.Pawn, GameColor.BLACK));
        }
        setPieces(GameColor.WHITE, 0);
        setPieces(GameColor.BLACK, height-1);
    }

    private void createState() {
        StringBuilder stateBuilder = new StringBuilder("{");
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                Square square = ((Square) squares[i][j]);
                if (square.getPiece() != null) {
                    stateBuilder.append(square.getPosition().getFile());
                    stateBuilder.append(square.getPosition().getRank());
                    stateBuilder.append(":");
                    stateBuilder.append(square.getPiece().getType());
                    stateBuilder.append(square.getPiece().getColor());
                    if(i != getWidth() - 1) {
                        stateBuilder.append(",");
                    }
                }
            }
        }
        stateBuilder.append("}#[]");
        this.state = stateBuilder.toString();
    }

    /**
     * Helper method for setting up the pieces on the board
     *
     * @param color  the color of the pieces to set
     * @param offset the offset from the top of the board to set the pieces
     */
    private void setPieces(GameColor color, int offset){
        squares[offset][0].setPiece(new Piece(ChessPieceType.Rook, color));
        squares[offset][width-1].setPiece(new Piece(ChessPieceType.Rook, color));
        squares[offset][1].setPiece(new Piece(ChessPieceType.Knight, color));
        squares[offset][width-2].setPiece(new Piece(ChessPieceType.Knight, color));
        squares[offset][2].setPiece(new Piece(ChessPieceType.Bishop, color));
        squares[offset][width-3].setPiece(new Piece(ChessPieceType.Bishop, color));
        squares[offset][3].setPiece(new Piece(ChessPieceType.Queen, color));
        squares[offset][4].setPiece(new Piece(ChessPieceType.King, color));
    }

    /**
     * Method that draws the state of the game board.
     */
    @Override
    public void draw(GameColor playerColor) {
        drawStrategy.draw(this, playerColor);
    }

    /**
     * Returns a 2D array of squares on the game board.
     *
     * @return a 2D array of SquareIF objects representing the squares on the board.
     */
    @Override
    public SquareIF[][] getSquares() {
        return squares;
    }

    /**
     * Sets the drawing strategy for the game board.
     *
     * @param d the BoardStrategy object that defines the drawing strategy to use.
     */
    @Override
    public void setDrawStrategy(BoardStrategy d) {
        drawStrategy = d;
    }

    /**
     * Returns the width of the game board.
     *
     * @return the width of the game board in squares.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the game board.
     *
     * @return the height of the game board in squares.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Returns the piece at the specified rank and file on the game board.
     *
     * @param r the rank of the piece.
     * @param f the file of the piece.
     * @return the PieceIF object at the specified rank and file.
     */
    @Override
    public PieceIF getPiece(Rank r, Files f) {
        return squares[r.getIndex()][f.getFileNum()].getPiece();
    }

    /**
     * Returns the piece at the specified column and row on the game board.
     * @param col the column of the piece.
     * @param row the row of the piece.
     * @return    the PieceIF object at the specified column and row.
     */
    @Override
    public PieceIF getPiece(int col, char row) {
        return squares[col][row].getPiece();
    }
    
    public String getState() {
        return this.state;
    }

    public void addMove(GameColor color, Files fromF, Rank fromR, Files toF, Rank toR) {
        StringBuilder stateBuilder = new StringBuilder(this.state);
        stateBuilder.deleteCharAt(stateBuilder.length() - 1);
        stateBuilder.append(color.toString().charAt(0));
        stateBuilder.append(":");
        stateBuilder.append(fromF.getFileChar());
        stateBuilder.append(fromR.getIndex());
        stateBuilder.append("-");
        stateBuilder.append(toF.getFileChar());
        stateBuilder.append(toR.getIndex());
        stateBuilder.append("]");
        this.state = stateBuilder.toString();
    }

    public BoardMemento createMemento() {
        return new BoardMemento(this.state);
    }

    public void loadFromMemento(BoardMementoIF boardMemento) {
        String[] contents = boardMemento.state().split("#");
        String[] pieces = contents[0].substring(1, contents[0].length() - 2).split(",");
        String[] movesForward = contents[1].substring(1, contents[1].length() - 2).split(",");
        ArrayList<String> movesAL = new ArrayList<>(Arrays.stream(movesForward).toList());
        Collections.reverse(movesAL);
        String[] moves = movesAL.toArray(new String[0]);
        setPiecesFromMemento(pieces);
        setFirstMovesFromMemento(moves);
        this.state = boardMemento.state();
    }

    private void setPiecesFromMemento(String[] pieces) {
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
            Piece pieceToInsert = new Piece(type, color);
            squares[newFile.getFileNum()][newRank.getIndex()].setPiece(pieceToInsert);
        }

    }

    private void setFirstMovesFromMemento(String[] moves) {
        for(String move : moves) {
            Files fromF = Files.valueOf(String.valueOf(move.charAt(2)).toLowerCase());
            Rank fromR = Rank.valueOf(String.valueOf(move.charAt(3)));
            Files toF = Files.valueOf(String.valueOf(move.charAt(5)).toLowerCase());
            Rank toR = Rank.valueOf(String.valueOf(move.charAt(6)));
            MovementIF movementType = squares[toF.getFileNum()][toR.index].getPiece().getMoveType();
            if (squares[toF.getFileNum()][toR.index].getPiece().getMoveType() instanceof FirstMoveIF) {
                FirstMoveIF movement = (FirstMoveIF) movementType;
                movement.setFirstMoveFalse();
            }
        }
    }

    public record BoardMemento(String state) implements BoardIF.BoardMementoIF {}
}
