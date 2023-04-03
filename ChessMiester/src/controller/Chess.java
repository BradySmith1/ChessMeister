/**
 * This file represents the necessary game logic loop needed for a game of chess to be played.
 * Includes methods to create a main menu and read player input, launch a new game, save/load
 * games (coming later) and many other crucial operations.
 *
 * @authors Brady Smith (25%), Zach Eanes (25%), Kaushal Patel (25%), and Colton Brooks (25%)
 * @version 1.0
 */
/* Package for the program. */
package controller;

/* Imports for our things in the program. */
import interfaces.MainMenuIF;
import uicli.MainMenuCLI;

public class Chess {

    private MainMenuIF menu; /* main menu to display to user */

    /**
     * Constructor method for the chess class. Initializes a scanner to be used.
     */
    public Chess() {
        this.menu = new MainMenuCLI();
    }

    /**
     * This function is used to display a main menu to the user. Here, we take user input
     * and decide what to be done next in processing.
     */
    public void go() {;
        menu.show();
    }
}
