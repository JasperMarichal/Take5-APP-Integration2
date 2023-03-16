package view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PlayingTable;

public class CardPresenter {
//    private PlayingTable model;
//    private BorderPane view;
//    public CardPresenter(
//            PlayingTable model, BorderPane view) {
//        this.model = model;
//        this.view= view;
//        addEventHandlers();
//        updateView(model);
//
//
//    }
//
//
//    private void addEventHandlers(){
//
//
//
//
//    }
//
//    private void updateView(PlayingTable playingTable) {
//        String urlBack = "file:resources/take5prototipfx/bacground1Finished.jpg";
//
//        VBox rows = new HboxBuilder().buildRows(playingTable);
//        playingTable.getBorderPane().setCenter(rows);
//        HBox firstRow = new HBox();
//        HBox secondRow = new HBox();
//        HBox thirdRow = new HBox();
//        HBox ForthRow = new HBox();
//        VBox cardHolder = new VBox();
//        // TODO: clear pane
//        // append player one cards
//        for (int i = 0; i < playingTable.getPlayers()[0].getHand().getCards().size(); i++) {
//            System.out.println(playingTable.getPlayers()[0].getHand().getCards().get(i));
//
//
//            ImageView currentCardView =  playingTable.getPlayers()[0].getHand().getCards().get(i).getImageView();
//            javafx.scene.image.Image img = new javafx.scene.image.Image(urlBack);
//            currentCardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//                String s = currentCardView.getImage().getUrl();
//                javafx.scene.image.Image imgForCenter = new Image(s);
//                ImageView currentImageWithoutClickEvent = new ImageView(imgForCenter);
//                ImageView currentImageWithoutClickEvent2 = new ImageView(imgForCenter);
//                currentImageWithoutClickEvent2.setFitWidth(150);
//                currentImageWithoutClickEvent2.setFitHeight(180);
////            cardHolder.getChildren().add(currentImageWithoutClickEvent);
//                VBox vBox = new VBox(currentImageWithoutClickEvent2);
//                playingTable.getBorderPane().setRight(vBox);
//
//
//                int i1 = playingTable.getPlayableRows(playingTable.getCardScanner().scanAndRetrieveCardForPlay(s));
//                playingTable.getBottomImages().getChildren().remove(currentCardView);
//                currentImageWithoutClickEvent.setFitWidth(80);
//                currentImageWithoutClickEvent.setFitHeight(100);
//                ((HBox) rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);
//                playingTable.getCardRows()[i1].add(playingTable.getCardScanner().scanAndRetrieveCardForPlay(s));
//                playingTable.getTopImages().getChildren().remove(1);
//            });
//
//
//            ImageView opponentsCards = new ImageView(img);
//            opponentsCards.setFitHeight(160);
//            opponentsCards.setFitWidth(110);
//
//            playingTable.getTopImages().getChildren().add(opponentsCards);
//            playingTable.getBottomImages().getChildren().add(currentCardView);
//            HBox.setMargin(currentCardView, new javafx.geometry.Insets(0, 10, 0, 0));
//
//        }
//        VBox.setMargin(rows, new Insets(10, 0, 0, 0));
//        playingTable.getBorderPane().setBottom(playingTable.getBottomImages());
//        playingTable.getBorderPane().setTop(playingTable.getTopImages());
//    }
//
//
//
//}
}