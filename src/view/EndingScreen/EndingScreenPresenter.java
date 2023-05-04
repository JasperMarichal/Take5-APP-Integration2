package view.EndingScreen;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.PlayingTable;
import model.Statistics;
import view.Statistics.StatisticsPresenter;
import view.Statistics.StatisticsView;

public class EndingScreenPresenter {
    EndingScreenView view;
    PlayingTable model;

    public EndingScreenPresenter(EndingScreenView view, PlayingTable model) {
        this.view = view;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        view.getSeeStatistics().setOnMouseClicked(e -> {
            Stage closeStage = (Stage) view.getScene().getWindow();
            closeStage.close();

            Statistics model = new Statistics();
            StatisticsView view = new StatisticsView();
            new StatisticsPresenter(model, view);
            Scene scene = new Scene(view);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });
    }

    private void updateView() {
        switch (model.checkWin()) {
            case 1 -> view.getResult().setText("YOU LOST :(");
            case 0 -> view.getResult().setText("YOU WON!");
        }
    }
}
