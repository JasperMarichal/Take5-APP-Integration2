package model;

import java.io.FileNotFoundException;

public class Loader {


    private PlayingTable playingTable;
    public void load(PlayingTable playingTable){
        try {
            Deck deck1 = new Deck("");
            playingTable.setDeck(deck1) ;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
