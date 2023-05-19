package model;

import java.sql.*;
import java.util.Random;
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
        createTables();
    }

    public void createTables(){
        try{
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS player\n" +
                    "(\n" +
                    "    player_id VARCHAR(255) PRIMARY KEY,\n" +
                    "    name      VARCHAR(255) NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS game\n" +
                    "(\n" +
                    "    game_id    VARCHAR(255) PRIMARY KEY,\n" +
                    "    start_time TIMESTAMP NOT NULL,\n" +
                    "    end_time   TIMESTAMP,\n" +
                    "    winner     VARCHAR(255)\n" +
                    ");\n" +
                    "\n" +
                    "ALTER TABLE game\n" +
                    "    ADD CONSTRAINT fk_player FOREIGN KEY (winner) REFERENCES player (player_id);\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS round\n" +
                    "(\n" +
                    "    round_id     VARCHAR(255) PRIMARY KEY,\n" +
                    "    game_id      VARCHAR(255) NOT NULL,\n" +
                    "    round_number INTEGER      NOT NULL,\n" +
                    "    start_time   TIMESTAMP    NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS move\n" +
                    "(\n" +
                    "    move_id     VARCHAR(255) PRIMARY KEY,\n" +
                    "    game_id     VARCHAR(255) NOT NULL,\n" +
                    "    round_id    VARCHAR(255) NOT NULL,\n" +
                    "    player_id   VARCHAR(255) NOT NULL,\n" +
                    "    move_number INTEGER      NOT NULL,\n" +
                    "    points      INTEGER,\n" +
                    "    end_time    TIMESTAMP    NOT NULL\n" +
                    ");\n");
        } catch (Exception e){
            System.out.println("Error when creating tables" + e.getMessage());
        }
    }

    private int roundNumer = 1;
    private String roundId = UUID.randomUUID().toString().substring(0,8);
    public void updateGameTableEnd(int loser, String loserName, int winner,String name, String gameId){
        try {
            statement.executeUpdate(String.format("INSERT INTO player (player_id, name) SELECT '%s', '%s' WHERE NOT EXISTS (  SELECT 1 FROM player WHERE player_id = '%s' AND name = '%s' );", loser, loserName, loser, loserName ));
            statement.executeUpdate(String.format("INSERT INTO player (player_id, name) SELECT '%s', '%s' WHERE NOT EXISTS (  SELECT 1 FROM player WHERE player_id = '%s' AND name = '%s' );", winner, name, winner, name ));
            statement.executeUpdate(String.format("INSERT INTO game (game_id, start_time, end_time, winner) VALUES('%s', '%s', now(), '%s')", gameId, timestamp, winner));
            statement.executeUpdate(String.format("ALTER TABLE round\n" +
                    "    ADD CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES game (game_id) ON DELETE CASCADE;\n" +
                    "ALTER TABLE move\n" +
                    "    ADD CONSTRAINT fk_game FOREIGN KEY (game_id) REFERENCES game (game_id) ON DELETE CASCADE;\n" +
                    "ALTER TABLE move\n" +
                    "    ADD CONSTRAINT fk_round FOREIGN KEY (round_id) REFERENCES round (round_id) ON DELETE CASCADE;\n" +
                    "ALTER TABLE move\n" +
                    "    ADD CONSTRAINT fk_player FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE CASCADE;"));
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


