package view.Start;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.PlayingTable;
import view.CardPresenter;
import view.MainMenu.MainPresenter;
import view.Take5View;


public class StartPresenter {

    private final StartView view;
    private PlayingTable model;

    public StartPresenter(StartView view, PlayingTable model) {
        this.model = model;
        this.view = view;
        addEventListeners();
        addAnimation();
    }

    private void addEventListeners() {
        view.getBackButton().setOnMouseClicked(this::setMainView);
        view.getStartButton().setOnMouseClicked(this::startGame);
    }

    private void setMainView(MouseEvent e){
        view.getScene().setRoot(MainPresenter.getMainView());
    }

    private void startGame(MouseEvent e){
        Stage closeStage = (Stage) view.getScene().getWindow();
        closeStage.close();
        Stage stage = new Stage();

        Take5View view = new Take5View();
        model.getPlayer(0).setName(this.view.getUsername().getText());
        model.getPlayers()[0].draw(model.getDeck());
        model.getPlayers()[1].draw(model.getDeck());
        CardPresenter cardPresenter = new CardPresenter(this.model, view, stage);
    }

    private void addAnimation(){
        fadeInTextField();
        popUpButton();
    }


    private void fadeInTextField() {
        view.getUsername().setOpacity(0);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), view.getUsername());
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    private void popUpButton() {
        ScaleTransition scale = new ScaleTransition(Duration.seconds(0.6), view.getStartButton());
        scale.setFromX(1);
        scale.setFromY(1);
        scale.setToX(1.2);
        scale.setToY(1.2);
        scale.setAutoReverse(true);
        scale.setCycleCount(2);

        scale.play();
    }

}
