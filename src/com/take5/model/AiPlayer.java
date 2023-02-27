package com.take5.model;

import java.util.Arrays;
import java.util.Comparator;

public class AiPlayer extends Player{

    public AiPlayer(String name, int counterPoints) {
        super(name, counterPoints);
    }

    @Override
    public void chooseCard(){
        // choose lowest playable card
        //Card[] rowsLastCards = table.getLastCards();
        Card lowestCard = Arrays
                .stream(table.getLastCards())
                .min(Comparator.comparing(Card::getNumber))
                .get();

    }

    @Override
    void placeCard() {

    }
}
