package uicli;

import interfaces.MainMenuIF;
import interfaces.RulesIF;

import java.util.Scanner;

public class RulesCLI implements RulesIF {

    private Scanner scan;

    public RulesCLI(Scanner scan) {
        this.scan = scan;
    }
    @Override
    public void show() {

    }
}
