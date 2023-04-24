package model;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardScanner {
    Deck deck;
    Player player;
    Button button;
    List<Button> buttonList;
    ArrayList<Integer> listOfNumbers;
    PlayingTable playingTable;
    List<Card> carsScanned;
    public void scan(){
        for (int i= 0; i<4; i++) {
            int number= playingTable.cardRows[i].get(playingTable.cardRows[i].size()-1).getNumber();
            listOfNumbers.add(number);
        }
    }

    public Card scanAndRetrieveCardForPlay(String url){
        Card c1 = null;
        try {
            Deck deck1 = new Deck("src/Cards");
            this.deck=deck1;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i=0; i<deck.getCards().size();i++){
            String s2 = deck.getCards().get(i).getURL();
            url = url.toLowerCase();
            s2 = s2.toLowerCase();

            // check if length is same
            if (url.length() == s2.length()) {

                // convert strings to char array
                char[] charArray1 = url.toCharArray();
                char[] charArray2 = s2.toCharArray();

                // if sorted char arrays are same
                // then the string is anagram
                boolean result = Arrays.equals(charArray1, charArray2);

                if (result == true) {
                    c1 = deck.getCards().get(i);
                }
            }
        }

        System.out.println(c1);
        return c1;
    }
}