package com.dissi.adventofcode.version2021.day21;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Player {

    public int pos;
    public long score;

    public Player(String fromString) {
        this.pos = Integer.parseInt(fromString.substring(fromString.lastIndexOf(" ") + 1));
        this.score = 0;
    }
}
