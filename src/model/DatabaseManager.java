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

    private int roundNumer = 1;
    private int roundId = new Random().nextInt();
    public void updateGameTableEnd(int loser, String loserName, int winner,String name, String gameId){
        try {
            statement.executeUpdate(String.format("INSERT INTO player (player_id, name) SELECT '%s', '%s' WHERE NOT EXISTS (  SELECT 1 FROM player WHERE player_id = '%s' AND name = '%s' );", loser, loserName, loser, loserName ));
            statement.executeUpdate(String.format("INSERT INTO player (player_id, name) SELECT '%s', '%s' WHERE NOT EXISTS (  SELECT 1 FROM player WHERE player_id = '%s' AND name = '%s' );", winner, name, winner, name ));
            statement.executeUpdate(String.format("INSERT INTO game (game_id, start_time, end_time, winner) VALUES('%s', '%s', now(), '%s')", gameId, timestamp, winner));
            addRound(gameId);
        } catch (SQLException e) {
            System.out.println("Error updateGameTableStart");
            e.printStackTrace();
        }
    }

    public void addRound(String gameId ){
        try{
            statement.executeUpdate(String.format("INSERT INTO ROUND(game_id, round_id ,start_time,round_number) VALUES ('%s', '%d', now(), %d);", gameId, roundId++, roundNumer++));
        } catch (SQLException e){
            System.out.println("Error addRound: " + e.getMessage());
        }

        System.out.println("CPLLLLL");
    }

    int moveNumber = 1;
    int moveId = new Random().nextInt();
    public void addMove(String gameId, String playerId, int points ){
        if(moveNumber == 10) moveNumber = 1;
        try{
            statement.executeUpdate(String.format("INSERT INTO move(move_id, game_id, round_id, player_id, move_number, points,end_time) VALUES ('%s', '%s', '%s', '%s', %d, %d, now())", moveId++, gameId, roundId, playerId,moveNumber++, points));
        } catch (SQLException e){
            System.out.println("Error addMove: " + e.getMessage());
        }
        System.out.println("Ai doamne mila");
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


