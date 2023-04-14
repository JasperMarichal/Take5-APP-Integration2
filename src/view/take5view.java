package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.FXTake5;
import model.Card;
import model.PlayingTable;

public class take5view {
    static BorderPane borderPane1;
    static BorderPane borderPane2;
    public  BorderPane getBorderPane2() {
        return borderPane2;
    }
    static String urlBack = "file:resources/take5prototipfx/bacground1Finished.jpg";
    static HboxBuilder h1 = new HboxBuilder();

    public void getImage(Card card){
        Image image= new Image(card.getURL());
    }
    public static ImageView getImageView(String URL) {
      Image image= new Image(URL);
      ImageView imageView= new ImageView(image);
            imageView.setFitHeight(160);
            imageView.setFitWidth(110);

        return imageView;
    }

    public void buildScene1(PlayingTable playingTable, Stage stage){
        BorderPane borderPane = new BorderPane();
        VBox vbox = new VBox();

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        vbox.getChildren().addAll(nameLabel, nameField);

        Label difficultyLabel = new Label("Difficulty:");
        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        vbox.getChildren().addAll(difficultyLabel, difficultyBox);

        Button playButton = new Button("Play!");

        vbox.getChildren().add(playButton);

        borderPane.setCenter(vbox);
        vbox.getChildren().forEach(node -> {
            if (node instanceof Labeled) {
                ((Labeled) node).setStyle("-fx-font-size: 20px;");
                ((Labeled) node).setAlignment(Pos.CENTER);
            }
            if (node instanceof Control) {
                ((Control) node).setStyle("-fx-font-size: 20px;");
            }
        });
        borderPane2 = borderPane;
        Scene sceneForMenu = new Scene(getBorderPane2(), 1100, 650);
        stage.setScene(sceneForMenu);

        stage.show();

         playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event2 ->{
             // Get user input
             String name = nameField.getText();
             String difficulty = difficultyBox.getValue();

             /* Create playing table
             //playingTable.setPlayerName(name);
             playingTable.setDifficulty(difficulty);*/

             // Show playing table
             buildBorderPane(playingTable);
             Scene scene1 = new Scene(getBorderPane1());
             stage.setHeight(700);
             stage.setWidth(1200);
             stage.setScene(scene1);
        });
    }
    public static void buildBorderPane(PlayingTable playingTable){
        VBox rows= h1.buildRows(playingTable);
        playingTable.getBorderPane().setCenter(rows);
        HBox firstRow= new HBox();
        HBox secondRow= new HBox();
        HBox thirdRow= new HBox();
        HBox ForthRow= new HBox();
        VBox cardHolder= new VBox();
        // TODO: clear pane
        // append player one cards

            for (int i = 0; i < playingTable.getPlayers()[0].getHand().getCards().size(); i++){
            System.out.println(playingTable.getPlayers()[0].getHand().getCards().get(i));


            ImageView currentCardView =  getImageView( playingTable.getPlayers()[0].getHand().getCards().get(i).getURL());
            javafx.scene.image.Image img= new javafx.scene.image.Image(urlBack);
            currentCardView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                String s= currentCardView.getImage().getUrl();
                javafx.scene.image.Image imgForCenter= new Image(s);

                ImageView currentImageWithoutClickEvent= new ImageView(imgForCenter);
                ImageView currentImageWithoutClickEvent2= new ImageView(imgForCenter);
                currentImageWithoutClickEvent2.setFitWidth(150);
                currentImageWithoutClickEvent2.setFitHeight(180);
//            cardHolder.getChildren().add(currentImageWithoutClickEvent);
                VBox vBox = new VBox(currentImageWithoutClickEvent2);
                playingTable.getBorderPane().setRight(vBox);


                Integer i1= playingTable.getPlayableRows(playingTable.getCardScanner().scanAndRetrieveCardForPlay(s));
                playingTable.getBottomImages().getChildren().remove(currentCardView);
                currentImageWithoutClickEvent.setFitWidth(80);
                currentImageWithoutClickEvent.setFitHeight(100);

                if (i1!= null){
                playingTable.getCardRows()[i1].add(playingTable.getCardScanner().scanAndRetrieveCardForPlay(s));
                ((HBox)rows.getChildren().get(i1)).getChildren().add(currentImageWithoutClickEvent);}
                else {

                    Button forFirstRow= new Button();
                    Button forSecondRow= new Button();
                    Button forThirdRow= new Button();
                    Button forFourTHRow= new Button();


                    forFirstRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->{
                        playingTable.getAllCardsFromRow(0);
                        ((HBox)rows.getChildren().get(0)).getChildren().removeAll(forFirstRow);
                        ((HBox)rows.getChildren().get(1)).getChildren().removeAll(forSecondRow);
                        ((HBox)rows.getChildren().get(2)).getChildren().removeAll(forThirdRow);
                        ((HBox)rows.getChildren().get(3)).getChildren().removeAll(forFourTHRow);
                        ((HBox)rows.getChildren().get(0)).getChildren().removeAll();
                        ((HBox)rows.getChildren().get(0)).getChildren().add(currentImageWithoutClickEvent);

                    });
                    forSecondRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->{
                        playingTable.getAllCardsFromRow(1);
                        ((HBox)rows.getChildren().get(0)).getChildren().removeAll(forFirstRow);
                        ((HBox)rows.getChildren().get(1)).getChildren().removeAll(forSecondRow);
                        ((HBox)rows.getChildren().get(2)).getChildren().removeAll(forThirdRow);
                        ((HBox)rows.getChildren().get(3)).getChildren().removeAll(forFourTHRow);
                        ((HBox)rows.getChildren().get(1)).getChildren().clear();
                        ((HBox)rows.getChildren().get(1)).getChildren().add(currentImageWithoutClickEvent);
                    });
                    forThirdRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->{
                        playingTable.getAllCardsFromRow(3);
                        ((HBox)rows.getChildren().get(0)).getChildren().removeAll(forFirstRow);
                        ((HBox)rows.getChildren().get(1)).getChildren().removeAll(forSecondRow);
                        ((HBox)rows.getChildren().get(2)).getChildren().removeAll(forThirdRow);
                        ((HBox)rows.getChildren().get(3)).getChildren().removeAll(forFourTHRow);
                        ((HBox)rows.getChildren().get(2)).getChildren().clear();
                        ((HBox)rows.getChildren().get(2)).getChildren().add(currentImageWithoutClickEvent);
                    });
                    forFourTHRow.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 ->{
                        playingTable.getAllCardsFromRow(3);
                        ((HBox)rows.getChildren().get(0)).getChildren().removeAll(forFirstRow);
                        ((HBox)rows.getChildren().get(1)).getChildren().removeAll(forSecondRow);
                        ((HBox)rows.getChildren().get(2)).getChildren().removeAll(forThirdRow);
                        ((HBox)rows.getChildren().get(3)).getChildren().removeAll(forFourTHRow);
                        ((HBox)rows.getChildren().get(3)).getChildren().clear();
                        ((HBox)rows.getChildren().get(3)).getChildren().add(currentImageWithoutClickEvent);
                    });
                    ((HBox)rows.getChildren().get(0)).getChildren().add(forFirstRow);
                    ((HBox)rows.getChildren().get(1)).getChildren().add(forSecondRow);
                    ((HBox)rows.getChildren().get(2)).getChildren().add(forThirdRow);
                    ((HBox)rows.getChildren().get(3)).getChildren().add(forFourTHRow);

                }
                playingTable.getTopImages().getChildren().remove(1);
            }
            );

            ImageView opponentsCards= new ImageView(img);
            opponentsCards.setFitHeight(160);
            opponentsCards.setFitWidth(110);

            playingTable.getTopImages().getChildren().add(opponentsCards);
            playingTable.getBottomImages().getChildren().add(currentCardView);
            HBox.setMargin(currentCardView, new javafx.geometry.Insets(10, 10 , 10, 10));
        }

        VBox.setMargin(rows, new Insets(10, 0, 0, 0));
        playingTable.getBorderPane().setBottom(playingTable.getBottomImages());
        playingTable.getBorderPane().setTop(playingTable.getTopImages());

        borderPane1= playingTable.getBorderPane();
    }

    public BorderPane getBorderPane1() {
        return borderPane1;
    }
    }