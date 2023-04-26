package view.Rules;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class RulesView extends BorderPane {
    private Label title;
    public RulesView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        title = new Label("Start");
    }

    private void layoutNodes() {
        setCenter(title);
    }
}
