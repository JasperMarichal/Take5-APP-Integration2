package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private Statement statement;
    private Connection connection;
    private String gameId;

    // Constructor
    public Statistics(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://10.134.178.10:5432/", "game", "7sur7");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Connection failed\n");
            e.printStackTrace();
        }
    }

    // Methods to retrieve data from database
    public String getAverageMoveDuration(String gameId){
        String average_move_duration = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format("WITH move_times AS (\n" +
                    "    SELECT end_time - LAG(end_time) OVER (ORDER BY end_time) AS move_time\n" +
                    "    FROM move\n" +
                    "    WHERE game_id = '%s'\n" +
                    ")\n" +
                    "SELECT extract('seconds' from AVG(move_time)) || ' seconds' AS avg_move_time\n" +
                    "FROM move_times\n" +
                    "WHERE move_time IS NOT NULL;", gameId));
            while(resultSet.next()){
                average_move_duration = resultSet.getString(1);
            }

        } catch (Exception e) {
            System.out.println("Error executing the more_avg method");
            e.printStackTrace();
        }
        this.gameId = gameId;

        return average_move_duration;
    }
    public Map<Integer, Integer> getMoveChartValues(String gameId){
        Map<Integer, Integer> move_dur_map = new HashMap<>();
        try{
            ResultSet resultSet = statement.executeQuery(String.format("WITH move_times AS (\n" +
                    "    SELECT end_time - LAG(end_time) OVER (ORDER BY end_time) AS move_time\n" +
                    "    FROM move\n" +
                    "    WHERE game_id = '%s'\n" +
                    "    ORDER BY move_id\n" +
                    ")\n" +
                    "SELECT extract('minutes' from move_time)*60 + extract('seconds' from move_time)\n" +
                    "FROM move_times\n" +
                    "WHERE move_times IS NOT NULL;", gameId));
            int i = 0;
            while(resultSet.next()){
                move_dur_map.put(i++, resultSet.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Error executing the move_dur_round");
            e.printStackTrace();
        }

        return move_dur_map;
    }
    public String getMostProfitableMoves() {
        Map<Integer, String> prof_moves_map = new HashMap<>();
        try {
            ResultSet resultSet = statement.executeQuery(String.format("""
                    SELECT round.round_number, move_number
                    FROM (SELECT round_id, points, move_number, ROW_NUMBER() OVER (ORDER BY round_id,move_number) AS row_num
                          FROM move
                          where game_id = '%s') t1
                    join round on round.round_id = t1.round_id
                    WHERE points = (SELECT points
                                    FROM (SELECT points, ROW_NUMBER() OVER (ORDER BY round_id,t1.move_number) AS row_num
                                          FROM move) t2
                                    WHERE t1.row_num = t2.row_num +1);""", gameId));

            while (resultSet.next()) {
                if (prof_moves_map.containsKey(resultSet.getInt(1))) {
                    String str = prof_moves_map.get(resultSet.getInt(1)) + ", " + resultSet.getString(2);
                    prof_moves_map.replace(resultSet.getInt(1), str);
                } else {
                    prof_moves_map.put(resultSet.getInt(1), resultSet.getString(2));
                }
            }
        } catch (Exception e) {
            System.out.println("Error executing the most_prof_move");
            e.printStackTrace();
        }

        StringBuilder str = new StringBuilder();
        for (Map.Entry<Integer, String> entry : prof_moves_map.entrySet()) {
            str.append("Round: ").append(entry.getKey()).append("--> Move(s): ").append(entry.getValue()).append("\n");
        }

        return str.toString();
    }
    public String getOutliersRounds(){
        StringBuilder outliers_moves;
        List<Double> input = new ArrayList<>();
        List<String> id_of_move = new ArrayList<>();
        try{
            ResultSet resultSet = statement.executeQuery(String.format("""
                    select move_id, points
                    from move
                    where game_id = '%s'
                    order by end_time;""", gameId));

            while (resultSet.next()){
                id_of_move.add(resultSet.getString(1));
                input.add(resultSet.getDouble(2));
            }
            connection.close();
        } catch (SQLException e){
            System.out.println("Error retrieving outliers");
            e.printStackTrace();
        }

        outliers_moves = new StringBuilder();
        List<Double> outliers = OutlierDetector.getOutliers(input);
        for (Double outlier : outliers){
            outliers_moves.append(id_of_move.get(input.indexOf(outlier))).append(", ");
        }

        return String.valueOf(outliers_moves);

    }

}
