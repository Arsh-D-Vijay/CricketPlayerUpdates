package CricketComponents;

import Player.Player;
import Team.Team;

public class Game {

    Innings firstInning;
    Innings secondInning;
    int maxPlayers;

    public void startGame(Team teamA, Team teamB, float maxOvers, int maxPlayers) {
        this.maxPlayers = maxPlayers;
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
            System.out.println("Team : " + firstInning.battingTeam.teamName + " has won the match by " +
                               (firstInning.runs - secondInning.runs) + "runs.");
            firstInning.battingTeam.matchesWon++;
        } else if (firstInning.runs < secondInning.runs) {
            System.out.println("Team : " + secondInning.battingTeam.teamName + " has won the match by " +
                               (maxPlayers - 1 - secondInning.wickets) + " wickets.");
            secondInning.battingTeam.matchesWon++;
        } else {
            System.out.println("DRAW");
        }
    }

    private void displayScoreboard(Innings innings) {
        System.out.println(
                "+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*- SCOREBOARD " +
                "-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*");
        System.out.println("BATTING TEAM: " + innings.battingTeam.teamName);
        Player p;
        for (int i = 1; (i <= maxPlayers && i <= innings.wickets + 2); i++) {
            p = innings.battingTeam.getPlayerByID(i);
            System.out.println(p + " Runs Scored: " + p.getPlayerRuns());
            p.savePerformance();
        }
        System.out.println();
        System.out.println("BOWLING TEAM: " + innings.bowlingTeam.teamName);

        for (int id : innings.didBowling) {
            p = innings.bowlingTeam.getPlayerByID(id);
            System.out.println(p + " Wickets Taken: " + p.getWicketsTaken());
            p.savePerformance();
        }
        System.out.println(
                "-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+**-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*--+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*-+*");
        System.out.println();
    }
}
