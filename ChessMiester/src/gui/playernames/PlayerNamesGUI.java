/**
 * This class is responsible for creating the player names GUI.
 *
 * @author Zach Eanes (60%), Kaushal Patel (40%)
 * @version 1.0 (done in sprint 3)
 */
package gui.playernames;

import enums.ToScreen;
import gui_backend.DefinePlayersGUI;
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
    Button exit, play;

    /** Text fields for the player names **/
    TextField player1Name, player2Name;

    /** DefinePlayersGUI object */
    private DefinePlayersGUI definePlayersGUI;

    /**
     * Constructor for the player names GUI.
     */
    public PlayerNamesGUI(){
        // Initialize the definePlayersGUI
        definePlayersGUI = new DefinePlayersGUI();

        // Create a border pane
        this.playerNamesPane = new BorderPane();

        // set parts of the pane
        VBox center = makeCenter();
        center.setId("main-pane");
        this.playerNamesPane.setCenter(center);

        // set center alignments
        BorderPane.setAlignment(center, Pos.CENTER);
        // Get stylesheet
        this.playerNamesPane.getStylesheets().add(
                getClass().getResource("PlayerNames.css").toExternalForm());
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
        Label title = new Label("Player Names");
        title.setId("name-menu-text");
        title.setAlignment(Pos.CENTER);
        Label player1Label = new Label("Player 1 Name");
        Label player2Label = new Label("Player 2 Name");
        player1Label.setId("sub-menu-text");
        player2Label.setId("sub-menu-text");

        // create and set ids for text fields
        this.createPlayerFields();


        // add elements to the vbox
        vBox.getChildren().addAll(title, player1Label, player1Name, player2Label, player2Name);
        vBox.getChildren().addAll(makeBottom());
        vBox.setSpacing(20);

        return vBox;
    }

    /**
     * Makes the bottom pane for the player names menu.
     *
     * @return the bottom pane
     */
    private VBox makeBottom(){
        //create 2 buttons and set ids
        this.exit = new Button("Return to Main Menu");
        this.play = new Button("Click to Play");
        play.setId("menu-button");
        exit.setId("bottom-button");

        // Set button actions
        exit.setOnAction(buttonHandler);
        play.setOnAction(buttonHandler);

        // Create the Vbox pane
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(20);
        vb.getChildren().addAll(play, exit);

        return vb;
    }

    /**
     * Set the handler for screen changes
     *
     * @param sch The ScreenChangeHandler
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch){
        this.screenChanger = sch;
    }

    /**
     * Getter for the scene.
     *
     * @return the scene
     */
    public Pane getRoot(){ return this.playerNamesPane;}

    /** Event Handler for buttons **/
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        /**
         * Handles the button events.
         *
         * @param event the event to be handled
         */
        @Override
        public void handle(ActionEvent event) {
            if (screenChanger != null){
                Object source = event.getSource();

                if (source == exit) {
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                } else if (source == play) {
                    if(!player1Name.getText().isEmpty()){
                        definePlayersGUI.setPlayer1Name(player1Name.getText());
                    }
                    else{
                        definePlayersGUI.setPlayer1Name("Player 1");
                    }
                    if(!player2Name.getText().isEmpty()){
                        definePlayersGUI.setPlayer2Name(player2Name.getText());
                    }
                    else{
                        definePlayersGUI.setPlayer2Name("Player 2");
                    }
                    screenChanger.changeScreen(ToScreen.GAME_BOARD, ToScreen.MAIN_MENU);
                }
            }
        }
    };

    private void createPlayerFields(){
        this.player1Name = new TextField();
        this.player2Name = new TextField();
        player1Name.setId("text-box");
        player2Name.setId("text-box");
        player1Name.setPromptText("Enter name here!");
        player2Name.setPromptText("Enter name here!");
    }

    /**
     * Getter for the DefinePlayersGUI object
     * @return the DefinePlayersGUI object
     */
    public DefinePlayersGUI getPlayer() {
    	return this.definePlayersGUI;
    }
}
