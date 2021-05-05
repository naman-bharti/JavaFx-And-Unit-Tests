package ca.cmpt213.asn4.tictactoe.game;

import javafx.scene.control.Label;

/**
 * Game logic class handles the logic behind the game to ensure that the game runs correctly.
 */

public class GameLogic {
    private int[][] grid = new int[4][4];

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void checkGrid(int[][] gameGrid, Label label) {
        int BoxCount = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameGrid[i][j] == 1 || gameGrid[i][j] == 2) {
                    BoxCount++;
                }
            }
        }

        if (BoxCount >= 16) {
            label.setText("Draw");
            stopGrid(gameGrid);
        }
        else if (gameGrid[0][0] == gameGrid[0][1] && gameGrid[0][1] == gameGrid[0][2] &&
                gameGrid[0][2] == gameGrid[0][3]) {
            checkBox(gameGrid, 0, 0, label);
        }
        else if (gameGrid[1][0] == gameGrid[1][1] && gameGrid[1][1] == gameGrid[1][2] &&
                gameGrid[1][2] == gameGrid[1][3]) {
            checkBox(gameGrid, 1, 0, label);
        }
        else if (gameGrid[2][0] == gameGrid[2][1] && gameGrid[2][1] == gameGrid[2][2] &&
                gameGrid[2][2] == gameGrid[2][3]) {
            checkBox(gameGrid, 2, 0, label);
        }
        else if (gameGrid[3][0] == gameGrid[3][1] && gameGrid[3][1] == gameGrid[3][2] &&
                gameGrid[3][2] == gameGrid[3][3]) {
            checkBox(gameGrid, 3, 0, label);
        }
        else if (gameGrid[0][0] == gameGrid[1][0] && gameGrid[1][0] == gameGrid[2][0] &&
                gameGrid[2][0] == gameGrid[3][0]) {
            checkBox(gameGrid, 0, 0, label);
        }
        else if (gameGrid[0][1] == gameGrid[1][1] && gameGrid[1][1] == gameGrid[2][1] &&
                gameGrid[2][1] == gameGrid[3][1]) {
            checkBox(gameGrid, 0, 1, label);
        }
        else if (gameGrid[0][2] == gameGrid[1][2] && gameGrid[1][2] == gameGrid[2][2] &&
                gameGrid[2][2] == gameGrid[3][2]) {
            checkBox(gameGrid, 0, 2, label);
        }
        else if (gameGrid[0][3] == gameGrid[1][3] && gameGrid[1][3] == gameGrid[2][3] &&
                gameGrid[2][3] == gameGrid[3][3]) {
            checkBox(gameGrid, 0, 3, label);
        }
        else if (gameGrid[0][0] == gameGrid[1][1] && gameGrid[1][1] == gameGrid[2][2] &&
                gameGrid[2][2] == gameGrid[3][3]) {
            checkBox(gameGrid, 0, 0, label);
        }
        else if (gameGrid[0][3] == gameGrid[1][2] && gameGrid[1][2] == gameGrid[2][1] &&
                gameGrid[2][1] == gameGrid[3][0]) {
            checkBox(gameGrid, 0, 3, label);
        }
    }

    private void checkBox(int[][] gameGrid, int row, int column, Label label) {
        if (gameGrid[row][column] == 1) {
            label.setText("X wins");
            stopGrid(gameGrid);
        }
        else if (gameGrid[row][column] == 2) {
            label.setText("O wins");
            stopGrid(gameGrid);
        }
    }

    private void stopGrid(int[][] gameGrid) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameGrid[i][j] = 100;
            }
        }
    }
}
