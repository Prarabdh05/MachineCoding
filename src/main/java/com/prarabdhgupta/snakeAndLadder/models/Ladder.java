package com.prarabdhgupta.snakeAndLadder.models;

import lombok.Data;

@Data
public class Ladder {
    private int start;
    private int end;

    public Ladder(int start, int end){
        this.start = start;
        this.end = end;
    }
}
