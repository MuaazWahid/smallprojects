package com.example.checkers;

import core.CheckersLogic;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Creates GUI and imports necessary GUI/Mouse packages
 * @author Muaaz Wahid
 * @version 9/11/23
 */
public class CheckersGUI extends Application {
    private static final int BOARD_SIZE = 8;
    private static final int CELL_SIZE = 100;
    @Override
    public void start(Stage primaryStage) throws IOException {
        CheckersLogic gameLogic = new CheckersLogic();
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE);
        final int[] clickCounter = new int[1];
        final int[] coordinate = new int[4];

        // Create a checkerboard backdrop
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                cell.setFill( (row + col) % 2 == 0 ? Color.LIGHTGRAY : Color.DARKGRAY);
                gridPane.add(cell, col, row);

                // Add circles/pieces to the appropriate cells
                Circle piece;
                if ((row < 3 || row > 4) && (row + col) % 2 != 0) {
                    piece = new Circle(CELL_SIZE * 0.5);
                    piece.setFill(row < 3 ? Color.RED : Color.BLACK);
                    piece.setOnMouseClicked(mouseEvent -> {});
                    gridPane.add(piece, col, row);
                }

                gridPane.setOnMouseClicked(event -> {
                    clickCounter[0]++;
                    // This is the user selecting the start position of their move
                    if (clickCounter[0] == 1) {
                        coordinate[0] = (int)(event.getSceneY() / 100);
                        coordinate[1] = (int)(event.getSceneX() / 100);

                        // check if starting position is not a piece
                        if (gameLogic.getBoardCoordinate(coordinate[0], coordinate[1]) == '_') {
                            // invalid move, reset clickCounter
                            clickCounter[0]--;
                        }
                    }
                    // This is the user selecting the end position of their move
                    else if (clickCounter[0] == 2) {
                        coordinate[2] = (int)(event.getSceneY() / 100);
                        coordinate[3] = (int)(event.getSceneX() / 100);
                        // check if starting position is not an empty square
                        if (gameLogic.getBoardCoordinate(coordinate[2], coordinate[3]) != '_') {
                            // invalid move, reset clickCounter
                            clickCounter[0]--;
                        }

                        // update internal representation of board
                        gameLogic.playerUserMoveInRowColumnFormat(coordinate[0], coordinate[1], coordinate[2], coordinate[3]);

                        // update GUI representation
                        for (Node x : gridPane.getChildren()) {
                            // may have to switch the RowIndex and ColumnIndex around
                            if (gridPane.getRowIndex(x) == coordinate[0] && gridPane.getColumnIndex(x) == coordinate[1]) {
                                gridPane.clearConstraints(x);
                                System.out.println("Cleared constraint");
                            }
                        }
                    }
                    // reset click counter
                    else {
                        clickCounter[0] = 0;
                    }
                });
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Checkers");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}