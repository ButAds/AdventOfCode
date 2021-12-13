package com.dissi.adventofcode.version2021.day11;

import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;

public class SquidGame {

    private final int[][] grid;
    @Getter
    private int day = 0;
    @Getter
    private int flashes = 0;

    public SquidGame(List<String> newGrid) {
        int sizeX = newGrid.get(0).length();
        int sizeY = newGrid.size();
        grid = new int[sizeY][sizeX];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                grid[y][x] = Integer.parseInt("" + newGrid.get(y).charAt(x));
            }
        }
    }

    public void simulateDay() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                // increase all
                grid[y][x]++;
            }
        }

        Set<Position> locations = new HashSet<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] > 9) {
                    // do flashes
                    doFlash(locations, new Position(x, y));
                }
            }
        }

        // reset counter for flashes.
        locations.forEach(loc -> grid[loc.getY()][loc.getX()] = 0);
        day++;
        flashes += locations.size();
    }

    private void doFlash(Set<Position> locations, Position location) {
        if (locations.contains(location)) {
            return;
        }
        locations.add(location);
        grid[location.getY()][location.getX()] = 0;
        // neighbours
        Arrays.stream(Direction.values()).map(dir -> dir.translate(location))
            .filter(this::validLocation)
            .forEach(check -> {
                grid[check.getY()][check.getX()]++;
                if (grid[check.getY()][check.getX()] > 9) {
                    doFlash(locations, check);
                }
            });
    }

    private boolean validLocation(Position location) {
        if (location.getY() < 0 || location.getY() >= grid.length) {
            return false;
        }
        return location.getX() >= 0 && location.getX() < grid[0].length;
    }

    public boolean isAllFlashes() {
        for (int[] yLine : grid) {
            for (int value : yLine) {
                if (value != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
