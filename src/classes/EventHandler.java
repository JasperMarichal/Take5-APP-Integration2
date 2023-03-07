package classes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class EventHandler implements javafx.event.EventHandler<ActionEvent> {
    PlayingTable playingTable;
    Button button;

    @Override
    public void handle(ActionEvent actionEvent) {
        String s = String.valueOf(button.getGraphic());
        button.setManaged(false);
        playingTable.players[0].placeCardOnSide(playingTable.players[0].hand.findCard(s));
    }
}