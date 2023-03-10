package classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;

public class Card {

    private int number;
    private int bulls;
    String URL;
    ImageView imageView;
    public String getURL(){
        int numberForImg= getNumber();
        URL= "C:\\Users\\vasil\\Desktop\\Web\\Take5PrototipFX\\src\\main\\resources\\com\\example\\take5prototipfx\\Card" + numberForImg + ".png";
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

    public ImageView getImageView() {
        if (imageView == null){
            imageView = new ImageView(getImage());
            imageView.setFitHeight(160);
            imageView.setFitWidth(110);
        }

        return imageView;
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









