package gui.gameboard;

import interfaces.ScreenChangeHandlerIF;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class GameBoardGUI{

    TopPane top;

    BottomPane bottom;

    LeftPane left;

    RightPane right;

    CenterPane center;

    BorderPane root;

    ScreenChangeHandlerIF screenChanger;
    public GameBoardGUI(){
        super();

        root = new BorderPane();

        //Initialize the Panes.
        top = new TopPane();
        top.getRoot().setId("top");
        bottom = new BottomPane();
        bottom.getRoot().setId("bottom");
        left = new LeftPane();
        left.getRoot().setId("left");
        right = new RightPane();
        right.getRoot().setId("right");
        center = new CenterPane();
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

    public Pane getRoot() {
        return root;
    }

    public void setScreenChangeHandler(ScreenChangeHandlerIF sch){ this.screenChanger = sch; }
}
