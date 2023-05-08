package model;

import enums.ToScreen;
import gui.tutorial.*;
import gui.colourselector.ColourSelectorGUI;
import gui.gameboard.GameBoardGUI;
import gui.loadgame.LoadGameGUI;
import gui.mainmenu.MainMenuGUI;
import gui.playernames.PlayerNamesGUI;
import gui.settingsmenu.SettingsMenuGUI;
import gui.viewplayed.ViewPlayedGUI;
import interfaces.ScreenChangeHandlerIF;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Class that implements ScreenChangeHandlerIF to be used to change screens, this is a singleton
 */
public final class ScreenFactory implements ScreenChangeHandlerIF {

    /** the scene to be passed in first through getInstance, then to be changed by changeScreen() */
    private Scene scene;

    /** The previous screen **/
    private ToScreen previousScreen;

    /** the singleton instance */
    private static ScreenFactory singleton;

    /** The main menu screen **/
    private static MainMenuGUI mainMenuScreen;

    /** The player names screen **/
    private static PlayerNamesGUI definePlayerNamesScreen;

    /** The tutorial screen **/
    private static TutorialMenuGUI tutorialMenuScreen;

    /** The settings screen **/
    private static SettingsMenuGUI settingsMenuScreen;

    /** The colour selector screen **/
    private static ColourSelectorGUI colourSelectorScreen;

    /** The view played screen **/
    private static ViewPlayedGUI viewPlayedScreen;

    /** The load game screen **/
    private static LoadGameGUI loadGameScreen;

    /** The main board screen **/
    private static GameBoardGUI gameBoardScreen;

    /** The board setup screen **/
    private static BoardSetupGUI boardSetupScreen;

    /** The chess notation screen */
    private static ChessNotationGUI chessNotationScreen;

    /** The chess organization screen */
    private static BoardOrganizationGUI boardOrganizationScreen;

    /** The check tutorial screen */
    private static CheckGUI checkTutorialScreen;

    /** The checkmate tutorial screen */
    private static CheckmateGUI checkmateTutorialScreen;

    /** The draw tutorial screen */
    private static DrawGUI drawTutorialScreen;

    /** The king tutorial screen */
    private static KingTutorialGUI kingTutorialScreen;

    /** The queen tutorial screen */
    private static QueenTutorialGUI queenTutorialScreen;

    /** The bishop tutorial screen */
    private static BishopTutorialGUI bishopTutorialScreen;

    /** The knight tutorial screen */
    private static KnightTutorialGUI knightTutorialScreen;

    /** The rook tutorial screen */
    private static RookTutorialGUI rookTutorialScreen;

    /** The pawn tutorial screen */
    private static PawnTutorialGUI pawnTutorialScreen;

    /** The piece tutorial screen */
    private static PieceTutorialGUI pieceTutorialScreen;

    /** The notation quiz screen */
    private static NotationQuizGUI notationQuizScreen;


    /**
     * The constructor for ScreenFactory
     * @param scene the scene passed in to be used by ScreenFactory
     */
    private ScreenFactory(Scene scene) {
        this.scene = scene;
        setScreen(ToScreen.MAIN_MENU);
    }

    /**
     * The getter for the singleton instance of this class
     * @param scene the scene to be used
     * @return the singleton instance
     */
    public static ScreenFactory getInstance(Scene scene) {
        if (singleton == null) {
            singleton = new ScreenFactory(scene);
        }
        return singleton;
    }

