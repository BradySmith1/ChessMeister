package gui.gameboard;

import enums.ToScreen;
import gui.playernames.PlayerNamesGUI;
import gui.settingsmenu.SettingsMenuGUI;
import gui_backend.PieceGUI;
import interfaces.PieceIF;
import interfaces.PlayerIF;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

/**
 * This class is responsible for creating the game board GUI.
 *
 * @author Brady Smith (100%)
 * @version 1.0 (done in sprint 3)
 */
public class GameBoardGUI extends BorderPane implements CenterPaneObserver{

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

    /** the screen change handler */
    ScreenChangeHandlerIF screenChanger;

    /** the settings menu */
    private SettingsMenuGUI settings;

    /** player2 name */
    private PlayerIF player1;

    /** player2 name */
    private PlayerIF player2;

    /**
     * Constructor for the game board GUI.
     */
    public GameBoardGUI() {
        super();


        //Initialize the Panes.
        this.top = new TopPaneGUI();
        this.top.getRoot().setId("top");
        this.bottom = new BottomPaneGUI("Player 1");
        this.bottom.getRoot().setId("bottom");
        this.center = new CenterPaneGUI();
        this.center.setId("center");

        // Add the observers
        center.addObserver(this);
        top.addObserver(center);

        //add the panes to the root
        this.setTop(top.getRoot());
        this.setBottom(bottom.getRoot());
        this.setCenter(center);

        // add the stylesheet and images
        this.getStylesheets().add(getClass().getResource("gameBoard.css").toExternalForm());
    }

    /**
     * Returns the root pane.
     *
     * @return the root pane
     */
    public Pane getRoot() { return this; }

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

        // sets bottom pane to reflect p1 name
        this.bottom.updateBottomPane(this.player1.getName());

        left = new LeftPaneGUI(this.player1);
        left.getRoot().setId("left");
        right = new RightPaneGUI(this.player2);
        right.getRoot().setId("right");

        // Set the players to the center pane
        this.center.setPlayer1(this.player1);
        this.center.setPlayer2(this.player2);

        this.setLeft(left.getRoot());
        this.setRight(right.getRoot());

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

        this.center.setHighlightColor(this.settings.getSettings().getHighlightColor());
        this.top.setShowUndoRedo(this.settings.getSettings().getUndoRedo());
        this.center.setHighlight(this.settings.getSettings().getShowMoves());

        updateBoard();
    }

    /**
     * Updates the board.
     */
    private void updateBoard(){
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if ((row + col) % 2 == 0){
                    this.center.getSquares()[row][col].setColor(this.settings.getSettings()
                            .getWhiteSquareColor());
                }
                else{
                    this.center.getSquares()[row][col].setColor(this.settings.getSettings()
                            .getBlackSquareColor());
                }
            }
        }
    }

    /**
     * Notifies the observer that the pane has been updated.
     *
     * @param notify true if the pane has been updated, false if wanting to switch to a
     *               different pane
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
        //notification to the right and left panes to update there tilepanes with the pieces.
        if(piece == null){
            RightPaneGUI right = (RightPaneGUI) this.getRight();
            right.makeCapturedRight(player2);
            LeftPaneGUI left = (LeftPaneGUI) this.getLeft();
            left.makeCapturedLeft(player1);
        }else{
            PieceIF pieceCopy = new PieceGUI(piece.getImage());
            if (pieceCopy.getColor() == this.player1.getColor()){
                this.player2.addCapturedPiece(pieceCopy);
                ((RightPaneGUI) this.getRight()).makeCapturedRight(player2);
            }
            else{
                this.player1.addCapturedPiece(pieceCopy);
                ((LeftPaneGUI) this.getLeft()).makeCapturedLeft(player1);
            }
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
