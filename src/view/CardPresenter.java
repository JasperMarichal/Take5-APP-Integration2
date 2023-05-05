package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AiPlayer;
import model.Card;
import model.PlayingTable;

import model.EventHandler;
import static view.Take5View.getImageView;

public class CardPresenter {
    private static PlayingTable model;
    static int counterForLatch=0;
    private static Take5View view;
    Stage stage= new Stage();
    public CardPresenter(PlayingTable model, Take5View view, Stage stage) {
        int intloop= 1 ;
        this.model = model;
        this.view = view;
        this.stage= stage;
        view.buildScene1(model,stage);
        addEventHandlers();
    }

    public static void setCounterForLatch(int counterForLatch) {
        counterForLatch = counterForLatch;
    }

    public static int getCounterForLatch() {
            return counterForLatch;
    }
    public void addEventHandlersForButton(){
        view.button.addEventHandler(MouseEvent.MOUSE_CLICKED, event2 ->{
            view.buildBorderPane(model);
            Scene scene1 = new Scene(view.getBorderPane1());
            stage.setScene(scene1);
            addEventHandlers();
        });
    }
    static void removeChildren(Take5View view, Button button1, Button button2, Button button3, Button button4){
        ((HBox)view.rows.getChildren().get(0)).getChildren().removeAll(button1);
        ((HBox)view.rows.getChildren().get(1)).getChildren().removeAll(button2);
        ((HBox)view.rows.getChildren().get(2)).getChildren().removeAll(button3);
        ((HBox)view.rows.getChildren().get(3)).getChildren().removeAll(button4);
    }
    public static void addEventHandlers(){
        for (int i=0; i<view.PlayerImages.size(); i++){
            Card c= model.getPlayers()[0].getHand().getCards().get(i);
            int y=i;
            ImageView currentCard= (view.PlayerImages.get(i));
            ((ImageView)currentCard).addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                model.getDbManager().addMove(model.getHashCode(), String.valueOf(model.getPlayer(0).hashCode()), model.getPlayer(0).getCounterPoints());
                if (getCounterForLatch() == 0) {
                    int humanIndex = model.getPlayers()[0].getTheSelectedCardFromHand(c);
                     String s = currentCard.getImage().getUrl();
                     javafx.scene.image.Image imgForCenter = new Image(s);

                     ImageView currentImageWithoutClickEvent = new ImageView(imgForCenter);
                     ImageView currentImageWithoutClickEvent2 = new ImageView(imgForCenter);
                     currentImageWithoutClickEvent2.setFitWidth(150);
                     currentImageWithoutClickEvent2.setFitHeight(180);
                     VBox vBox = new VBox(currentImageWithoutClickEvent2);

                     Integer i1 = model.getPlayableRows(model.getCardScanner().scanAndRetrieveCardForPlay(s));
                     view.getBottomImages().getChildren().remove(currentCard);
                     currentImageWithoutClickEvent.setFitWidth(80);
                     currentImageWithoutClickEvent.setFitHeight(100);
                     int aiCardIndex = ((AiPlayer) (model.getPlayers()[1])).cardPlayable();
                     ImageView AIimgview = getImageView(((AiPlayer) (model.getPlayers()[1])).getCard(aiCardIndex).getURL());
                     AIimgview.setFitHeight(100);
                     AIimgview.setFitWidth(80);
                     String aistring = AIimgview.getImage().getUrl();
                     Card cAI = ((AiPlayer) (model.getPlayers()[1])).getHand().getCards().get(aiCardIndex);
                     Integer i2 = model.getPlayableRows(cAI);
                     int aiCardRowSelectionForRetrival = ((AiPlayer) (model.getPlayers()[1])).cardRowNumberForReplacement();
                     if ((model.cardChecker(model.getPlayers()[0].getHand().getCards().get(humanIndex),
                             model.getPlayers()[1].getHand().getCards().get(aiCardIndex))) == 0) {
                         if (i1 != null) {
                             ((HBox) view.rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);
                             (model.getPlayers()[0]).playCard(humanIndex, i1);
                             view.getH1().refreshRows(model);

                             Integer i4 = model.getPlayableRows(cAI);
                             if (i4 != null) {

                                 ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i4);
                             } else {
                                 model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                                 model.getAllCardsFromRow(aiCardRowSelectionForRetrival);

                                 ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                             }
                             view.getH1().refreshRows(model);
                             model.showRows();
                             model.checkDeck();
                             view.refreshHands(model,0);
                             addEventHandlers();
                         } else {
                             setCounterForLatch(1);

                             Button forFirstRow = new Button();
                             Button forSecondRow = new Button();
                             Button forThirdRow = new Button();
                             Button forFourTHRow = new Button();

                             forFirstRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                 model.dmgCalculationHuman(0, model.getPlayers()[0]);
                                 model.getAllCardsFromRow(0);
                                 removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);

                                 (model.getPlayers()[0]).playCard(humanIndex, 0);
                                 Integer i4 = model.getPlayableRows(cAI);
                                 if (i4 != null) {
                                     ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i4);
                                 } else {
                                     model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);

                                     model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                     ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                 }
                                 setCounterForLatch(0);
                                 view.getH1().refreshRows(model);
                                 model.showRows();
                                 model.checkDeck();
                                 view.refreshHands(model,0);
                                 addEventHandlers();
                             });
                             forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                 model.dmgCalculationHuman(1, model.getPlayers()[0]);
                                 model.getAllCardsFromRow(1);
                                 removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
                                 (model.getPlayers()[0]).playCard(humanIndex, 1);
                                 Integer i4 = model.getPlayableRows(cAI);
                                 if (i4 != null) {
                                     ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i4);
                                 } else {
                                     model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                                     model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                     ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                 }
                                 setCounterForLatch(0);
                                 view.getH1().refreshRows(model);
                                 model.showRows();
                                 model.checkDeck();
                                 view.refreshHands(model,0);
                                 addEventHandlers();
                             });
                             forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                 model.dmgCalculationHuman(2, model.getPlayers()[0]);
                                 model.getAllCardsFromRow(2);
                                 removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
                                 (model.getPlayers()[0]).playCard(humanIndex, 2);
                                 Integer i4 = model.getPlayableRows(cAI);
                                 if (i4 != null) {
                                     model.getPlayers()[1].playCard(aiCardIndex, i4);
                                 } else {
                                     model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                                     model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                     ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                 }
                                 setCounterForLatch(0);
                                 view.getH1().refreshRows(model);
                                 model.showRows();
                                 model.checkDeck();
                                 view.refreshHands(model,0);
                                 addEventHandlers();
                             });
                             forFourTHRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                 model.dmgCalculationHuman(3, model.getPlayers()[0]);
                                 model.getAllCardsFromRow(3);
                                 removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
                                 (model.getPlayers()[0]).playCard(humanIndex, 3);
                                 Integer i4 = model.getPlayableRows(cAI);
                                 if (i4 != null) {
                                     ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i4);
                                 } else {
                                     model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                                     model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                     ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                 }
                                 setCounterForLatch(0);
                                 view.getH1().refreshRows(model);
                                 model.showRows();
                                 model.checkDeck();
                                 view.refreshHands(model,0);
                                 addEventHandlers();
                             });
                             view.addButtons(forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
                         }
                         view.getTopImages().getChildren().remove(0);
                     } else {
                         if (i2 != null) {
                             ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i2);
                             view.getH1().refreshRows(model);

                             EventHandler.fix1(model, view, humanIndex,s);
                             view.getTopImages().getChildren().remove(0);


                         } else {
                             model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                             model.getAllCardsFromRow(aiCardRowSelectionForRetrival);

                             ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                             view.getH1().refreshRows(model);
                             model.showRows();

                             EventHandler.fix1(model, view, humanIndex,s);

                         }
                         model.checkDeck();
                         view.refreshHands(model, 1);
                         addEventHandlers();
//                                    model.getSaver().initializeDatabaseSave(model);
                     }
                }
            });
        }
    }
}