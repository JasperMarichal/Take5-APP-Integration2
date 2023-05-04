package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.AiPlayer;
import model.Card;
import model.PlayingTable;

import javax.swing.text.View;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static view.take5view.getImageView;
import static view.take5view.h1;

public class CardPresenter {
    private PlayingTable model;
    int counterForLatch=0;

    private take5view view;
    Stage stage= new Stage();
    public CardPresenter(
            PlayingTable model, take5view view, Stage stage) {
        int intloop= 1 ;
        this.model = model;
        this.view = view;
        this.stage= stage;
        view.buildScene1(model,stage);
//        addEventHandlersForButton();
        addEventHandlers();
//        updateView(model);

        }



//    public Card getCardFromArrayListOfImageViews(List<ImageView> list , String URL ){

    //        for (int i=0; i<list.size(); i++){
//            Card c;
//                String s1URL=
//            String s1 = c.getURL();
//            String s2;
//            if ()
//            s1 = s2.toLowerCase();
//            s2 = s2.toLowerCase();
//
//            // check if length is same
//            if (s2.length() == s2.length()) {
//
//                // convert strings to char array
//                char[] charArray1 = s1.toCharArray();
//                char[] charArray2 = s2.toCharArray();
//
//                // if sorted char arrays are same
//                // then the string is anagram
//                boolean result = Arrays.equals(charArray1, charArray2);
//
//                if (result == true) {
//                    c = deck.getCards().get(i);
//                }
//        }
//    }
    public void setCounterForLatch(int counterForLatch) {
        this.counterForLatch = counterForLatch;
    }

    public int getCounterForLatch() {
        return counterForLatch;
    }

    public void addEventHandlersForButton(){
        view.button.addEventHandler(MouseEvent.MOUSE_CLICKED, event2 ->{

            view.buildBorderPane(model);
            Scene scene1 = new Scene(view.getBorderPane1());
            stage.setScene(scene1);
            addEventHandlers();
        });}
    void removeChildren(take5view view, Button button1, Button button2, Button button3, Button button4){
        ((HBox)view.rows.getChildren().get(0)).getChildren().removeAll(button1);
        ((HBox)view.rows.getChildren().get(1)).getChildren().removeAll(button2);
        ((HBox)view.rows.getChildren().get(2)).getChildren().removeAll(button3);
        ((HBox)view.rows.getChildren().get(3)).getChildren().removeAll(button4);
    }
    public void addEventHandlers(){


        for (int i=0; i<view.PlayerImages.size(); i++){
            System.out.println(i);
            Card c= model.getPlayers()[0].getHand().getCards().get(i);
            int y=i;
            ImageView currentCard= (view.PlayerImages.get(i));
            ((ImageView)currentCard).addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                ///// TODO hasu
                model.getDbManager().addMove(String.valueOf(model.getHashCode()),String.valueOf(model.getPlayer(0).hashCode()), model.getPlayer(0).getCounterPoints());
                   if (getCounterForLatch() == 0) {
                       int humanIndex = model.getPlayers()[0].getTheSelectedCardFromHand(c);

                       String s = currentCard.getImage().getUrl();
                       javafx.scene.image.Image imgForCenter = new Image(s);


                       ImageView currentImageWithoutClickEvent = new ImageView(imgForCenter);
                       ImageView currentImageWithoutClickEvent2 = new ImageView(imgForCenter);
                       currentImageWithoutClickEvent2.setFitWidth(150);
                       currentImageWithoutClickEvent2.setFitHeight(180);
//            view.cardHolder.getChildren().add(currentImageWithoutClickEvent);
                       VBox vBox = new VBox(currentImageWithoutClickEvent2);
//                        model.getBorderPane().setRight();


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
                       System.out.println(aiCardRowSelectionForRetrival + "Proverka");
                       if ((model.cardChecker(model.getPlayers()[0].getHand().getCards().get(humanIndex),
                               model.getPlayers()[1].getHand().getCards().get(aiCardIndex))) == 0) {


                           if (i1 != null) {
//                            model.getCardRows()[i1].add(model.getCardScanner().scanAndRetrieveCardForPlay(s));
                               ((HBox) view.rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);
                               (model.getPlayers()[0]).playCard(humanIndex, i1);
                               view.getH1().refreshRows(model);
//                    playingTable.CollectCardRows(i1);

                               if (i2 != null) {
                                   System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                   ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i2);
                                   view.getH1().refreshRows(model);
                               } else {
                                   model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                                   model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                   System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                   ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                   view.getH1().refreshRows(model);
                                   model.showRows();
                               }
                               view.refreshHands(model,0);
//                            view.borderPane1.setBottom(view.bottomImages);
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
//                                ((HBox)view.rows.getChildren().get(0)).getChildren().removeAll();
//                                ((HBox)view.rows.getChildren().get(0)).getChildren().add(currentImageWithoutClickEvent);
                                   (model.getPlayers()[0]).playCard(humanIndex, 0);


                                   if (i2 != null) {
//                                    model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                    ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                       ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i2);

                                   } else {
                                       model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);

                                       model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                       System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                       ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);

                                   }
                                   setCounterForLatch(0);
                                   view.getH1().refreshRows(model);
                                   model.showRows();
                                   view.refreshHands(model,0);
//                            view.borderPane1.setBottom(view.bottomImages);
                                   addEventHandlers();

                               });
                               forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                   model.dmgCalculationHuman(1, model.getPlayers()[0]);

