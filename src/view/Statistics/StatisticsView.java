package view.Statistics;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;


public class StatisticsView extends BorderPane {
    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
    private VBox vboxTop;
    private VBox vBoxLeft;
    private VBox vBoxRight;
    private HBox hboxCenter;
    private Text average_move_duration;
    private Label outliers_title;
    private Text most_profit;
    private Label most_profit_moves_title;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private Text outliers_moves;
    public StatisticsView(){
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes(){
        //Chart
        xAxis = new NumberAxis();
        xAxis.setTickUnit(1);
        xAxis.setAnimated(true);
        xAxis.setMinorTickCount(0);
        xAxis.setLabel("Move Number");

        yAxis = new NumberAxis();
        yAxis.setLabel("Seconds");
        lineChart = new LineChart <>(xAxis, yAxis);
        series1.setName("Durations of moves");

        //Text on top
        average_move_duration = new Text();

        //VBox top
        vboxTop = new VBox();

        //Nodes that are inside the HBox ( at the center of the page )
        outliers_title = new Label("REMARKABLE MOVES");
        hboxCenter = new HBox();
       vBoxLeft = new VBox();
       most_profit = new Text();
       most_profit_moves_title = new Label("MOST PROFITABLE MOVES");
       vBoxRight = new VBox();
       outliers_moves = new Text();

    }

    public void layoutNodes(){
        //Top
        vboxTop.getChildren().addAll(average_move_duration, lineChart);
        vboxTop.setAlignment(Pos.CENTER);
        vboxTop.setSpacing(50);
        vboxTop.setPadding(new Insets(20,20,20,20));
        setTop(vboxTop);
        setMargin(vboxTop, new Insets(20,20,20,20));

            //AVG MOVE

        average_move_duration.setTextAlignment(TextAlignment.CENTER);

            //Chart
        lineChart.setMaxHeight(250);
        lineChart.setMaxWidth(700);
        lineChart.legendVisibleProperty().setValue(false);

        //Center
        setCenter(hboxCenter);
        setMargin(hboxCenter, new Insets(20,20,20,20));
        hboxCenter.getChildren().addAll(vBoxLeft, vBoxRight);
        hboxCenter.setSpacing(50);
        hboxCenter.setAlignment(Pos.CENTER);

            // Center-left
        vBoxLeft.getChildren().addAll(most_profit_moves_title,most_profit);
        vBoxLeft.setPadding(new Insets(20,20,20,20));
        vBoxLeft.setSpacing(30);
        vBoxLeft.setMinWidth(500);
        vBoxLeft.setAlignment(Pos.CENTER);

            //Center-right
        vBoxRight.getChildren().addAll(outliers_title, outliers_moves);
        vBoxRight.setPadding(new Insets(20,20,20,20));
        vBoxRight.setSpacing(30);
        vBoxRight.setMinWidth(500);
        vBoxRight.setAlignment(Pos.CENTER);


        addStyle();
    }

    private void addStyle(){
        outliers_moves.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 25px; -fx-padding: 10px; -fx-fill: #4d71ff;");
        lineChart.setStyle("-fx-chart-background: transparent;");
        vboxTop.setStyle("-fx-background-color: rgba(165,169,248,0.46); -fx-background-radius: 20px;");
        outliers_title.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 35px; -fx-padding: 10px; -fx-text-fill: #4d71ff;");
        vBoxRight.setStyle("-fx-background-color: rgba(165,169,248,0.46); -fx-background-radius: 20px;");
        most_profit.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 25px; -fx-padding: 10px; -fx-fill: #4d71ff;");
        average_move_duration.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 50px; -fx-padding: 10px; -fx-fill: #6788fd;");
        lineChart.getXAxis().setStyle("-fx-fill: #4d71ff;-fx-font-family: 'Luckiest Guy';-fx-border-color: #c1d1ff rgba(168,24,24,0) transparent; -fx-border-width:3");
        yAxis.setStyle("-fx-font-family: 'Luckiest Guy';-fx-border-color: transparent #c1d1ff transparent transparent; -fx-border-width:3");
        most_profit_moves_title.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 35px; -fx-padding: 10px; -fx-text-fill: #4d71ff;");
        average_move_duration.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 25px; -fx-padding: 10px; -fx-fill: #4d71ff;");
        vBoxLeft.setStyle("-fx-background-color: rgba(165,169,248,0.46); -fx-background-radius: 20px;");
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        lineChart.getStylesheets().add("chart-style.css");
        setStyle("-fx-background-color: #f4f2f8");
    }


    // Method to set the data for the chart
    void setSeries(int x, int y){
        series1.getData().add(new XYChart.Data<>(x, y));
    }

    void setLineChart(){
        lineChart.getData().add(series1);
    }

    // Getters
    Text getAverage_move_duration(){
        return average_move_duration;
    }

    Text getMost_profit(){return most_profit;}

    Text getOutliers_rounds(){return outliers_moves;}

}
