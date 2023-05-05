package gui.gameboard;

import enums.ToScreen;
import gui.settingsmenu.SettingsMenuGUI;
import gui_backend.SquareGUI;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class GameBoardGUI{

    private TopPaneGUI top;

    private BottomPaneGUI bottom;

    private LeftPaneGUI left;

    private RightPaneGUI right;

    private CenterPaneGUI center;

    private BorderPane root;

    ScreenChangeHandlerIF screenChanger;

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

    public Pane getRoot() {
        return root;
    }

    public void setScreenChangeHandler(ScreenChangeHandlerIF sch){
        this.screenChanger = sch;
        top.setScreenChangeHandler(this.screenChanger);
        bottom.setScreenChangeHandler(this.screenChanger);

        // TODO
        System.out.println((this.getSettings().getSettings().getBlackSquareColor()));
        System.out.println((this.getSettings().getSettings().getWhiteSquareColor()));
        System.out.println((this.getSettings().getSettings().getUndoRedo()));
        System.out.println((this.getSettings().getSettings().getShowMoves()));

        // TODO Remove this
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if (row + col % 2 == 0) {
                    this.center.getSquares()[row][col].setStyle("-fx-background-color: " + this.getSettings().getSettings().getWhiteSquareColor() + ";");
                } else {
                    this.center.getSquares()[row][col].setStyle("-fx-background-color: " + this.getSettings().getSettings().getBlackSquareColor() + ";");
                }
            }
        }
    }

    private SettingsMenuGUI getSettings(){
        return (SettingsMenuGUI) this.screenChanger.getGuiScene(ToScreen.SETTINGS_MENU);
    }
}
