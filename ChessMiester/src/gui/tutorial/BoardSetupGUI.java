/**
 * This class is the GUI implementation for the board setup tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.tutorial;

import enums.ToScreen;
import gui.gameboard.CenterPane;
import interfaces.ScreenChangeHandlerIF;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.ScreenFactory;

public class BoardSetupGUI {
    /**
     * The board setup pane.
     */
    VBox boardSetupPane;

    /**
     * Reference to the implementation for the ScreenChangeHandlerIF
     **/
    ScreenChangeHandlerIF screenChanger;

    /** The return button. */
    Button returnButton;

    /**
     * Constructor for the board setup GUI.
     */
    public BoardSetupGUI() {
        // Create an HBox
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setId("main-pane");

        // Create a text object
        Text text = new Text("""
                Above is how a board will look when it's loaded. The board is 8x8,and each square\s
                is represented by a file (letter) and a rank (number). The files represent the\s
                columns, and the ranks represent the rows. Moves are made by moving a piece from\s
                one square to another. The squares are labeled with a file and a rank, and the\s
                pieces are labeled with their color and type.\s\s
                
                Board setup will always be the same at the beginning of a chess game, so don't\s
                worry about having to learn but so much!\s\s
                """);
        text.setId("tutorialText");
        hb.getChildren().add(text);
        hb.setAlignment(Pos.CENTER);

        // create board
        CenterPane board = new CenterPane();
        hb.getChildren().add(board.getRoot());

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> { screenChanger.changeScreen(ToScreen.TUTORIAL_MENU); });

        // create a vbox
        boardSetupPane = new VBox();
        boardSetupPane.getChildren().addAll(hb, returnButton);
        boardSetupPane.setAlignment(Pos.CENTER);
        boardSetupPane.setSpacing(20);
        boardSetupPane.setId("main-pane");


        this.boardSetupPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the board setup pane.
     *
     * @return Pane the board setup pane
     */
    public Pane getRoot() { return this.boardSetupPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
