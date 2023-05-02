/**
 * This class is the GUI implementation for the main menu.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.MainMenu;

import interfaces.MainMenuIF;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainMenuGUI extends BorderPane {

    /** The main menu pane. */
    BorderPane mainMenuPane;

    /** Scene for the main menu. */
    private Scene scene;

    private static MainMenuGUI instance;

    /**
     * Constructor for the main menu GUI.
     */
    public static MainMenuGUI getInstance(){
        if(instance == null){
            instance = new MainMenuGUI();
        }
        return instance;
    }


    private MainMenuGUI(){
        // Create a border pane
        this.mainMenuPane = new BorderPane();

        // set parts of the pane
        Label top = makeTop();
        this.mainMenuPane.setTop(top);

        VBox center = makeCenter();
        this.mainMenuPane.setCenter(center);

        ImageView left = makeLeft();
        this.mainMenuPane.setLeft(left);

        ImageView right = makeRight();
        this.mainMenuPane.setRight(right);

        AnchorPane bottom = makeBottom();
        this.mainMenuPane.setBottom(bottom);

        // set center alignments
        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setAlignment(center, Pos.CENTER);
        BorderPane.setAlignment(left, Pos.CENTER);
        BorderPane.setAlignment(right,Pos.CENTER);

        // Create a scene object
        this.scene = new Scene(mainMenuPane);

        // Get stylesheet
        this.scene.getStylesheets().add(
                   getClass().getResource("MainMenu.css").toExternalForm());
    }
    /**
     * Getter for the scene.
     * @return the scene
     */
    public Scene getMenu(){ return this.scene; }

    /**
     * Method that creates the top component of the main menu
     */
    private Label makeTop(){
        Label topLabel = new Label("Chess Meister");
        topLabel.setId("topLabel");
        return topLabel;
    }

    private ImageView makeLeft(){
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("gui/MainMenu/img/KingPiece.png"));
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private ImageView makeRight(){
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("gui/MainMenu/img/KingPiece.png"));
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    /**
     * This method is used to create the bottom section of our main menu.
     */
    private AnchorPane makeBottom(){
        //create 2 buttons and set ids
        Button settings = new Button("Settings");
        Button exit = new Button("Exit");
        settings.setId("bottom-button");
        exit.setId("bottom-button");

        //create anchor pane and add buttons
        AnchorPane ap = new AnchorPane(settings, exit);

        //anchor the settings button
        AnchorPane.setBottomAnchor(settings, 10.0);
        AnchorPane.setLeftAnchor(settings, 10.0);

        //anchor the exit button
        AnchorPane.setRightAnchor(exit, 10.0);
        AnchorPane.setRightAnchor(exit, 10.0);

        return ap;
    }

    /**
     * Method that creates the center components of the main menu
     */
    private VBox makeCenter(){
        // Create buttons
        Button versusPlayer = new Button("Play Chess");
        Button definePlayers = new Button("Define Players");
        Button loadGame = new Button("Load Game");
        Button playedGames = new Button("View Played Games");
        Button tutorial = new Button("View Tutorials");

        // Set button ids
        versusPlayer.setId("menu-button");
        definePlayers.setId("menu-button");
        loadGame.setId("menu-button");
        playedGames.setId("menu-button");
        tutorial.setId("menu-button");

        // Create a vbox and add buttons
        VBox centerBox = new VBox();
        centerBox.setSpacing(10);
        centerBox.getChildren().addAll(versusPlayer, definePlayers,
                                       loadGame, playedGames, tutorial);

        // Center buttons in Vbox
        centerBox.setAlignment(javafx.geometry.Pos.CENTER);
        return centerBox;
    }
}
