package com.take5.model;

import java.util.List;

public abstract class Player{
    public String name;
    PlayingTable table;
    Hand hand;

    Card card;
    int counterPoints;

    public Player(String name, int counterPoints) {
        this.name = name;
        this.counterPoints = counterPoints;
        this.hand= new Hand();
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

    public int getCounterPoints() {
        return counterPoints;
    }

    abstract void chooseCard();
    abstract void placeCard();

    public void draw(Deck deck){
        hand.cards.addAll(List.of(deck.get10()));
    }

}
