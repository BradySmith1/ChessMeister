package gui.PlayerNames;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.awt.*;

public class PlayerNamesGUI extends BorderPane {

    /** The playerNamesPane pane. */
    BorderPane playerNamesPane;

    /** Scene */
    private Scene scene;

    private static gui.PlayerNames.PlayerNamesGUI instance;

    /**
     * Constructor for the main menu GUI.
     */
    public static gui.PlayerNames.PlayerNamesGUI getInstance(){
        if(instance == null){
            instance = new gui.PlayerNames.PlayerNamesGUI();
        }
        return instance;
    }


    private PlayerNamesGUI(){
        // Create a border pane
        this.playerNamesPane = new BorderPane();

        // set parts of the pane
        Label top = makeTop();
        this.playerNamesPane.setTop(top);

        VBox center = makeCenter();
        this.playerNamesPane.setCenter(center);

        AnchorPane bottom = makeBottom();
        this.playerNamesPane.setBottom(bottom);

        // set center alignments
        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setAlignment(center, Pos.CENTER);


        // Create a scene object
        this.scene = new Scene(playerNamesPane);

        // Get stylesheet
        this.scene.getStylesheets().add(
                getClass().getResource("PlayerNames.css").toExternalForm());
    }
    /**
     * Getter for the scene.
     * @return the scene
     */
    public Scene getMenu(){ return this.scene;}

    private Label makeTop(){
        Label topLabel = new Label("Enter Player Names");
        topLabel.setId("name-menu-text");
        return topLabel;
    }

    private VBox makeCenter(){
        VBox vBox = new VBox();

        Label player1Label = new Label("Player 1 name");
        Label player2Label = new Label("Player 2 name");

        TextField player1Name = new TextField();
        TextField player2Name = new TextField();

        vBox.getChildren().addAll(player1Label, player1Name, player2Label, player2Name);
        return vBox;
    }

    private AnchorPane makeBottom(){
        //create 2 buttons and set ids
        javafx.scene.control.Button play = new javafx.scene.control.Button("Play");
        javafx.scene.control.Button exit = new Button("Exit");
        play.setId("bottom-button");
        exit.setId("bottom-button");

        //create anchor pane and add buttons
        AnchorPane ap = new AnchorPane(play, exit);

        //anchor the settings button
        AnchorPane.setBottomAnchor(play, 10.0);

        //anchor the exit button
        AnchorPane.setRightAnchor(exit, 10.0);
        AnchorPane.setRightAnchor(exit, 10.0);

        return ap;
    }
}
