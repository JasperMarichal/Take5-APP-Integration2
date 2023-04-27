package view.MainMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class MainView extends HBox {
    private VBox vBox;
    private Button startButton;
    private Button rulesButton;
    private Button exitButton;
    private Button loadButton;
    private ImageView cardsRight;
    private Label title;

    public MainView() {
        initialiseNode();
        layoutNodes();
    }

    private void initialiseNode() {
        vBox = new VBox();
        title = new Label("TAKE 5!");
        startButton = new Button("START");
        rulesButton = new Button("RULES");
        loadButton = new Button("  LOAD  ");
        exitButton = new Button("  EXIT  ");
        cardsRight = new ImageView(new Image("file:resources/mainMenu/cards_main_menu.png"));
    }

    private void layoutNodes() {
        //Font
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/LuckiestGuy-Regular.ttf"), 40);
        Font buttonFont = Font.font("Luckiest Guy",20);
        cardsRight.setFitWidth(311);
        cardsRight.setFitHeight(700);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(title, startButton, loadButton,rulesButton, exitButton);
        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);
        getChildren().addAll(vBox, rightSpacer, cardsRight);
        setBackground();
        applyStyle();

    }

    private void setBackground(){
        ImageView background = new ImageView(new Image("file:resources/mainMenu/background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(background.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }

    private void applyStyle(){
        title.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 120px; -fx-padding: 10px 80px; -fx-text-fill: #ffff;");
        startButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 100px; -fx-text-fill: #061424; -fx-background-color: #ffffff;");
        rulesButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 100px; -fx-text-fill: #061424; -fx-background-color: #ffffff; ");
        exitButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 100px; -fx-text-fill: #061424; -fx-background-color: #ffffff; ");
        loadButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 100px; -fx-text-fill: #061424; -fx-background-color: #ffffff;");

    }

    public Label getTitle() {
        return title;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getRulesButton() {
        return rulesButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }
}
