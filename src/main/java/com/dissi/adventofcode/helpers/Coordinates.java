package com.dissi.adventofcode.helpers;

import java.util.HashSet;
import java.util.Set;

public class Coordinates {

    public final Set<Position> coords;

    public Coordinates() {
        coords = new HashSet<>();
    }

    public void add(Position position) {
        this.coords.add(position);
    }

    public int sizeX() {
        return coords.stream().mapToInt(d -> (int)d.getX()).max().getAsInt() + Math.abs(coords.stream().mapToInt(
            d -> (int)d.getX()).min().getAsInt());
    }

    public int sizeY() {
        return coords.stream().mapToInt(d -> (int)d.getY()).max().getAsInt() + Math.abs(coords.stream().mapToInt(
            d -> (int)d.getY()).min().getAsInt());
    }

    public int minX() {
        return coords.stream().mapToInt(d -> (int)d.getX()).min().getAsInt();
    }

    public int minY() {
        return coords.stream().mapToInt(d -> (int)d.getY()).min().getAsInt();
    }

    public Set<Position> getCoordinates() {
        return coords;
    }

    public int size() {
        return this.coords.size();
    }

    @Override
    public String toString() {
        return "Coordinates{coords=" + coords.size() + '}';
    }

    public boolean has(Position translate) {
        return this.coords.contains(translate);
    }

    public void fill(Object[][] grid, Object c) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == c) {
                    add(new Position(x, y));
                }
            }
        }
    }
}