                                   model.getAllCardsFromRow(1);


                                   removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(1)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(1)).getChildren().add(currentImageWithoutClickEvent);
                                   (model.getPlayers()[0]).playCard(humanIndex, 1);


                                   if (i2 != null) {
//                                    model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                    ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                       ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i2);

                                   } else {
                                       model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);

                                       model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                       System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                       ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                   }

                                   setCounterForLatch(0);
                                   view.getH1().refreshRows(model);
                                   model.showRows();
                                   view.refreshHands(model,0);
//                            view.borderPane1.setBottom(view.bottomImages);
                                   addEventHandlers();

                               });
                               forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                   model.dmgCalculationHuman(2, model.getPlayers()[0]);

                                   model.getAllCardsFromRow(2);
                                   removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(2)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(2)).getChildren().add(currentImageWithoutClickEvent);
                                   (model.getPlayers()[0]).playCard(humanIndex, 2);

                                   if (i2 != null) {
//                                    model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                    ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                       model.getPlayers()[1].playCard(aiCardIndex, i2);

                                   } else {
                                       model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);

                                       model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                       System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                       ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                   }

                                   setCounterForLatch(0);
                                   view.getH1().refreshRows(model);
                                   model.showRows();
                                   view.refreshHands(model,0);
//                            view.borderPane1.setBottom(view.bottomImages);
                                   addEventHandlers();

                               });
                               forFourTHRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                   model.dmgCalculationHuman(3, model.getPlayers()[0]);

                                   model.getAllCardsFromRow(3);
                                   removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(3)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(3)).getChildren().add(currentImageWithoutClickEvent);
                                   (model.getPlayers()[0]).playCard(humanIndex, 3);


                                   if (i2 != null) {
                                       System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                       model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                    ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                       ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i2);


                                   } else {
                                       model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);

                                       model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                       System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                       ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);


                                   }

                                   setCounterForLatch(0);
                                   view.getH1().refreshRows(model);
                                   model.showRows();

                                   view.refreshHands(model,0);
