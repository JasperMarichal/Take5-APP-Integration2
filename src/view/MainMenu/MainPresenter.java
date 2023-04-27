package view.MainMenu;


import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import view.Rules.RulesPresenter;
import view.Rules.RulesView;
import view.Start.StartPresenter;
import view.Start.StartView;
import java.util.Optional;

public class MainPresenter {
    private static MainView view;
    public MainPresenter(MainView view){
        MainPresenter.view = view;
        addEventHandlers();
        addAnimations();
    }


    private void addEventHandlers(){
        //Set hover animation ro buttons
        setupHoverAnimation(view.getStartButton());
        setupHoverAnimation(view.getLoadButton());
        setupHoverAnimation(view.getRulesButton());
        setupHoverAnimation(view.getExitButton());

        //Event handlers to buttons, changing the screen
        view.getStartButton().setOnMouseClicked(this::setStartView); //method reference
        view.getRulesButton().setOnMouseClicked(this::setRulesView); //method reference
        view.getExitButton().setOnMouseClicked(this::exitApplication); //method reference

    }

    private void setupHoverAnimation(Button button) {
        final double SCALE_FACTOR = 1.2;
        ScaleTransition scaleIn = new ScaleTransition(Duration.millis(150), button);
        scaleIn.setToX(SCALE_FACTOR);
        scaleIn.setToY(SCALE_FACTOR);

        ScaleTransition scaleOut = new ScaleTransition(Duration.millis(150), button);
        scaleOut.setToX(1);
        scaleOut.setToY(1);

        // Animation is played when the mouse is hovering the button
        button.setOnMouseEntered(event -> scaleIn.play());
        button.setOnMouseExited(event -> scaleOut.play());
    }

    private void setStartView(MouseEvent e){
        StartView startView = new StartView();
        StartPresenter startPresenter = new StartPresenter(startView);
        view.getScene().setRoot(startView);
        startView.getScene().getWindow().sizeToScene();
    }

    private void setRulesView(MouseEvent e){
        RulesView rulesView = new RulesView();
        RulesPresenter rulesPresenter = new RulesPresenter(rulesView);
        view.getScene().setRoot(rulesView);
        rulesView.getScene().getWindow().sizeToScene();
    }


    private void exitApplication(MouseEvent e){
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Do you really want to quit this awesome application ?");
        alert.setTitle("Quit application");
        Optional<ButtonType> choice = alert.showAndWait();
        choice.ifPresent(buttonType -> {
            if ((buttonType == ButtonType.OK)) {
                Platform.exit();
            } else {
                e.consume();
            }
        });
    }

    private void addAnimations(){
        ScaleTransition scale = new ScaleTransition(Duration.seconds(0.6), view.getTitle());
        scale.setFromX(1);
        scale.setFromY(1);
        scale.setToX(1.2);
        scale.setToY(1.2);
        scale.setAutoReverse(true);
        scale.setCycleCount(2);
        scale.play();
    }

    public static MainView getMainView(){
        return view;
    }

}
