package CricketComponents;
import Team.Team;
import java.util.HashSet;
import java.util.Set;

public class Innings {

    public int runs;
    public int wickets;
    public Team battingTeam;
    public Team bowlingTeam;
    public Set<Integer> didBowling = new HashSet<>();
    public int targetScore = Integer.MAX_VALUE;
    public int strikerID;
    public int nonStrikerID;
    public int bowlerID;

    public Innings setTargetScore(int targetScore) {
        this.targetScore = targetScore;
        return this;
    }

    public Innings(Team battingTeam, Team bowlingTeam) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.runs = 0;
        this.wickets = 0;
        this.strikerID = 1;
        this.nonStrikerID = 2;
        this.bowlerID = this.bowlingTeam.randomNewBowler(1);
    }

    public void playInning(float maxOvers, int maxPlayers) {
        System.out.println();
        float currentOver = 0f;
        while (currentOver < maxOvers) {
            System.out.println("Over : " + currentOver);
            currentOver = playOver(currentOver, maxOvers,maxPlayers);
        }
    }

    private float playOver(float currentOver, float maxOvers, int maxPlayers) {
        int ball = 1;
        while (ball < 7) {
            int ballOutcome = this.battingTeam.getPlayerByID(this.strikerID).playNextBall();
            if (!updateScore(ball, ballOutcome,maxPlayers)) {
                return maxOvers;
            }
            ball++;
        }
        currentOver ++;
        overChange(currentOver);
        return currentOver;
    }

    private boolean updateScore(int ball, int ballOutcome,int maxPlayers) {

        // Showing Current Ball Outcome

        if (ballOutcome != 7) {
            System.out.println("\t\t" + (ball) + " : " + ballOutcome);
            this.runs += ballOutcome;
            this.battingTeam.getPlayerByID(this.strikerID).addPlayerRuns(ballOutcome);
        } else {
            System.out.println("\t\t" + (ball) + " : Wicket!!! " + this.battingTeam.getPlayerByID(this.strikerID) +
                               " is out.. by stunning bowling of  " + this.bowlingTeam.getPlayerByID(this.bowlerID));

            this.bowlingTeam.getPlayerByID(this.bowlerID).addWicketsTaken();
            this.wickets += 1;
            // Checking for ALL OUT
            if (this.wickets == maxPlayers - 1) {
                System.out.println("               ALL OUT !!!");
                return false;
            }
            this.strikerID = this.wickets + 2;
        }

        // Updating Striker and NonStriker
        if (ballOutcome % 2 != 0) {
            swapPlayersSide();
        }

        // Checking if the chasing team has won or not...
        if (runs > targetScore) {
            return false;
        }
        return true;
    }

    private void swapPlayersSide() {
        int temp = this.strikerID;
        this.strikerID = this.nonStrikerID;
        this.nonStrikerID = temp;
    }

    public void overChange(float currentOver) {
        System.out.println("=====================================");
        System.out.println("OVER SUMMARY");
        System.out.println(
                "Over : " + currentOver + " TEAM " + this.battingTeam.teamName + "  Runs: " + this.runs + " Wickets: " + this.wickets);
        System.out.println("ON STRIKE: " + this.battingTeam.getPlayerByID(this.strikerID) + " : " +
                           this.battingTeam.getPlayerByID(this.strikerID).getPlayerRuns());
        System.out.println("ON NON-STRIKE: " + this.battingTeam.getPlayerByID(this.nonStrikerID) + " : " +
                           this.battingTeam.getPlayerByID(this.nonStrikerID).getPlayerRuns());
        System.out.println("BOWLER : " + this.bowlingTeam.getPlayerByID(this.bowlerID));
        System.out.println("====================================");
        System.out.println();
        didBowling.add(this.bowlerID);
        swapPlayersSide();
        this.bowlerID =  this.bowlingTeam.randomNewBowler(this.bowlerID);
    }
}
