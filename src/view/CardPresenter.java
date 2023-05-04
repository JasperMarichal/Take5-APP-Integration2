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
            addEventHandlers();

        }




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
                                System.out.println(aiCardRowSelectionForRetrival + "Proverka");
                                if ((model.cardChecker(model.getPlayers()[0].getHand().getCards().get(humanIndex),
                                        model.getPlayers()[1].getHand().getCards().get(aiCardIndex))) == 0) {


                                    if (i1 != null) {

                                        ((HBox) view.rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);
                                        (model.getPlayers()[0]).playCard(humanIndex, i1);
                                        view.getH1().refreshRows(model);

                                        Integer i4 = model.getPlayableRows(cAI);
                                        if (i4 != null) {
                                            System.out.println(aiCardRowSelectionForRetrival + "Proverka");

                                            ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i4);
                                            view.getH1().refreshRows(model);
                                        } else {
                                            model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                                            model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                            System.out.println(aiCardRowSelectionForRetrival + "Proverka");

                                            ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                            view.getH1().refreshRows(model);
                                            model.showRows();
                                        }
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
                                                System.out.println(aiCardRowSelectionForRetrival + "Proverka");

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
                                                System.out.println(aiCardRowSelectionForRetrival + "Proverka");

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
                                                System.out.println(aiCardRowSelectionForRetrival + "Proverka");

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
                                                System.out.println(aiCardRowSelectionForRetrival + "Proverka");

                                                ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i4);


                                            } else {
                                                model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);

                                                model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                                System.out.println(aiCardRowSelectionForRetrival + "Proverka");

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
                                        System.out.println(aiCardRowSelectionForRetrival + "Proverka");

                                        ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i2);
                                        view.getH1().refreshRows(model);


                                        Integer i3 = model.getPlayableRows(model.getCardScanner().scanAndRetrieveCardForPlay(s));
                                        if (i3 != null) {

                                            (model.getPlayers()[0]).playCard(humanIndex, i3);
                                            view.getH1().refreshRows(model);



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


                                                setCounterForLatch(0);
                                                view.getH1().refreshRows(model);
                                                model.showRows();
                                                model.checkDeck();
                                                view.refreshHands(model, 1);

                                                addEventHandlers();

                                            });
                                            forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                                model.dmgCalculationHuman(1, model.getPlayers()[0]);

                                                model.getAllCardsFromRow(1);


                                                removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);

                                                (model.getPlayers()[0]).playCard(humanIndex, 1);


                                                setCounterForLatch(0);
                                                view.getH1().refreshRows(model);
                                                model.showRows();
                                                model.checkDeck();
                                                view.refreshHands(model, 1);

                                                addEventHandlers();

                                            });
                                            forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                                model.dmgCalculationHuman(2, model.getPlayers()[0]);

                                                model.getAllCardsFromRow(2);
                                                removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);

                                                (model.getPlayers()[0]).playCard(humanIndex, 2);


                                                setCounterForLatch(0);
                                                view.getH1().refreshRows(model);
                                                model.showRows();
                                                model.checkDeck();
                                                view.refreshHands(model, 1);

                                                addEventHandlers();

                                            });
                                            forFourTHRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                                model.dmgCalculationHuman(3, model.getPlayers()[0]);

                                                model.getAllCardsFromRow(3);
                                                removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);

                                                (model.getPlayers()[0]).playCard(humanIndex, 3);


                                                setCounterForLatch(0);
                                                view.getH1().refreshRows(model);
                                                model.showRows();
                                                model.checkDeck();
                                                view.refreshHands(model, 1);

                                                addEventHandlers();
                                            });
                                            view.addButtons(forFirstRow, forSecondRow, forThirdRow, forFourTHRow);


                                        }


                                        view.getTopImages().getChildren().remove(0);


                                    } else {
                                        model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
                                        model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                        System.out.println(aiCardRowSelectionForRetrival + "Proverka");

                                        ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
                                        view.getH1().refreshRows(model);
                                        model.showRows();



                                        Integer i3 = model.getPlayableRows(model.getCardScanner().scanAndRetrieveCardForPlay(s));
                                        if (i3 != null) {

                                            (model.getPlayers()[0]).playCard(humanIndex, i3);
                                            view.getH1().refreshRows(model);



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


                                                setCounterForLatch(0);
                                                view.getH1().refreshRows(model);
                                                model.showRows();
                                                model.checkDeck();
                                                view.refreshHands(model, 1);
//                            view.borderPane1.setBottom(view.bottomImages);
                                                addEventHandlers();

                                            });
                                            forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                                model.dmgCalculationHuman(1, model.getPlayers()[0]);

                                                model.getAllCardsFromRow(1);


                                                removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);

                                                (model.getPlayers()[0]).playCard(humanIndex, 1);


                                                setCounterForLatch(0);
                                                view.getH1().refreshRows(model);
                                                model.showRows();
                                                model.checkDeck();
                                                view.refreshHands(model, 1);

                                                addEventHandlers();

                                            });
                                            forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                                model.dmgCalculationHuman(2, model.getPlayers()[0]);

                                                model.getAllCardsFromRow(2);
                                                removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);

                                                (model.getPlayers()[0]).playCard(humanIndex, 2);


                                                setCounterForLatch(0);
                                                view.getH1().refreshRows(model);
                                                model.showRows();
                                                model.checkDeck();
                                                view.refreshHands(model, 1);

                                                addEventHandlers();

                                            });
                                            forFourTHRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                                                model.dmgCalculationHuman(3, model.getPlayers()[0]);

                                                model.getAllCardsFromRow(3);
                                                removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourTHRow);

                                                (model.getPlayers()[0]).playCard(humanIndex, 3);


                                                setCounterForLatch(0);
                                                view.getH1().refreshRows(model);
                                                model.showRows();
                                                model.checkDeck();

                                                view.refreshHands(model, 1);

                                                addEventHandlers();
                                            });
                                            view.addButtons(forFirstRow, forSecondRow, forThirdRow, forFourTHRow);


                                        }
                                    }


                                    for (int x = 0; x < model.getPlayers()[0].getHand().getCards().size(); x++) {
                                        System.out.println("current hand of player: " + model.getPlayers()[0].getHand().getCards().get(x).getNumber());
                                    }


                                    model.checkDeck();
                                    view.refreshHands(model, 1);


                                    addEventHandlers();


                                }
                            }

                           }

                );
            }

        }}

    