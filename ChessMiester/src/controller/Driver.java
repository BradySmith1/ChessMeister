/**
 * Simple driver for the Chess game.
 *
 * @author Zach Eanes (30%)
 * @author Colton Brooks (70%)
 */

package controller;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Board;
import uicli.BoardColorCLI;
import uicli.BoardMonoCLI;

public class Driver {
    public static void main(String[] args){
        Chess chess = new Chess();
        chess.mainMenu();
    }
}
