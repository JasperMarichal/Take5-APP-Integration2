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

    public Player(String name, int counterPoints) {
        this.name = name;
        this.hand= new Hand();
        this.counterPoints=counterPoints;
    }
    public void playCard(int index, int cardrow){
        Card c= this.getHand().getCards().get(index);
        table.addCard(c, cardrow);
        this.getHand().getCards().remove(c);
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

        System.out.println("This is the hand of player:" + getName());
        for (int i= 0; i<hand.cards.size(); i++) {

            System.out.print(hand.cards.get(i).getNumber()+ "   ");
            System.out.print(hand.cards.get(i).getBulls());
            System.out.println();

        }
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
