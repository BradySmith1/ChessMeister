package uicli;

import interfaces.MainMenuIF;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenuCLI implements MainMenuIF {

    private Scanner scan;

    private String menuTitle;

    private String[] menuOptions;

    private MainMenuIF option;

    public MainMenuCLI(){
        scan = new Scanner(System.in);
        this.menuTitle = """
                   _____ _                   __  __      _     _           \s
                  / ____| |                 |  \\/  |    (_)   | |          \s
                 | |    | |__   ___  ___ ___| \\  / | ___ _ ___| |_ ___ _ __\s
                 | |    | '_ \\ / _ \\/ __/ __| |\\/| |/ _ \\ / __| __/ _ \\ '__|
                 | |____| | | |  __/\\__ \\__ \\ |  | |  __/ \\__ \\ ||  __/ |  \s
                  \\_____|_| |_|\\___||___/___/_|  |_|\\___|_|___/\\__\\___|_|  \s
                """;
        this.menuOptions = new String[7];
        populateMenu();
    }

    private void populateMenu(){
        menuOptions[0] = "1: Play Chess\n";
        menuOptions[1] = "2: View Rules\n";
        menuOptions[2] = "3: Define Players\n";
        menuOptions[3] = "4: Settings\n";
        menuOptions[4] = "5: Load Game\n";
        menuOptions[5] = "0: Quit";
    }

    @Override
    public void show() {
        System.out.println(menuTitle);
        String menu = "---------------------------------------------------------------\n" +
                "Please make a selection as to what you would like to do:\n" +
                menuOptions[0] +
                menuOptions[1] +
                menuOptions[2] +
                menuOptions[3] +
                menuOptions[4] +
                menuOptions[5] +
                "\n-----------------------------------------" +
                "---------------------\n";
        promptUser(menu);
    }

    private void promptUser(String menu) {
        int choice = 999; //initialized to 999 so there is no option chosen or quitting loop
        String prompt = "Enter your menu choice here -> ";
        while (choice != 0) { //while user has not quit
            System.out.println(menu);   //shows user menu options
            System.out.print(prompt);
            try {
                choice = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
            System.out.println();
            switch(choice) {
                case 1:
                    option = new PlayChessCLI();
                    break;
                case 2:
                    option = new RulesCLI();
                    break;
                case 3:
                    option = new DefinePlayersCLI();
                    break;
                case 4:
                    option = new SettingsCLI();
                    break;
                case 5:
                    option = new LoadGameCLI();
                    break;
                case 0:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid number (0 - 5)");
                    break;
            }
            if(option != null){
                option.show();
            }
            System.out.println();
            scan.nextLine();//consumes a new line so nextInt throwing an exception will not loop
        }
    }
}
