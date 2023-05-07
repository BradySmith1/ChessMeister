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

        //Initialize the root pane.
        root = new BorderPane();


        //Initialize the Panes.
        this.top = new TopPaneGUI();
        this.top.getRoot().setId("top");
        this.bottom = new BottomPaneGUI("Player 1");
        this.bottom.getRoot().setId("bottom");
        this.center = new CenterPaneGUI();
        this.center.getRoot().setId("center");

        // Add the observers
        center.addObserver(this);

        //add the panes to the root
        root.setTop(top.getRoot());
        root.setBottom(bottom.getRoot());
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

        // Set the players to the center pane
        this.center.setPlayer1(this.player1);
        this.center.setPlayer2(this.player2);

        // sets bottom pane to reflect p1 name
        this.bottom.updateBottomPane(this.player1.getName());

        left = new LeftPaneGUI(this.player1);
        left.getRoot().setId("left");
        right = new RightPaneGUI(this.player2);
        right.getRoot().setId("right");
        root.setLeft(left.getRoot());
        root.setRight(right.getRoot());

        // Set the constraints of the left and right panes

        System.out.println(this.settings.getSettings().getShowMoves());
        System.out.println(this.settings.getSettings().getUndoRedo());
        System.out.println(this.player1.getName());
        System.out.println(this.player2.getName());

        this.screenChanger.notifyBoard();


    }

    /**
     * getter method for the settings menu.
     * @return the settings menu
     */
    private SettingsMenuGUI getSettings(){
        return (SettingsMenuGUI) this.screenChanger.getGuiScene(ToScreen.SETTINGS_MENU);
    }

    /**
     * getter method for the player names.
     * @return the player names
     */
    private PlayerNamesGUI getPlayer(){
        return (PlayerNamesGUI) this.screenChanger.getGuiScene(ToScreen.PLAYER_NAMES);
    }


    /**
     * Updates the settings.
     */
    public void updateSettings(){
        this.settings = getSettings();
//        System.out.println("SHOW MOVES: " + this.settings.getSettings().getShowMoves());
//        System.out.println("SHOW UNDO/REDO: " + this.settings.getSettings().getUndoRedo());
//        System.out.println("SHOW THE WHITE: " + this.settings.getSettings().getWhiteSquareColor());
//        System.out.println("SHOW THE BLACK: " + this.settings.getSettings().getBlackSquareColor());
//        System.out.println("SHOW THE HIGHLIGHT: " + this.settings.getSettings().getHighlightColor());

        this.center.setHighlightColor(this.settings.getSettings().getHighlightColor());
        //this.top.setShowMoves(this.settings.getSettings().getShowMoves());
//        System.out.println("UPDATE SETTINGS: " + this.settings.getSettings().getUndoRedo());
        this.top.setShowUndoRedo(this.settings.getSettings().getUndoRedo());

        updateBoard();
    }

    /**
     * Updates the board.
     */
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
     *
     * @param notify true if the pane has been updated, false if wanting to switch to a different pane
     */
    @Override
    public void notifyPane(boolean notify) {
        if (notify){
            this.screenChanger.notifyBoard();
        }
        else{
            this.screenChanger.changeScreen(ToScreen.MAIN_MENU);
        }
    }

    /**
     * Notifies the observer that a piece has been captured.
     * @param piece the piece that was captured
     */
    public void notifyAddCapturedPiece(PieceIF piece){
        if (piece.getColor() == this.player1.getColor()){
            this.player2.addCapturedPiece(piece);
            this.right = new RightPaneGUI(player2);
            this.root.setRight(right.getRoot());
        }
        else if (piece.getColor() == this.player2.getColor()){
            this.player1.addCapturedPiece(piece);
            this.left = new LeftPaneGUI(player1);
            this.root.setLeft(left.getRoot());
        }
    }

    /**
     * Notifies the observer that the bottom pane should be updated.
     *
     * @param currPlayer the current player
     */
    @Override
    public void notifyBottomPane(String currPlayer) {
        this.bottom.updateBottomPane(currPlayer);
    }
}
