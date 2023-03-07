package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {
    Deck deck;
    List<Card> cards;
    public Hand() {
        cards = new ArrayList<Card>();
    }

    public Card findCard(String s) {
        Card c1;
        for (int i = 0; i < cards.size(); i++) {
            String s2 = cards.get(i).getURL();
            s = s.toLowerCase();
            s2 = s2.toLowerCase();

            // check if length is same
            if (s.length() == s2.length()) {

                // convert strings to char array
                char[] charArray1 = s.toCharArray();
                char[] charArray2 = s2.toCharArray();

                // sort the char array
                Arrays.sort(charArray1);
                Arrays.sort(charArray2);

                // if sorted char arrays are same
                // then the string is anagram
                boolean result = Arrays.equals(charArray1, charArray2);

                if (result == true) {
                   c1=  cards.get(i);

                    cards.remove(i);

                    return c1;
                }

            } else return null;
        }
        return null;
    }
}