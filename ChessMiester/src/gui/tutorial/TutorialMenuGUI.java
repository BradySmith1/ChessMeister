/**
 * This class is the GUI implementation for the tutorial menu.
 *
 * @author Zach Eanes (100%)
 */
package gui.tutorial;

import enums.ToScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.text.Text;

public class TutorialMenuGUI extends BorderPane {
    /** The main menu pane. */
    BorderPane tutorialPane;

    /** Scene */
    private Scene scene;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** Buttons for the menu **/
    Button setup, notation, organization, check, checkmate, draw, returnButton,
           king, queen, bishop, knight, rook, pawn;

    /**
     * Constructor for the tutorial menu GUI.
     */
    public TutorialMenuGUI() {
        // Create a border pane
        this.tutorialPane = new BorderPane();

        // Create label and buttons for the center
        VBox center = makeCenter();
        this.tutorialPane.setCenter(center);
        this.tutorialPane.setId("main-pane");

        this.tutorialPane.getStylesheets().add(
                   getClass().getResource("TutorialMenu.css").toExternalForm());
    }

    /**
     * Creates the center scene for the tutorial menu.
     *
     * @return VBox the center scene
     */
    private VBox makeCenter(){
        // Create buttons for vbox one
        this.setup = new Button("Board Setup");
        this.notation = new Button("Understanding Notation");
        this.organization = new Button("Board Organization");
        this.check = new Button("Check");
        this.checkmate = new Button("Checkmate");
        this.draw = new Button("Draw");
        this.returnButton = new Button("Return to Main Menu");

        // Create buttons for vbox two
        this.king = new Button("King Moves");
        this.queen = new Button("Queen Moves");
        this.bishop = new Button("Bishop Moves");
        this.knight = new Button("Knight Moves");
        this.rook = new Button("Rook Moves");
        this.pawn = new Button("Pawn Moves");

        // Set button actions
        setup.setOnAction(buttonHandler);
        notation.setOnAction(buttonHandler);
        organization.setOnAction(buttonHandler);
        check.setOnAction(buttonHandler);
        checkmate.setOnAction(buttonHandler);
        draw.setOnAction(buttonHandler);
        returnButton.setOnAction(buttonHandler);
        king.setOnAction(buttonHandler);
        queen.setOnAction(buttonHandler);
        bishop.setOnAction(buttonHandler);
        knight.setOnAction(buttonHandler);
        rook.setOnAction(buttonHandler);
        pawn.setOnAction(buttonHandler);

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

        // Create center vbox and center elements
        VBox center = new VBox();
        Label title = new Label("Tutorials");
        title.setId("topLabel");
        title.setAlignment(Pos.CENTER);
        center.getChildren().addAll(title, hb, returnButton);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(20);

        return center;
    }

    /**
     * Returns the scene.
     */
    public Pane getRoot(){ return this.tutorialPane;}

    /**
     * Set the handler for screen changes
     * @param sch The ScreenChangeHandler
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch){
        this.screenChanger = sch;
    }

    /** Event Handler for buttons **/
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        /**
         * Handles the button events.
         *
         * @param event the event to handle
         */
        @Override
        public void handle(ActionEvent event) {
            if (screenChanger != null){
                Object source = event.getSource();

                if (source == setup) {
                    screenChanger.changeScreen(ToScreen.BOARD_SETUP);
                } else if (source == notation) {
                    screenChanger.changeScreen(ToScreen.CHESS_NOTATION);
                } else if (source == organization) {
                    screenChanger.changeScreen(ToScreen.BOARD_ORGANIZATION);
                } else if (source == check) {
                    screenChanger.changeScreen(ToScreen.CHECK);
                } else if (source == checkmate) {
                    screenChanger.changeScreen(ToScreen.CHECKMATE);
                } else if (source == draw) {
                    screenChanger.changeScreen(ToScreen.DRAW);
                } else if (source == king) {
                    System.out.println("King button pressed");
                } else if (source == queen) {
                    System.out.println("Queen button pressed");
                } else if (source == bishop) {
                    System.out.println("Bishop button pressed");
                } else if (source == knight) {
                    System.out.println("Knight button pressed");
                } else if (source == rook) {
                    System.out.println("Rook button pressed");
                } else if (source == pawn) {
                    System.out.println("Pawn button pressed");
                } else if (source == returnButton) {
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                }
            }
        }
    };
}