    /**
     * the helper method to change screens based on a ToScreen enum, edit this to add more screens as well as
     * the enum file ToScreen
     * @param screenChoice  the enum to be referenced for the changing screen
     * @return  a screen that the scene will now be using
     */
    private Pane setScreen(ToScreen screenChoice) {
        Pane screen;
        switch (screenChoice){
            case MAIN_MENU :
                if (mainMenuScreen == null) {
                    mainMenuScreen = new MainMenuGUI();
                    mainMenuScreen.setScreenChangeHandler(this);
                }
                definePlayerNamesScreen = null;
                screen = mainMenuScreen.getRoot();
                break;
            case PLAYER_NAMES :
                if (definePlayerNamesScreen == null) {
                    definePlayerNamesScreen = new PlayerNamesGUI();
                    definePlayerNamesScreen.setScreenChangeHandler(this);
                }
                screen = definePlayerNamesScreen.getRoot();
                break;
            case SETTINGS_MENU :
                if (settingsMenuScreen == null) {
                    settingsMenuScreen = new SettingsMenuGUI();
                    settingsMenuScreen.setScreenChangeHandler(this);
                }
                screen = settingsMenuScreen.getRoot();
                break;
            case TUTORIAL_MENU :
                if (tutorialMenuScreen == null) {
                    tutorialMenuScreen = new TutorialMenuGUI();
                    tutorialMenuScreen.setScreenChangeHandler(this);
                }
                screen = tutorialMenuScreen.getRoot();
                break;
            case COLOUR_SELECTOR:
                if (colourSelectorScreen == null) {
                    colourSelectorScreen = new ColourSelectorGUI();
                }
                screen = colourSelectorScreen.getRoot();
                break;
            case VIEW_PLAYED:
                if (viewPlayedScreen == null) {
                    viewPlayedScreen = new ViewPlayedGUI();
                    viewPlayedScreen.setScreenChangeHandler(this);
                }
                screen = viewPlayedScreen.getRoot();
                break;
            case LOAD_GAME:
                if (loadGameScreen == null) {
                    loadGameScreen = new LoadGameGUI();
                    loadGameScreen.setScreenChangeHandler(this);
                }
                screen = loadGameScreen.getRoot();
                break;
            case GAME_BOARD:
                // Create settings as it will be needed
                if (settingsMenuScreen == null) {
                    settingsMenuScreen = new SettingsMenuGUI();
                    settingsMenuScreen.setScreenChangeHandler(this);
                }
                if (this.previousScreen != ToScreen.SETTINGS_MENU) {
                    previousScreen = ToScreen.GAME_BOARD;
                    gameBoardScreen = new GameBoardGUI();
                    gameBoardScreen.setScreenChangeHandler(this);
                }
                screen = gameBoardScreen.getRoot();
                this.notifyBoard();
                break;
            case BOARD_SETUP:
                if (boardSetupScreen == null) {
                    boardSetupScreen = new BoardSetupGUI();
                    boardSetupScreen.setScreenChangeHandler(this);
                }
                screen = boardSetupScreen.getRoot();
                break;
            case CHESS_NOTATION:
                if (chessNotationScreen == null) {
                    chessNotationScreen = new ChessNotationGUI();
                    chessNotationScreen.setScreenChangeHandler(this);
                }
                screen = chessNotationScreen.getRoot();
                break;
            case BOARD_ORGANIZATION:
                if (boardOrganizationScreen == null) {
                    boardOrganizationScreen = new BoardOrganizationGUI();
                    boardOrganizationScreen.setScreenChangeHandler(this);
                }
                screen = boardOrganizationScreen.getRoot();
                break;
            case CHECK:
                if (checkTutorialScreen == null) {
                    checkTutorialScreen = new CheckGUI();
                    checkTutorialScreen.setScreenChangeHandler(this);
                }
                screen = checkTutorialScreen.getRoot();
                break;
            case CHECKMATE:
                if (checkmateTutorialScreen == null) {
                    checkmateTutorialScreen = new CheckmateGUI();
                    checkmateTutorialScreen.setScreenChangeHandler(this);
                }
                screen = checkmateTutorialScreen.getRoot();
                break;
            case DRAW:
                if (drawTutorialScreen == null) {
                    drawTutorialScreen = new DrawGUI();
                    drawTutorialScreen.setScreenChangeHandler(this);
                }
                screen = drawTutorialScreen.getRoot();
                break;
            case KING_TUTORIAL:
                if (kingTutorialScreen == null) {
                    kingTutorialScreen = new KingTutorialGUI();
                    kingTutorialScreen.setScreenChangeHandler(this);
                }
                screen = kingTutorialScreen.getRoot();
                break;
            case QUEEN_TUTORIAL:
                if (queenTutorialScreen == null) {
                    queenTutorialScreen = new QueenTutorialGUI();
                    queenTutorialScreen.setScreenChangeHandler(this);
                }
                screen = queenTutorialScreen.getRoot();
                break;
            case BISHOP_TUTORIAL:
                if (bishopTutorialScreen == null) {
                    bishopTutorialScreen = new BishopTutorialGUI();
                    bishopTutorialScreen.setScreenChangeHandler(this);
                }
                screen = bishopTutorialScreen.getRoot();
                break;
            case ROOK_TUTORIAL:
                if (rookTutorialScreen == null) {
                    rookTutorialScreen = new RookTutorialGUI();
                    rookTutorialScreen.setScreenChangeHandler(this);
                }
                screen = rookTutorialScreen.getRoot();
                break;
            case KNIGHT_TUTORIAL:
                if (knightTutorialScreen == null) {
                    knightTutorialScreen = new KnightTutorialGUI();
                    knightTutorialScreen.setScreenChangeHandler(this);
                }
                screen = knightTutorialScreen.getRoot();
                break;
            case PAWN_TUTORIAL:
                if (pawnTutorialScreen == null) {
                    pawnTutorialScreen = new PawnTutorialGUI();
                    pawnTutorialScreen.setScreenChangeHandler(this);
                }
                screen = pawnTutorialScreen.getRoot();
                break;
            case PIECE_TUTORIAL:
                if (pieceTutorialScreen == null) {
                    pieceTutorialScreen = new PieceTutorialGUI("king"); //TODO figure this out
                    pieceTutorialScreen.setScreenChangeHandler(this);
                }
                screen = pieceTutorialScreen.getRoot();
                break;
            case NOTATION_QUIZ:
                if (notationQuizScreen == null) {
                    notationQuizScreen = new NotationQuizGUI();
                    notationQuizScreen.setScreenChangeHandler(this);
                }
                screen = notationQuizScreen.getRoot();
                break;
            default:
                screen = null;
                break;
        }

        // Apply the screen to the root scene
        scene.setRoot(screen);

        return screen;
    }

