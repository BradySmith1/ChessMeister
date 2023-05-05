/**
 * This class is responsible for creating the game board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import interfaces.ScreenChangeHandlerIF;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class GameBoardGUI{

    /** the top pane */
    private TopPaneGUI top;

    /** the bottom pane */
    private BottomPaneGUI bottom;

    /** the left pane */
    private LeftPaneGUI left;

    /** the right pane */
    private RightPaneGUI right;

    /** the center pane */
    private CenterPaneGUI center;

    /** the root pane */
    private BorderPane root;

    /** the screen change handler */
    ScreenChangeHandlerIF screenChanger;

    /**
     * Constructor for the game board GUI.
     */
    public GameBoardGUI(){
        super();

        root = new BorderPane();

        //Initialize the Panes.
        top = new TopPaneGUI();
        top.getRoot().setId("top");
        bottom = new BottomPaneGUI();
        bottom.getRoot().setId("bottom");
        left = new LeftPaneGUI();
        left.getRoot().setId("left");
        right = new RightPaneGUI();
        right.getRoot().setId("right");
        center = new CenterPaneGUI();
        center.getRoot().setId("center");

        //add the panes to the root
        root.setTop(top.getRoot());
        root.setBottom(bottom.getRoot());
        root.setLeft(left.getRoot());
        root.setRight(right.getRoot());
        root.setCenter(center.getRoot());

        // add the stylesheet and images
        root.getStylesheets().add(getClass().getResource("gameBoard.css").toExternalForm());
    }

    /**
     * Returns the root pane.
     *
     * @return the root pane
     */
    public Pane getRoot() { return root; }

    /**
     * Sets the screen change handler.
     * @param sch the screen change handler
     */
    public void setScreenChangeHandler(ScreenChangeHandlerIF sch){
        top.setScreenChangeHandler(this.screenChanger);
        bottom.setScreenChangeHandler(this.screenChanger);
    }
}
