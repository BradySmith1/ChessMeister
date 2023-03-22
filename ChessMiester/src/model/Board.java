package model;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.BoardIF;
import interfaces.BoardStrategy;
import interfaces.PieceIF;
import interfaces.SquareIF;
import uicli.BoardMonoCLI;

/**
 * This class represents a game board.
 */
public class Board implements BoardIF {

    /**
     * The squares that make up the game board.
     */
    private SquareIF[][] squares;

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
    */
    public Board() {
        this.width = 8;
        this.height = 8;
        squares = new Square[height][width];
        drawStrategy = new BoardMonoCLI();
    }

    public Board(BoardStrategy strategy){
        this.width = 8;
        this.height = 8;
        squares = new Square[height][width];
        drawStrategy = strategy;
    }

    /**
    * Initializes the game board.
    */
    @Override
    public void initBoard() {
        GameColor color = GameColor.WHITE;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                squares[i][j] = new Square(new Position(Rank.values()[j], Files.values()[i]), color);
                color = color == GameColor.WHITE ? GameColor.BLACK : GameColor.WHITE;
            }
            color = color == GameColor.WHITE ? GameColor.BLACK : GameColor.WHITE;
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
        GameColor color = GameColor.WHITE;
        for(int i = 0; i < width; i++) {
            squares[1][i].setPiece(new Piece(ChessPieceType.Pawn, color));
            squares[6][i].setPiece(new Piece(ChessPieceType.Pawn, color));
            color = color == GameColor.WHITE ? GameColor.BLACK : GameColor.WHITE;
        }
        color = GameColor.WHITE;
        squares[0][0].setPiece(new Piece(ChessPieceType.Rook, color));
        squares[0][width-1].setPiece(new Piece(ChessPieceType.Rook, color));
        squares[0][1].setPiece(new Piece(ChessPieceType.Knight, color));
        squares[0][width-2].setPiece(new Piece(ChessPieceType.Knight, color));
        squares[0][2].setPiece(new Piece(ChessPieceType.Bishop, color));
        squares[0][width-3].setPiece(new Piece(ChessPieceType.Bishop, color));
        squares[0][3].setPiece(new Piece(ChessPieceType.Queen, color));
        squares[0][4].setPiece(new Piece(ChessPieceType.King, color));
        color = GameColor.BLACK;
        squares[height-1][0].setPiece(new Piece(ChessPieceType.Rook, color));
        squares[height-1][width-1].setPiece(new Piece(ChessPieceType.Rook, color));
        squares[height-1][1].setPiece(new Piece(ChessPieceType.Knight, color));
        squares[height-1][width-2].setPiece(new Piece(ChessPieceType.Knight, color));
        squares[height-1][2].setPiece(new Piece(ChessPieceType.Bishop, color));
        squares[height-1][width-3].setPiece(new Piece(ChessPieceType.Bishop, color));
        squares[height-1][3].setPiece(new Piece(ChessPieceType.Queen, color));
        squares[height-1][4].setPiece(new Piece(ChessPieceType.King, color));
    }

    /**
     * draws the state of the game board.
     */
    @Override
    public void draw() {
        drawStrategy.draw(this);
    }

    /**
     * Returns a 2D array of squares on the game board.
     * @return a 2D array of SquareIF objects representing the squares on the board.
     */
    @Override
    public SquareIF[][] getSquares() {
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
