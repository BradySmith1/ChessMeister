package gui.playernames;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class PlayerNamesGUI extends BorderPane {

    /** The playerNamesPane pane. */
    BorderPane playerNamesPane;

    /** Scene */
    private Scene scene;

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** Buttons for the menu **/
    Button play, exit;

    /**
     * Constructor for the player names GUI.
     */
    public PlayerNamesGUI(){
        // Create a border pane
        this.playerNamesPane = new BorderPane();

        // set parts of the pane
        HBox top = makeTop();
        top.setId("main-pane");
        this.playerNamesPane.setTop(top);

        VBox center = makeCenter();
        center.setId("main-pane");
        this.playerNamesPane.setCenter(center);

        AnchorPane bottom = makeBottom();
        bottom.setId("main-pane");
        this.playerNamesPane.setBottom(bottom);

        // set center alignments
        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setAlignment(center, Pos.CENTER);
        BorderPane.setAlignment(bottom, Pos.CENTER);

        // Get stylesheet
        this.playerNamesPane.getStylesheets().add(
                getClass().getResource("PlayerNames.css").toExternalForm());
    }
    /**
     * Getter for the scene.
     * @return the scene
     */
    public Pane getRoot(){ return this.playerNamesPane;}

    /**
     * Makes the top pane for the player names menu.
     *
     * @return the top pane
     */
    private HBox makeTop(){
        HBox top = new HBox();
        Label topLabel = new Label("Enter Player Names");
        topLabel.setId("name-menu-text");
        top.getChildren().add(topLabel);
        top.setAlignment(Pos.CENTER);
        return top;
    }

    /**
     * Makes the center pane for the player names menu.
     *
     * @return the center pane
     */
    private VBox makeCenter(){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        // create and set ids for labels
        Label player1Label = new Label("Player 1 name");
        Label player2Label = new Label("Player 2 name");
        player1Label.setId("sub-menu-text");
        player2Label.setId("sub-menu-text");

        // create and set ids for text fields
        TextField player1Name = new TextField();
        TextField player2Name = new TextField();
        player1Name.setId("text-box");
        player2Name.setId("text-box");
        player1Name.setPromptText("Enter name here!");
        player2Name.setPromptText("Enter name here!");

        vBox.getChildren().addAll(player1Label, player1Name, player2Label, player2Name);

        return vBox;
    }

    /**
     * Makes the bottom pane for the player names menu.
     *
     * @return the bottom pane
     */
    private AnchorPane makeBottom(){
        //create 2 buttons and set ids
        this.play = new Button("Play");
        this.exit = new Button("Return to Main Menu");
        play.setId("bottom-button");
        exit.setId("bottom-button");

        // Set button actions
        play.setOnAction(buttonHandler);
        exit.setOnAction(buttonHandler);

        // Create the anchor pane
        AnchorPane ap = new AnchorPane();

        // Set AnchorPane constraints
        AnchorPane.setBottomAnchor(play, 10.0);
        AnchorPane.setLeftAnchor(play, 10.0);

        AnchorPane.setBottomAnchor(exit, 10.0);
        AnchorPane.setRightAnchor(exit, 10.0);

        // Add buttons to Anchor Pane
        ap.getChildren().addAll(play,exit);

        return ap;
    }

    /**
     * Set the handler for screen changes
     *
     * @param sch The ScreenChangeHandler
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch){
        this.screenChanger = sch;
    }

    /** Event Handler for buttons **/
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if (screenChanger != null){
                Object source = event.getSource();

                if (source == play){
                    System.out.println("Play button pressed");
                } else if (source == exit) {
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                }
            }
        }
    };
}
