package classes;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingTable {final int cardRowsSize = 4;
    Deck deck;
    CardScanner cardScanner;
    public Player[] players;
    ArrayList<Card>[] cardRows;

    String urlBack = "C:\\Users\\vasil\\Desktop\\Web\\Take5PrototipFX\\src\\main\\resources\\com\\example\\take5prototipfx\\BackgroundFinished.png";

    Button[] buttons;
    public ArrayList<Card>[] chosenCards;
    public BorderPane borderPane;
    HBox bottomImages = new HBox();
    HBox topImages = new HBox();
    VBox CenterImages = new VBox();


    HboxBuilder h1b = new HboxBuilder();


    public Integer getPlayableRows(Card card) {
        Integer[] checkedNumbers = new Integer[4];
        int cnumber = card.getNumber();

        int numberForChecking1 = cardRows[0].get(cardRows[0].size()-1).getNumber();
        int numberForChecking2 = cardRows[1].get(cardRows[1].size()-1).getNumber();
        int numberForChecking3 = cardRows[2].get(cardRows[2].size()-1).getNumber();
        int numberForChecking4 = cardRows[3].get(cardRows[3].size()-1).getNumber();

        int numberDifference1checked;
        int numberDifference2checked;
        int numberDifference3checked;
        int numberDifference4checked;
        List<Integer> integers= new ArrayList<>();


        if (numberForChecking1 < cnumber) {
            integers.add(numberForChecking1);

        }
        if (numberForChecking2 < cnumber) {
            integers.add(numberForChecking2);


        }
        if (numberForChecking3 < cnumber) {
            integers.add(numberForChecking3);



        }
        if (numberForChecking4 < cnumber) {
            integers.add(numberForChecking4);



        }

        int maximumNumberOfCard= Collections.max(integers);
        int thatWeNeed = 0;
        for (int i=0; i<4; i++){
            if (cardRows[i].get(cardRows[i].size()-1).getNumber()==maximumNumberOfCard){
                thatWeNeed= i;
            }
            System.out.println(thatWeNeed);
        }
        return thatWeNeed;

    }



    public void addToChosen(Card card){
        chosenCards[0].add(card);

    }

    public Deck getDeck() {
        return deck;
    }

    public void refreshPane(){
        VBox rows= h1b.buildRows(this);
        borderPane.setCenter(rows);

        HBox firstRow= new HBox();
        HBox secondRow= new HBox();
        HBox thirdRow= new HBox();
        HBox ForthRow= new HBox();
        VBox cardHolder= new VBox();



        // TODO: clear pane
        // append player one cards
        for (int i = 0; i < players[0].hand.cards.size(); i++){
            System.out.println(players[0].hand.cards.get(i));


            ImageView currentCardView = players[0].hand.cards.get(i).getImageView();
            javafx.scene.image.Image img= new javafx.scene.image.Image(urlBack);
            currentCardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                cardScanner = new CardScanner();
                String s= currentCardView.getImage().getUrl();
                javafx.scene.image.Image imgForCenter= new Image(s);
                ImageView currentImageWithoutClickEvent= new ImageView(imgForCenter);
                ImageView currentImageWithoutClickEvent2= new ImageView(imgForCenter);
                currentImageWithoutClickEvent2.setFitWidth(150);
                currentImageWithoutClickEvent2.setFitHeight(180);
//            cardHolder.getChildren().add(currentImageWithoutClickEvent);
                VBox vBox = new VBox(currentImageWithoutClickEvent2);
                borderPane.setRight(vBox);


                int i1= getPlayableRows(cardScanner.scanAndRetrieveCardForPlay(s));
                bottomImages.getChildren().remove(currentCardView);
                currentImageWithoutClickEvent.setFitWidth(80);
                currentImageWithoutClickEvent.setFitHeight(100);
                ((HBox)rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);
                cardRows[i1].add(cardScanner.scanAndRetrieveCardForPlay(s));
                topImages.getChildren().remove(1);
            });



            ImageView opponentsCards= new ImageView(img);
            opponentsCards.setFitHeight(160);
            opponentsCards.setFitWidth(110);

            topImages.getChildren().add(opponentsCards);
            bottomImages.getChildren().add(currentCardView);
            HBox.setMargin(currentCardView, new javafx.geometry.Insets(0, 10 , 0, 0));

        }



        VBox.setMargin(rows, new Insets(10, 0, 0, 0));
        borderPane.setBottom(bottomImages);
        borderPane.setTop(topImages);


    }

    public PlayingTable(Player human, Player AI, BorderPane borderPane) {
        this.borderPane = borderPane;
        players = new Player[2];
        players[0]= human;
        players[1]= AI;
        players[0].setTable(this);
        players[1].setTable(this);


        cardRows = new ArrayList[cardRowsSize];


        try {
            Deck deck1 = new Deck("C:\\Users\\vasil\\Desktop\\Web\\Take5PrototipFX\\src\\main\\java\\Cards");
            this.deck=deck1;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < cardRowsSize; i++) {
            cardRows[i] = new ArrayList<Card>();
            cardRows[i].add(deck.deal1());
        }
    }

    public void addCard(Card card, int rowIndex){
        cardRows[rowIndex].add(card);
    }

    public List<Card> getAllCardsFromRow(int rowIndex){
        List<Card> output = new ArrayList<>(cardRows[rowIndex]);
        cardRows[rowIndex].clear();
        return output;
    }


    public void showRows(){
        for (int i = 0; i < cardRowsSize; i++) {
            System.out.println(cardRows[i].get(0).getNumber());
        }
    }




    public void dmgCalculation (int rowIndex,Player humanPlayer){
        DmgCalculator dmgCalculator= new DmgCalculator();

        for (int i=0; i<cardRows[rowIndex].size(); i++){
            int bulls= cardRows[rowIndex].get(i).getBulls();
            dmgCalculator.takeDmg(bulls, humanPlayer);
            System.out.println(bulls);
        }

        getAllCardsFromRow(rowIndex);

        System.out.println(humanPlayer.getName() + " now you have: " + humanPlayer.getCounterPoints() + " left.");
        System.out.println("The chosen card is: " + humanPlayer.chooseCard(1).getNumber() );

        humanPlayer.placeCard(humanPlayer.chooseCard(1), rowIndex);


    }

}
