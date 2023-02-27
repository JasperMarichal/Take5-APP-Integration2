package com.take5.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Deck {
    private LinkedList<Card> cards = new LinkedList<>();
    String fileName;

    public Deck(String fileName) throws FileNotFoundException {

        this.fileName = fileName;
        refillAndShuffle();
    }

    public Card[] get10(){
        Card[] newCards = new Card[10];
        for(int i = 0; i < 10; i++){
            newCards[i] = this.cards.remove(0);
        }

        return newCards;
    }

    public int getCardsLeft(){
        return cards.size();
    }

    public void refillAndShuffle(){
        cards.clear();

        try {
            loadCards(this.fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Collections.shuffle(cards);
    }



    private void loadCards(String fileName) throws FileNotFoundException {

        File cards1 = new File(fileName);
        Scanner scF = new Scanner(cards1);

        while (scF.hasNext()) {

            String record = scF.nextLine();
            Scanner scR = new Scanner(record);
            scR.useDelimiter("#");
            int number = scR.nextInt();
            int bulls = scR.nextInt();
            cards.add(new Card(number,bulls));
        }
    }

    public LinkedList<Card> getCards() {
        return cards;
    }


    public ArrayList<Card> draw10(){
        ArrayList<Card> drawn= new ArrayList<>();
        for (int i=0; i<10; i++){

            drawn.add(cards.get(i));


        }
        return drawn;
    }


}
