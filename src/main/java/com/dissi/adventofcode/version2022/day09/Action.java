package com.dissi.adventofcode.version2022.day09;


import com.dissi.adventofcode.helpers.Direction;

public record Action(Direction direction, int steps) {

    public static Action parse(String line) {
        String[] action = line.split(" ");
        Direction dir = switch (action[0]) {
            case "L" -> Direction.EAST;
            case "R" -> Direction.WEST;
            case "U" -> Direction.NORTH;
            case "D" -> Direction.SOUTH;
            default -> throw new IllegalArgumentException("No direction found for " + action[0]);
        };
        return new Action(dir, Integer.parseInt(action[1]));
    }
}
