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


    public int getTheSelectedCardFromHand(Card card) {
        int y = 0;
        System.out.println( card.getNumber() + "podadena karta" );
        System.out.println(card.getNumber()+ "number of card");
        for (int i = 0; i < hand.cards.size(); i++) {
            System.out.println(i + "smth");
            System.out.println(hand.cards.get(i).getNumber() + " karta vzeta ot ruka ");
            if (hand.cards.get(i).getNumber() == card.getNumber()) {
                System.out.println(hand.cards.get(i).getNumber() + "hand cards");
                System.out.println(card.getNumber() + "inputed card");
                return i;
            }



        }
        System.out.println(y);
        return 0;
    }

    public int findCardPlaceInHand(Card card) {
        int index = -1;
        for (int i = 0; i < this.hand.cards.size(); i++) {
            System.out.println(hand.cards.get(i) + " karta vzeta ot ruka ");
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
