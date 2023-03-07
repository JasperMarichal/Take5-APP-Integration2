package classes;

import java.awt.*;
import java.io.File;

public class Card {
    private int number;
    private int bulls;
    private Image image;
    String URL;
    public String getURL(){
        int numberForImg= getNumber();
        URL = "file:resources/take5prototipfx/Card" + numberForImg + ".png";
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Image getImage() {
        return image;
    }

    public Card(int number, int bulls) {
        this.setBulls(bulls);
        this.setNumber(number);

        File img = new File("file:resources/take5prototipfx/Card" + number + ".png");
    }

    public Card() {
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public int getNumber() {
        return number;
    }

    public int getBulls() {
        return bulls;
    }

}
//
//public void print() {
//    for (int i = 0; i < listDeck2.size(); i++) {
//
//        System.out.println(getNumber(listDeck2.get(i)) + "and " + getBulls(deck1.listDeck2.get(i)));
//    }
//}
//    }
//









