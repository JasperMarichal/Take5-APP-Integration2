package view.MainMenu;


import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import view.Rules.RulesPresenter;
import view.Rules.RulesView;
import view.Start.StartPresenter;
import view.Start.StartView;
import java.util.Optional;

public class MainPresenter {
    private static  MainView view = null;

    public static MainView getMainView() {
        return view;
    }

    public MainPresenter(MainView view){
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers(){
        setupHoverAnimation(view.getStartButton());
        setupHoverAnimation(view.getLoadButton());
        setupHoverAnimation(view.getRulesButton());
        setupHoverAnimation(view.getExitButton());
        view.getStartButton().setOnMouseClicked(e ->{setPlayerView();});
        view.getRulesButton().setOnMouseClicked(e ->{setRulesView();});
        view.getExitButton().setOnMouseClicked(e ->{
            if(exitApplication()){
                Platform.exit();
            } else {
                e.consume();
            }
        });
    }

    private void setupHoverAnimation(Button button) {
        final double SCALE_FACTOR = 1.2;
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(150), button);
        scaleIn.setToX(SCALE_FACTOR);
        scaleIn.setToY(SCALE_FACTOR);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(150), button);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        button.setOnMouseEntered(event -> scaleIn.play());
        button.setOnMouseExited(event -> scaleOut.play());
    }

    private void setPlayerView(){
        StartView startView = new StartView();
        StartPresenter startPresenter = new StartPresenter(startView);
        view.getScene().setRoot(startView);
        startView.getScene().getWindow().sizeToScene();
    }

    private void setRulesView(){
        RulesView rulesView = new RulesView();
        RulesPresenter rulesPresenter = new RulesPresenter(rulesView);
        view.getScene().setRoot(rulesView);
        rulesView.getScene().getWindow().sizeToScene();
    }


    private boolean exitApplication(){
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you really want to quit this awesome application ?");
        alert.setTitle("Quit application");
        Optional<ButtonType> choice = alert.showAndWait();

        return (ButtonType.OK == choice.get());
    }








}
