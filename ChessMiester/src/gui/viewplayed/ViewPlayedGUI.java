package gui.viewplayed;

import enums.ToScreen;
import interfaces.ScreenChangeHandlerIF;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class ViewPlayedGUI extends VBox {
    /**
     * Reference to the implementation for the ScreenChangeHandlerIF
     **/
    ScreenChangeHandlerIF screenChanger;

    /**
     * The main menu pane.
     */
    VBox loadGamePane;

    /**
     * Scene for the main menu.
     */
    private Scene scene;

    /**
     * Buttons for the menu
     **/
    Button game1, game2, mainMenu;

    /**
     * Constructor for the load games GUI.
     */
    public ViewPlayedGUI() {
        // Create a border pane
        this.loadGamePane = new VBox();

        // create title
        Label title = new Label("Select Game to Load");
        title.setId("topLabel");

        // create game buttons
        this.game1 = new Button("Scholar's Mate");
        this.game2 = new Button("Stalemate");
        this.game1.setId("menu-button");
        this.game2.setId("menu-button");
        this.game1.setOnAction(buttonHandler);
        this.game2.setOnAction(buttonHandler);

        // return to main menu button
        this.mainMenu = new Button("Return to Main Menu");
        this.mainMenu.setId("bottom-button");
        this.mainMenu.setOnAction(buttonHandler);

        // add title and buttons to pane
        this.loadGamePane.getChildren().addAll(title, game1, game2, mainMenu);
        this.loadGamePane.setId("main-pane");
        this.loadGamePane.setAlignment(Pos.CENTER);
        this.loadGamePane.setSpacing(20);

        // Get stylesheet
        this.loadGamePane.getStylesheets().add(
                getClass().getResource("LoadGame.css").toExternalForm());

    }

    /**
     * Returns the root of the load game GUI.
     *
     * @return the pane for the load game GUI
     */
    public Pane getRoot() { return this.loadGamePane; }

    /**
     * Sets the screen change handler for the load game GUI.
     *
     * @param sch the screen change handler to use
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch) { this.screenChanger = sch; }


    /** Event Handler for the buttons */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if( screenChanger != null){
                Object source = event.getSource();

                if(source == game1){
                    screenChanger.changeScreen(null);
                }
                else if(source == game2){
                    screenChanger.changeScreen(null);
                }
                else if(source == mainMenu){
                    screenChanger.changeScreen(ToScreen.MAIN_MENU);
                }
            }

        }
    };

}
