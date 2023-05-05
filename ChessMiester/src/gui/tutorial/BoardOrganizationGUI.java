/**
 * This class is the GUI implementation for the board organization tutorial.
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

public class BoardOrganizationGUI {
    /**
     * The board organization pane.
     */
    VBox boardOrganizationPane;

    /**
     * Reference to the implementation for the ScreenChangeHandlerIF
     **/
    ScreenChangeHandlerIF screenChanger;

    /** The return button. */
    Button returnButton;

    /**
     * Constructor for the board organization GUI.
     */
    public BoardOrganizationGUI() {
        // Create an HBox
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setId("main-pane");

        // Create a text object
        Text text = new Text("""
                Players each receive 16 pieces, and the pieces are divided into six categories:\s
                        
                  P/P_W/P_B are Pawns - 8, which are placed before all your other pieces.\s
                  
                  R/R_W/R_B are Rooks - 2, which are placed into the corners of the board.\s
                  
                  N/N_W/N_B are Knights - 2, which are placed inside next to the rooks. \s
                  
                  B/B_W/B_B are Bishops - 2, which are placed inside next to the knights.\s
                  
                  Q/Q_W/Q_B is the Queen - 1, which is placed on the respective color square \s
                                              between the bishops. \s
                    For example, white is played on the light squares and black on dark squares.\s
                                              
                  K/K_W/K_B is the King - 1, is placed on the last vacant square between\s
                                             all of the pieces. \s\s
                        
                Each of these pieces have their own respective tutorial, so feel free to try them\s
                out and learn more about them.\s\s
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
        boardOrganizationPane = new VBox();
        boardOrganizationPane.getChildren().addAll(hb, returnButton);
        boardOrganizationPane.setAlignment(Pos.CENTER);
        boardOrganizationPane.setSpacing(20);
        boardOrganizationPane.setId("main-pane");


        this.boardOrganizationPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the board setup pane.
     *
     * @return Pane the board setup pane
     */
    public Pane getRoot() { return this.boardOrganizationPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
