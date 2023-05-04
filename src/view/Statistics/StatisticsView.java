package view.Statistics;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.text.TextAlignment;


public class StatisticsView extends BorderPane {
    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
    private VBox vboxTop;
    private HBox hbox;
    private Text average_move_duration;
    private Text most_profit;
    private Text outliers_rounds;
    public StatisticsView(){
        initialiseNodes();
        layoutNodes();
    }

    public void initialiseNodes(){
        //Chart
        NumberAxis xAxis = new NumberAxis();
        xAxis.setTickUnit(1);
        xAxis.setAnimated(true);
        xAxis.setMinorTickCount(0);
        xAxis.setLabel("Move Number");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Seconds");
        lineChart = new LineChart <>(xAxis, yAxis);
        series1.setName("Durations of moves");

        //Text on top
        average_move_duration = new Text();

        //VBox top
        vboxTop = new VBox();

        //Nodes that are inside the HBox ( at the bottom of the page )
       // hbox = new HBox(15);
        //most_profit = new Text();
        //outliers_rounds = new Text();
        //hbox.getChildren().addAll(most_profit, outliers_rounds);

    }

    public void layoutNodes(){
        //Top
        vboxTop.getChildren().addAll(average_move_duration, lineChart);
        vboxTop.setAlignment(Pos.CENTER);
        vboxTop.setSpacing(30);
        setTop(vboxTop);
        vboxTop.setPadding(new Insets(10,10,10,10));

            //AVG MOVE
        average_move_duration.minWidth(1000);
        average_move_duration.setStyle("-fx-font-family: 'Luckiest Guy'; -fx-font-size: 30px; -fx-padding: 10px; -fx-text-fill: #4d71ff;");
        average_move_duration.setTextAlignment(TextAlignment.CENTER);

            //Chart
        lineChart.setMaxHeight(300);
        lineChart.setMaxWidth(600);
        lineChart.getXAxis().setStyle("-fx-border-color: #4d71ff rgba(168,24,24,0) transparent; -fx-border-width:3");
        lineChart.getYAxis().setStyle("-fx-border-color: transparent #4d71ff transparent transparent; -fx-border-width:3");
//        // HBox
//        most_profit.setId("most-profit");
//        outliers_rounds.setId("most-profit");
//        setAlignment(most_profit, Pos.CENTER);
//        hbox.setId("hbox");
//        setBottom(hbox);
//        setMargin(hbox, new Insets(30,30,30,30));
//        hbox.setPadding(new Insets(10,10,10,10));
//        series1.setName("Moves timing");

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

    Text getOutliers_rounds(){return outliers_rounds;}

}
