package com.take5.model;

import java.io.FileNotFoundException;
import java.util.*;

public class PlayingTable {
    final int cardRowsSize = 4;
    Deck deck;
    Player[] players;
    ArrayList<Card>[] cardRows;


    public Card[] getLastCards(){
        Card[] lastCards = new Card[cardRowsSize];
        for (int i = 0; i < cardRowsSize; i++){
            lastCards[i] = cardRows[i].get(cardRows[i].size() - 1);
        }
        return lastCards;
    }

    public Deck getDeck() {
        return deck;
    }

    public PlayingTable() {
        players = new Player[2];
        cardRows = new ArrayList[cardRowsSize];
        for (int i = 0; i < cardRowsSize; i++) {
            cardRows[i] = new ArrayList<Card>();
        }


        try {
            Deck deck1 = new Deck("src/Cards");
            this.deck=deck1;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
    void addCard(Card card, int rowIndex){
        cardRows[rowIndex].add(card);
    }

    List<Card> getAllCardsFromRow(int rowIndex){
        List<Card> output = new ArrayList<>(cardRows[rowIndex]);
        cardRows[rowIndex].clear();
        return output;
    }




}
