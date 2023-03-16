package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AiPlayer extends Player{

    public AiPlayer(String name, int counterPoints) {
        super(name, counterPoints);
    }

    @Override
    public Card chooseCard(int chosen){
//        // choose lowest playable card
//        //classes.Card[] rowsLastCards = table.getLastCards();
        Card lowestCard = null;
//        = Arrays
//                .stream(table.getLastCards())
//                .min(Comparator.comparing(Card::getNumber))
//                .get();
//
        return lowestCard;
    }
    public int getLowestCardRow() {
        int lowest = Integer.MAX_VALUE;
        int lowestIndex = 0;
        for (int i = 0; i < table.getCardRows().length; i++) {
            int sumBulls = table.getCardRows()[i].stream().mapToInt(Card::getBulls).sum();
            if (sumBulls < lowest){
                lowest = sumBulls;
                lowestIndex = i;
            }
        }

        return lowestIndex;
    }

    @Override
    void placeCard(Card card, int row) {

    }

    @Override
    void placeCardOnSide(Card card) {

    }
}
