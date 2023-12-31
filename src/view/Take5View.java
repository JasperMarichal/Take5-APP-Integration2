package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Card;
import model.PlayingTable;
import view.EndingScreen.EndingScreenView;

import java.util.ArrayList;
import java.util.List;

public class Take5View {

    BorderPane borderPane = new BorderPane();
    HBox bottomImages = new HBox();
    HBox topImages = new HBox();

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public HBox getBottomImages() {
        return bottomImages;
    }

    public HBox getTopImages() {
        return topImages;
    }

    protected static List<ImageView> AIimages = new ArrayList<>();
    protected static List<Card> CardsOfPlayer = new ArrayList<>();
    protected static List<ImageView> PlayerImages = new ArrayList<>();

    static BorderPane borderPane1;

    static String urlBack = "file:resources/take5prototipfx/bacground1Finished.jpg";
    static HboxBuilder h1 = new HboxBuilder();

    public VBox rows = new VBox();
    Button button = new Button();

    VBox dmg = new VBox();

    public static ImageView getImageView(String URL) {

        Image image = new Image(URL);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(160);
        imageView.setFitWidth(110);

        return imageView;
    }

    public void addButtons(Button button1, Button button2, Button button3, Button button4) {
        ((HBox) rows.getChildren().get(0)).getChildren().add(button1);
        ((HBox) rows.getChildren().get(1)).getChildren().add(button2);
        ((HBox) rows.getChildren().get(2)).getChildren().add(button3);
        ((HBox) rows.getChildren().get(3)).getChildren().add(button4);
    }

    public void buildScene1(PlayingTable playingTable, Stage stage) {
        buildBorderPane(playingTable);
//        borderPane2=borderPane;
        Scene sceneForMenu = new Scene(getBorderPane1());
        stage.setScene(sceneForMenu);
        stage.setHeight(900);
        stage.setWidth(1500);
        stage.show();
        setBackground();
    }

    public static HboxBuilder getH1() {
        return h1;
    }

    public void buildBorderPane(PlayingTable playingTable) {

        rows = h1.buildRows(playingTable);
        getBorderPane().setCenter(rows);
        dmg = h1.buildDmgPoints(playingTable);
        getBorderPane().setRight(dmg);

        // TODO: clear pane
        // append player one cards

        for (int i = 0; i < playingTable.getPlayers()[0].getHand().getCards().size(); i++) {
            System.out.println(playingTable.getPlayers()[0].getHand().getCards().get(i));
            CardsOfPlayer.add(playingTable.getPlayers()[1].getHand().getCards().get(i));
            ImageView currentCardViewai = getImageView(playingTable.getPlayers()[1].getHand().getCards().get(i).getURL());
            ImageView currentCardView = getImageView(playingTable.getPlayers()[0].getHand().getCards().get(i).getURL());
            javafx.scene.image.Image img = new javafx.scene.image.Image(urlBack);
            PlayerImages.add(currentCardView);

            ImageView opponentsCards = new ImageView(img);
            opponentsCards.setFitHeight(160);
            opponentsCards.setFitWidth(110);

            getTopImages().getChildren().add(opponentsCards);

            HBox.setMargin(currentCardView, new javafx.geometry.Insets(10, 10, 10, 10));

            AIimages.add(currentCardViewai);
        }

        getBottomImages().getChildren().addAll(PlayerImages);

        VBox.setMargin(rows, new Insets(10, 0, 0, 0));
        getBorderPane().setBottom(getBottomImages());
        getBorderPane().setTop(getTopImages());

        borderPane1 = getBorderPane();
    }

    public BorderPane getBorderPane1() {
        return borderPane1;
    }

    private void setBackground() {
        ImageView background = new ImageView(new Image("file:resources/mainMenu/board_background.png"));
        BackgroundImage backgroundImage = new BackgroundImage(background.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));
    }

    public void refreshHands(PlayingTable playingTable, int playerWithPriority) {

        for (int i = 0; i < CardsOfPlayer.size(); i++) {
            CardsOfPlayer.remove(i);
        }
        PlayerImages.clear();
        AIimages.clear();
        getTopImages().getChildren().clear();


        bottomImages.getChildren().clear();

        for (int i = 0; i < playingTable.getPlayers()[0].getHand().getCards().size(); i++) {

            CardsOfPlayer.add(playingTable.getPlayers()[0].getHand().getCards().get(i));
//                ImageView currentCardViewai = getImageView(playingTable.getPlayers()[1].getHand().getCards().get(i).getURL());
            ImageView currentCardView = getImageView(playingTable.getPlayers()[0].getHand().getCards().get(i).getURL());
            javafx.scene.image.Image img = new javafx.scene.image.Image(urlBack);
            PlayerImages.add(currentCardView);


            ImageView opponentsCards = new ImageView(img);
            opponentsCards.setFitHeight(160);
            opponentsCards.setFitWidth(110);

            getTopImages().getChildren().add(opponentsCards);

            HBox.setMargin(currentCardView, new javafx.geometry.Insets(10, 10, 10, 10));

//            AIimages.add(currentCardViewai);
        }

        getBottomImages().getChildren().addAll(PlayerImages);

        VBox.setMargin(rows, new Insets(10, 0, 0, 0));
        getBorderPane().getChildren().remove(getBorderPane().getBottom());
        getBorderPane().setBottom(getBottomImages());
        getBorderPane().setTop(getTopImages());

        borderPane1 = getBorderPane();

        if (playingTable.checkWin() == 1 || playingTable.checkWin() == 0) {

            EndingScreenView view = new EndingScreenView();
            Stage stage = new Stage();
            Scene scene = new Scene(view);
            stage.setScene(scene);
            stage.show();
            int winner = playingTable.checkWin();
            int loser;
            if(winner == 1){
                 loser = 0;
            } else { loser = 1; }
            playingTable.getDbManager().updateGameTableEnd(playingTable.getPlayer(loser).hashCode(), playingTable.getPlayer(loser).getName(), playingTable.getPlayer(winner).hashCode(), playingTable.getPlayer(winner).getName(), String.valueOf(playingTable.getHashCode()) );
            playingTable.getDbManager().closeConnection();
        }
    }
}