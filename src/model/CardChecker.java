package model;

public class CardChecker {
        PlayingTable model;

    public CardChecker(PlayingTable model){
        this.model= model;
    }


    public int checkHigherCard(Card player, Card AI){
        if (player.getNumber()>AI.getNumber()){
            return 0;
        }
        else {
            return 1;
        }
    }
}
