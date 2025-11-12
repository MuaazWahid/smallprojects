package core;

import java.util.ArrayList;

/**
 * Implementation of a checkers game.
 * Handles logic of a checkers game such as valid moves, maintaining whose turn it is to play, etc. etc.
 * @author Muaaz Wahid
 * @version 9/5/2023
 */
public class CheckersLogic {
    /**
     * Two-dimensional array that represents the checkerboard.
     */
    private final char [][] checkerBoardArray;
    /**
     * This represents the user's pieces.
     */
    private final char USER = 'x';
    /**
     * This represents the computer's pieces.
     */
    private final char COMPUTER = 'o';
    /**
     * This represents the user's pieces.
     */
    private final char BLANK = '_';
    /**
     * This field keeps track of whose turn it is to play.
     */
    private char currentPlayer;

    /**
     * Initialize Checker Board with values and set user's turn to play.
     */
    public CheckersLogic() {
        checkerBoardArray = new char[][] {
                {'_', 'o', '_', 'o', '_', 'o', '_', 'o'},
                {'o', '_', 'o', '_', 'o', '_', 'o', '_'},
                {'_', 'o', '_', 'o', '_', 'o', '_', 'o'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'x', '_', 'x', '_', 'x', '_', 'x', '_'},
                {'_', 'x', '_', 'x', '_', 'x', '_', 'x'},
                {'x', '_', 'x', '_', 'x', '_', 'x', '_'}
        };
        currentPlayer = USER;
    }

    /**
     * Returns character at specified position in 2d array.
     * @param x row
     * @param y column
     * @return either chars 'x', 'o', or '_'.
     */
    public char getBoardCoordinate(int x, int y) { return checkerBoardArray[x][y]; }

    /**
     * Translates user input into valid checkerboard moves.
     * Also maintains whose turn it is to play.
     * <p>
     * This way the user can make 1 move, the computer will respond appropriately
     * and at the end of this method the computer will have finished all it's moves.
     * <p>
     * Additionally, the user can be prompted multiple times if it is their turn again.
     *
     * @param userInputMove user inputted move
     */
    public void playMoves(String userInputMove) {
        // TODO: May have to change structure of this method.
        // TODO: May have to update javadoc.

        currentPlayer = USER;

        // convert user input to indices in 2d array
        int [] mappedUserCoordinates = convertUserInputToCoordinates(userInputMove);

        int startX = mappedUserCoordinates[0];
        int startY = mappedUserCoordinates[1];
        int endX = mappedUserCoordinates[2];
        int endY = mappedUserCoordinates[3];

        // if capture is mandatory then do capture move, else do diagonal move
        if (Math.abs(endX - startX) == 2) {
            updateBoardWithCaptureMove(startX, startY, endX, endY);
            // add logic to keep on checking for captures
        }
        else
            movePieceOneSquareDiagonally(startX, startY, endX, endY);

//        currentPlayer = COMPUTER;
//
//        // choose a random single square move
//        // ERROR: Can move to different color square that shouldn't be accessible
//        Move randomMove = generateRandomMove();
//        startX = randomMove.getStartX();
//        startY = randomMove.getStartY();
//        endX = randomMove.getStartX();
//        endY = randomMove.getEndY();
//
//        if (Math.abs(endX - startX) == 2) {
//            updateBoardWithCaptureMove(startX, startY, endX, endY);
//            // add logic to keep on checking for captures
//        }
//        else
//            movePieceOneSquareDiagonally(startX, startY, endX, endY);
    }

    public void playerUserMoveInRowColumnFormat(int startX, int startY, int endX, int endY) {
        // check if ending position is valid
        if (!isValidMove(startX, startY, endX, endY) )
            throw new NullPointerException("Move invalid");

        // if capture is mandatory then do capture move, else do diagonal move
        if (Math.abs(endX - startX) == 2) {
            updateBoardWithCaptureMove(startX, startY, endX, endY);
            if (isCapturePresent())
                throw new NumberFormatException();
        }
        else
            movePieceOneSquareDiagonally(startX, startY, endX, endY);
    }

