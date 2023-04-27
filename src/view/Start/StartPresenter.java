package view.Start;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.AiPlayer;
import model.HumanPlayer;
import model.PlayingTable;
import view.CardPresenter;
import view.MainMenu.MainPresenter;
import view.MainMenu.MainView;
import view.take5view;


public class StartPresenter {
    private final StartView view;

    public StartPresenter(StartView view) {
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
        HumanPlayer hp= new HumanPlayer(view.getUsername().getText(),64);
        AiPlayer AI = new AiPlayer("Bot 1", 64);

        Stage closeStage = (Stage) view.getScene().getWindow();
        closeStage.close();
        Stage stage = new Stage();

        // TODO: VASIL FIX THIS
        PlayingTable playingTable = new PlayingTable(hp, AI);
        take5view b1= new take5view();

        playingTable.getPlayers()[0].draw(playingTable.getDeck());
        playingTable.getPlayers()[1].draw(playingTable.getDeck());
        CardPresenter cardPresenter = new CardPresenter(playingTable, b1, stage);
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
