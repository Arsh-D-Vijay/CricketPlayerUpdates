package Team;

import DataAccessObject.TeamDao;
import Player.Player;

import java.sql.Array;
import java.util.*;

public class Team {

    private static int teamCounter = 0;
    private String teamID;
    private String teamName;
    private int matchesWon;
    private int matchesPlayed;
    private ArrayList<Player> playersList;
    private ArrayList<Integer> bowlersIDs;

    public ArrayList getPlayersList() {
        return playersList;
    }


    public String getTeamName() {
        return teamName;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }



    public Team(String name) {
        this.playersList = new ArrayList<>();
        this.bowlersIDs = new ArrayList<>();
        this.teamName = name;
        teamCounter++;
        this.teamID = "TEAM_%s".formatted(teamCounter);
        TeamDao.insertTeamToDB(this);
    }

    public void addPlayerToTeam(Player player) {
        this.playersList.add(player);
        if (player.getPlayerPlayingStyle() != 0) {
            bowlersIDs.add(this.playersList.size() - 1);
        }
    }

    public void addToMatchesWon() {
        this.matchesWon++;
    }

    public Player getPlayerByIndex(int idx) {
        return this.playersList.get(idx);
    }

    public int randomNewBowler(int bowlerID) {
        Random rand = new Random();
        int newBowler = bowlerID;

        if (this.bowlersIDs.size() > 2) {
            while (newBowler == bowlerID) {
                newBowler = this.bowlersIDs.get(rand.nextInt(0, this.bowlersIDs.size()));
            }
        } else {
            while (newBowler == bowlerID) {
                newBowler = rand.nextInt(0, this.playersList.size());
            }
        }
        return newBowler;
    }

    public void addToMatchesPlayed() {
        this.matchesPlayed ++;
    }

    public String getTeamID() {
        return teamID;
    }

}
