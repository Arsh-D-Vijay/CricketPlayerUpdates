package driver;

import CricketComponents.MatchController;
import Player.Player;
import Team.Teams;

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
        Teams team1 = new Teams(teamName1);
        for(int i= 1; i<=numberOfPlayers;i++){
            System.out.print("\tEnter the Player"+ i +" Name:  ");
            String name = scan.next();
            System.out.print("\tEnter Players playing style: Batsman (0) || Bowler (1)  ||  All-Rounder (2)  : ");
            int playingStyle = scan.nextInt();
            new Player(name,playingStyle,team1,i);
        }

        System.out.println();
        System.out.print("ENTER THE NAME OF OPPONENT TEAM : ");
        String teamName2 = scan.next();
        Teams team2 = new Teams(teamName2);
        for(int i= 1; i<=numberOfPlayers;i++){
            System.out.print("\tEnter the Player"+ i +" Name:  ");
            String name = scan.next();
            System.out.print("\tEnter Players playing style: Batsman (0) || Bowler (1)  ||  All-Rounder (2)  : ");
            int playingStyle = scan.nextInt();
            new Player(name,playingStyle,team2, i);

        }
        System.out.println();
        System.out.print("ENTER THE NUMBER OF OVERS TO BE PLAYED : ");
        int maxOver = scan.nextInt();


        MatchController game = new MatchController();
        game.setMaxOver(maxOver);
        game.seriesMatch(matchesToPlay,team1,team2,numberOfPlayers);
//         Team.Teams T1 = new Team.Teams("Arsh",4);

    }
}