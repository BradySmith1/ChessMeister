package controller;

/**
 * Simple driver for the Chess game.
 *
 * @author Brady Smith (100%)
 * @version 1.0
 */

package controller;

import uicli.MainMenuCLI;

public class Driver {
    public static void main(String[] args){
        Chess chess = new Chess();
        chess.go();
    }
}
