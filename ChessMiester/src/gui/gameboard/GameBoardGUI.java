package gui.gameboard;

import enums.ToScreen;
import gui.playernames.PlayerNamesGUI;
import gui.settingsmenu.SettingsMenuGUI;
import gui_backend.DefinePlayersGUI;
import gui_backend.SquareGUI;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.Square;

public class GameBoardGUI{

    private TopPaneGUI top;

    private BottomPaneGUI bottom;

    private LeftPaneGUI left;

    private RightPaneGUI right;

    private CenterPaneGUI center;

    private BorderPane root;

    ScreenChangeHandlerIF screenChanger;

    private SettingsMenuGUI settings;

    private  PlayerNamesGUI player;

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

        // Get settings and get players
        this.settings = getSettings();
        this.player = getPlayer();

        System.out.println(this.settings.getSettings().getShowMoves());
        System.out.println(this.settings.getSettings().getUndoRedo());
        System.out.println(this.player.getPlayer().getPlayer1Name());
        System.out.println(this.player.getPlayer().getPlayer2Name());

        this.screenChanger.notifyBoard();


    }

    private SettingsMenuGUI getSettings(){
        return (SettingsMenuGUI) this.screenChanger.getGuiScene(ToScreen.SETTINGS_MENU);
    }

    private PlayerNamesGUI getPlayer(){
        return (PlayerNamesGUI) this.screenChanger.getGuiScene(ToScreen.PLAYER_NAMES);
    }


    /**
     * Updates the settings.
     */
    public void updateSettings(){
        this.settings = getSettings();
        System.out.println("SHOW MOVES: " + this.settings.getSettings().getShowMoves());
        System.out.println("SHOW UNDO/REDO: " + this.settings.getSettings().getUndoRedo());
        System.out.println("SHOW THE WHITEY: " + this.settings.getSettings().getWhiteSquareColor());
        System.out.println("SHOW THE BLACKY: " + this.settings.getSettings().getBlackSquareColor());

        updateBoard();
    }

    private void updateBoard(){
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if ((row + col) % 2 == 0){
                    this.center.getSquares()[row][col].setColor(this.settings.getSettings().getWhiteSquareColor());
                }
                else{
                    this.center.getSquares()[row][col].setColor(this.settings.getSettings().getBlackSquareColor());
                }
            }
        }
    }
}
