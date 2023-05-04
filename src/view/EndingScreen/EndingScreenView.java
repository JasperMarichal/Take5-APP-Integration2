package view.EndingScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class EndingScreenView extends VBox {
    Label result;
    Button seeStatistics;

    public EndingScreenView(){
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes(){
        result = new Label("YOU WON");
        seeStatistics = new Button("Statistics");
    }

    private void layoutNodes(){
        getChildren().addAll(result, seeStatistics);
        setSpacing(20);
        setAlignment(Pos.CENTER);
        applyStyle();
        setBackground();
        setPadding(new Insets(10,10,10,30));

    }

    private void applyStyle(){
        result.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 120px; -fx-padding: 100px 80px; -fx-text-fill: #ffff;");
        seeStatistics.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 50px; -fx-text-fill: #122C3FFF; -fx-background-color: #ffffff;");

    }

    private void setBackground(){
        ImageView background = new ImageView(new Image("file:resources/mainMenu/background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(background.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }

    public Label getResult() {
        return result;
    }


    public Button getSeeStatistics() {
        return seeStatistics;
    }
}
