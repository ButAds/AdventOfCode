package com.dissi.adventofcode.helpers;

public enum Direction {
    NORTHWEST(-1, -1),
    NORTH(0, -1),
    NORTHEAST(1, -1),
    WEST(-1, 0),
    SELF(0, 0),
    EAST(1, 0),
    SOUTHWEST(-1, 1),
    SOUTH(0, 1),
    SOUTHEAST(1, 1);

    public static final Direction[] NON_DIAGONAL = new Direction[] {NORTH, EAST, SOUTH, WEST};
    public static final Direction[] DIAGONAL = new Direction[] {NORTH, EAST, SOUTH, WEST, NORTHEAST, NORTHWEST,
        SOUTHWEST, SOUTHEAST};
    public static final Direction[] DIAGONAL_ONLY = new Direction[] {NORTHEAST, NORTHWEST,
        SOUTHWEST, SOUTHEAST};

    final int dx;
    final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Position translate(Position location) {
        return new Position(location.getX() + this.dx, location.getY() + this.dy);
    }
}
