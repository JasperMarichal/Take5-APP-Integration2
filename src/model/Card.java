package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Take5View;

public class Card {
    private int number;
    Take5View take5view = new Take5View();
    private int bulls;
    String URL;
    ImageView imageView;
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

    public void setURL(String URL) {
        this.URL = URL;
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

    public void getImageView() {
        take5view.getImageView(this.getURL());
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
