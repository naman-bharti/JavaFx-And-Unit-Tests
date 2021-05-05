package ca.cmpt213.asn4.tictactoe.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class launches the application of javafx.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tic Tac Toe");
        GamePlay gamePlay = new GamePlay();
        gamePlay.setUpScene(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
