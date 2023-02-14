package Player;

import Team.Team;

import java.util.HashMap;

public class Player {

    private String playerName;
    private int playerPlayingStyle; // 0 --> Bowler || 1 --> Batter || 2 --> All Rounder
    private int playerRuns;
    private int totalRuns;
    private int wicketsTaken;
    private int totalWicketsTaken;

    private Team playerTeam;

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerPlayingStyle() {
        return playerPlayingStyle;
    }

    public int getPlayerRuns() {
        return playerRuns;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public int getTotalWicketsTaken() {
        return totalWicketsTaken;
    }

    public Team getPlayerTeam() {
        return playerTeam;
    }

    public HashMap<Integer, int[]> getPlayerPerformanceHistory() {
        return playerPerformanceHistory;
    }

    HashMap<Integer,int[]> playerPerformanceHistory;

    public Player(String name, int playerPlayingStyle, Team playerTeam, int playerID){
        this.playerName = name;
        this.playerPlayingStyle = playerPlayingStyle;
        this.playerTeam = playerTeam;
        playerTeam.addPlayerToTeam(playerID, this);
    }

    public int playNextBall() {
        double number = Math.random();

        if (this.playerPlayingStyle == 1) {
            // Probability Distribution for Bowler
            // 0 --> 15%  , 1 --> 35%, 2--> 25%, 3 --> 5%, 4 --> 5%, 5 --> 5%, 6 -->5%, W --> 10%
            if (number < 0.15) {
                return 0;
            } else if (number < 0.45) {
                return 1;
            } else if (number < 0.7) {
                return 2;
            } else if (number < 0.75) {
                return 3;
            } else if (number < 0.8) {
                return 4;
            } else if (number < 0.85) {
                return 5;
            } else if (number < 0.9) {
                return 6;
            } else {
                return 7;
            }
        } else {
            // Probability Distribution for Batsman and All-Rounder
            // 0 --> 5%  , 1 --> 20%, 2--> 15%, 3 --> 10%, 4 --> 20%, 5 --> 5%, 6 -->20%, W --> 5%
            if (number < 0.05) {
                return 0;
            } else if (number < 0.25) {
                return 1;
            } else if (number < 0.4) {
                return 2;
            } else if (number < 0.5) {
                return 3;
            } else if (number < 0.7) {
                return 4;
            } else if (number < 0.75) {
                return 5;
            } else if (number < 0.95) {
                return 6;
            } else {
                return 7;
            }

        }
    }

    public void savePerformance(){
        totalRuns += playerRuns;
        totalWicketsTaken += wicketsTaken;
        playerRuns = 0;
        wicketsTaken = 0;
    }

    public void addPlayerRuns(int ballOutcome){
        this.playerRuns += ballOutcome;
    }
    public  void addWicketsTaken(){
        this.wicketsTaken += 1;
    }

    @Override
    public String toString() {
        return playerName;
    }
}
