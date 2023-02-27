package DataAccessObject;

import Player.Player;
import Team.Team;

import java.sql.*;

public class PlayerDao {

    // GET from TABLE with ID

    public static boolean getPlayer(String playerID){
        Connection conn = ConnectionPoint.getConnection();
        String query = "SELECT * FROM players WHERE playerID = ?;";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,playerID);
            ResultSet res = preparedStatement.executeQuery();
            String id = res.getNString(1);
            String name = res.getNString(2);
            String teamID = res.getNString(3);
            String[] a = {"BATSMAN", "BOWLER", "ALL_ROUNDER"};
            String playStyle = a[res.getInt(4)];
            int runs = res.getInt(5);
            int wickets = res.getInt(6);

            System.out.println("Player ID: "+id+ " || Player Name: "+name+" || Team ID: "+ teamID+" || Playing Style:" +
                               " "+ playStyle+" || Runs: "+ runs+ " || WicketsTaken: "+ wickets+"." );

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void insertPlayerToDB(Player player) {
        Connection conn = ConnectionPoint.getConnection();
        String query = "INSERT INTO players(playersID,playerName,teamID,playingStyle,totalRunsScored," +
                       "totalWicketsTaken) values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, player.getPlayerID());
            preparedStatement.setString(2, player.getPlayerName());
            preparedStatement.setString(3, player.getPlayerTeam().getTeamID());
            preparedStatement.setInt(4, player.getPlayerPlayingStyle());
            preparedStatement.setInt(5, player.getTotalRuns());
            preparedStatement.setInt(6, player.getTotalWicketsTaken());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePlayerPerformance(Player player){
        Connection conn = ConnectionPoint.getConnection();
        String query = "UPDATE players SET totalRunsScored = ? , totalWicketsTaken = ? WHERE playersID = ? ;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, player.getTotalRuns());
            preparedStatement.setInt(2, player.getTotalWicketsTaken());
            preparedStatement.setString(3, player.getPlayerID());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
