/**
 * This class is the GUI implementation for the main menu.
 *
 * @author Zach Eanes (50%), Kaushal Patel (50%)
 */
package gui.mainmenu;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainMenuGUI extends BorderPane {

    /** Reference to the implementation for the ScreenChangeHandlerIF **/
    ScreenChangeHandlerIF screenChanger;

    /** The main menu pane. */
    BorderPane mainMenuPane;

    /** Scene for the main menu. */
    private Scene scene;

    /** Buttons for the menu **/
    Button versusPlayer, definePlayers, loadGame, playedGames, tutorial, exit, settings;

    /**
     * Constructor for the main menu GUI.
     */
    public MainMenuGUI(){
        // Create a border pane
        this.mainMenuPane = new BorderPane();

        // set parts of the pane
        HBox top = makeTop();
        top.setId("main-pane");
        this.mainMenuPane.setTop(top);

        VBox center = makeCenter();
        center.setId("main-pane");
        this.mainMenuPane.setCenter(center);

        VBox left = makeLeft();
        left.setId("main-pane");
        this.mainMenuPane.setLeft(left);

        VBox right = makeRight();
        right.setId("main-pane");
        this.mainMenuPane.setRight(right);

        AnchorPane bottom = makeBottom();
        bottom.setId("main-pane");
        this.mainMenuPane.setBottom(bottom);

        // set center alignments
        BorderPane.setAlignment(top, Pos.CENTER);
        BorderPane.setAlignment(center, Pos.CENTER);
        BorderPane.setAlignment(left, Pos.CENTER);
        BorderPane.setAlignment(right,Pos.CENTER);

        // Get stylesheet
        this.mainMenuPane.getStylesheets().add(
                   getClass().getResource("MainMenu.css").toExternalForm());
    }
    /**
     * Getter for the scene.
     * @return the scene
     */
    public Pane getRoot(){ return this.mainMenuPane; }

    /**
     * Method that creates the top component of the main menu
     */
    private HBox makeTop(){
        HBox top = new HBox();
        Label topLabel = new Label("Welcome to Chess Meister!");
        topLabel.setId("topLabel");
        top.getChildren().add(topLabel);
        top.setAlignment(Pos.CENTER);
        return top;
    }

    private VBox makeLeft(){
        VBox left = new VBox();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("gui/mainmenu/img/KingPiece.png"));
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        left.getChildren().add(imageView);
        left.setAlignment(Pos.CENTER_LEFT);
        return left;
    }

    private VBox makeRight(){
        VBox right = new VBox();
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("gui/mainmenu/img/KingPiece.png"));
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        right.getChildren().add(imageView);
        right.setAlignment(Pos.CENTER_RIGHT);
        return right;
    }

    /**
     * This method is used to create the bottom section of our main menu.
     */
    private AnchorPane makeBottom(){
        //create 2 buttons and set ids
        this.settings = new Button("Settings");
        this.exit = new Button("Exit");
        this.settings.setId("bottom-button");
        this.exit.setId("bottom-button");

        // Set on action for the two buttons
        this.settings.setOnAction(buttonHandler);
        this.exit.setOnAction(buttonHandler);

        //create anchor pane and add buttons
        AnchorPane ap = new AnchorPane(this.settings, this.exit);

        //anchor the settings button
        AnchorPane.setBottomAnchor(this.settings, 10.0);
        AnchorPane.setLeftAnchor(this.settings, 10.0);

        //anchor the exit button
        AnchorPane.setRightAnchor(this.exit, 10.0);
        AnchorPane.setRightAnchor(this.exit, 10.0);

        return ap;
    }

    /**
     * Method that creates the center components of the main menu
     */
    private VBox makeCenter(){
        // Create buttons
        this.versusPlayer = new Button("Play Chess");
        this.definePlayers = new Button("Define Players");
        this.loadGame = new Button("Load Game");
        this.playedGames = new Button("View Played Games");
        this.tutorial = new Button("View Tutorials");

        // Set button ids
        this.versusPlayer.setId("menu-button");
        this.definePlayers.setId("menu-button");
        this.loadGame.setId("menu-button");
        this.playedGames.setId("menu-button");
        this.tutorial.setId("menu-button");

        // Set on actions for the buttons
        this.versusPlayer.setOnAction(buttonHandler);
        this.definePlayers.setOnAction(buttonHandler);
        this.loadGame.setOnAction(buttonHandler);
        this.playedGames.setOnAction(buttonHandler);
        this.tutorial.setOnAction(buttonHandler);


        // Create a vbox and add buttons
        VBox centerBox = new VBox();
        centerBox.setSpacing(10);
        centerBox.getChildren().addAll(this.versusPlayer, this.definePlayers,
                this.loadGame, this.playedGames, this.tutorial);

        // Center buttons in Vbox
        centerBox.setAlignment(javafx.geometry.Pos.CENTER);
        return centerBox;
    }

    /**
     * Set the handler for screen changes
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

                if (source == versusPlayer){
                    screenChanger.changeScreen(null);
                } else if (source == definePlayers){
                    screenChanger.changeScreen(ToScreen.PLAYER_NAMES);
                } else if (source == loadGame) {
                    screenChanger.changeScreen(ToScreen.LOAD_GAME);
                } else if (source == playedGames) {
                    screenChanger.changeScreen(ToScreen.VIEW_PLAYED);
                } else if (source == tutorial) {
                    screenChanger.changeScreen(ToScreen.TUTORIAL_MENU);
                } else if (source == settings) {
                    screenChanger.changeScreen(ToScreen.SETTINGS_MENU);
                } else if (source == exit) {
                    System.exit(1);
                }
            }
        }
    };
}