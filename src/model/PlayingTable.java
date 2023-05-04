package model;

import view.take5view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayingTable {final int cardRowsSize = 4;
    private int TimerCounter= 0;


    public int getTimerCounter() {
        return TimerCounter;
    }

    public void setTimerCounter(int timerCounter) {
        TimerCounter = timerCounter;
    }

    private TimeCounter timeCounter;

    private CardChecker cardChecker= new CardChecker(this);
    static int counterForLatch=0;
    Deck deck;
    Integer maximumNumberOfCard=null;
    CardScanner cardScanner = new CardScanner();
    Player[] players;
    ArrayList<Card>[] cardRows;
    String urlBack = "file:resources/take5prototipfx/bacground1Finished.jpg";
    ArrayList<Card>[] chosenCards;



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

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public void CollectCardRows (int index){
        cardRows[index].clear();

    }
    public ArrayList<Card>[] getChosenCards() {
        return chosenCards;
    }

    public void checkDeck() {
        try {
            Deck deck1 = new Deck("src/Cards");
            this.deck=deck1;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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




    public PlayingTable(Player human, Player AI) {
//        this.borderPane = borderPane;
        timeCounter= new TimeCounter(this );

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
        if (rowIndex>3 || rowIndex<0 ){
            rowIndex=0;
        }
        cardRows[rowIndex].add(card);
    }

    public List<Card> getAllCardsFromRow(int rowIndex){
        if (rowIndex>3 || rowIndex<0 ){
            rowIndex=0;
        }
        List<Card> output = new ArrayList<>(cardRows[rowIndex]);
        cardRows[rowIndex].clear();
        return output;
    }

    public void showRows(){
        for (int i = 0; i < cardRowsSize; i++) {
            System.out.println(cardRows[i].get(0).getNumber());
        }
    }

    public void dmgCalculationHuman (int rowIndex,Player humanPlayer) {
        DmgCalculator dmgCalculator = new DmgCalculator();

        for (int i = 0; i < cardRows[rowIndex].size(); i++) {
            int bulls = cardRows[rowIndex].get(i).getBulls();
            dmgCalculator.takeDmgHuman(bulls, this);
            System.out.println(bulls);
        }
    }
        public void dmgCalculationAI (int rowIndex,Player AiPlayer){
            DmgCalculator dmgCalculator= new DmgCalculator();
if (rowIndex>3 || rowIndex<0 ){
    rowIndex=0;
}
            for (int i=0; i<cardRows[rowIndex].size(); i++){
                int bulls= cardRows[rowIndex].get(i).getBulls();
                dmgCalculator.takeDmgAI(bulls, this);
                System.out.println(bulls);
            }



    }



    public int checkWin() {
        if (getPlayers()[0].getCounterPoints()<=0) {
            return 0;

        }
        if (getPlayers()[1].getCounterPoints()<=0){
            return 1;

        }
      return 2;
    }
    public int cardChecker(Card human, Card AI){
        if (human.getNumber()<AI.getNumber()){
            return 0;
        }
        else{
            return 1;
    }
}}