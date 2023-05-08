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

/**
 * This class is responsible for the GUI implementation for the rook tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
public class RookTutorialGUI {
    /** The rook tutorial pane. */
    VBox rookPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The continue & return button. */
    Button cont, returnButton;

    /**
     * Constructor for the rook tutorial GUI.
     */
    public RookTutorialGUI() {
        // Create a VBox
        rookPane = new VBox();
        rookPane.setId("main-pane");

        // get rook image and add to pane
        try{
            Image rook = new Image(new FileInputStream(
                    "src/gui/gameboard/images/WhiteRook.png"));
            ImageView rookView = new ImageView(rook);
            rookView.setFitHeight(100);
            rookView.setFitWidth(100);
            rookPane.getChildren().add(rookView);
        }catch(Exception e){
            System.out.println("Error: File not found.");
        }

        // Create a text object
        Text text = new Text("""
                Rooks are considered to be one of the better pieces in chess, and is why \s
                a player is only provided with two of them. Rooks can move horizontally or \s
                vertically in any direction, as long as there are no pieces in the way. Rooks \s
                can capture pieces by moving to the square that the opposing piece is on.\s
                                
                Click continue whenever you're ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        rookPane.setAlignment(Pos.CENTER);
        rookPane.getChildren().add(text);

        // create continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.PIECE_TUTORIAL));
        rookPane.getChildren().add(cont);
        //TODO implement menu to make rook tutorial

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        rookPane.getChildren().add(returnButton);

        // get css
        rookPane.setSpacing(20);
        rookPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the check pane.
     *
     * @return VBox the check pane
     */
    public VBox getRoot() { return rookPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }
}
