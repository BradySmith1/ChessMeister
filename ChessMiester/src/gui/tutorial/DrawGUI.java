/**
 * This class is responsible for the GUI implementation for the draw tutorial.
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

public class DrawGUI {
    /** The draw pane. */
    VBox drawPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The continue & return button. */
    Button cont, returnButton;

    /**
     * Constructor for the draw GUI.
     */
    public DrawGUI() {
        // Create a VBox
        drawPane = new VBox();
        drawPane.setId("main-pane");

        // Create a label
        Text label = new Text("Draw");
        label.setId("topLabel");
        drawPane.getChildren().add(label);

        // Create a text object
        Text text = new Text("""
                A draw is a result in chess where neither player wins or loses. \s
                There are many different ways of achieving the result of a draw, \s
                so let's explain them each: \s
                                
                1. Stalemate: A stalemate is a result in chess where a player's king is \s
                              placed in a position where they are not currently under attack\s
                              but the only legal moves they have are to move into a square \s
                              that is under attack.\s
                              
                2. Threefold Repetition: A threefold repetition is a result in chess where \s
                                         a player has repeated the same position three times \s
                                         in a row. This can be achieved by moving the same \s
                                         pieces in the same order three times in a row.\s
                                         
                3. Fifty Move Rule: A fifty move rule is a result in chess where a player \s
                                    has not made a capture or a pawn move in the last 50 \s
                                    moves total, 25 for each player. \s
                                    
                4. Mutual Agreement: A mutual agreement is a result in chess where both \s
                                     players agree to end the game in a draw. \s
                               
                
                Click continue whenever ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        drawPane.setAlignment(Pos.CENTER);
        drawPane.getChildren().add(text);

        // create continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.DRAW));
        drawPane.getChildren().add(cont);
        //TODO implement menu to make check

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        drawPane.getChildren().add(returnButton);

        // get css
        drawPane.setSpacing(20);
        drawPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the draw pane.
     *
     * @return VBox the draw pane
     */
    public VBox getRoot() { return drawPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
