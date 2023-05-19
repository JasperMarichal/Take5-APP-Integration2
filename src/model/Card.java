package model;

import javafx.scene.image.Image;

public class Card {
    private int number;
    private int bulls;
    String URL;
    public String getURL(){
        int numberForImg= getNumber();
        URL= "file:resources/take5prototipfx/" + numberForImg + ".png";
        return URL;
    }

    public Card getCard(String url){
        Card card = null;
        if (url==this.getURL()) {
            card = this;
        }
        return card;
    }


    public Card(int number, int bulls) {
        this.setBulls(bulls);
        this.setNumber(number);
    }

    public Card() {
    }

    public javafx.scene.image.Image getImage(){
        String imageUrl = this.getURL();
        return new Image(imageUrl);
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