    /**
     * Plays the user inputted move on the checkerboard.
     * @param userInputMove User inputted move.
     */
    public void playUserMove(String userInputMove) throws NullPointerException {
        // First check formatting of user input
        if (!checkIfUserInputIsCorrectFormat(userInputMove))
            throw new NullPointerException("User Input in incorrect format.");

        int [] mappedUserCoordinates = convertUserInputToCoordinates(userInputMove);
        int startX = mappedUserCoordinates[0];
        int startY = mappedUserCoordinates[1];
        int endX = mappedUserCoordinates[2];
        int endY = mappedUserCoordinates[3];

        // check if ending position is valid
        if (!isValidMove(startX, startY, endX, endY) )
            throw new NullPointerException("Move invalid");

        // if capture is mandatory then do capture move, else do diagonal move
        if (Math.abs(endX - startX) == 2) {
            updateBoardWithCaptureMove(startX, startY, endX, endY);
            if (isCapturePresent())
                throw new NumberFormatException();
        }
        else
            movePieceOneSquareDiagonally(startX, startY, endX, endY);

    }

    /**
     * This is used to check if, after a capture has been done,
     * the user/computer still has to make another capture.
     *
     * @return true if capture is present on board for user or computer
     */
    public boolean isCapturePresent() {
        // check if another capture for user
        if (currentPlayer == USER) {
            for (int i = 2; i < checkerBoardArray.length; i++)
                for (int j = 2; j < checkerBoardArray[0].length-1; j++)
                    if (checkerBoardArray[i][j] == USER)
                        if (checkerBoardArray[i-1][j-1] == COMPUTER && checkerBoardArray[i-2][j-2] == BLANK)
                            return true;
                        else if (checkerBoardArray[i-1][j+1] == COMPUTER && checkerBoardArray[i-2][j-2] == BLANK)
                            return true;
        }
        // check if another capture for computer
        else {
            for (int i = 0; i < checkerBoardArray.length-2; i++)
                for (int j = 2; j < checkerBoardArray[0].length-2; j++)
                    if (checkerBoardArray[i][j] == COMPUTER)
                        if (checkerBoardArray[i+1][j-1] == USER && checkerBoardArray[i+2][j-2] == BLANK)
                            return true;
                        else if (checkerBoardArray[i+1][j+1] == USER && checkerBoardArray[i+2][j+2] == BLANK)
                            return true;
        }
        return false;
    }

    /*------------------------------------ PRIVATE HELPER METHODS ------------------------------------*/
    /*------------------------------------ PRIVATE HELPER METHODS ------------------------------------*/
    /*------------------------------------ PRIVATE HELPER METHODS ------------------------------------*/
    /*------------------------------------ PRIVATE HELPER METHODS ------------------------------------*/
    /*------------------------------------ PRIVATE HELPER METHODS ------------------------------------*/

