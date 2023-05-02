package model;

public class AiPlayer extends Player {

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
            if (sumBulls < lowest) {
                lowest = sumBulls;
                lowestIndex = i;
            }
        }

        return lowestIndex;
    }

    @Override
    public void placeCard(Card card, int row) {

    }

    public int cardPlayable() {
        int minCardCol = 105;
        int minColIndex = 0;
        for (int i = 0; i < table.cardRowsSize; i++) {
            Card lastCard = table.cardRows[i].get(table.cardRows[i].size() - 1);
            if (lastCard.getNumber() < minCardCol) {
                minCardCol = lastCard.getNumber();
                minColIndex = i;
            }
        }

        int minCardHand = 105;
        int minCardHandIndex = 0;

        for (int i = 0; i < this.getHand().getCards().size(); i++) {
            if (this.getHand().getCards().get(i).getNumber() < minCardHand &&
                    this.getHand().getCards().get(i).getNumber() > minCardCol) {
                minCardHand = this.getHand().getCards().get(i).getNumber();
                minCardHandIndex = i;
            }
        }
        return minCardHandIndex;
    }


    public int cardRowNumberForReplacement() {
        int min = 10;
        for (int i = 0; i <4; i++) {
            if (table.cardRows[i].size() < min && table.cardRows[i].size() < 5){
                min = i;
            }
        }

        return min;

    }



    public Card getCard(int number) {
        return this.getHand().getCards().get(number);


    }
    @Override
    void placeCardOnSide (Card card){

    }
}

