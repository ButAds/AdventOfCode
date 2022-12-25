package com.dissi.adventofcode.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Position {

    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+)");

    private long x;
    private long y;

    public Position() {
        this(0, 0);
    }

    public Position(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    /**
     * Line looking like x,y
     */
    public Position(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Can not match " + line);
        }
        x = Integer.parseInt(matcher.group(1));
        y = Integer.parseInt(matcher.group(2));
    }

    public Position substract(Position other) {
        return new Position(x - other.x, y - other.y);
    }

    public Position add(int deltaX, int deltaY) {
        return new Position(x + deltaX, y + deltaY);
    }

    public Position add(long deltaX, long deltaY) {
        return new Position(x + deltaX, y + deltaY);
    }

    public Position add(Position direction) {
        return this.add(direction.x, direction.y);
    }

    public Position[] getCardinals() {
        return new Position[] {
            Direction.NORTH.translate(this),
            Direction.SOUTH.translate(this),
            Direction.EAST.translate(this),
            Direction.WEST.translate(this),
        };
    }

    public Position[] getNeighbours() {
        return new Position[] {
            Direction.NORTH.translate(this),
            Direction.SOUTH.translate(this),
            Direction.EAST.translate(this),
            Direction.WEST.translate(this),
            Direction.NORTHEAST.translate(this),
            Direction.NORTHWEST.translate(this),
            Direction.SOUTHEAST.translate(this),
            Direction.SOUTHWEST.translate(this),
        };
    }

    public void setX(long newX) {
        this.x = newX;
    }

    public void setY(long newY) {
        this.y = newY;
    }

    public long distanceTo(Position other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}
