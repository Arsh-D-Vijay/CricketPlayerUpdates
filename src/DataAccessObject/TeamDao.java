package DataAccessObject;

import Team.Team;

import java.sql.*;


public class TeamDao {

    // Get
    public static void getTeamData(String teamID) {
        Connection conn = ConnectionPoint.getConnection();
        String query = "SELECT * FROM teams WHERE teamID = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, teamID);
            ResultSet res = preparedStatement.executeQuery();
            String id = res.getNString(1);
            String name = res.getNString(2);
            String playersIDs = res.getString(3);
            int matchesWon = res.getInt(4);
            int matchesPlayed = res.getInt(5);

            System.out.println(
                    "Team ID: " + id + " || Team Name: " + name + " || PlayersIDs: " + playersIDs +
                    " || Matches Won: " + matchesWon + " || Matches Played: " + matchesPlayed + ".");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Insert
    public static void insertTeamToDB(Team team) {
        Connection conn = ConnectionPoint.getConnection();
        String query = "INSERT INTO teams (teamID,teamName,playersIDs,matchesWon,matchesPlayed) values(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, team.getTeamID());
            preparedStatement.setString(2, team.getTeamName());
            preparedStatement.setString(3, team.getPlayersList().toString());
            preparedStatement.setInt(4, team.getMatchesWon());
            preparedStatement.setInt(5, team.getMatchesPlayed());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public static void updateTeamPerformance(Team team) {
        Connection conn = ConnectionPoint.getConnection();
        String query = "UPDATE teams SET matchesWon = ?, matchesPlayed = ? WHERE teamID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, team.getMatchesWon());
            preparedStatement.setInt(2, team.getMatchesPlayed());
            preparedStatement.setString(3, team.getTeamID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
