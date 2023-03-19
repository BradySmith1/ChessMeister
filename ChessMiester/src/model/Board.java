package model;

import enums.ChessPieceType;
import enums.Files;
import enums.Rank;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.PieceIF;

/**
 * This class represents a game board.
 */
public class Board implements BoardIF, BoardStrategy {

    /**
     * The squares that make up the game board.
     */
    private Square[][] squares;

    /**
     * The width of the board in squares.
     */
    private int width;

    /**
     * The height of the board in squares.
     */
    private int height;

    /**
     * The strategy used to draw the board.
     */
    private BoardStrategy drawStrategy;

    /**
    * Creates a new game board with the specified width and height.
    * @param width the width of the board in squares.
    * @param height the height of the board in squares.
    */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        squares = new Square[width][height];
    }

    /**
    * Initializes the game board.
    */
    @Override
    public void initBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                squares[i][j] = new Square(new Position(Rank.values()[i], Files.values()[j]));
            }
        }
    }

    /**
    * Sets up the initial state of the game board.
    */
    @Override
    public void setup() {
        //need 8 pawns
        //need 2 rooks
        //need 2 knights
        //need 2 bishops
        //need 1 queen
        //need 1 king
        //need to figure out how to set up the board
        for(int i = 0; i < width; i++) {
            squares[i][1].setPiece(new Piece(ChessPieceType.Pawn));
            squares[i][6].setPiece(new Piece(ChessPieceType.Pawn));
        }
        squares[0][0].setPiece(new Piece(ChessPieceType.Rook));
        squares[width-1][0].setPiece(new Piece(ChessPieceType.Rook));
        squares[0][height-1].setPiece(new Piece(ChessPieceType.Rook));
        squares[width-1][height-1].setPiece(new Piece(ChessPieceType.Rook));
        squares[1][0].setPiece(new Piece(ChessPieceType.Knight));
        squares[width-2][0].setPiece(new Piece(ChessPieceType.Knight));
        squares[1][height-1].setPiece(new Piece(ChessPieceType.Knight));
        squares[width-2][height-1].setPiece(new Piece(ChessPieceType.Knight));
        squares[2][0].setPiece(new Piece(ChessPieceType.Bishop));
        squares[width-3][0].setPiece(new Piece(ChessPieceType.Bishop));
        squares[2][height-1].setPiece(new Piece(ChessPieceType.Bishop));
        squares[width-3][height-1].setPiece(new Piece(ChessPieceType.Bishop));
        squares[3][0].setPiece(new Piece(ChessPieceType.Queen));
        squares[3][height-1].setPiece(new Piece(ChessPieceType.Queen));
        squares[4][0].setPiece(new Piece(ChessPieceType.King));
        squares[4][height-1].setPiece(new Piece(ChessPieceType.King));
    }

    /**
     * draws a game board
     */
    @Override
    public void draw() {
        //need to figure out what is going on here
    }

    /**
     * Draws the game board using the specified strategy.
     * @param board the BoardIF object representing the game board to be drawn.
     */
    @Override
    public void draw(BoardIF board) {

    }

    /**
     * Returns a 2D array of squares on the game board.
     * @return a 2D array of SquareIF objects representing the squares on the board.
     */
    @Override
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * Sets the drawing strategy for the game board.
     * @param d the BoardStrategy object that defines the drawing strategy to use.
     */
    @Override
    public void setDrawStrategy(BoardStrategy d) {
        drawStrategy = d;
    }

    /**
     * Returns the width of the game board.
     * @return the width of the game board in squares.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the game board.
     * @return the height of the game board in squares.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Returns the piece at the specified rank and file on the game board.
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
     * @return the PieceIF object at the specified column and row.
     */
    @Override
    public PieceIF getPiece(int col, char row) {
        return squares[col][row].getPiece();
    }
}
