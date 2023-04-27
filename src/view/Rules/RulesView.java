package view.Rules;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import view.MainMenu.MainView;

public class RulesView extends BorderPane {
    private Label title;
    protected MainView mainView;
    Button backButton;
    public RulesView(MainView mainView) {
        this.mainView= mainView;
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
         backButton= new Button();
        title = new Label("Start");
    }

    private void layoutNodes() {
        setCenter(title);
        setTop(backButton);
    }
}
