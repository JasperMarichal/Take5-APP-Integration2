import java.util.Arrays;
import java.util.Comparator;

public class AiPlayer extends Player{

    public AiPlayer(String name, int counterPoints) {
        super(name, counterPoints);
    }

    @Override
    public Card chooseCard(int chosen){
        // choose lowest playable card
        //Card[] rowsLastCards = table.getLastCards();
        Card lowestCard = Arrays
                .stream(table.getLastCards())
                .min(Comparator.comparing(Card::getNumber))
                .get();

        return lowestCard;
    }

    @Override
    void placeCard(Card card, int row) {

    }

    @Override
    void placeCardOnSide(Card card) {

    }
}
