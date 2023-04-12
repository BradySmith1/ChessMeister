package uicli;

import interfaces.LoadSaveGameIF;

import java.util.Scanner;

public class SaveGameCLI implements LoadSaveGameIF {
    private Scanner scan; /* Scanner for user input */

    private String url; /* URL of the file to load */

    /**
     * Constructor for the save game dialog.
     * @param scan Scanner for user input
     */
    public SaveGameCLI(Scanner scan) {
        this.scan = scan;
        this.url = "";
    }

    /**
     * Displays the save game dialog.
     */
    @Override
    public void show() {
        System.out.print("Save Game\n-------------------------------------" +
                "--------------------------\nEnter file path:");
        this.url = scan.nextLine();
    }

    /**
     * Returns the URL of the file to save.
     * @return URL of the file to save
     */
    public String getURL(){
        return this.url;
    }
}
