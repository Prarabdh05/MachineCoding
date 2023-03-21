package com.prarabdhgupta.snakeAndLadder.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class SnakeLadderBoard {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Map<String,Integer> playerPieces;

        public SnakeLadderBoard(int size){
            this.snakes = new ArrayList<>();
            this.ladders = new ArrayList<>();
            this.size = size;
            this.playerPieces = new HashMap<>();
        }

}
