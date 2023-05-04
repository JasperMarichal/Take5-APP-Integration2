package model;

import java.sql.*;
import java.util.Random;

public class DatabaseManager {
    private Statement statement;
    private Connection connection;
    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://10.134.178.10:5432/game", "game", "7sur7");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Connection failed\n");
            e.printStackTrace();
        }
    }

    public void updateGameTableEnd(int winner,String name, String gameId){
        try {
            statement.executeUpdate(String.format("INSERT INTO player (player_id, name) SELECT '%s', '%s' WHERE NOT EXISTS (  SELECT 1 FROM player WHERE player_id = '%s' AND name = '%s' );", winner, name, winner, name ));
            statement.executeUpdate(String.format("INSERT INTO game (game_id, start_time, end_time, winner) VALUES('%s', '%s', now(), '%s')", gameId, timestamp, winner));
        } catch (SQLException e) {
            System.out.println("Error updateGameTableStart");
            e.printStackTrace();
        }
    }

    private int roundNumer = 1;
    public void addRound(String gameId ){
        try{
            statement.executeUpdate(String.format("INSERT INTO ROUND(game_id, round_id ,start_time,round_number) VALUES ('%s', '%d', now(), %d);", gameId, new Random().nextInt(), roundNumer++));
        } catch (SQLException e){
            System.out.println("Error addRound: " + e.getMessage());
        }
    }


}


