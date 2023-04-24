package view.MainMenu;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MainView extends BorderPane{

    private VBox vBox;
    private Button startButton;
    private Button rulesButton;
    private Button exitButton;
    private Button loadButton;
    private ImageView logo;
    public MainView() {
        initialiseNode();
        layoutNodes();
    }

    private void initialiseNode() {
        vBox = new VBox();
        startButton = new Button("START");
        rulesButton = new Button("RULES");
        loadButton = new Button("  LOAD  ");
        exitButton = new Button("  EXIT  ");
        logo = new ImageView(new Image("file:resources/mainMenu/logo_main_menu.png"));
    }

    private void layoutNodes() {
        logo.setFitWidth(400);
        logo.setFitHeight(240);

        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/LuckiestGuy-Regular.ttf"), 40);
        Font buttonFont = Font.font("Luckiest Guy",  20);
        vBox.setSpacing(20);
        vBox.getChildren().addAll(logo, startButton, loadButton,rulesButton, exitButton);
        setCenter(vBox);
        vBox.setAlignment(Pos.CENTER);

        setStyle("-fx-background-color: lightblue;");


        startButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 80px; -fx-text-fill: #abbdf3; -fx-background-color: #ffffff; -fx-border-color: #7ddef5; -fx-border-width: 6px;");
        rulesButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 80px; -fx-text-fill: #abbdf3; -fx-background-color: #ffffff; -fx-border-color: #7ddef5; -fx-border-width: 6px;");
        exitButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 80px; -fx-text-fill: #abbdf3; -fx-background-color: #ffffff; -fx-border-color: #7ddef5; -fx-border-width: 6px;");
        loadButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 80px; -fx-text-fill: #abbdf3; -fx-background-color: #ffffff; -fx-border-color: #7ddef5; -fx-border-width: 6px;");

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10.0);
        shadow.setSpread(0.5);
//        shadow.setOffsetX(3.0);
//        shadow.setOffsetY(3.0);
        shadow.setColor(Color.rgb(169,225,238));

        ScaleTransition st = new ScaleTransition(Duration.millis(300), startButton);

// Set the number of times the button will bounce during the animation
        st.setCycleCount(2);

// Set the rate of the animation
        st.setRate(1.5);

// Set the from value for the scale transformation
        st.setFromX(1.0);
        st.setFromY(1.0);

// Set the to value for the scale transformation
        st.setToX(1.2);
        st.setToY(1.2);

        startButton.setOnMouseEntered(e -> {
            // Set the new background color of the button on hover
            startButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 80px; -fx-text-fill: #abbdf3; -fx-background-color: #e13d3d; -fx-border-color: #7ddef5; -fx-border-width: 6px;");

            // Play the hover animation
            st.play();
        });

// Add an event handler for the mouse exit event
        startButton.setOnMouseExited(e -> {
            // Set the original background color of the button on mouse exit
            startButton.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-font-size: 30px; -fx-padding: 10px 80px; -fx-text-fill: #abbdf3; -fx-background-color: #ffffff; -fx-border-color: #7ddef5; -fx-border-width: 6px;");

            // Stop the hover animation
            st.stop();

            // Reset the opacity of the button to 1.0
            startButton.setOpacity(1.0);
        });

    }
}
