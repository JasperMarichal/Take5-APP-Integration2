package view.Start;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import view.MainMenu.MainPresenter;
import view.MainMenu.MainView;


public class StartPresenter {
    private final StartView view;

    public StartPresenter(StartView view) {
        this.view = view;
        addEventListeners();
        addAnimation();
    }

    private void addEventListeners() {
        view.getBackButton().setOnMouseClicked(this::setMainView);
    }

    private void setMainView(MouseEvent e){
        view.getScene().setRoot(MainPresenter.getMainView());
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
