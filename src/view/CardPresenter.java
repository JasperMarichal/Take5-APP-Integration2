package view;

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
        this.model = model;
        this.view = view;
        this.stage= stage;
        view.buildScene1(model,stage);
        addEventHandlersForButton();
//        addEventHandlers();
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
            Card c= model.getPlayers()[1].getHand().getCards().get(i);
                int y=i;
                ImageView currentCard= (view.PlayerImages.get(i));
            ((ImageView)currentCard).addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    if (getCounterForLatch()==0){
                        int humanIndex= (model.getPlayers()[1]).getTheSelectedCardFromHand(c);

                        String s=    currentCard.getImage().getUrl();
                        javafx.scene.image.Image imgForCenter= new Image(s);


                        ImageView currentImageWithoutClickEvent= new ImageView(imgForCenter);
                        ImageView currentImageWithoutClickEvent2= new ImageView(imgForCenter);
                        currentImageWithoutClickEvent2.setFitWidth(150);
                        currentImageWithoutClickEvent2.setFitHeight(180);
//            cardHolder.getChildren().add(currentImageWithoutClickEvent);
                        VBox vBox = new VBox(currentImageWithoutClickEvent2);
                        model.getBorderPane().setRight(vBox);


                        Integer i1= model.getPlayableRows(model.getCardScanner().scanAndRetrieveCardForPlay(s));
                        model.getBottomImages().getChildren().remove(   currentCard);
                        currentImageWithoutClickEvent.setFitWidth(80);
                        currentImageWithoutClickEvent.setFitHeight(100);
                        if (i1!= null){
//                            model.getCardRows()[i1].add(model.getCardScanner().scanAndRetrieveCardForPlay(s));
                            ((HBox)view.rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);
                            (model.getPlayers()[0]).playCard(humanIndex,i1);
                            view.getH1().refreshRows(model);
//                    playingTable.CollectCardRows(i1);
                            int aiCardIndex= ((AiPlayer)(model.getPlayers()[1])).cardPlayable();
                            ImageView AIimgview=getImageView(((AiPlayer)(model.getPlayers()[1])).getCard(aiCardIndex).getURL());
                            AIimgview.setFitHeight(100);
                            AIimgview.setFitWidth(80);
                            String aistring=AIimgview.getImage().getUrl();
                            Card cAI= ((AiPlayer)(model.getPlayers()[1])).getHand().getCards().get(aiCardIndex);
                            Integer i2= model.getPlayableRows(cAI);
                            int aiCardRowSelectionForRetrival= ((AiPlayer)(model.getPlayers()[1])).cardRowNumberForReplacement();
                            System.out.println(aiCardRowSelectionForRetrival + "Proverka");
                            if (i2!= null){
                                System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,i2);
                                view.getH1().refreshRows(model);
                            }
                            else {
                                model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,aiCardRowSelectionForRetrival);
                                view.getH1().refreshRows(model);
                                model.showRows();
                            }
                        }
                        else {
                            setCounterForLatch(1);

                            Button forFirstRow= new Button();
                            Button forSecondRow= new Button();
                            Button forThirdRow= new Button();
                            Button forFourTHRow= new Button();


                            forFirstRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->{

                                model.getAllCardsFromRow(0);
                              removeChildren(view,forFirstRow,forSecondRow,forThirdRow,forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(0)).getChildren().removeAll();
//                                ((HBox)view.rows.getChildren().get(0)).getChildren().add(currentImageWithoutClickEvent);
                                (model.getPlayers()[0]).playCard(humanIndex,0);


                                int aiCardIndex= ((AiPlayer)(model.getPlayers()[1])).cardPlayable();
                                ImageView AIimgview=getImageView(((AiPlayer)(model.getPlayers()[1])).getCard(aiCardIndex).getURL());
                                AIimgview.setFitHeight(100);
                                AIimgview.setFitWidth(80);
                                String aistring=AIimgview.getImage().getUrl();
                                Card cAI= ((AiPlayer)(model.getPlayers()[1])).getHand().getCards().get(aiCardIndex);
                                Integer i2= model.getPlayableRows(cAI);
                                int aiCardRowSelectionForRetrival= ((AiPlayer)(model.getPlayers()[1])).cardRowNumberForReplacement();
                                if (i2!= null){
//                                    model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                    ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                    ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,i2);

                                }
                                else {
                                    model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                    System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                    ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,aiCardRowSelectionForRetrival);

                                }
                                setCounterForLatch(0);
                                view.getH1().refreshRows(model);
                                model.showRows();

                            });
                            forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->{

                                model.getAllCardsFromRow(1);


                                removeChildren(view,forFirstRow,forSecondRow,forThirdRow,forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(1)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(1)).getChildren().add(currentImageWithoutClickEvent);
                                (model.getPlayers()[0]).playCard(humanIndex,1);


                                int aiCardIndex= ((AiPlayer)(model.getPlayers()[1])).cardPlayable();
                                ImageView AIimgview=getImageView(((AiPlayer)(model.getPlayers()[1])).getCard(aiCardIndex).getURL());
                                AIimgview.setFitHeight(100);
                                AIimgview.setFitWidth(80);
                                String aistring=AIimgview.getImage().getUrl();
                                Card cAI= ((AiPlayer)(model.getPlayers()[1])).getHand().getCards().get(aiCardIndex);
                                Integer i2= model.getPlayableRows(cAI);
                                int aiCardRowSelectionForRetrival= ((AiPlayer)(model.getPlayers()[1])).cardRowNumberForReplacement();
                                if (i2!= null){
//                                    model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                    ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                    ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,i2);

                                }
                                else {
                                    model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                    System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                    ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,aiCardRowSelectionForRetrival);
                                }

                                setCounterForLatch(0);
                                view.getH1().refreshRows(model);
                                model.showRows();

                            });
                            forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->{

                                model.getAllCardsFromRow(3);
                                removeChildren(view,forFirstRow,forSecondRow,forThirdRow,forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(2)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(2)).getChildren().add(currentImageWithoutClickEvent);
                                (model.getPlayers()[0]).playCard(humanIndex,2);
                                int aiCardIndex= ((AiPlayer)(model.getPlayers()[1])).cardPlayable();
                                ImageView AIimgview=getImageView(((AiPlayer)(model.getPlayers()[1])).getCard(aiCardIndex).getURL());
                                AIimgview.setFitHeight(100);
                                AIimgview.setFitWidth(80);
                                String aistring=AIimgview.getImage().getUrl();
                                Card cAI= ((AiPlayer)(model.getPlayers()[1])).getHand().getCards().get(aiCardIndex);
                                Integer i2= model.getPlayableRows(cAI);
                                int aiCardRowSelectionForRetrival= ((AiPlayer)(model.getPlayers()[1])).cardRowNumberForReplacement();
                                if (i2!= null){
//                                    model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                    ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                    model.getPlayers()[1].playCard(aiCardIndex,i2);

                                }
                                else {
                                    model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                    System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                    ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,aiCardRowSelectionForRetrival);
                                }

                                setCounterForLatch(0);
                                view.getH1().refreshRows(model);
                                model.showRows();

                            });
                            forFourTHRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->{

                                model.getAllCardsFromRow(3);
                                removeChildren(view,forFirstRow,forSecondRow,forThirdRow,forFourTHRow);
//                                ((HBox)view.rows.getChildren().get(3)).getChildren().clear();
//                                ((HBox)view.rows.getChildren().get(3)).getChildren().add(currentImageWithoutClickEvent);
                                (model.getPlayers()[0]).playCard(humanIndex,3);



                                int aiCardIndex= ((AiPlayer)(model.getPlayers()[1])).cardPlayable();
                                ImageView AIimgview=getImageView(((AiPlayer)(model.getPlayers()[1])).getCard(aiCardIndex).getURL());
                                AIimgview.setFitHeight(100);
                                AIimgview.setFitWidth(80);
                                String aistring=AIimgview.getImage().getUrl();
                                Card cAI= ((AiPlayer)(model.getPlayers()[1])).getHand().getCards().get(aiCardIndex);
                                Integer i2= model.getPlayableRows(cAI);
                                int aiCardRowSelectionForRetrival= ((AiPlayer)(model.getPlayers()[1])).cardRowNumberForReplacement();
                                if (i2!= null){
                                    System.out.println(aiCardRowSelectionForRetrival + "Proverka");
                                    model.getCardRows()[i2].add(model.getCardScanner().scanAndRetrieveCardForPlay(aistring));
//                                    ((HBox)view.rows.getChildren().get(i2)).getChildren().add(AIimgview);
                                    ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,i2);


                                }
                                else {
                                    model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
                                    System.out.println(aiCardRowSelectionForRetrival + "Proverka");
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().clear();
//                                    ((HBox)view.rows.getChildren().get(aiCardRowSelectionForRetrival)).getChildren().add(AIimgview);
                                    ((AiPlayer)(model.getPlayers()[1])).playCard(aiCardIndex,aiCardRowSelectionForRetrival);


                                }

                                setCounterForLatch(0);
                                view.getH1().refreshRows(model);
                                model.showRows();

                            });
                            view.addButtons(forFirstRow,forSecondRow,forThirdRow,forFourTHRow);




                        }


                        model.getTopImages().getChildren().remove(1);

                    }


                }


        );
    }

}}
