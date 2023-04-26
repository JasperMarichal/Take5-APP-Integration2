package model;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.HboxBuilder;
import view.take5view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingTable {final int cardRowsSize = 4;


    private CardChecker cardChecker= new CardChecker(this);
    static int counterForLatch=0;
    Deck deck;
    Integer maximumNumberOfCard=null;
     CardScanner cardScanner = new CardScanner();
     Player[] players;
     ArrayList<Card>[] cardRows;
    String urlBack = "file:resources/take5prototipfx/bacground1Finished.jpg";
     ArrayList<Card>[] chosenCards;
     BorderPane borderPane;
     HBox bottomImages = new HBox();
     HBox topImages = new HBox();
    VBox CenterImages = new VBox();
    HboxBuilder h1b = new HboxBuilder();


    take5view take5view= new take5view();

    public  int getCounterForLatch() {
        return counterForLatch;
    }

    public  void setCounterForLatch(int counterForLatch) {
        PlayingTable.counterForLatch = counterForLatch;
    }

    public int getCardRowsSize() {
        return cardRowsSize;
    }

    public CardScanner getCardScanner() {
        return cardScanner;
    }

    public Player[] getPlayers() {
        return players;
    }

    public ArrayList<Card>[] getCardRows() {
        return cardRows;
    }

    public String getUrlBack() {
        return urlBack;
    }


    public void CollectCardRows (int index){
        cardRows[index].clear();

    }
    public ArrayList<Card>[] getChosenCards() {
        return chosenCards;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public HBox getBottomImages() {
        return bottomImages;
    }

    public HBox getTopImages() {
        return topImages;
    }

    public VBox getCenterImages() {
        return CenterImages;
    }

    public HboxBuilder getH1b() {
        return h1b;
    }


    public Integer getPlayableRows(Card card) {
        Integer[] checkedNumbers = new Integer[4];
        int cnumber = card.getNumber();
        int numberForChecking1=-5;
        int numberForChecking2=-5;
        int numberForChecking3=-5;
        int numberForChecking4=-5;



        if (cardRows[0].size()<5){
          numberForChecking1 = cardRows[0].get(cardRows[0].size()-1).getNumber();
        }
        if (cardRows[1].size()<5){
            numberForChecking2 = cardRows[1].get(cardRows[1].size()-1).getNumber();
        }
        if (cardRows[2].size()<5){
            numberForChecking3 = cardRows[2].get(cardRows[2].size()-1).getNumber();
        }
        if (cardRows[3].size()<5){
            numberForChecking4 = cardRows[3].get(cardRows[3].size()-1).getNumber();
        }
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

        if (integers.isEmpty()){
            return null;
        }
        else {
            maximumNumberOfCard = Collections.max(integers);
            Integer thatWeNeed = null;
            for (int i = 0; i < 4; i++) {
                if (cardRows[i].get(cardRows[i].size() - 1).getNumber() == maximumNumberOfCard) {
                    thatWeNeed = i;
                }
//            System.out.println(thatWeNeed);
            }
            return thatWeNeed;
        }





    }

    public int getMaximumNumberOfCard() {
        return maximumNumberOfCard;
    }

    public void addToChosen(Card card){
        chosenCards[0].add(card);
    }
    public Deck getDeck() {
        return deck;
    }

    public void refreshPane(){

//        view.take5view.buildBorderPane(this);

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
            Deck deck1 = new Deck("src/Cards");
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



    }

    public Card cardChecker(Card c, Card b){
        if (c.getNumber()<b.getNumber()){
            return c;
        }
        else return b;
    }
}