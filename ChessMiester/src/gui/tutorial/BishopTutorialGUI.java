/**
 * This class is responsible for the GUI implementation for the bishop tutorial.
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

public class BishopTutorialGUI {
    /** The bishop tutorial pane. */
    VBox bishopPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The continue & return button. */
    Button cont, returnButton;

    /**
     * Constructor for the bishop tutorial GUI.
     */
    public BishopTutorialGUI() {
        // Create a VBox
        bishopPane = new VBox();
        bishopPane.setId("main-pane");

        // get bishop image and add to pane
        try{
            Image bishop = new Image(new FileInputStream(
                    "src/gui/gameboard/images/WhiteBishop.png"));
            ImageView bishopView = new ImageView(bishop);
            bishopView.setFitHeight(100);
            bishopView.setFitWidth(100);
            bishopPane.getChildren().add(bishopView);
        }catch(Exception e){
            System.out.println("Error: File not found.");
        }

        // Create a text object
        Text text = new Text("""
                Bishops are considered to be one of the better piece in chess, and is why \s
                a player is only provided with two of them. Bishops can move diagonally in any \s
                direction, as long as there are no pieces in the way. Bishops can capture \s
                pieces by moving to the square that the opposing piece is on.\s\s
                                
                Click continue whenever you're ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        bishopPane.setAlignment(Pos.CENTER);
        bishopPane.getChildren().add(text);

        // create continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.PIECE_TUTORIAL));
        bishopPane.getChildren().add(cont);
        //TODO implement menu to make bishop tutorial

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        bishopPane.getChildren().add(returnButton);

        // get css
        bishopPane.setSpacing(20);
        bishopPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the check pane.
     *
     * @return VBox the check pane
     */
    public VBox getRoot() { return bishopPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
