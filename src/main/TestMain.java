package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MainMenu.MainPresenter;
import view.MainMenu.MainView;

public class TestMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        MainView mainView = new MainView();
        MainPresenter mainPresenter = new MainPresenter(mainView);
        Scene scene = new Scene(mainView, 1200,700);
        stage.setScene(scene);
        stage.show();

    }
}

