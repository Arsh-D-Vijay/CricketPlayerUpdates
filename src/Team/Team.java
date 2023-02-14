package Team;
import Player.Player;
import java.util.*;

public class Team {

    public String teamName;
    public int matchesWon;
    public int matchesPlayed;
    HashMap<Integer, Player> playersMap;
    ArrayList<Integer> bowlersIDs;

    public HashMap<Integer, int[]> teamMatchHistory = new HashMap<>();
    Scanner scan = new Scanner(System.in);



    public Team(String name) {
        this.playersMap = new HashMap<>();
        this.bowlersIDs= new ArrayList<>();
        this.teamName = name;
    }

    public void addPlayerToTeam(int id,Player player){
        this.playersMap.put(id, player);
        if(player.getPlayerPlayingStyle() != 0){
            bowlersIDs.add(this.playersMap.size());
        }
    }

    public Player getPlayerByID(int id) {
        return this.playersMap.get(id);
    }

    public int randomNewBowler(int bowlerID) {
        Random rand = new Random();
        int newBowler = bowlerID;

        if (this.bowlersIDs.size() > 2) {
            while (newBowler == bowlerID) {
                newBowler = this.bowlersIDs.get(rand.nextInt(this.bowlersIDs.size()));
            }
        } else {
            while (newBowler == bowlerID) {
                newBowler = rand.nextInt(1, this.playersMap.size() + 1);
            }
        }
        return newBowler;
    }
}
