package view.Rules;

import javafx.scene.input.MouseEvent;
import view.MainMenu.MainPresenter;
import view.MainMenu.MainView;

public class RulesPresenter {
    private RulesView view;

    protected MainView mainView;
    public RulesPresenter(RulesView view) {
        this.view = view;
        addEventHandlers();
    }
    public void addEventHandlers() {
        view.backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            view.getScene().setRoot(MainPresenter.getMainView());
        });
    }
}
