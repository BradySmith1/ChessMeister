/**
 * This class is responsible for the GUI implementation for the check tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.tutorial;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CheckGUI {
    /** The check pane. */
    VBox checkPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The continue & return button. */
    Button cont, returnButton;

    /**
     * Constructor for the check GUI.
     */
    public CheckGUI() {
        // Create a VBox
        checkPane = new VBox();
        checkPane.setId("main-pane");

        // Create a text object
        Text text = new Text("""
                Check is a condition in chess where a player's king is under attack by an \s
                opposing piece. If a player's king is in check, they must move their king to \s
                a square that is not under attack, capture the piece that is attacking \s
                their king, or place one of their own pieces in the way in order to defend \s
                the king. \s\s
                                
                Whenever you are in check, that means there is a way of out the \s
                situation or else you would be in checkmate. To learn more about that, \s
                check out the tutorial for checkmate!\s\s
                
                Click continue whenever ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        checkPane.setAlignment(Pos.CENTER);
        checkPane.getChildren().add(text);

        // create continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.CHECK));
        checkPane.getChildren().add(cont);
        //TODO implement menu to make check

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        checkPane.getChildren().add(returnButton);

        // get css
        checkPane.setSpacing(20);
        checkPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the check pane.
     *
     * @return VBox the check pane
     */
    public VBox getRoot() { return checkPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
