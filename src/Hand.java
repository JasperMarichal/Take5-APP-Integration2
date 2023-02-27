package com.take5.model;

import com.take5.model.Card;
import com.take5.model.Deck;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    List<Card> cards;
    public Hand(Deck deck) {
        cards = new ArrayList<>();
    }

    public void draw(Deck deck){
        cards.clear();
        cards.addAll(List.of(deck.get10()));
    }
}
