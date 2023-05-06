/**
 * This class is responsible for creating the game board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
package gui.gameboard;

import enums.ToScreen;
import gui.playernames.PlayerNamesGUI;
import gui.settingsmenu.SettingsMenuGUI;
import interfaces.PieceIF;
import interfaces.PlayerIF;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import model.Player;

public class GameBoardGUI implements CenterPaneObserver{

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

    private SettingsMenuGUI settings;

    private PlayerIF player1;

    private PlayerIF player2;

    /**
     * Constructor for the game board GUI.
     */
    public GameBoardGUI() {
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

        // Add the observers
        center.addObserver(this);

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
        this.screenChanger = sch;
        top.setScreenChangeHandler(this.screenChanger);
        bottom.setScreenChangeHandler(this.screenChanger);

        // Get settings and get players
        this.settings = getSettings();
        this.player1 = this.getPlayer().getPlayer().getPlayer1();
        this.player2 = this.getPlayer().getPlayer().getPlayer2();
        this.player1.assignPieces(center);
        this.player2.assignPieces(center);
        this.left.setLabel(this.player1.getName());
        this.right.setLabel(this.player2.getName());

        System.out.println(this.settings.getSettings().getShowMoves());
        System.out.println(this.settings.getSettings().getUndoRedo());
        System.out.println(this.player1.getName());
        System.out.println(this.player2.getName());

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
        System.out.println("SHOW THE WHITE: " + this.settings.getSettings().getWhiteSquareColor());
        System.out.println("SHOW THE BLACK: " + this.settings.getSettings().getBlackSquareColor());
        System.out.println("SHOW THE HIGHLIGHT: " + this.settings.getSettings().getHighlightColor());

        this.center.setHighlightColor(this.settings.getSettings().getHighlightColor());
        //this.top.setShowMoves(this.settings.getSettings().getShowMoves());
        System.out.println("UPDATE SETTINGS: " + this.settings.getSettings().getUndoRedo());
        this.top.setShowUndoRedo(this.settings.getSettings().getUndoRedo());

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

    /**
     * Notifies the observer that the pane has been updated.
     */
    @Override
    public void notifyPane() {
        this.screenChanger.notifyBoard();
    }

    /**
     * Notifies the observer that a piece has been captured.
     * @param piece the piece that was captured
     */
    public void notifyAddCapturedPiece(PieceIF piece){
        if (piece.getColor() == this.player1.getColor()){
            this.player2.addCapturedPiece(piece);
        }
        else{
            this.player1.addCapturedPiece(piece);
        }
        this.left = new LeftPaneGUI();
        this.right = new RightPaneGUI();
    }
}
