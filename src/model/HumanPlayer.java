package model;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, int counterPoints) {
        super(name, counterPoints);
    }

    @Override
    Card chooseCard(int chosen) {
        Card c = this.getHand().cards.get(chosen);
        return c;
    }

//    @Override
//    classes.Card chooseCard() {
//
//        return null;
//    }

    @Override
    public void placeCard(Card card, int row) {
        table.addCard(card, row);
        this.getHand().cards.remove(card);
    }

    @Override
    void placeCardOnSide(Card card) {
        table.chosenCards[0].add(card);
    }
}
