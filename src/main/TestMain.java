package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AiPlayer;
import model.HumanPlayer;
import model.PlayingTable;
import view.MainMenu.MainPresenter;
import view.MainMenu.MainView;


public class TestMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        int counterPoints = 10;
        HumanPlayer player = new HumanPlayer(null,counterPoints);
        AiPlayer AI = new AiPlayer("Bot 1", counterPoints);
        PlayingTable model = new PlayingTable(player, AI);
        MainView mainView = new MainView();
        MainPresenter mainPresenter = new MainPresenter(mainView, model);
        Scene scene = new Scene(mainView, 1200,700);
        stage.setScene(scene);
        stage.show();
    }
}

