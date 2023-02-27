package CricketComponents;

import DataAccessObject.MatchDao;
import DataAccessObject.TeamDao;
import Player.Player;
import Team.Team;

public class Game {

    private static int playerCounter=0;
    private String matchID;
    public Innings firstInning;
    public Innings secondInning;
    public String winner;
    public int maxPlayers;
    public float maxOvers;

    public Game() {
        this.matchID = "MAT_"+playerCounter;
    }

    public String getMatchID() {
        return matchID;
    }

    public void startGame(Team teamA, Team teamB, float maxOvers, int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.maxOvers = maxOvers;
        this.firstInning = new Innings(teamA, teamB);
        System.out.println(
                "-----------------------------------------------------------------------------FIRST-HALF" +
                "-----------------------------------------------------------------------------");
        firstInning.playInning(maxOvers,maxPlayers);
        displayScoreboard(firstInning);

        this.secondInning = new Innings(teamB, teamA);
        secondInning.setTargetScore(firstInning.runs + 1);
        System.out.println(
                "------------------------------------------------------------------------------SECOND-HALF" +
                "------------------------------------------------------------------------------");
        secondInning.playInning(maxOvers,maxPlayers);
        displayScoreboard(secondInning);
        System.out.println();
        gameResult();
    }

    private void gameResult() {
        if (firstInning.runs > secondInning.runs) {
            System.out.println("Team : " + firstInning.battingTeam.getTeamName() + " has won the match by " +
                               (firstInning.runs - secondInning.runs) + "runs.");
            firstInning.battingTeam.addToMatchesWon();
            winner = firstInning.battingTeam.getTeamID();
        } else if (firstInning.runs < secondInning.runs) {
            System.out.println("Team : " + secondInning.battingTeam.getTeamName() + " has won the match by " +
                               (maxPlayers - 1 - secondInning.wickets) + " wickets.");
            secondInning.battingTeam.addToMatchesWon();
            winner = secondInning.battingTeam.getTeamID();

        }  else {
            System.out.println("DRAW");
            winner = "DRAW";
        }

    }

    private void displayScoreboard(Innings innings) {
        System.out.println(
                "+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*- SCOREBOARD " +
                "-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*");
        System.out.println("BATTING TEAM: " + innings.battingTeam.getTeamName());
        Player p;
        for (int i = 0; (i < maxPlayers && i < innings.wickets + 2); i++) {
            p = innings.battingTeam.getPlayerByIndex(i);
            System.out.println(p + " Runs Scored: " + p.getPlayerRuns());
            p.savePerformance();
        }
        System.out.println();
        System.out.println("BOWLING TEAM: " + innings.bowlingTeam.getTeamName());

        for (int id : innings.didBowling) {
            p = innings.bowlingTeam.getPlayerByIndex(id);
            System.out.println(p + " Wickets Taken: " + p.getWicketsTaken());
            p.savePerformance();
        }
        System.out.println(
                "-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+**-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*--+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*");
        System.out.println();
    }
}
