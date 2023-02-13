package Team;
import Player.Player;
import java.util.*;

public class Teams {

    public String teamName;
    public int matchesWon;
    public int matchesPlayed;
    HashMap<Integer, Player> playersMap;
    ArrayList<Integer> bowlersIDs;

    public HashMap<Integer, int[]> teamMatchHistory = new HashMap<>();
    Scanner scan = new Scanner(System.in);



    public Teams(String name) {
        this.playersMap = new HashMap<>();
        this.bowlersIDs= new ArrayList<>();
        this.teamName = name;
    }

//    private void createTeam() {
//        for (int i = 1; i <= this.totalPlayers; i++) {
//            System.out.print("Enter Player" + i + " name: ");
//            String name = scan.next();
//            System.out.print("Enter Player" + i + " playing Style: Batter (0) Bowler (1) All-Rounder(2) ");
//            int playingStyle = scan.nextInt();
//            if (playingStyle == 0 || playingStyle == 2) {
//                bowlersIDs.add(i);
//            } else if (playingStyle < 0 || playingStyle > 2) {
//                System.out.println("OUT OF BOUNDS");
//            }
//            Player p = new Player(name, playingStyle);
//            this.playersMap.put(i, p);
//        }
//    }

    public void addPlayerToTeam(int id,Player player){
        this.playersMap.put(id, player);
        if(player.getPlayerPlayingStyle() != 0){
            bowlersIDs.add(this.playersMap.size());
        }
        System.out.println(playersMap);
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