    /**
     * Method that sets the scenes root to a new scene
     * @param screenChoice  the ToScreen enum reference
     * @param previousScreen the ToScreen enum reference of the previous screen
     */
    @Override
    public void changeScreen(ToScreen screenChoice, ToScreen previousScreen) {
        this.previousScreen = previousScreen;
        Pane root = setScreen(screenChoice);
        scene.setRoot(root);
    }

    /**
     * Method that sets the scenes root to a new scene
     * @param screenChoice  the ToScreen enum reference
     */
    @Override
    public void changeScreen(ToScreen screenChoice) {
        Pane root = setScreen(screenChoice);
        scene.setRoot(root);
    }

    /**
     * Method that returns an enum representing the previous scene
     *
     * @return the ToScreen enum reference of the previous screen
     */
    @Override
    public ToScreen getPreviousScreen() {
        return this.previousScreen;
    }

    /**
     * Method that returns a selected scene
     *
     * @return the selected scene
     */
    public Pane getGuiScene(ToScreen screenChoice) {
        Pane returnScreen = null;
        if (screenChoice == ToScreen.SETTINGS_MENU){
            returnScreen = settingsMenuScreen;
        } else if (screenChoice == ToScreen.PLAYER_NAMES) {
            returnScreen = definePlayerNamesScreen;
        } else if (screenChoice == ToScreen.GAME_BOARD){
            returnScreen = gameBoardScreen;
        }
        return returnScreen;
    }

    public void notifyBoard(){
        gameBoardScreen.updateSettings();
    }
}
