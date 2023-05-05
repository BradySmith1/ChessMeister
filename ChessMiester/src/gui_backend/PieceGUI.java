/**
 * This class is responsible for creating the graphical implementation and logic
 * for a piece in the chess board.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui_backend;

import enums.ChessPieceType;
import enums.GameColor;
import interfaces.MovementIF;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import movements.*;

public class PieceGUI extends ImageView{
    /** the image of the piece */
    private Image image;

    /** the type of the piece */
    private ChessPieceType type;

    /** the color of the piece */
    private GameColor color;

    /** the movement type of the piece */
    private MovementIF moveType;

    /**
     * Constructor for the piece given an image.
     *
     * @param image the image of the piece
     */
    public PieceGUI(Image image){
        super();
        this.setFitWidth(50);
        this.setFitHeight(50);
        this.image = image;
        if(image != null){
            setPieceImage(image);
        }
        this.setImage(this.image);
    }

    /**
     * Constructor for the piece when no image is given.
     */
    public PieceGUI(){
        this(null);
    }

    /**
     * Setter method for the piece image.
     *
     * @param image the image of the piece
     */
    public void setPieceImage(Image image) {
        this.image = image;
        this.setImage(this.image);
        if(this.image == null){
            type = null;
            color = null;
            moveType = null;
        }else{
            setType();
        }
    }

    /**
     * Setter method for the piece type.
     */
    public void setType() {
        String path = image.getUrl();
        int lastSlash = path.lastIndexOf('/') + 6;
        int lastPeriod = path.lastIndexOf('.');
        path = path.substring(lastSlash, lastPeriod);
        if(path.equals("KnightLeft") || path.equals("KnightRight")){
            path = path.substring(0,6);
        }
        switch(path){
            case("Bishop") -> type = ChessPieceType.Bishop;
            case("King") -> type = ChessPieceType.King;
            case("Knight") -> type = ChessPieceType.Knight;
            case("Pawn") -> type = ChessPieceType.Pawn;
            case("Queen") -> type = ChessPieceType.Queen;
            case("Rook") -> type = ChessPieceType.Rook;
        }
        moveTypeFactory();
        setColor();
    }

    /**
     * Defines the piece typing based on the type of the piece.
     */
    private void moveTypeFactory() {
        switch (type) {
            case King -> moveType = new KingMovement(getColor());
            case Pawn -> moveType = new PawnMovement(getColor());
            case Rook -> moveType = new RookMovement(getColor());
            case Queen -> moveType = new QueenMovement(getColor());
            case Bishop -> moveType = new BishopMovement(getColor());
            case Knight -> moveType = new KnightMovement(getColor());
        }
    }

    /**
     * Setter method for the piece color.
     */
    public void setColor() {
        String path = image.getUrl();
        int lastSlash = path.lastIndexOf('/') + 1;
        int lastChar = path.lastIndexOf('/') + 6;
        path = path.substring(lastSlash, lastChar);
        switch(path){
            case("Black") -> this.color = GameColor.WHITE;
            case("White") -> this.color = GameColor.BLACK;
        }
    }

    /**
     * Getter method for the piece image.
     *
     * @return the image of the piece
     */
    public Image getPieceImage() {
        return image;
    }

    /**
     * Getter method for the piece type.
     *
     * @return the type of the piece
     */
    public ChessPieceType getType() {
        return type;
    }

    /**
     * Getter method for the piece color.
     *
     * @return the color of the piece
     */
    public GameColor getColor() {
        return color;
    }

    /**
     * Getter method for the piece movement type.
     *
     * @return the movement type of the piece
     */
    public MovementIF getMoveType() {
        return moveType;
    }

/**
     * Setter method for the piece movement type.
     *
     * @param moveType the movement type of the piece
     */
    public void setMoveType(MovementIF moveType) { this.moveType = moveType; }
}
