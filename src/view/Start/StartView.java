package view.Start;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class StartView extends BorderPane {
    private Label title;
    public StartView() {
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
