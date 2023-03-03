import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PlayingTable {
    final int cardRowsSize = 4;
    Deck deck;
    Player[] players;
    ArrayList<Card>[] cardRows;

    Button[] buttons;
  public  ArrayList<Card>[] chosenCards;






    public Card[] getLastCards(){
        Card[] lastCards = new Card[cardRowsSize];
        for (int i = 0; i < cardRowsSize; i++){
            lastCards[i] = cardRows[i].get(cardRows[i].size() - 1);
        }
        return lastCards;
    }


    public void addToChosen(Card card){
        chosenCards[0].add(card);

    }

    public Deck getDeck() {
        return deck;
    }

    public PlayingTable(Player human, Player AI) {
        players = new Player[2];
        players[0]= human;
        players[1]= AI;
        players[0].setTable(this);
        players[1].setTable(this);


        cardRows = new ArrayList[cardRowsSize];


        try {
            Deck deck1 = new Deck("C:\\Users\\vasil\\Desktop\\Web\\Take5PrototipFX\\src\\main\\java\\Cards");
            this.deck=deck1;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        for (int i = 0; i < cardRowsSize; i++) {
            cardRows[i] = new ArrayList<Card>();
            cardRows[i].add(deck.deal1());
        }
    }

    public void addCard(Card card, int rowIndex){
        cardRows[rowIndex].add(card);
    }

    public List<Card> getAllCardsFromRow(int rowIndex){
        List<Card> output = new ArrayList<>(cardRows[rowIndex]);
        cardRows[rowIndex].clear();
        return output;
    }


    public void showRows(){
        for (int i = 0; i < cardRowsSize; i++) {
                    System.out.println(cardRows[i].get(0).getNumber());
        }
    }




    public void dmgCalculation (int rowIndex,Player humanPlayer){
        DmgCalculator dmgCalculator= new DmgCalculator();

        for (int i=0; i<cardRows[rowIndex].size(); i++){
            int bulls= cardRows[rowIndex].get(i).getBulls();
            dmgCalculator.takeDmg(bulls, humanPlayer);
            System.out.println(bulls);
        }
        getAllCardsFromRow(rowIndex);

                System.out.println(humanPlayer.getName() + " now you have: " + humanPlayer.getCounterPoints() + " left.");
                System.out.println("The chosen card is: " + humanPlayer.chooseCard(1).getNumber() );

                humanPlayer.placeCard(humanPlayer.chooseCard(1), rowIndex);

    }

}
