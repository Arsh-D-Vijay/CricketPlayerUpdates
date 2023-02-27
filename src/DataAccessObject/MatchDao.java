package DataAccessObject;

import CricketComponents.Game;
import CricketComponents.MatchController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MatchDao {

    public static void insertGameData(Game game){
        Connection conn = ConnectionPoint.getConnection();
        String query = "INSERT INTO matches(matchID,teamAID,teamBID,matchWinner,overs,totalPlayers," +
                       "teamARuns, teamBRuns ) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,game.getMatchID());
            preparedStatement.setString(2,game.firstInning.battingTeam.getTeamID());
            preparedStatement.setString(3,game.firstInning.bowlingTeam.getTeamID());
            preparedStatement.setString(4,game.winner);
            preparedStatement.setFloat(5,game.maxOvers);
            preparedStatement.setInt(6,game.maxPlayers);
            preparedStatement.setInt(7,game.firstInning.runs);
            preparedStatement.setInt(8,game.secondInning.runs);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
