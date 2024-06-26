package uicli;

import interfaces.LoadSaveGameIF;

import java.util.Scanner;


/**
 * This class implements the load game dialog.
 * @author Brady Smith (100%)
 */
public class LoadGameCLI implements LoadSaveGameIF {

    private Scanner scan; /* Scanner for user input */

    private String url; /* URL of the file to load */

    /**
     * Constructor for the load game dialog.
     *
     * @param scan Scanner for user input
     */
    public LoadGameCLI(Scanner scan) {
        this.scan = scan;
        this.url = "";
    }

    /**
     * Displays the load game dialog.
     */
    @Override
    public void showLoadSave() {

        System.out.print(" Load Game-------------------------------------------------------------" +
                "--\n\n" + "File input is expected with just the title, so 'example.txt' should\n" +
                "be entered as 'example'.\n\n" + "Enter file name: ");
        scan = new Scanner(System.in);
        this.url = scan.nextLine();
    }

    /**
     * Returns the URL of the file to load.
     *
     * @return URL of the file to load
     */
    public String getURL(){
        return this.url;
    }
}
