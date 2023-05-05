package model;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import view.Take5View;

import static view.CardPresenter.addEventHandlers;

public class EventHandler implements javafx.event.EventHandler<ActionEvent> {
    PlayingTable playingTable;
    int counterForLatch=0;
    public static void setCounterForLatch(int counterForLatch) {
        counterForLatch = counterForLatch;
    }

    public int getCounterForLatch() {
        return counterForLatch;
    }
    Button button;
    static void removeChildren(Take5View view, Button button1, Button button2, Button button3, Button button4){
        ((HBox)view.rows.getChildren().get(0)).getChildren().removeAll(button1);
        ((HBox)view.rows.getChildren().get(1)).getChildren().removeAll(button2);
        ((HBox)view.rows.getChildren().get(2)).getChildren().removeAll(button3);
        ((HBox)view.rows.getChildren().get(3)).getChildren().removeAll(button4);
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        String s = String.valueOf(button.getGraphic());
        button.setManaged(false);
        playingTable.players[0].placeCardOnSide(playingTable.players[0].hand.findCard(s));
    }

    public static void fix1(PlayingTable model, Take5View view, int humanIndex, String s) {
        Integer i3 = model.getPlayableRows(model.getCardScanner().scanAndRetrieveCardForPlay(s));
        if (i3 != null) {
            (model.getPlayers()[0]).playCard(humanIndex, i3);
            view.getH1().refreshRows(model);
        } else {
            setCounterForLatch(1);
            Button forFirstRow = new Button();
            Button forSecondRow = new Button();
            Button forThirdRow = new Button();
            Button forFourthRow = new Button();

            forFirstRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                fixForHuman(model, view
                        , forFirstRow, forSecondRow
                        , forThirdRow, forFourthRow
                        , humanIndex, 0);
                initialize(model,view,1);
            });
            forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                fixForHuman(model, view
                        , forFirstRow, forSecondRow
                        , forThirdRow, forFourthRow
                        , humanIndex, 1);
                initialize(model,view,1);
            });
            forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                fixForHuman(model, view
                        , forFirstRow, forSecondRow
                        , forThirdRow, forFourthRow
                        , humanIndex, 2);
                initialize(model,view,1);
            });
            forFourthRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                fixForHuman(model, view
                        , forFirstRow, forSecondRow
                        , forThirdRow, forFourthRow
                        , humanIndex, 3);

                initialize(model,view,1);
            });
            view.addButtons(forFirstRow, forSecondRow, forThirdRow, forFourthRow);
        }
    }

    //fix2
    public static void fixForHuman(PlayingTable model, Take5View view
            , Button forFirstRow, Button forSecondRow
            , Button forThirdRow, Button forFourthRow
            , int humanIndex, int rowIndex) {

        model.dmgCalculationHuman(rowIndex, model.getPlayers()[0]);
        model.getAllCardsFromRow(rowIndex);
        removeChildren(view, forFirstRow, forSecondRow, forThirdRow, forFourthRow);
        (model.getPlayers()[0]).playCard(humanIndex, rowIndex);
    }
    //fix3
    public static void fixForAI(PlayingTable model, Card cAI, int aiCardIndex, int aiCardRowSelectionForRetrival) {
        Integer i4 = model.getPlayableRows(cAI);
        if (i4 != null) {
            System.out.println(aiCardRowSelectionForRetrival + "Proverka");
            ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, i4);
        } else {
            model.dmgCalculationAI(aiCardRowSelectionForRetrival, model.getPlayers()[1]);
            model.getAllCardsFromRow(aiCardRowSelectionForRetrival);
            System.out.println(aiCardRowSelectionForRetrival + "Proverka");
            ((AiPlayer) (model.getPlayers()[1])).playCard(aiCardIndex, aiCardRowSelectionForRetrival);
        }
    }
    //fix4
    public static void initialize(PlayingTable model, Take5View view, int playerWithPriority){
        setCounterForLatch(0);
        view.getH1().refreshRows(model);
        model.showRows();
        model.checkDeck();
        view.refreshHands(model, playerWithPriority);
        //playerWithPriority for Human ==1 AI==0

        addEventHandlers();
    }

    public static void fix1b(PlayingTable model, Take5View view
            , int humanIndex
            , Card cAI, int aiCardIndex, int aiCardRowSelectionForRetrival){

        setCounterForLatch(1);

        Button forFirstRow = new Button();
        Button forSecondRow = new Button();
        Button forThirdRow = new Button();
        Button forFourthRow = new Button();

        forFirstRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            fixForHuman(model, view
                    , forFirstRow, forSecondRow
                    , forThirdRow, forFourthRow
                    , humanIndex, 0);

            fixForAI(model, cAI, aiCardIndex, aiCardRowSelectionForRetrival);

            EventHandler.initialize(model,view,0);
        });
        forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            fixForHuman(model, view
                    , forFirstRow, forSecondRow
                    , forThirdRow, forFourthRow
                    , humanIndex, 1);
            fixForAI(model, cAI, aiCardIndex, aiCardRowSelectionForRetrival);
            EventHandler.initialize(model,view,0);
        });
        forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            fixForHuman(model, view
                    , forFirstRow, forSecondRow
                    , forThirdRow, forFourthRow
                    , humanIndex, 2);

            fixForAI(model, cAI, aiCardIndex, aiCardRowSelectionForRetrival);

            EventHandler.initialize(model,view,0);
        });
        forFourthRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
            fixForHuman(model, view
                    , forFirstRow, forSecondRow
                    , forThirdRow, forFourthRow
                    , humanIndex, 3);

            fixForAI(model, cAI, aiCardIndex, aiCardRowSelectionForRetrival);

            EventHandler.initialize(model,view,0);
        });
        view.addButtons(forFirstRow, forSecondRow, forThirdRow, forFourthRow);
    }
}