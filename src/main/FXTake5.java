package main;

import model.*;
import javafx.application.Application;
import javafx.stage.Stage;
import view.CardPresenter;
import view.Take5View;

public class FXTake5 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {


        HumanPlayer hp= new HumanPlayer("Vasil",10);
        AiPlayer AI= new AiPlayer("Bot 1", 64);

        PlayingTable playingTable = new PlayingTable(hp, AI);

        Take5View b1= new Take5View();
        playingTable.getPlayers()[0].draw(playingTable.getDeck());
        playingTable.getPlayers()[1].draw(playingTable.getDeck());

        CardPresenter cardPresenter = new CardPresenter(playingTable, b1, stage);
        int loopint=1 ;
        while (loopint==1 ){
            b1.getH1().refreshDmg(playingTable);
            System.out.println("refreshed " );
        }

        for (int i=0; i< AI.getHand().getCards().size(); i++) {


            System.out.println(AI.getHand().getCards().get(i).getNumber() + "AI CARDS ");
        }
        playingTable.showRows();
        playingTable.showRows();


            for (int i=0; i<playingTable.getPlayers().length; i++){
                System.out.println(playingTable.getPlayers()[i] + "igrach ");
            }
        for (int i=0; i< hp.getHand().getCards().size(); i++) {


            System.out.println(hp.getHand().getCards().get(i).getNumber() + "human cards ");
        }
        for (int i=0; i< AI.getHand().getCards().size(); i++) {


            System.out.println(AI.getHand().getCards().get(i).getNumber() + "AI CARDS ");
        }



    }
}
