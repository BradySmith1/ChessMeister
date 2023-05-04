package gui.viewplayed;

import interfaces.ScreenChangeHandlerIF;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    Button game1, game2;

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

        // return to main menu button
        Button mainMenu = new Button("Return to Main Menu");
        mainMenu.setId("bottom-button");

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




}
