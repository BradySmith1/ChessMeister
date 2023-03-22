package controller;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){

        Chess chess = new Chess();
        Scanner scan = new Scanner(System.in);

        String menu =
                "Welcome to ChessMeister!\n" +
                "Please make a selection as to what you would like to do:" +
                "0 - Play Local Game Against another Player.\n" +
                "1 - Play Local Game Against a Computer\n" +
                "2 - Play Online Game Against another Player.\n\n" +
                "COMING SOON!! 4-Player Chess.";

        System.out.println("Enter the menu choice (0/1/2) -> ");
        int menuChoice = scan.nextInt();

        setup(menuChoice);
    }
}
