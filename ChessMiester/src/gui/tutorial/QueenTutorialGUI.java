/**
 * This class is responsible for the GUI implementation for the queen tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.tutorial;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;

public class QueenTutorialGUI {
    /** The queen tutorial pane. */
    VBox queenPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The continue & return button. */
    Button cont, returnButton;

    /**
     * Constructor for the queen tutorial GUI.
     */
    public QueenTutorialGUI() {
        // Create a VBox
        queenPane = new VBox();
        queenPane.setId("main-pane");

        try{
            Image queen = new Image(new FileInputStream(
                    "src/gui/gameboard/images/WhiteKing.png"));
            ImageView queenView = new ImageView(queen);
            queenView.setFitHeight(100);
            queenView.setFitWidth(100);
            queenPane.getChildren().add(queenView);
        }catch(Exception e){
            System.out.println("Error: File not found.");
        }

        // Create a text object
        Text text = new Text("""
                The queen is the most powerful piece in chess. The queen can move \s
                in any direction, as long as there are no pieces in the way. The queen \s
                can capture pieces by moving to the square that the opposing piece is on.\s\s
                                    
                You can think of the Queen as an all-in-one piece! It combines the moves \s
                of the rook and bishop, and can move in any direction. Make sure that \s
                you're careful with this piece, as it can completely turn the tide of \s
                a game!\s\s
                                    
                Click continue whenever you're ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        queenPane.setAlignment(Pos.CENTER);
        queenPane.getChildren().add(text);

        // create continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.PIECE_TUTORIAL));
        queenPane.getChildren().add(cont);
        //TODO implement menu to make queen tutorial

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        queenPane.getChildren().add(returnButton);

        // get css
        queenPane.setSpacing(20);
        queenPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the check pane.
     *
     * @return VBox the check pane
     */
    public VBox getRoot() { return queenPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
