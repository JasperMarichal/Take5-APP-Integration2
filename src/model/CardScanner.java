package model;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class CardScanner {
    Deck deck;

    public Card scanAndRetrieveCardForPlay(String url){
        Card c1 = null;
        try {
            this.deck= new Deck("src/Cards");
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
        return c1;
    }
}