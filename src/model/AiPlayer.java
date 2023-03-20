package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AiPlayer extends Player{

    public AiPlayer(String name, int counterPoints) {
        super(name, counterPoints);
    }

    @Override
    Card chooseCard(int chosen) {
        return null;
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
