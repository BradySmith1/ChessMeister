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
 * This class is responsible for the GUI implementation for the king tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
public class KingTutorialGUI {
    /** The king tutorial pane. */
    VBox kingPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The continue & return button. */
    Button cont, returnButton;

    /**
     * Constructor for the king tutorial GUI.
     */
    public KingTutorialGUI() {
        // Create a VBox
        kingPane = new VBox();
        kingPane.setId("main-pane");

        // Add King image
        this.addImage();

        // Create a text object
        Text text = new Text("""
                Kings are the most important piece in chess, and is why a player is only \s
                provided with one of them. Kings can move one square in any direction, \s
                as long as there are no pieces in the way. Kings can capture pieces by \s
                moving to the square that the opposing piece is on.\s\s
                            
               Kings are the life of the game, and if they are captured or placed into checkmate,\s
                which is explained in another section of the tutorial, the game is over.\s
                Kings can be put into check by an opposing piece, which another rule also \s
                explained in another section of the tutorial.\s\s
                            
                Click continue whenever you're ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        kingPane.setAlignment(Pos.CENTER);
        kingPane.getChildren().add(text);

        // create continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.PIECE_TUTORIAL));
        kingPane.getChildren().add(cont);
        //TODO implement menu to make king tutorial

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        kingPane.getChildren().add(returnButton);

        // get css
        kingPane.setSpacing(20);
        kingPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }



    /**
     * Returns the check pane.
     *
     * @return VBox the check pane
     */
    public VBox getRoot() { return kingPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }

    /**
     * Adds the king image to the pane.
     */
    private void addImage() {
        try{
            Image king = new Image(new FileInputStream(
                    "src/gui/gameboard/images/WhiteQueen.png"));
            ImageView kingView = new ImageView(king);
            kingView.setFitHeight(100);
            kingView.setFitWidth(100);
            kingPane.getChildren().add(kingView);
        }catch(Exception e){
            System.out.println("Error: File not found.");
        }
    }
}
