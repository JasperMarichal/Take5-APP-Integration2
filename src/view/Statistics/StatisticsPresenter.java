package view.Statistics;

import model.PlayingTable;
import model.Statistics;

import java.util.Map;

public class StatisticsPresenter {
    private final Statistics model;
    private final StatisticsView view;
    private PlayingTable playingTable;

    public StatisticsPresenter(Statistics model, StatisticsView view, PlayingTable playingTable) {
        this.playingTable = playingTable;
        this.model  = model;
        this.view  = view;
        addEventHandlers();
        updateView();
    }
    private void addEventHandlers() {
        // No event handlers yet
    }
    private void updateView() {
        //Give data to the chart
        for(Map.Entry<Integer, Integer> set : model.getMoveChartValues(playingTable.getHashCode()).entrySet()){
            view.setSeries(set.getKey(), set.getValue());
        }
        view.setLineChart();

        // Show the other statistic ( Avg move duration, Most profitable moves, Outliers moves)
        view.getAverage_move_duration().setText(String.format("  Congrats you finished with %s points \n your average move duration was:  %s",playingTable.getPlayer(0).getCounterPoints(), model.getAverageMoveDuration(playingTable.getHashCode())));
        if(model.getMostProfitableMoves().length() < 1){
            view.getMost_profit().setText("You had no profitable moves :( ");
        } else {
            view.getMost_profit().setText(model.getMostProfitableMoves());
        }
        if(model.getOutliersRounds().length() < 1){
            view.getOutliers_rounds().setText("NO REMARKABLE MOVES TO SEE");
        } else{
            view.getOutliers_rounds().setText( model.getOutliersRounds());
        }

    }
}
