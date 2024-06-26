package gui.tutorial;

import enums.Files;
import enums.Rank;
import enums.ToScreen;
import gui.gameboard.CenterPaneGUI;
import gui.gameboard.GameBoardObserver;
import gui_backend.SquareGUI;
import interfaces.PieceIF;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import model.Position;
import tutorialuicli.NotationCLI;

import java.io.PrintWriter;
import java.util.List;

/**
 * This is responsible for creating the loop to let a player take a quiz
 * about the chess notation.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 3)
 */
public class NotationQuizGUI implements GameBoardObserver {
    /** Pane for the quiz tutorial gui */
    BorderPane quizPane;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** Button for the piece tutorial gui */
    Button returnButton;

    /** Location of where to click */
    String location;

    /**
     * Constructor for the notation quiz gui.
     */
    public NotationQuizGUI() {
        // Create a BorderPane
        quizPane = new BorderPane();
        quizPane.setId("main-pane");

        // create and assign board
        quizPane.setCenter(makeCenter().getRoot());

        // create and assign buttons
        quizPane.setBottom(makeBottom());

        // make top to match style
        HBox top = makeTop();
        top.setId("main-pane");
        quizPane.setTop(top);

        // make sides
        VBox left = makeSide();
        VBox right = makeSide();
        quizPane.setLeft(left);
        quizPane.setRight(right);
        BorderPane.setAlignment(left, Pos.CENTER);
        BorderPane.setAlignment(right, Pos.CENTER);

        this.quizPane.getStylesheets().add(
                getClass().getResource("TutorialMenu.css").toExternalForm());

    }

    /**
     * This method creates the top pane for the piece tutorial gui.
     *
     * @return Pane for the top
     */
    private HBox makeTop(){
        // create an HBox
        HBox hb = new HBox();
        hb.setId("top");
        hb.setAlignment(Pos.CENTER);

        // generate a random location
        NotationCLI notation = new NotationCLI();
        Files randFile = notation.getRandomFile();
        Rank randRank = notation.getRandomRank();

        this.location = randFile.toString() + randRank.toString().substring(1);

        // create a label telling user where to click
        Label text = new Label("Click on " + location + "!");
        text.setId("topLabel");

        // add label to HBox
        hb.getChildren().add(text);
        hb.setId("main-pane");

        return hb;
    }

    /**
     * This method returns the center pane for the piece tutorial gui.
     *
     * @return the center pane
     */
    private CenterPaneGUI makeCenter(){
        // initialize the center pane
        CenterPaneGUI center = new CenterPaneGUI();
        center.setup();

        // iterate squares and add observers
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                center.getSquares()[i][j].addObserver(this);
            }
        }

        return center;
    }

    /**
     * This method creates the side panes for the piece tutorial gui.
     *
     * @return VBox for the sides
     */
    private VBox makeSide(){
        // create vbox
        VBox vb = new VBox();
        vb.setId("main-pane");
        vb.setAlignment(Pos.CENTER);

        // create rank identifiers
        Label rank1 = new Label("1");
        Label rank2 = new Label("2");
        Label rank3 = new Label("3");
        Label rank4 = new Label("4");
        Label rank5 = new Label("5");
        Label rank6 = new Label("6");
        Label rank7 = new Label("7");
        Label rank8 = new Label("8");
        rank1.setId("rank");
        rank2.setId("rank");
        rank3.setId("rank");
        rank4.setId("rank");
        rank5.setId("rank");
        rank6.setId("rank");
        rank7.setId("rank");
        rank8.setId("rank");

        // add rank identifiers to vbox
        vb.getChildren().addAll(rank8, rank7, rank6, rank5, rank4, rank3, rank2, rank1);
        vb.setSpacing(60);
        vb.setMinWidth(100);
        return vb;
    }


    /**
     * This method creates the bottom pane for the piece tutorial gui.
     *
     * @return HBox
     */
    private VBox makeBottom(){
        // create HBox for buttons
        HBox hb = new HBox();
        hb.setSpacing(20);
        hb.setId("bottom");

        // create all file identifiers
        Label fileA = new Label("A");
        Label fileB = new Label("B");
        Label fileC = new Label("C");
        Label fileD = new Label("D");
        Label fileE = new Label("E");
        Label fileF = new Label("F");
        Label fileG = new Label("G");
        Label fileH = new Label("H");
        fileA.setId("file");
        fileB.setId("file");
        fileC.setId("file");
        fileD.setId("file");
        fileE.setId("file");
        fileF.setId("file");
        fileG.setId("file");
        fileH.setId("file");

        // create buttons and add them to the HBox
        returnButton = new Button("Return to Tutorial Menu");
        returnButton.setOnAction(e -> { screenChanger.changeScreen(ToScreen.TUTORIAL_MENU); });
        returnButton.setId("bottom-button");

        // add elements to HBox
        hb.getChildren().addAll(fileA, fileB, fileC, fileD, fileE, fileF, fileG, fileH);
        hb.setAlignment(Pos.CENTER);
        hb.setId("main-pane");
        hb.setSpacing(135);

        // add to vbox
        VBox vb = new VBox();
        vb.getChildren().addAll(hb, returnButton);
        vb.setAlignment(Pos.CENTER);
        vb.setId("main-pane");
        return vb;
    }

    /**
     * This method returns the piece tutorial gui.
     *
     * @return BorderPane
     */
    public BorderPane getRoot() { return quizPane; }

    /**
     * This method sets the screen changer for the piece tutorial gui.
     *
     * @param sch implementation of the ScreenChangeHandlerIF
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }


    /**
     * This method is called when the user clicks on a square.
     *
     * @param event the event that was fired
     */
    @Override
    public void notifyLeftClick(Event event) {
        SquareGUI square = (SquareGUI) event.getSource();

        String clicked = square.getPosition().getFile().toString() +
                         square.getPosition().getRank().toString().substring(1);

        // check if the user clicked on the correct square
        if (clicked.equals(location)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct!");
            alert.setHeaderText(null);
            alert.setContentText("You clicked on " + location + "!");
            alert.showAndWait();
            screenChanger.changeScreen(ToScreen.NOTATION_QUIZ);
            this.quizPane.setTop(makeTop());
        }
        // if the user clicked on the wrong square, tell them
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect!");
            alert.setHeaderText(null);
            alert.setContentText("You clicked on " + clicked + "!");
            alert.showAndWait();
        }

    }

    /**
     * This method is called when the user right clicks on a square.
     *
     * @param event the event that was fired
     */
    @Override
    public void notifyRightClick(Event event) {
        // ignore
    }

    /**
     * This method is called when the user hovers over a square.
     * @param event the event that occurred
     * @return a list of positions to highlight
     */
    @Override
    public List<Position> notifyPieceMoving(Event event) {
        return null;
    }

    /**
     * This method is called to add captured pieces to the gui.
     * @param piece the piece that was captured.
     */
    @Override
    public void notifyAddCapturedPiece(PieceIF piece) {
    }

    /**
     * This method is called to notify the gui that the board has been reset.
     * @param event the event that was fired
     */
    @Override
    public void notifyBoardLoader(Event event) {
    }

    /**
     * This method is called to notify the gui that undo has been pressed.
     */
    @Override
    public void notifyUndo() {

    }

    /**
     * This method is called to notify the gui that redo has been pressed.
     */
    @Override
    public void notifyRedo() {

    }

    /**
     * This method is called to notify the gui that the game has been saved.
     * @param writer the writer to write to
     */
    @Override
    public void notifySaveGame(PrintWriter writer) {

    }


}
