package model;

import java.sql.*;

public class Saver{
    private PlayingTable playingTable;

    public static Connection initializeDatabaseSave(PlayingTable playingTable){

        try {
            //Connection to database
            System.out.println("INFO: Connecting to database...");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/game"
                    , "game"
                    , "7sur7"
            );

            Statement statement;

            statement = connection.createStatement();
            String Query = "insert into \"Cards For Hands\" (game_number, card_number, player, card_bulls) values (?, ?, ?, ? )";
            PreparedStatement pstmt = connection.prepareStatement(Query);
            for (int i=0; i<playingTable.getPlayers()[0].getHand().getCards().size(); i++){


            pstmt.setInt(1, 0);
            pstmt.setInt(2, playingTable.getPlayers()[0].getHand().getCards().get(i).getNumber());
            pstmt.setInt(3, 0);
            pstmt.setInt(4, playingTable.getPlayers()[0].getHand().getCards().get(i).getBulls());
            pstmt.executeUpdate();
            }
            for (int i=0; i<playingTable.getPlayers()[1].getHand().getCards().size(); i++){


                pstmt.setInt(1, 0);
                pstmt.setInt(2, playingTable.getPlayers()[1].getHand().getCards().get(i).getNumber());
                pstmt.setInt(3, 1);
                pstmt.setInt(4, playingTable.getPlayers()[1].getHand().getCards().get(i).getBulls());
                pstmt.executeUpdate();
            }




        } catch (SQLException e) {
            System.err.println("ERROR: Failed to connect to database!");
            e.printStackTrace();

        }

        return null;
    }
}
