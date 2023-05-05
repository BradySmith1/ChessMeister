/**
 * This class is responsible for displaying the chess notation tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */

package gui.tutorial;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

import java.io.FileInputStream;

public class ChessNotationGUI {
    /** the chess notation pane */
    VBox chessNotationPane;

    /** reference to the implementation for the ScreenChangeHandlerIF */
    ScreenChangeHandlerIF screenChanger;

    /** the return button */
    Button cont, returnButton;

    /**
     * Constructor for the chess notation GUI.
     */
    public ChessNotationGUI() {
        // make a new HBox
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setId("main-pane");

        Text text = new Text("""
                Chess notation is a system that allows players to record and communicate the \s
                moves that have been made in a chess game. There are many different types of \s
                chess notation, but the most common is called algebraic notation. In algebraic \s
                notation, each square on the chess board is assigned a letter and a number. \s
                The letters are assigned from left to right, starting with the letter 'a'. \s
                The numbers are assigned from bottom to top, starting with the number '1'. \s
                For example, the square in the bottom left corner of the board is 'a1', and \s
                the square in the top right corner of the board is 'h8'.\s\s
                
                As seen in the above board, this implementation of chess follows the algebraic \s
                notation system. To make a move, simply type the square that the piece is \s
                currently on, followed by the square that the piece is moving to. The letters \s
                and numbers are displayed throughout the entire course of the game, so you \s
                should be able to easily identify the squares that you want to move to. \s\s
                
                Now, let's see if you have the right understanding! We're gonna highlight a \s
                few squares, and just enter what square they are. \s\s
                
                Click Continue to continue whenever you're ready.
                """);
        text.setId("tutorialText");
        hb.getChildren().add(text);

        // add image for notation
        try{
            ImageView img = new ImageView(new Image(
                    new FileInputStream("src/gui/tutorial/boardNotation.jpg")));
            img.setFitHeight(400);
            img.setFitWidth(400);
            hb.getChildren().add(img);
        } catch (Exception e) {
            System.out.println("Error loading image");
        }

        hb.setAlignment(Pos.CENTER);
        // add the HBox to the VBox
        chessNotationPane = new VBox();
        chessNotationPane.getChildren().add(hb);
        chessNotationPane.setId("main-pane");

        // add continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.CHESS_NOTATION));
        //TODO chess notation quiz

        // add return button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));

        // add the return button to the VBox
        chessNotationPane.getChildren().addAll(cont, returnButton);
        chessNotationPane.setAlignment(Pos.CENTER);
        chessNotationPane.setSpacing(20);

        // add css
        this.chessNotationPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }

    /**
     * Gets the chess notation pane.
     *
     * @return the chess notation pane
     */
    public VBox getRoot() { return chessNotationPane; }
}
