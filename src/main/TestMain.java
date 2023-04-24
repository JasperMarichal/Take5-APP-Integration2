package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AiPlayer;
import model.HumanPlayer;
import model.PlayingTable;
import view.CardPresenter;
import view.MainMenu.MainPresenter;
import view.MainMenu.MainView;
import view.take5view;

public class TestMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        MainPresenter mainPresenter = new MainPresenter(mainView);
        Scene scene = new Scene(mainView, 700,700);
        stage.setScene(scene);
        stage.show();

    }
}

