package ca.cmpt213.asn4.tictactoe.ui;

import ca.cmpt213.asn4.tictactoe.game.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Game Play class ensures the smooth running of game by setting up the initial game
 * and handling the mouse events.
 */

public class GamePlay {

    private GridPane gridPane = new GridPane();
    private boolean teamTurn = true;
    private GameLogic gameLogic = new GameLogic();
    private int[][] gameGrid = gameLogic.getGrid();
    private Label label = new Label("");

    public void makeNewGrid() {
        teamTurn = true;
        label.setText("X will start");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageView blankImageView = new ImageView(new Image("file:Images/blank_pic.png"));
                blankImageView.setFitHeight(70);
                blankImageView.setFitWidth(70);
                gridPane.add(blankImageView, i, j);
                blankImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    ImageView src = (ImageView) mouseEvent.getSource();
                    int column = GridPane.getColumnIndex(src);
                    int row = GridPane.getRowIndex(src);
                    if (gameGrid[row][column] == 100) {
                        label.setText("Game ended");
                        return;
                    }
                    if (teamTurn) {
                        if (gameGrid[row][column] != 0) {
                            label.setText("Try again X");
                            return;
                        }
                        src.setImage(new Image("file:Images/X_pic.png"));
                        label.setText("");
                        teamTurn = false;
                        gameGrid[row][column] = 1;
                    }
                    else {
                        if (gameGrid[row][column] != 0) {
                            label.setText("Try again O");
                            return;
                        }
                        src.setImage(new Image("file:Images/O_pic.png"));
                        label.setText("");
                        teamTurn = true;
                        gameGrid[row][column] = 2;
                    }
                    gameLogic.checkGrid(gameGrid, label);
                });
            }
        }
    }

    public void setUpScene(Stage primaryStage) {
        gridPane.add(label, 1, 4);
        makeNewGrid();
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction((actionEvent -> {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    gameGrid[i][j] = 0;
                }
            }
            makeNewGrid();
        }));
        newGameButton.setMaxSize(150, 70);
        gridPane.add(newGameButton, 1, 6, 4, 7);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(10));
        Scene scene = new Scene(gridPane, 450, 450);
        primaryStage.setScene(scene);
    }
}
