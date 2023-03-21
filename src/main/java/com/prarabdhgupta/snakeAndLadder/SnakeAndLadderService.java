package com.prarabdhgupta.snakeAndLadder;

import com.prarabdhgupta.snakeAndLadder.models.Ladder;
import com.prarabdhgupta.snakeAndLadder.models.Player;
import com.prarabdhgupta.snakeAndLadder.models.Snake;
import com.prarabdhgupta.snakeAndLadder.models.SnakeLadderBoard;

import java.util.*;

public class SnakeAndLadderService {

    private SnakeLadderBoard snakeLadderBoard;
    private Queue<Player> players ;
    private int totalPlayers;

    public static final int BOARD_SIZE = 100;
    public SnakeAndLadderService(){
        this.snakeLadderBoard = new SnakeLadderBoard(BOARD_SIZE);
        this.players = new LinkedList<>();
    }

    // intiialize sanke and ladder board

    public void setSnakes(List<Snake> snakes){
        snakeLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders){
        snakeLadderBoard.setLadders(ladders);
    }

    public void setPlayers(List<Player> players){
        Map<String,Integer> playerPieces = new HashMap<>();
        this.totalPlayers = players.size();
        for(Player player: players){
            this.players.add(player);
            playerPieces.put(player.getId(),0);
        }
        snakeLadderBoard.setPlayerPieces(playerPieces);
    }



    // core logic
    private boolean isGameCompleted(){
        return totalPlayers > players.size();
    }

    private int getNumberAfterRollingDice(){
        return DiceService.roll();
    }

    private void movePlayer(Player player,int moveValue){
            int currentPosition = snakeLadderBoard.getPlayerPieces().get(player.getId());
            int newPosition = currentPosition + moveValue;
        int boardSize = snakeLadderBoard.getSize();

        if(newPosition>boardSize){
                newPosition = currentPosition;
        }else{
            newPosition = getNewPositionAfterSnakeAndLAdder(newPosition) ;
        }
        snakeLadderBoard.getPlayerPieces().put(player.getId(), newPosition);
        System.out.println(player.getName() + " moved from " + currentPosition + " to " + newPosition);
    }

    private int getNewPositionAfterSnakeAndLAdder(int newPosition){
        List<Snake> snakes = snakeLadderBoard.getSnakes();
        List<Ladder> ladders = snakeLadderBoard.getLadders();
        int prevPosition;
        do{
            prevPosition = newPosition;
            for(Snake snake: snakes){
                if(newPosition==snake.getStart()){
                    newPosition = snake.getEnd();
                }
            }
            for (Ladder ladder: ladders){
                if(newPosition==ladder.getStart()){
                    newPosition = ladder.getEnd();
                }
            }
        }while (prevPosition!=newPosition);

        return newPosition;
    }
    private boolean hasPlayerWon(Player player){
        int playerPosition = snakeLadderBoard.getPlayerPieces().get(player.getId());
        int winningPosition = snakeLadderBoard.getSize();
        return playerPosition == winningPosition;
    }

    public void startGame(){
        while (!isGameCompleted()){
            int diceValue = getNumberAfterRollingDice();
            Player currentPlayer = players.poll();
            System.out.println("Dice roll value for " + currentPlayer.getName() + " is "+ diceValue);
            movePlayer(currentPlayer,diceValue);
            if(hasPlayerWon(currentPlayer)){
                System.out.println(currentPlayer.getName() + " wins the game");
                snakeLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
            }else{
                players.add(currentPlayer);
            }
        }
    }

}
