package model;

public class CardChecker {
        PlayingTable model;

    public CardChecker(PlayingTable model){
        this.model= model;



    }


    public int checkHigherCard(Card player, Card AI){
        if (player.getNumber()>AI.getNumber()){
            return AI.getNumber();
        }
        else {
            return player.getNumber();
        }
    }
}
