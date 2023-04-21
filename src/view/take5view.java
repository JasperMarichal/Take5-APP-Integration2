package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.FXTake5;
import model.AiPlayer;
import model.Card;
import model.PlayingTable;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class take5view {


   protected static List<ImageView> AIimages = new ArrayList<>();
    protected static  List<Card>CardsOfPlayer= new ArrayList<>();
    protected static   List<ImageView> PlayerImages = new ArrayList<>();


    static BorderPane borderPane1;
    static BorderPane borderPane2;

    public  BorderPane getBorderPane2() {
        return borderPane2;
    }

    static String urlBack = "file:resources/take5prototipfx/bacground1Finished.jpg";
    static HboxBuilder h1 = new HboxBuilder();

   protected VBox rows= new VBox();
    Button button= new Button();

   protected HBox firstRow= new HBox();
   protected HBox secondRow= new HBox();
   protected HBox thirdRow= new HBox();
   protected HBox ForthRow= new HBox();
    protected  VBox cardHolder= new VBox();



    public void getImage(Card card){
        Image image= new Image(card.getURL());

    }
    public static ImageView getImageView(String URL) {

      Image image= new Image(URL);
      ImageView imageView= new ImageView(image);
            imageView.setFitHeight(160);
            imageView.setFitWidth(110);


        return imageView;
    }

            public void addButtons(Button button1, Button button2, Button button3, Button button4){
                ((HBox)rows.getChildren().get(0)).getChildren().add(button1);
                ((HBox)rows.getChildren().get(1)).getChildren().add(button2);
                ((HBox)rows.getChildren().get(2)).getChildren().add(button3);
                ((HBox)rows.getChildren().get(3)).getChildren().add(button4);
            }
    public void buildScene1(PlayingTable playingTable, Stage stage){
        BorderPane borderPane = new BorderPane();

         borderPane.setCenter(button);
          borderPane2=borderPane;
        Scene sceneForMenu= new Scene(getBorderPane2());
        stage.setScene(sceneForMenu);
        stage.setHeight(900);
        stage.setWidth(1500);
        stage.show();
//
//         button.addEventHandler(MouseEvent.MOUSE_CLICKED, event2 ->{
//
//             buildBorderPane(playingTable);
//             Scene scene1 = new Scene(getBorderPane1());
//             stage.setScene(scene1);

        }

    public static HboxBuilder getH1() {
        return h1;
    }

    public void buildBorderPane(PlayingTable playingTable){

       rows= h1.buildRows(playingTable);
        playingTable.getBorderPane().setCenter(rows);

        // TODO: clear pane
        // append player one cards
        int counterForLatch=0;
        CountDownLatch latch = new CountDownLatch(counterForLatch);


            for (int i = 0; i < playingTable.getPlayers()[0].getHand().getCards().size(); i++){
            System.out.println(playingTable.getPlayers()[0].getHand().getCards().get(i));
                CardsOfPlayer.add(playingTable.getPlayers()[1].getHand().getCards().get(i));
                ImageView currentCardViewai =  getImageView( playingTable.getPlayers()[1].getHand().getCards().get(i).getURL());
            ImageView currentCardView =  getImageView( playingTable.getPlayers()[0].getHand().getCards().get(i).getURL());
            javafx.scene.image.Image img= new javafx.scene.image.Image(urlBack);
                Card c= playingTable.getPlayers()[1].getHand().getCards().get(i);
                PlayerImages.add(currentCardView);




            ImageView opponentsCards= new ImageView(img);
            opponentsCards.setFitHeight(160);
            opponentsCards.setFitWidth(110);

            playingTable.getTopImages().getChildren().add(opponentsCards);
            playingTable.getBottomImages().getChildren().add(currentCardView);
            HBox.setMargin(currentCardView, new javafx.geometry.Insets(10, 10 , 10, 10));


                AIimages.add(currentCardViewai);
        }



        VBox.setMargin(rows, new Insets(10, 0, 0, 0));
        playingTable.getBorderPane().setBottom(playingTable.getBottomImages());
        playingTable.getBorderPane().setTop(playingTable.getTopImages());


                borderPane1= playingTable.getBorderPane();
    }


    public BorderPane getBorderPane1() {
        return borderPane1;
    }
}
