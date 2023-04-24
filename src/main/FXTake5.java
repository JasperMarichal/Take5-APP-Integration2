package main;

import model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.CardPresenter;
import view.take5view;

public class FXTake5 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {


        HumanPlayer hp= new HumanPlayer("Vasil",64);
        AiPlayer AI= new AiPlayer("Bot 1", 64);

        PlayingTable playingTable = new PlayingTable(hp, AI, new BorderPane());

        take5view b1= new take5view();
        playingTable.getPlayers()[0].draw(playingTable.getDeck());
        playingTable.getPlayers()[1].draw(playingTable.getDeck());

        CardPresenter cardPresenter = new CardPresenter(playingTable, b1, stage);
        for (int i=0; i< AI.getHand().getCards().size(); i++) {


            System.out.println(AI.getHand().getCards().get(i).getNumber() + "AI CARDS ");
        }
        playingTable.showRows();
        playingTable.showRows();


        for (int i=0; i< hp.getHand().getCards().size(); i++) {


            System.out.println(hp.getHand().getCards().get(i).getNumber() + "AI CARDS ");
        }


    }
}
