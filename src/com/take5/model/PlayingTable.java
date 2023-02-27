package com.take5.model;

import com.take5.model.Card;
import com.take5.model.Deck;
import com.take5.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayingTable {
    final int cardRowsSize = 4;
    Deck deck;
    Player[] players;
    List<Card>[] cardRows;

    public Card[] getLastCards(){
        Card[] lastCards = new Card[cardRowsSize];
        for (int i = 0; i < cardRowsSize; i++){
            lastCards[i] = cardRows[i].get(cardRows[i].size() - 1);
        }
        return lastCards;
    }
    public PlayingTable(){
        players = new Player[2];
        cardRows = new List<>[cardRowsSize];
        for (int i = 0; i < cardRowsSize; i++){
            cardRows[i] = new ArrayList<Card>();
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
