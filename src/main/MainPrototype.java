package main;

import classes.AiPlayer;
import classes.HumanPlayer;
import classes.PlayingTable;

public class MainPrototype {


    public static void main(String[] args) {
        PlayingTable playingTable = new PlayingTable(
                new HumanPlayer("Vasil",64)
                , new AiPlayer("Bot1",64));

//        classes.Player player= new classes.HumanPlayer("Someone" );
//        classes.Player player1AI= new classes.AiPlayer("Bot1");

        System.out.println(playingTable.getDeck());
        playingTable.players[0].draw(playingTable.getDeck());
        playingTable.players[1].draw(playingTable.getDeck());

        System.out.println(playingTable.players[0].getHand());
        System.out.println(playingTable.players[1].getHand());

        //playingTable.addCard(playingTable.getDeck().deal1(), 0);
        //playingTable.addCard(playingTable.getDeck().deal1(), 1);
        //playingTable.addCard(playingTable.getDeck().deal1(), 2);
        //playingTable.addCard(playingTable.getDeck().deal1(), 3);

        playingTable.showRows();
        System.out.println("Before dmg calculation.");

        playingTable.dmgCalculation(1, playingTable.players[0]);

        System.out.println("After dmg calculation.");

        playingTable.showRows();
        System.out.println( playingTable.players[0].getCounterPoints());
    }
}
