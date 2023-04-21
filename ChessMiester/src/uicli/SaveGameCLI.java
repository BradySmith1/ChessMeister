package uicli;

import interfaces.LoadSaveGameIF;

import java.util.Scanner;

/**
 * This class implements the save game dialog.
 *
 * @author Zach Eanes (100%)
 * @version 1.0 (done in sprint 2)
 */
public class SaveGameCLI implements LoadSaveGameIF {
    private Scanner scan; /* Scanner for user input */

    private String url; /* URL of the file to load */

    /**
     * Constructor for the save game dialog.
     *
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
    public void showLoadSave() {
        System.out.print("Save Game\n-------------------------------------" +
                "--------------------------\nEnter file name: ");
        scan = new Scanner(System.in);
        this.url = scan.nextLine();
    }

    /**
     * Returns the URL of the file to save.
     *
     * @return URL of the file to save
     */
    public String getURL(){
        return this.url;
    }
}
