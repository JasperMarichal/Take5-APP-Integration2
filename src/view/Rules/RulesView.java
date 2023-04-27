package view.Rules;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class RulesView extends BorderPane {
    private VBox vbox;
    private Label rules;
    private Label rulesTitle;
    Button backButton;
    public RulesView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        rulesTitle = new Label("Rules");
        vbox = new VBox();
         backButton= new Button("");
         rules = new Label();
         rules.setText("Objective:\n" +
                 "The objective of the game is to avoid collecting the most bullheads (cards with bullheads) by playing cards in numerical sequence.\n" +
                 "\n" +
                 "Rules: \n" +
                 "1. The game is played with a deck of 104 cards, each numbered from 1 to 104.\n" +
                 "2. At the beginning of each round, the deck is shuffled and each player is dealt 10 cards.\n" +
                 "3. Each turn, players must choose one card from their hand to play. The cards are played face down, and then simultaneously revealed.\n" +
                 "4. The player who played the lowest numbered card gets to choose which row to place their card in. Each row can hold up to 5 cards.\n" +
                 "5. If a player cannot play a card higher than the highest card in any row, they must choose a row to take all the cards in that row.\n" +
                 "6. At the end of the round, each player scores points based on the number of cows on the cards they have taken. Each card has a number\n" +
                 " of cows on it, ranging from 1 to 7.\n" +
                 "7. The player with the most cows at the end of the game wins.\n" +
                 "8. In the video game version, there can be a single-player mode, where the player plays against AI opponents, or a multiplayer mode,\n where players can play against each other online.\n"+
                 "9. The video game can keep track of high scores and statistics, allowing players to see their progress over time.\n"
         );

    }

    private void layoutNodes() {
        setBottom(backButton);
        vbox.getChildren().addAll(rulesTitle,rules);
        setLeft(vbox);
        setBackground();
        setStyle();
        rules.setMinWidth(1100);
        rules.setMinHeight(400);
        vbox.setPadding(new Insets(0,0,0,50));


        ImageView back = new ImageView(new Image("file:resources/mainMenu/backButton.png"));
        back.setFitHeight(60);
        back.setFitWidth(60);
        backButton.setGraphic(back);

        rules.setBackground(Background.EMPTY);
    }

    private void setBackground(){
        ImageView background = new ImageView(new Image("file:resources/mainMenu/background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(background.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
    }

    private void setStyle(){
        rules.setStyle("-fx-text-fill: white; -fx-padding: 0 0 0 10; -fx-font-size: 20px");
        backButton.setStyle("-fx-background-color: transparent; -fx-background-radius: 0; -fx-padding: 50;");
        backButton.setPrefWidth(10);
        backButton.setPrefHeight(10);
        rulesTitle.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 80px; -fx-padding: 10px; -fx-text-fill: #ffff;");
    }
}
