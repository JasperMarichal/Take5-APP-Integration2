package classes;

import java.awt.*;
import java.util.List;

public class CardScanner {
    Player player;
    Button button;
    java.util.List<Button> buttonList;
    java.util.List<Card> carsScanned;
    public void scan(){
        carsScanned.addAll(List.of(player.get10FromHandToScan()));
        for (int i= 0; i<carsScanned.size(); i++) {
            Button button1= new Button();
        }
    }
}
