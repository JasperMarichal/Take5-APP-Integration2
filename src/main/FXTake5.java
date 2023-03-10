package main;

import classes.AiPlayer;
import classes.Card;
import classes.HumanPlayer;
import classes.PlayingTable;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXTake5 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {        HumanPlayer hp= new HumanPlayer("Vasil",64);
        AiPlayer AI= new AiPlayer("Bot 1", 64);

        PlayingTable playingTable = new PlayingTable(hp, AI,new BorderPane() );
        Card c= new Card(1,2);

        playingTable.players[0].draw(playingTable.getDeck());
//        playingTable.players[1].draw(playingTable.getDeck());


//        playingTable.showRows();

//        playingTable.dmgCalculation(1, playingTable.players[0]);

//        playingTable.showRows();


//        Card card1 = new Card(1, 1);
//        Card card2 = new Card(2, 2);
//        card2.getImageView().setX(100);

//        card1.getImageView().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            System.out.println("Tile pressed ");
//            event.consume();
//                playingTable.players[0].hand.findCard(card1.getURL().toString());
//            System.out.println(card1.getURL().toString());
//        });

//        BorderPane root= new BorderPane();
//        root.setBottom(card1.getImageView());
//        root.setTop(card2.getImageView());
//        BorderPane.setMargin(card2.getImageView(), new Insets(100, 0, 0, 0));
//        playingTable.refreshPane();
        playingTable.refreshPane();
        Scene scene1 = new Scene(playingTable.borderPane);
        stage.setScene(scene1);
        stage.setHeight(600);
        stage.setWidth(600);
        stage.show();
    }
}
