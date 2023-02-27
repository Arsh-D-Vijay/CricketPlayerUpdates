package CricketComponents;

import DataAccessObject.MatchDao;
import DataAccessObject.TeamDao;
import Team.Team;

import java.util.Random;
import java.util.Scanner;

public class MatchController {

    //    Declaring Class Variables
    private static int counter = 0;
    private String seriesID;
    private float maxOver = 6f; // Default Maximum Over that can be played are 6


    private Team batTeam;
    private Team bowlTeam;

    // Some Final Fields
    Scanner scan = new Scanner(System.in); // Scanner class object

    public MatchController() {
        this.seriesID = "SER_" + ++counter;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public float getMaxOver() {
        return maxOver;
    }

    public Team getBatTeam() {
        return batTeam;
    }

    public Team getBowlTeam() {
        return bowlTeam;
    }

    public void setMaxOver(int maxOver) {
        this.maxOver = maxOver;
    }


    public void seriesMatch(int numberOfMatches, Team teamA, Team teamB, int maxPlayers) {
        while (numberOfMatches > 0) {
            System.out.println();
            toss(teamA, teamB);
            Game game = new Game();
            game.startGame(batTeam, bowlTeam, maxOver,maxPlayers);
            MatchDao.insertGameData(game);
            batTeam.addToMatchesPlayed();
            bowlTeam.addToMatchesPlayed();
            TeamDao.updateTeamPerformance(batTeam);
            TeamDao.updateTeamPerformance(bowlTeam);
            numberOfMatches--;
            System.out.println(
                    "-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-* MATCH " +
                    "ENDED -*-**-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        }
        seriesResult(teamA, teamB);
    }

    private void toss(Team team1, Team team2) {
        System.out.println("Begin the TOSS: ");
        System.out.println("Choose Heads (0) or Tails (1)");
        int calledToss = scan.nextInt();
        Random rand = new Random();
        System.out.println("TOSSING COIN");

        // Randomly selecting the actual output of toss
        int actualToss = rand.nextInt(0, 2);
        int optedPlay;

        // If Won the Toss providing with option to BAT or BOWL first
        if (actualToss == calledToss) {
            System.out.println("You have WON the TOSS...");
            System.out.println("Choose to BAT (1) or BOWL (2) first....");
            optedPlay = scan.nextByte();
        }
        // Else Randomly selecting to Bat or Bowl First
        else {
            System.out.println("You have LOST the TOSS");
            optedPlay = rand.nextInt(1, 3);
            if (optedPlay == 2) {
                System.out.println(team2.getTeamName() + " has chosen to Bat first!!..");
            } else {
                System.out.println(team2.getTeamName() + " has chosen to Bowl first!!..");
            }
        }

        // Setting Batting and Bowling team as per toss outcome
        if (optedPlay == 1) {
            batTeam = team1;
            bowlTeam = team2;
        } else {
            batTeam = team2;
            bowlTeam = team1;
        }
    }
    private void seriesResult(Team teamA, Team teamB) {
        // Series Winner Check
        System.out.println("\n===================================================");
        if (teamA.getMatchesWon() > teamB.getMatchesWon()) {
            System.out.println("Team " + teamA.getTeamName() + " has won the Series with maximum win of " + teamA.getMatchesWon());
        } else if (teamA.getMatchesWon() < teamB.getMatchesWon()) {
            System.out.println("Team " + teamB.getTeamName() + " has won the Series with maximum win of " + teamB.getMatchesWon());
        }else {
            System.out.println(" Nobody WON the Series as both teams has equal Wins!!!");
        }
        System.out.println("===================================================");
    }
}
