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
 * This class is responsible for the GUI implementation for the pawn tutorial.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
public class PawnTutorialGUI {
    /** The pawn tutorial pane. */
    VBox pawnPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The continue & return button. */
    Button cont, returnButton;

    /**
     * Constructor for the pawn tutorial GUI.
     */
    public PawnTutorialGUI() {
        // Create a VBox
        pawnPane = new VBox();
        pawnPane.setId("main-pane");

        // Add image to pawn
        this.addImage();

        // Create a text object
        Text text = new Text("""
                Pawns are considered to be the least powerful piece in chess, and is why a \s
                player is provided with eight of them. Pawns can only move forward, and \s
                can only capture diagonally. Pawns can move two spaces on their first move, \s
                but only one space on all subsequent moves (unless blocked by another piece \s
                of course). Pawns can promote to a queen, rook, bishop, or knight if they \s
                reach the other players end of the board.\s

                Pawns can also perform an en passant capture, which is a special move that \s
                can only be performed if the pawn is on the 5th rank and the opposing pawn is \s
                on the 4th rank. The pawn can only capture the opposing pawn if it moves \s
                diagonally one space forward and lands on the square that the opposing pawn \s
                just moved to.\s
                                
                Click continue whenever you're ready to try it out!\s\s
                """);
        text.setId("tutorialText");
        pawnPane.setAlignment(Pos.CENTER);
        pawnPane.getChildren().add(text);

        // create continue button
        cont = new Button("Continue");
        cont.setId("menu-button");
        cont.setOnAction(e -> screenChanger.changeScreen(ToScreen.PIECE_TUTORIAL));
        pawnPane.getChildren().add(cont);
        //TODO implement menu to make pawn tutorial

        // create back button
        returnButton = new Button("Return");
        returnButton.setId("bottom-button");
        returnButton.setOnAction(e -> screenChanger.changeScreen(ToScreen.TUTORIAL_MENU));
        pawnPane.getChildren().add(returnButton);

        // get css
        pawnPane.setSpacing(20);
        pawnPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Returns the check pane.
     *
     * @return VBox the check pane
     */
    public VBox getRoot() { return pawnPane; }

    /**
     * Sets the screen changer.
     *
     * @param sch the screen changer
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }

    /**
     * Adds the pawn image to the pane.
     */
    public void addImage(){
        // get pawn image and add to pane
        try{
            Image pawn = new Image(new FileInputStream(
                    "src/gui/gameboard/images/WhitePawn.png"));
            ImageView pawnView = new ImageView(pawn);
            pawnView.setFitHeight(100);
            pawnView.setFitWidth(100);
            pawnPane.getChildren().add(pawnView);
        }catch(Exception e){
            System.out.println("Error: File not found.");
        }
    }
}
