package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Saver{
    private PlayingTable playingTable;

    public static Connection initializeDatabase() {
        try {
            //Connection to database
            System.out.println("INFO: Connecting to database...");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/game"
                    , "game"
                    , "7sur7"
            );
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to connect to database!");
            e.printStackTrace();

        }

        return null;
    }
}
