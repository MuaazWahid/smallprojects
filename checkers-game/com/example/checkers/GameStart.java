package com.example.checkers;

import java.util.Scanner;

/**
 * Asks user if they want to play Checkers in GUI or Text Console interface.
 */
public class GameStart {
    public static void main(String [] args) {
        Scanner keyboardScanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("Would you like to play checkers in a GUI or Text Console?");
            System.out.println("Answer with 'GUI' or 'Text'");
            userInput = keyboardScanner.nextLine();
        } while (!userInput.equals("GUI") && !userInput.equals("Text"));

        if (userInput.equals("Text")) {
            new CheckersTextConsole().runGameLoop();
        }
        else {
            CheckersGUI.main(null);
        }
    }
}
