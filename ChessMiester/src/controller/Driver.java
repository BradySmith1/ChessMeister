/**
 * Simple driver for the Chess game.
 *
 * @author Zach Eanes (30%)
 * @author Colton Brooks (70%)
 */

package controller;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in); /* scanner to read in player choice */
        String menu =
                "Welcome to ChessMeister!\n---------------------------------------------------------------\n" +
                "Please make a selection as to what you would like to do:\n" +
                "0 - Play Local Game Against another Player.\n" +
                "(COMING SOON!) 1 - Play Local Game Against a Computer\n" +
                "(COMING SOON!) 2 - Play Online Game Against another Player.\n\n" +
                "(COMING SOON!) 3 - 4-Player Chess!";

        Integer menuChoice = null;
        /* Validate that our menu choice is proper. */
        while(menuChoice == null || menuChoice < 0 || menuChoice > 3){
            try{
                menuChoice = Integer.parseInt(scan.nextLine().strip());
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid number choice (0-3).");
                System.out.println("Enter another menu choice -> ");
            }
            if(menuChoice != null && (menuChoice < 0 || menuChoice > 3)){
                System.out.println("Please enter a valid number choice (0-3).");
                System.out.println("Enter another menu choice -> ");
            }
        }

        Chess chess = new Chess();
    }

    /**
     * This method is to set-up and help launch into the selection of the game.
     * @param choice int value to represent the choice
     */
    public void setup(int choice){
        switch(choice){
            case 0 -> this.playLocalP();
            case 1 -> this.playLocalC();
            case 2 -> this.playOnline();
        }
    }

    /**
     * This function is used to launch and go through a local game with another player.
     */
    public void playLocalP(){
        //call new board and setup

    }

    /**
     * This function is used to launch and go through a local game against a computer.
     */
    public void playLocalC(){
        System.out.println("This feature is coming soon! Please choose another feature!");
    }

    /**
     * This function is used to launch and go through an online game with another player.
     */
    public void playOnline(){
        //init board
    }
}
