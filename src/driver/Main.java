package driver;

import CricketComponents.MatchController;
import Player.Player;
import Team.Team;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("ENTER THE NUMBER OF MATCHES TO BE PLAYED IN THIS SERIES!! :  ");
        int matchesToPlay = scan.nextInt();
        System.out.print("ENTER THE NUMBER OF PLAYERS IN TEAM : ");
        byte numberOfPlayers = scan.nextByte();

        System.out.print("ENTER THE NAME OF YOUR TEAM : ");
        String teamName1 = scan.next();
        Team team1 = new Team(teamName1);
        for(int i= 1; i<=numberOfPlayers;i++){
            System.out.print("\tEnter the Player"+ i +" Name:  ");
            String name = scan.next();
            System.out.print("\tEnter Players playing style: Batsman (0) || Bowler (1)  ||  All-Rounder (2)  : ");
            int playingStyle = scan.nextInt();
            new Player(name,playingStyle,team1);
        }

        System.out.println();
        System.out.print("ENTER THE NAME OF OPPONENT TEAM : ");
        String teamName2 = scan.next();
        Team team2 = new Team(teamName2);
        for(int i= 1; i<=numberOfPlayers;i++){
            System.out.print("\tEnter the Player"+ i +" Name:  ");
            String name = scan.next();
            System.out.print("\tEnter Players playing style: Batsman (0) || Bowler (1)  ||  All-Rounder (2)  : ");
            int playingStyle = scan.nextInt();
            new Player(name,playingStyle,team2);

        }
        System.out.println();
        System.out.print("ENTER THE NUMBER OF OVERS TO BE PLAYED : ");
        int maxOver = scan.nextInt();


        MatchController game = new MatchController();
        game.setMaxOver(maxOver);
        game.seriesMatch(matchesToPlay,team1,team2,numberOfPlayers);
//         Team.Team T1 = new Team.Team("Arsh",4);

    }
}