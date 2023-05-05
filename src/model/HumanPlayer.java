package model;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, int counterPoints) {
        super(name, counterPoints);
    }

    @Override
    Card chooseCard(int chosen) {
        return this.getHand().cards.get(chosen);
    }

    public int getTheSelectedCardFromHand(Card card) {
        int y = 0;

        for (int i = 0; i < hand.cards.size(); i++) {

            if (hand.cards.get(i).getNumber() == card.getNumber()) {

                return i;
            }
        }
        System.out.println(y);
        return 0;
    }

    public int findCardPlaceInHand(Card card) {
        int index = -1;
        for (int i = 0; i < this.hand.cards.size(); i++) {
            if (card.getNumber() == this.hand.cards.get(i).getNumber()) {
                index = i;
            }
        }
        return index;
    }

    public void setName(String name) {
        super.setName(name);
    }

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