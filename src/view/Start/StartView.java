package view.Start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class StartView extends BorderPane {
    VBox vbox;
    TextField username;
    Button startButton;
    Button backButton;

    public StartView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        vbox = new VBox();
        startButton = new Button("START");
        username = new TextField();
        backButton = new Button("");
    }

    private void layoutNodes() {
        //Font
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/LuckiestGuy-Regular.ttf"), 40);
        Font buttonFont = Font.font("Luckiest Guy",20);

        setCenter(vbox);
        vbox.getChildren().addAll(username,startButton);
        setBottom(backButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        setBackground();
        setStyle();

        // Use image as button
        ImageView back = new ImageView(new Image("file:resources/mainMenu/backButton.png"));
        back.setFitHeight(60);
        back.setFitWidth(60);
        backButton.setGraphic(back);

    }

    private void setBackground(){
        ImageView background = new ImageView(new Image("file:resources/mainMenu/background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(background.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }

    private void setStyle(){
        //Start button style
        startButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 100px; -fx-text-fill: #23496e; -fx-background-color: #ffffff;");

        //Back button style
        backButton.setStyle("-fx-background-color: transparent; -fx-background-radius: 0; -fx-padding: 25;");
        backButton.setPrefWidth(10);
        backButton.setPrefHeight(10);

        //Text field style
        username.setPromptText("Enter your name");
        username.setAlignment(Pos.CENTER);
        username.setStyle("-fx-font-family: 'Luckiest Guy';-fx-prompt-text-fill: #e5e5e5; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 100px; -fx-background-color: #ffffff;");
        username.setPrefHeight(100);
        username.setMaxWidth(600);
    }

    public TextField getUsername() {
        return username;
    }
    public Button getStartButton() {
        return startButton;
    }

    public Button getBackButton() {
        return backButton;
    }
}
