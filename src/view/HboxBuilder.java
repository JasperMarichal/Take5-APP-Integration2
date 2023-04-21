package view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PlayingTable;

public class HboxBuilder {

    HBox firstRow= new HBox();
    HBox secondRow= new HBox();
    HBox thirdRow= new HBox();
    HBox ForthRow= new HBox();
    VBox rows= new VBox();

    public VBox buildRows(PlayingTable playingTable){

        for (int x = 0; x< playingTable.getCardRows()[0].size(); x++ ) {
            Image img2 = playingTable.getCardRows()[0].get(x).getImage();
            ImageView imgv = new ImageView(img2);
            imgv.setFitWidth(80);
            imgv.setFitHeight(100);
            firstRow.getChildren().add(imgv);
//               CenterImages.getChildren().add(imgv);
//               rows.getChildren().add(imgv);
            HBox.setMargin(firstRow, new Insets(0,10,0,0));


//            VBox.setMargin(rows, new Insets(10, 0, 0, 0));
        }
        for (int x = 0; x< playingTable.getCardRows()[1].size(); x++ ) {
            Image img2 = playingTable.getCardRows()[1].get(x).getImage();
            ImageView imgv2 = new ImageView(img2);
            imgv2.setFitWidth(80);
            imgv2.setFitHeight(100);
            secondRow.getChildren().add(imgv2);
            HBox.setMargin(secondRow, new Insets(0, 10, 0, 0));
        }
        for (int x = 0; x< playingTable.getCardRows()[2].size(); x++ ) {
            Image img2 = playingTable.getCardRows()[2].get(x).getImage();
            ImageView imgv3 = new ImageView(img2);
            imgv3.setFitWidth(80);
            imgv3.setFitHeight(100);
            thirdRow.getChildren().add(imgv3);
            HBox.setMargin(thirdRow, new Insets(0, 10, 0, 0));
        }
        for (int x = 0; x< playingTable.getCardRows()[3].size(); x++ ) {
            Image img2 = playingTable.getCardRows()[3].get(x).getImage();
            ImageView imgv4 = new ImageView(img2);
            imgv4.setFitWidth(80);
            imgv4.setFitHeight(100);
            ForthRow.getChildren().add(imgv4);
            HBox.setMargin(ForthRow, new Insets(0, 10, 0, 0));
        }
        HBox[] hboxes= new HBox[4];
        hboxes[0]=firstRow;
        hboxes[1]=secondRow;

        hboxes[2]=thirdRow;

        hboxes[3]=ForthRow;
        rows.getChildren().addAll(hboxes);
        return rows;
    }

        public void refreshRows(PlayingTable playingTable){
            firstRow.getChildren().clear();
            secondRow.getChildren().clear();

            thirdRow.getChildren().clear();

            ForthRow.getChildren().clear();
            buildRows(playingTable);
        }
    public void removefromHand(BorderPane borderPane){


    }


}
