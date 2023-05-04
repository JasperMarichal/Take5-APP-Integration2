package view.Statistics;

import model.Statistics;

import java.util.Map;

public class StatisticsPresenter {
    private final Statistics model;
    private final StatisticsView view;

    public StatisticsPresenter(Statistics model, StatisticsView view) {
        this.model  = model;
        this.view  = view;
        addEventHandlers();
        updateView();
    }
    private void addEventHandlers() {
        // No event handlers yet
    }
    private void updateView() {
        // Give data to the chart
        for(Map.Entry<Integer, Integer> set : model.getMoveChartValues().entrySet()){
            view.setSeries(set.getKey(), set.getValue());
        }
        view.setLineChart();

        // Show the other statistic ( Avg move duration, Most profitable moves, Outliers moves)
        view.getAverage_move_duration().setText(String.format("  Congrats you scored %s points \n your average move duration was: \n    %s",model.getFinalScore(), model.getAverageMoveDuration()));
        view.getMost_profit().setText(model.getMostProfitableMoves());
        view.getOutliers_rounds().setText("Outliers moves based on the score: " + model.getOutliersRounds());
    }
}
