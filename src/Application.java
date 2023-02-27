

import com.take5.model.*;

import java.io.FileNotFoundException;
import java.util.Collections;

public class Application {


    public static void main(String[] args) {


        PlayingTable playingTable = new PlayingTable();


        Player player= new HumanPlayer("Someone", 0 );
        Player player1AI= new AiPlayer("Bot1", 0);

        System.out.println(playingTable.getDeck());
        player.draw(playingTable.getDeck());
        player1AI.draw(playingTable.getDeck());


        System.out.println(player.getHand());
        System.out.println(player1AI.getHand());


    }
}

