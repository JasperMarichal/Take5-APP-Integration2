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
    public void start(Stage stage) {
        HumanPlayer hp= new HumanPlayer("Vasil",64);
        AiPlayer AI= new AiPlayer("Bot 1", 64);

        PlayingTable playingtable = new PlayingTable(hp, AI);
        Card c= new Card(1,2);

        Button button= new Button();

        PlayingTable playingTable = new PlayingTable(
                new HumanPlayer("Vasil",64)
                , new AiPlayer("Bot1",64));

        playingTable.players[0].draw(playingTable.getDeck());
        playingTable.players[1].draw(playingTable.getDeck());

        //System.out.println(playingTable.players[1].getHand());

        //playingTable.addCard(playingTable.getDeck().deal1(), 0);
        //playingTable.addCard(playingTable.getDeck().deal1(), 1);
        //playingTable.addCard(playingTable.getDeck().deal1(), 2);
        //playingTable.addCard(playingTable.getDeck().deal1(), 3);

        playingTable.showRows();

        playingTable.dmgCalculation(1, playingTable.players[0]);

        playingTable.showRows();

        Card b= new Card();
        Image card= new Image(c.getURL().toString());
        /*try {
           card = new Image(c.getURL());
        } catch (IllegalArgumentException e) {
            System.err.println("Error loading image: " + e.getMessage());
            card = new Image("C:\\Users\\Jasper School\\OneDrive - Karel de Grote Hogeschool\\Desktop\\Integration 2\\S2-10\\resources\\take5prototipfx\\Card1.png");
            System.out.println(c.getURL());
        }*/
        ImageView imageView = new ImageView(card);
        imageView.setFitHeight(160);
        imageView.setFitWidth(110);
        Image finalCard = card;
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Tile pressed ");
            event.consume();
            playingTable.players[0].getHand().findCard(finalCard.getUrl().toString());
            System.out.println(finalCard.getUrl().toString());
//                b.setURL(imageView.getImage().getUrl().toString());
//            System.out.println(b.getURL());
        });

//        button.setGraphic(imageView);
//        button.setOnMouseClicked(event -> {
//            String imageUrl = button.getGraphic().toString();
//            System.out.println(button.getStyle());
//            System.out.println("Image URL: " + imageUrl);
//        });
//        TextArea textArea= new TextArea();
//        button.setPrefSize(20,20);
        BorderPane root= new BorderPane();
        root.setBottom(imageView);
        BorderPane.setAlignment(button, Pos.CENTER);
        BorderPane.setMargin(button, new Insets(20,20,20,20));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.setHeight(600);
        stage.setWidth(600);
        stage.show();
    }
}
