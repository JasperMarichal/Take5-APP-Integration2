package view.Statistics;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class StatisticsView extends BorderPane {
    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
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
        lineChart = new LineChart <>(new NumberAxis(), new NumberAxis());
        series1.setName("Durations of moves");

        //Text on the left
        average_move_duration = new Text();

        //Nodes that are inside the HBox ( at the bottom of the page )
        hbox = new HBox(15);
        most_profit = new Text();
        outliers_rounds = new Text();
        hbox.getChildren().addAll(most_profit, outliers_rounds);

    }

    public void layoutNodes(){
        //BorderPane
        setCenter(average_move_duration);
        average_move_duration.maxWidth(100);
        setMargin(this.average_move_duration, new Insets(10,10,10,10));
        setAlignment(average_move_duration, Pos.CENTER);
        average_move_duration.setId("avg-mv-dur");

        //Chart
        setCenter(this.lineChart);
        lineChart.setMaxHeight(300);
        lineChart.setMaxWidth(600);
        lineChart.getXAxis().setStyle("-fx-border-color: #0095ff transparent transparent; -fx-border-width:3");
        lineChart.getYAxis().setStyle("-fx-border-color: transparent #0095ff transparent transparent; -fx-border-width:3");
        setLeft(average_move_duration);
        // HBox
        most_profit.setId("most-profit");
        outliers_rounds.setId("most-profit");
        setAlignment(most_profit, Pos.CENTER);
        hbox.setId("hbox");
        setBottom(hbox);
        setMargin(hbox, new Insets(30,30,30,30));
        hbox.setPadding(new Insets(10,10,10,10));
        series1.setName("Moves timing");

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
