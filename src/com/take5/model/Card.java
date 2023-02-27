package com.take5.model;


public class Card {

    private int number;
    private int bulls;


    public Card(int number, int bulls) {
        this.setBulls(bulls);
        this.setNumber(number);
    }

    public Card() {
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public int getNumber() {
        return number;
    }

    public int getBulls(Card card) {
        return bulls;
    }




}
//
//public void print() {
//    for (int i = 0; i < listDeck2.size(); i++) {
//
//        System.out.println(getNumber(listDeck2.get(i)) + "and " + getBulls(deck1.listDeck2.get(i)));
//    }
//}
//    }
//









