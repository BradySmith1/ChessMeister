package model;

import enums.ChessPieceType;
import enums.Files;
import enums.GameColor;
import enums.Rank;
import interfaces.*;
import movements.PawnMovement;
import uicli.BoardMonoCLI;

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

    /**
     * Method that creates the initial state for the board.
     */
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

    /**
     * Getter for the state field of the Board
     * @return the String of the board state
     */
    @Override
    public String getState() {
        return this.state;
    }

    /**
     * Adds the move to the boards state that it holds in a field.
     * @param color color of the moving piece
     * @param fromF current file for the piece
     * @param fromR current rank for the piece
     * @param toF   the file to move to
     * @param toR   the rank to move to
     */
    @Override
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

    /**
     * Creates a memento for the current state of the board
     * @return the memento to be stored in a caretaker
     */
    @Override
    public BoardMementoIF createMemento() {
        return new BoardMemento(this.state);
    }

    /**
     * Method to load the board from a different memento / board state.
     * @param boardMemento  the memento to load in
     */
    @Override
    public void loadFromMemento(BoardMemento boardMemento) {
        String[] contents = boardMemento.state().split("#");
        String[] pieces = contents[0].substring(1, contents[0].length() - 1).split(",");
        setPiecesFromMemento(pieces);
        this.state = boardMemento.state();
    }

    /**
     * Method to place the pieces depending on the String[] passed in from loadFromMemento()
     * @param pieces    An array in which each string describes a piece and its location
     */
    private void setPiecesFromMemento(String[] pieces) {
        for (String piece : pieces) {
            Files newFile = Files.valueOf(String.valueOf(piece.charAt(0))); // get file
            Rank newRank = Rank.valueOf("R" + piece.charAt(1)); // get rank
            // identify piece type from provided letter
            String type = ChessPieceType.identify(String.valueOf(piece.charAt(3)));
            // get piece type from returned string
            ChessPieceType pieceType = ChessPieceType.valueOf(type);
            String colorCase = String.valueOf(piece.charAt(4)); //get color
            GameColor color = null;
            switch(colorCase){
                case "W" -> color = GameColor.WHITE;
                case "B" -> color = GameColor.BLACK;
            }
            PieceIF pieceToInsert = new Piece(pieceType, color); // make piece to place
            squares[newRank.getIndex()][newFile.getFileNum()].setPiece(pieceToInsert); // place
            pawnCheck(pieceToInsert, pieceType, color, newRank);
        }
    }

    /**
     * Method to check if a pawn has moved from the starting positions for pawns of its color,
     * if it has move set its firstMove field to false.
     * @param pieceToInsert the piece to be modified if it is a pawn
     * @param pieceType the tpe of piece it is
     * @param color the color of the piece
     * @param newRank   the rank of the piece
     */
    private void pawnCheck(PieceIF pieceToInsert, ChessPieceType pieceType, GameColor color, Rank newRank) {
        if(pieceType == ChessPieceType.Pawn) {
            PawnMovement pawn = (PawnMovement) pieceToInsert.getMoveType();
            if(color == GameColor.BLACK && newRank.getIndex() != squares[0].length - 2) {
                pawn.setFirstMove();
            }
            else if(color == GameColor.BLACK && newRank.getIndex() != 1) {
                pawn.setFirstMove();
            }
        }
    }

    /**
     * A memento nested class for the board object. It can hold a boards state.
     * @param state A string representing the state the board is in
     */
    public record BoardMemento(String state) implements BoardMementoIF{}
}
