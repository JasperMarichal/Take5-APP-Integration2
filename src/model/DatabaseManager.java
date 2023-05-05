package model;

import java.sql.*;
import java.util.UUID;

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

    private int roundNumer = 1;
    private String roundId = UUID.randomUUID().toString().substring(0,8);
    public void updateGameTableEnd(int loser, String loserName, int winner,String name, String gameId){
        try {
            statement.executeUpdate(String.format("INSERT INTO player (player_id, name) SELECT '%s', '%s' WHERE NOT EXISTS (  SELECT 1 FROM player WHERE player_id = '%s' AND name = '%s' );", loser, loserName, loser, loserName ));
            statement.executeUpdate(String.format("INSERT INTO player (player_id, name) SELECT '%s', '%s' WHERE NOT EXISTS (  SELECT 1 FROM player WHERE player_id = '%s' AND name = '%s' );", winner, name, winner, name ));
            statement.executeUpdate(String.format("INSERT INTO game (game_id, start_time, end_time, winner) VALUES('%s', '%s', now(), '%s')", gameId, timestamp, winner));
            addRound(gameId);
        } catch (SQLException e) {
            System.out.println("Error updateGameTableStart: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private int moveNumber = 1;
    private String moveId = UUID.randomUUID().toString().substring(0,8);
    public void addRound(String gameId ){
        try{
            statement.executeUpdate(String.format("INSERT INTO ROUND(game_id, round_id ,start_time,round_number) VALUES ('%s', '%s', now(), %d);", gameId, roundId, roundNumer++));
        } catch (SQLException e){
            System.out.println("Error addRound: " + e.getMessage());
        }
        roundId = UUID.randomUUID().toString().substring(0,8);

        moveNumber=1;
    }


    public void addMove(String gameId, String playerId, int points ){
        try{
            statement.executeUpdate(String.format("INSERT INTO move(move_id, game_id, round_id, player_id, move_number, points,end_time) VALUES ('%s', '%s', '%s', '%s', %d, %d, now())", moveId, gameId, roundId, playerId,moveNumber++, points));
        } catch (SQLException e){
            System.out.println("Error addMove: " + e.getMessage());
        }

        moveId = UUID.randomUUID().toString().substring(0,8);

    }

    public void closeConnection(){
        try {
            connection.close();
            statement.close();

        } catch (SQLException e){
            System.out.println("Error closeConnection: " + e.getMessage());
        }
    }


}


