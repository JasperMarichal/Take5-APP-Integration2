package model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
            for (int i=0; i<playingTable.getDeck().getCards().size(); i++){


                statement.executeQuery("INSERT INTO  \"deck\"  (card_number) values "+"("+ playingTable.getDeck().getCards().get(i).getNumber() + "#"  + playingTable.getDeck().getCards().get(i).getBulls() +")");
            }
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



    public void loadTxtFile(PlayingTable playingTable){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        FileWriter writer = null;

        try {
            System.out.println("INFO: Connecting to database...");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/game"
                    , "game"
                    , "7sur7"
            );

            Statement statement;

            statement = connection.createStatement();




            // Retrieve data from the table
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM deck");

            // Write to a text file
            writer = new FileWriter("src/output.txt");
            while (rs.next()) {
                writer.write(rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }






    }



    public void loadDeck(){
        try {
            Deck deck1 = new Deck("src/output.txt");
            playingTable.setDeck(deck1) ;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
