package model;

import java.util.List;

public abstract class Player{
    public String name;
    PlayingTable table;
    Hand hand;
    Card card;
    private int counterPoints;

    public void setTable(PlayingTable table) {
        this.table = table;
    }



    public int getTheSelectedCardFromHand(Card card){
        int y = 0;
        for (int i=0; i<this.hand.cards.size(); i++){
            if (this.hand.cards.get(i).getNumber()==card.getNumber()){
                y=i;
            }

        }
        return y;
    }


    public void playCardForHuman(Card card, int cardrow){
        int index = getTheSelectedCardFromHand(card);
        Card c= this.getHand().getCards().get(index);
        table.addCard(c, cardrow);
        this.getHand().getCards().remove(c);
    }
    public Player(String name, int counterPoints) {
        this.name = name;
        this.hand= new Hand();
        this.counterPoints=counterPoints;

    }
    public void playCard(int index, int cardrow) {
        Card c = this.getHand().getCards().get(index);
        table.addCard(c, cardrow);
        this.getHand().getCards().remove(c);
        if (this.hand.cards.isEmpty()) {
            draw(table.getDeck());
            table.getPlayers()[1].draw(table.getDeck());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCounterPoints(int counterPoints) {
        this.counterPoints = counterPoints;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {

        return hand;
    }

    public Card[] get10FromHandToScan() {
        Card[] newCards = new Card[10];
        for (int i = 0; i < 10; i++) {
            newCards[i] = this.hand.cards.get(i);
        }

        return newCards;
    }
    public int getCounterPoints() {
        return counterPoints;
    }

    abstract Card chooseCard(int chosen);

    abstract public void placeCard(Card card, int row);

    abstract void placeCardOnSide(Card card);

    public void draw(Deck deck){
        hand.cards.addAll(List.of(deck.get10()));
    }

}