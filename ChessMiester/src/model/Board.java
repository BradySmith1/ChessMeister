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
 * @author Brady Smith %100
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
        this(new BoardMonoCLI());
    }

    public Board(BoardStrategy strategy){
        this.width = 8;
        this.height = 8;
        squares = new Square[height][width];
        drawStrategy = strategy;
        this.initBoard();
    }

    /**
    * Initializes the game board.
    */
    @Override
    public void initBoard() {
        GameColor color = GameColor.WHITE;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                squares[i][j] = new Square(new Position(Rank.values()[(Rank.values().length - 1) - i], Files.values()[j]), color);
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
        for(int i = 0; i < width; i++) {
            squares[1][i].setPiece(new Piece(ChessPieceType.Pawn, GameColor.WHITE));
            squares[6][i].setPiece(new Piece(ChessPieceType.Pawn, GameColor.BLACK));
        }
        setPieces(GameColor.WHITE, 0);
        setPieces(GameColor.BLACK, height-1);
    }

    /**
     * Helpher method for setting up the pieces on the board
     * @param color : the color of the pieces to set
     * @param offset : the offset from the top of the board to set the pieces
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
