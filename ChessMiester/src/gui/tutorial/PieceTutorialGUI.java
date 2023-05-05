/**
 * This is responsible for creating the loop to let a player move a single
 * piece around the board. They are also allowed to spawn a piece.
 */
package gui.tutorial;

import enums.ToScreen;
import gui.gameboard.CenterPane;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PieceTutorialGUI {
    /** Pane for the piece tutorial gui */
    BorderPane piecePane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** Buttons for the piece tutorial gui */
    Button returnButton, spawnPiece;

    /**
     * Constructor for the piece tutorial gui.
     */
    public PieceTutorialGUI(String pieceName) {
        // Create a BorderPane
        piecePane = new BorderPane();
        piecePane.setId("main-pane");

        // create and assign board
        piecePane.setCenter(makeCenter(pieceName).getRoot());

        // create and assign buttons
        piecePane.setBottom(makeBottom());

        // make top to match style
        HBox top = new HBox();
        top.setId("top");
        piecePane.setTop(top);

        this.piecePane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());

    }

    /**
     * This method returns the center pane for the piece tutorial gui.
     *
     * @param pieceName name of the piece to initialize the board with
     * @return the center pane
     */
    private CenterPane makeCenter(String pieceName){
        // initialize the center pane
        CenterPane center = new CenterPane();
        // TODO: load the game based on pieceName

        return center;

    }

    /**
     * This method creates the bottom pane for the piece tutorial gui.
     *
     * @return HBox
     */
    private HBox makeBottom(){
        // create HBox for buttons
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setId("bottom");

        // create buttons and add them to the HBox
        returnButton = new Button("Return to Tutorial Menu");
        returnButton.setOnAction(e -> { screenChanger.changeScreen(ToScreen.TUTORIAL_MENU); });
        returnButton.setId("bottom-button");

        spawnPiece = new Button("Spawn Piece");
        spawnPiece.setOnAction(e -> { /* TODO: spawn a piece based on piece */ });
        spawnPiece.setId("bottom-button");

        // add elements, set alignment, set css
        hb.getChildren().addAll(returnButton, spawnPiece);
        hb.setAlignment(javafx.geometry.Pos.CENTER);
        return hb;


    }

    /**
     * This method returns the piece tutorial gui.
     *
     * @return BorderPane
     */
    public BorderPane getRoot() { return piecePane; }

    /**
     * This method sets the screen changer for the piece tutorial gui.
     *
     * @param sch implementation of the ScreenChangeHandlerIF
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
