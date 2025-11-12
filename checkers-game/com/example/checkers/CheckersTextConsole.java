package com.example.checkers;

import core.CheckersLogic;

import java.util.Scanner;

/**
 * Text Interface program for a checkers game
 * @author Muaaz Wahid
 * @version 9/5/2023
 */
public class CheckersTextConsole {
    /**
     * Field to access logic class/handle logic of game.
     */
    static CheckersLogic gameLogic;

    /**
     * Initializes logic class.
     */
    public CheckersTextConsole() { gameLogic = new CheckersLogic(); }

    /**
     * Displays current state of checkerboard.
     */
    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                System.out.print("| " +  gameLogic.getBoardCoordinate(row, column) + " ");
            }
            System.out.println("| " + (8 - row));
        }
        System.out.println("  a   b   c   d   e   f   g   h");
    }

    public void runGameLoop() {
        Scanner keyboardScanner = new Scanner(System.in);
        // VARIABLES for game
        String userInput;
        gameLogic = new CheckersLogic();
        CheckersTextConsole userInterface = new CheckersTextConsole();

        // Begin Game
        userInterface.printBoard();
        System.out.println("Begin Game. Player X – your turn.\n" +
                "Choose a cell position of piece to be moved and the new position. e.g., a3-b4");

        while (true) {
            userInput = keyboardScanner.nextLine();
            try {
                // TODO this should be playMoves(), which will allow CheckersLogic to maintain whose turn it is
                gameLogic.playUserMove(userInput);
            }
            catch(NullPointerException userInputError) {
                System.out.println("Please provide input in example format & make sure it is a valid move");
                // skip rest of loop take user input again
                continue;
            }
            // catch for a capture prompt
            catch(NumberFormatException nfe) {
                while (gameLogic.isCapturePresent()) {
                    userInterface.printBoard();
                    System.out.println("Choose a cell position of piece to be moved and the new position. e.g., a3-b4");
                    userInput = keyboardScanner.nextLine();
                    try {
                        // TODO this should be playMoves(), which will allow CheckersLogic to maintain whose turn it is
                        gameLogic.playUserMove(userInput);
                    }
                    catch(NullPointerException userInputError) {
                        System.out.println("Please provide input in example format & make sure it is a valid move");
                    }
                }
            }
            // TODO implement computer moves

            userInterface.printBoard();
            System.out.println("Choose a cell position of piece to be moved and the new position. e.g., a3-b4");
        }
    }
}