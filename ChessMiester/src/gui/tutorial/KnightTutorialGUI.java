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
 * This class is responsible for the GUI implementation for the knight tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
public class KnightTutorialGUI {
    /** The knight tutorial pane. */
    VBox knightPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The continue & return button. */
    Button cont, returnButton;

    /**
     * Constructor for the bishop knight GUI.
     */
    public KnightTutorialGUI() {
        // Create a VBox
        knightPane = new VBox();
        knightPane.setId("main-pane");

        // Add Knight image
        this.addImage();


        // Create a text object
        Text text = new Text("""
                Knights are considered to be one of the better piece in chess, and is why \s
                a player is only provided with two of them. Knights can move in an L shape, \s
                as long as there are no pieces in the way. Knights can capture pieces by \s
                moving to the square that the opposing piece is on.\s\s
                                    
                                
                Click continue whenever you're ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        knightPane.setAlignment(Pos.CENTER);
        knightPane.getChildren().add(text);

        // create continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.PIECE_TUTORIAL));
        knightPane.getChildren().add(cont);
        //TODO implement menu to make bishop tutorial

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        knightPane.getChildren().add(returnButton);

        // get css
        knightPane.setSpacing(20);
        knightPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the check pane.
     *
     * @return VBox the check pane
     */
    public VBox getRoot() { return knightPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }


    /**
     * Adds the knight image to the pane.
     */
    private void addImage(){
        // get knight image and add to pane
        try{
            Image knight = new Image(new FileInputStream(
                    "src/gui/gameboard/images/WhiteKnightLeft.png"));
            ImageView knightView = new ImageView(knight);
            knightView.setFitHeight(100);
            knightView.setFitWidth(100);
            knightPane.getChildren().add(knightView);
        }catch(Exception e){
            System.out.println("Error: File not found.");
        }
    }
}