    /**
     * Generates a random single square move for computer
     * <p>
     * 1. Determine all the legal moves available for the computer by iterating through all positions
     * <p>
     * 2. Check if there are any legal moves available.
     * If there are no moves, the computer cannot make a move,
     * <p>
     * 3. If there are legal moves available, randomly select one of the moves.
     * @return coordinates of move encapsulated in a Move object
     */
    private Move generateRandomMove() {
        ArrayList<Move> listOfLegalMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkerBoardArray[i][j] == COMPUTER) {
                    // TODO: This method creating sideways moves
                    // Check for valid single square moves
                    if (isValidMove(i, j, i + 1, j - 1))
                        listOfLegalMoves.add(new Move(i, j, i - 1, j - 1));

                    if (isValidMove(i, j, i + 1, j + 1))
                        listOfLegalMoves.add(new Move(i, j, i - 1, j + 1));

                    // Handle additional moves for kings, if applicable
                    // ...
                }
            }
        }
        // randomly choose a move and return it
        return listOfLegalMoves.get( (int) (Math.random() * listOfLegalMoves.size()) );
    }

    /**
     * Updates the board with a single square diagonal move based on the coordinates passed in.
     * It will not check if moves are valid.
     */
    private void movePieceOneSquareDiagonally(int startX, int startY, int endX, int endY) {
        checkerBoardArray[startX][startY] = BLANK;
        checkerBoardArray[endX][endY] = currentPlayer;
    }

    /**
     * Updates board with a capture move. ie: A piece captures an enemy piece.
     */
    private void updateBoardWithCaptureMove(int startX, int startY, int endX, int endY) {
        // initialize values
        int midX = (startX + endX) / 2;
        int midY = (startY + endY) / 2;
        // using ternary operation, find whoever isn't playing
        char oppositeOfCurrentPlayer = (currentPlayer == USER) ? COMPUTER : USER;
        // set values accordingly
        checkerBoardArray[startX][startY] = BLANK;
        checkerBoardArray[midX][midY] = BLANK;
        checkerBoardArray[endX][endY] = currentPlayer;
    }

    /**
     * Maps user input in specified format to the appropriate indices on the 2d array.
     * @param userInputInSpecifiedFormat User input formatted as specified.
     * @return An array with 4 elements specifying row and column.
     */
    private int[] convertUserInputToCoordinates(String userInputInSpecifiedFormat) {
        int [] numberCoords = new int[4];
        // get numbers
        numberCoords[0] = 8 - Integer.parseInt(userInputInSpecifiedFormat.substring(1,2));
        numberCoords[2] = 8 - Integer.parseInt(userInputInSpecifiedFormat.substring(4,5));

        // convert letters to numbers
        char tempLetter = userInputInSpecifiedFormat.charAt(0);
        char [] letters = {'a','b','c','d','e','f','g','h'};
        for (int i = 0; i < letters.length; i++)
            if (tempLetter == letters[i])
                numberCoords[1] = i;

        tempLetter = userInputInSpecifiedFormat.charAt(3);
        for (int i = 0; i < letters.length; i++)
            if (tempLetter == letters[i])
                numberCoords[3] = i;

        return numberCoords;
    }

    /**
     * Checks if given coordinates are valid for a move
     * @return true if coordinates are valid, false if otherwise
     */
    private boolean isValidMove(int startX, int startY, int endX, int endY) {
        // Check if the start and end position in bounds of board
        // 7 because params range from 0-7
        if (startX >= 7 || startY >= 7 || endX < 0 || endX >= 7 || endY < 0 || endY >= 7)
            return false;

        // check if starting position has the correct piece the user requests
        if (checkerBoardArray[startX] [startY] != currentPlayer)
            return false;

        // Check if the ending position is empty
        if (checkerBoardArray[endX][endY] != BLANK)
            return false;

        // Check if the move is diagonal (for every unit horizontally, same units vertically)
        int horizontalChange = Math.abs(endX - startX);
        int verticalChange = Math.abs(endY - startY);
        if (horizontalChange != verticalChange)
            return false;

        // Check if the move is in the correct direction based on whose playing
        if (currentPlayer == USER && endX > startX )
            return false;

        if (currentPlayer == COMPUTER && endX < startX )
            return false;

        // Check if the move is a capture move
        if (horizontalChange == 2) {
            int midX = (startX + endX) / 2;
            int midY = (startY + endY) / 2;

            // using ternary operator, oppositeOfCurrentPlayer value is set to opposite of currentPlayer value
            // ie: if currentPlayer == 'x' then oppositeOfCurrentPlayer = 'o'
            char oppositeOfCurrentPlayer = (currentPlayer == USER) ? COMPUTER : USER;

            // Does the space in between contain an opponents piece?
            // Is the ending position a blank space?
            return checkerBoardArray[midX][midY] == oppositeOfCurrentPlayer && checkerBoardArray[endX][endY] == BLANK;
        }

        // Can't have 3-step moves etc. etc.
        // Return true if a single step move is valid
        return horizontalChange == 1;
    }

    /**
     * Checks if user input is correctly formatted.
     * As well as if values inputted are in correct range. ie: 1-8, a-h only.
     * @param userInputMove user input
     * @return true if user input is correct, false otherwise
     */
    private boolean checkIfUserInputIsCorrectFormat(String userInputMove) {
        // first check if format of user input is valid
        userInputMove = userInputMove.trim();
        if (userInputMove.length() != 5)
            return false;

        // check if no dash present in input
        if (userInputMove.charAt(2) != '-')
            return false;

        // check if 2nd & 5th characters are numbers
        // checks if numbers are within boundaries of checkerboard
        try {
            // if numbers are outside range of accepted values
            if (Integer.parseInt(userInputMove.substring(1,2)) < 1 ||
                    Integer.parseInt(userInputMove.substring(1,2)) > 8)
                return false;
            if (Integer.parseInt(userInputMove.substring(4,5)) < 1 ||
                    Integer.parseInt(userInputMove.substring(4,5)) > 8)
                return false;
        }
        catch (NumberFormatException notValidNumber) {
            return false;
        }

        // check if 2nd & 3rd chars are letters between (a-h)
        char [] validLetters = {'a','b','c','d','e','f','g','h'};
        for (char letter : validLetters) {
            if (userInputMove.charAt(0) == letter) {
                // check if 3rd char is a valid letter
                for (char secondLetter : validLetters)
                    if (userInputMove.charAt(3) == secondLetter)
                        return true;
                break;
            }
        }

        // user inputted an invalid letter
        return false;
    }
}