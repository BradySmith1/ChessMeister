/**
 * This class is the GUI implementation for the tutorial menu.
 *
 * @author Zach Eanes (100%)
 */
package gui.Tutorial;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class TutorialMenuGUI extends BorderPane {
    /** The main menu pane. */
    BorderPane tutorialPane;

    /** Scene */
    private Scene scene;

    /** The instance of the tutorial menu GUI. */
    private static TutorialMenuGUI instance;

    /**
     * Gets the instance of the tutorial menu GUI. Uses the singleton design pattern.
     */
    public static TutorialMenuGUI getInstance(){
        if(instance == null){
            instance = new TutorialMenuGUI();
        }
        return instance;
    }

    /**
     * Constructor for the tutorial menu GUI.
     */
    private TutorialMenuGUI() {
        // Create a border pane and add title to top
        this.tutorialPane = new BorderPane();
        Label title = new Label("Tutorials");
        title.setId("topLabel");
        this.tutorialPane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        // Create buttons for the center
        VBox center = makeCenter();
        this.tutorialPane.setCenter(center);


        this.scene = new Scene(this.tutorialPane);

        this.scene.getStylesheets().add(
                   getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Creates the center scene for the tutorial menu.
     *
     * @return VBox the center scene
     */
    private VBox makeCenter(){
        // Create buttons for vbox one
        Button setup = new Button("Board Setup");
        Button notation = new Button("Understanding Notation");
        Button organization = new Button("Board Organization");
        Button check = new Button("Check");
        Button checkmate = new Button("Checkmate");
        Button draw = new Button("Draw");
        Button returnButton = new Button("Return to Main Menu");

        // Create buttons for vbox two
        Button king = new Button("King Moves");
        Button queen = new Button("Queen Moves");
        Button bishop = new Button("Bishop Moves");
        Button knight = new Button("Knight Moves");
        Button rook = new Button("Rook Moves");
        Button pawn = new Button("Pawn Moves");

        // Set button ids
        setup.setId("menu-button");
        notation.setId("menu-button");
        organization.setId("menu-button");
        check.setId("menu-button");
        checkmate.setId("menu-button");
        draw.setId("menu-button");
        king.setId("menu-button");
        queen.setId("menu-button");
        bishop.setId("menu-button");
        knight.setId("menu-button");
        rook.setId("menu-button");
        pawn.setId("menu-button");
        returnButton.setId("bottom-button");

        // Center vbox's and center
        VBox vb1 = new VBox();
        VBox vb2 = new VBox();
        vb1.getChildren().addAll(setup, notation, organization, check, checkmate, draw);
        vb2.getChildren().addAll(king, queen, bishop, knight, rook, pawn);

        vb1.setAlignment(Pos.CENTER);
        vb1.setSpacing(10);
        vb2.setAlignment(Pos.CENTER);
        vb2.setSpacing(10);

        // Create hbox and center
        HBox hb = new HBox();
        hb.getChildren().addAll(vb1, vb2);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        // Create center vbox and center
        VBox center = new VBox();
        center.getChildren().addAll(hb, returnButton);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(20);

        return center;
    }

    /**
     * Returns the scene.
     */
    public Scene getMenu(){ return this.scene;}
}