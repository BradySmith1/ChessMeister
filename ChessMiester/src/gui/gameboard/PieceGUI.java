package gui.gameboard;

import enums.ChessPieceType;
import enums.GameColor;
import interfaces.MovementIF;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import movements.*;

public class PieceGUI extends ImageView{
    private Image image;
    private ChessPieceType type;
    private GameColor color;
    private MovementIF moveType;

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

    public PieceGUI(){
        this(null);
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

    public Image getPieceImage() {
        return image;
    }

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

    public ChessPieceType getType() {
        return type;
    }

    public GameColor getColor() {
        return color;
    }

    public MovementIF getMoveType() {
        return moveType;
    }

    public void setMoveType(MovementIF moveType) {
        this.moveType = moveType;
    }
}
