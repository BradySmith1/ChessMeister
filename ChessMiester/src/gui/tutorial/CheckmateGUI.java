/**
 * This class is responsible for the GUI implementation for the checkmate tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.tutorial;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CheckmateGUI {
    /** The checkmate pane. */
    VBox checkMatePane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The return button. */
    Button returnButton;

    /**
     * Constructor for the checkmate GUI.
     */
    public CheckmateGUI() {
        // Create a VBox
        checkMatePane = new VBox();
        checkMatePane.setId("main-pane");

        // Create a label
        Label title = new Label("Checkmate");
        title.setId("topLabel");
        checkMatePane.getChildren().add(title);

        // Create a text object
        Text text = new Text("""
                Checkmate is a condition in chess where a player's king is under attack by an \s
                opposing piece, and the player cannot move their king to a square that is not \s
                under attack, or capture the piece that is attacking their king. If a player's \s
                king is in checkmate, then the game is over. Simply put, the goal of chess is \s
                to put your opponent in checkmate, as that's the true win condition of \s
                the game.\s\s
                                
                To understand more, we suggest looking at the tutorial for check as well!\s
                
                Click continue whenever ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        checkMatePane.setAlignment(Pos.CENTER);
        checkMatePane.getChildren().add(text);

        // create continue button
        Button continueButton = new Button("Continue");
        continueButton.setId("menu-button");
        continueButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.CHECKMATE));
        checkMatePane.getChildren().add(continueButton);
        //TODO implement menu to make checkmate

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        checkMatePane.getChildren().add(returnButton);

        // get css
        checkMatePane.setSpacing(20);
        checkMatePane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the checkmate pane.
     *
     * @return VBox the check pane
     */
    public VBox getRoot() { return checkMatePane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