//                            view.borderPane1.setBottom(view.bottomImages);
                                   addEventHandlers();
                               });
                               view.addButtons(forFirstRow, forSecondRow, forThirdRow, forFourTHRow);


                           }


                           view.getTopImages().getChildren().remove(0);


                       } else {
                           if (i2 != null) {
                               System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                               ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i2);
                               view.getH1().refreshRows(model);
                               if (i1 != null) {
//                            model.getCardRows()[i1].add(model.getCardScanner().scanAndRetrieveCardForPlay(s));
                                   ((HBox) view.rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);
                                   (model.getPlayers()[0]).playCard(humanIndex, i1);
                                   view.getH1().refreshRows(model);
//                    playingTable.CollectCardRows(i1);


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
//                                ((HBox)view.rows.getChildren().get(0)).getChildren().removeAll();
//                                ((HBox)view.rows.getChildren().get(0)).getChildren().add(currentImageWithoutClickEvent);
                                       (model.getPlayers()[0]).playCard(humanIndex, 0);


                                       setCounterForLatch(0);
                                       view.getH1().refreshRows(model);
                                       model.showRows();
                                       view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                       addEventHandlers();

                                   });
                                   forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                       model.dmgCalculationHuman(1, model.getPlayers()[0]);

                                       model.getAllCardsFromRow(1);


                                       removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(1)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(1)).getChildren().add(currentImageWithoutClickEvent);
                                       (model.getPlayers()[0]).playCard(humanIndex, 1);


                                       setCounterForLatch(0);
                                       view.getH1().refreshRows(model);
                                       model.showRows();
                                       view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                       addEventHandlers();

                                   });
                                   forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                       model.dmgCalculationHuman(2, model.getPlayers()[0]);

                                       model.getAllCardsFromRow(2);
                                       removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(2)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(2)).getChildren().add(currentImageWithoutClickEvent);
                                       (model.getPlayers()[0]).playCard(humanIndex, 2);


                                       setCounterForLatch(0);
                                       view.getH1().refreshRows(model);
                                       model.showRows();
                                       view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                       addEventHandlers();

                                   });
                                   forFourTHRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                       model.dmgCalculationHuman(3, model.getPlayers()[0]);

                                       model.getAllCardsFromRow(3);
                                       removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(3)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(3)).getChildren().add(currentImageWithoutClickEvent);
                                       (model.getPlayers()[0]).playCard(humanIndex, 3);


                                       setCounterForLatch(0);
                                       view.getH1().refreshRows(model);
                                       model.showRows();

                                       view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                       addEventHandlers();
                                   });
                                   view.addButtons(forFirstRow, forSecondRow, forThirdRow, forFourTHRow);


                               }


                               view.getTopImages().getChildren().remove(0);

                           } else {
                               model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                               model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                               System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                               ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                               view.getH1().refreshRows(model);
                               model.showRows();




                               if (i1 != null) {
//                            model.getCardRows()[i1].add(model.getCardScanner().scanAndRetrieveCardForPlay(s));
                                   ((HBox) view.rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);
                                   (model.getPlayers()[0]).playCard(humanIndex, i1);
                                   view.getH1().refreshRows(model);
//                    playingTable.CollectCardRows(i1);


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
//                                ((HBox)view.rows.getChildren().get(0)).getChildren().removeAll();
//                                ((HBox)view.rows.getChildren().get(0)).getChildren().add(currentImageWithoutClickEvent);
                                       (model.getPlayers()[0]).playCard(humanIndex, 0);


                                       setCounterForLatch(0);
                                       view.getH1().refreshRows(model);
                                       model.showRows();
                                       view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                       addEventHandlers();

                                   });
                                   forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                       model.dmgCalculationHuman(1, model.getPlayers()[0]);

                                       model.getAllCardsFromRow(1);


                                       removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(1)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(1)).getChildren().add(currentImageWithoutClickEvent);
                                       (model.getPlayers()[0]).playCard(humanIndex, 1);


                                       setCounterForLatch(0);
                                       view.getH1().refreshRows(model);
                                       model.showRows();
                                       view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                       addEventHandlers();

                                   });
                                   forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                       model.dmgCalculationHuman(2, model.getPlayers()[0]);

                                       model.getAllCardsFromRow(2);
                                       removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(2)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(2)).getChildren().add(currentImageWithoutClickEvent);
                                       (model.getPlayers()[0]).playCard(humanIndex, 2);


                                       setCounterForLatch(0);
                                       view.getH1().refreshRows(model);
                                       model.showRows();
                                       view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                       addEventHandlers();

                                   });
                                   forFourTHRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                       model.dmgCalculationHuman(3, model.getPlayers()[0]);

                                       model.getAllCardsFromRow(3);
                                       removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(3)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(3)).getChildren().add(currentImageWithoutClickEvent);
                                       (model.getPlayers()[0]).playCard(humanIndex, 3);


                                       setCounterForLatch(0);
                                       view.getH1().refreshRows(model);
                                       model.showRows();

                                       view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                       addEventHandlers();
                                   });
                                   view.addButtons(forFirstRow, forSecondRow, forThirdRow, forFourTHRow);


                               }
                           }




                           for (int x = 0; x < model.getPlayers()[0].getHand().getCards().size(); x++) {
                               System.out.println("current hand of player: " + model.getPlayers()[0].getHand().getCards().get(x).getNumber());
                           }
//                        if (model.getPlayers()[0].getHand().getCards().isEmpty()) {
//                            model.getPlayers()[0].draw(model.getDeck());
//                            model.getPlayers()[1].draw(model.getDeck());
//                            for (int x = 0; x < model.getPlayers()[0].getHand().getCards().size(); x++) {
//                                System.out.println("refreshed hand of player: " + model.getPlayers()[0].getHand().getCards().get(x).getNumber());
//                            }
                           view.refreshHands(model, 1);

//                            view.borderPane1.setBottom(view.bottomImages);
                           addEventHandlers();


                       }
                    }

                            }

            );
        }

    }}