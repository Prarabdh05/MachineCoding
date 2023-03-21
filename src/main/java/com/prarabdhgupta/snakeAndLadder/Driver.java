package com.prarabdhgupta.snakeAndLadder;

import com.google.inject.Inject;
import com.prarabdhgupta.snakeAndLadder.models.Ladder;
import com.prarabdhgupta.snakeAndLadder.models.Player;
import com.prarabdhgupta.snakeAndLadder.models.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // input snake
        int noOfSnakes = scanner.nextInt();
        List<Snake>snakes = new ArrayList<>();
        for(int i=0;i<noOfSnakes;i++){
            snakes.add(new Snake(scanner.nextInt(),scanner.nextInt()));
        }

        //input ladder
        int noOfLadders = scanner.nextInt();
        List<Ladder>ladders = new ArrayList<>();
        for (int i = 0;i<noOfLadders;i++){
            ladders.add(new Ladder(scanner.nextInt(),scanner.nextInt()));
        }

        //input players
        int noOfPlayers = scanner.nextInt();
        List<Player> players =  new ArrayList<>();
        for(int i=0;i<noOfPlayers;i++){
            players.add(new Player(scanner.next()));
        }

        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setLadders(ladders);

        snakeAndLadderService.startGame();

    }
}
