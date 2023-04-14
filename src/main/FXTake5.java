package main;

import model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.take5view;

public class FXTake5 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    take5view b1= new take5view();
    @Override
    public void start(Stage stage) {
        HumanPlayer hp= new HumanPlayer("Vasil",64);
        AiPlayer AI= new AiPlayer("Bot 1", 64);
        PlayingTable playingTable = new PlayingTable(hp, AI,new BorderPane() );
        Card c= new Card(1,2);
        playingTable.getPlayers()[0].draw(playingTable.getDeck());

        b1.buildScene1(playingTable, stage);
    }
}