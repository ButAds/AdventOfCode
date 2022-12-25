package com.dissi.adventofcode.version2022.day24;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Blazin {

    private static final Map<Character, Direction> DIR = Map.of(
        // row/col switched... too lazy to fix
        '>', Direction.SOUTH,
        'v', Direction.EAST,
        '<', Direction.NORTH,
        '^', Direction.WEST);
    private final Position mapsize;
    private final Position startPoint;
    private final Position exitPoint;
    private final List<Position> hurricanePositions;
    private final List<Direction> hurricaneDirections;


    public Blazin() {
        List<String> data = BufferUtils.getInputAsStringList(2022, 24, false);
        mapsize = new Position(data.size(), data.get(0).length());
        startPoint = new Position(0, 1);
        exitPoint = new Position(data.size() - 1L, data.get(0).length() - 2L);

        hurricanePositions = new ArrayList<>();
        hurricaneDirections = new ArrayList<>();

        for (int l = 1; l < data.size() - 1; l++) {
            String hurricane = data.get(l);
            for (int c = 1; c < hurricane.length() - 1; c++) {
                char hurLoc = hurricane.charAt(c);
                if (hurLoc != '.') {
                    hurricanePositions.add(new Position(l, c));
                    hurricaneDirections.add(DIR.get(hurLoc));
                }
            }
        }

    }


    @SolutionAnnotation(day = 24, section = 1, year = 2022)
    public int doTrip() {
        Set<Position> paths = new HashSet<>();
        int minutes = 0;
        List<Position> huricanes = new ArrayList<>(hurricanePositions);
        paths.add(startPoint);
        while (!paths.contains(exitPoint)) {
            huricanes = doHurricanes(huricanes);
            paths = doElf(exitPoint, paths, huricanes);
            minutes++;
        }

        return minutes;
    }

    @SolutionAnnotation(day = 24, section = 2, year = 2022)
    public int getToTheSnacks() {
        Set<Position> paths = new HashSet<>();
        int minutes = 0;
        List<Position> huricanes = new ArrayList<>(hurricanePositions);
        paths.add(startPoint);
        while (!paths.contains(exitPoint)) {
            huricanes = doHurricanes(huricanes);
            paths = doElf(exitPoint, paths, huricanes);
            minutes++;
        }

        // Find path back to start:
        paths.clear();
        paths.add(exitPoint);
        while (!paths.contains(startPoint)) {
            huricanes = doHurricanes(huricanes);
            paths = doElf(startPoint, paths, huricanes);
            minutes++;
        }

        paths.clear();
        paths.add(startPoint);
        while (!paths.contains(exitPoint)) {
            huricanes = doHurricanes(huricanes);
            paths = doElf(exitPoint, paths, huricanes);
            minutes++;
        }
        return minutes;
    }

    private Set<Position> doElf(Position exitPoint, Set<Position> paths, List<Position> hurricanePositions) {
        Set<Position> deeperPaths = new HashSet<>();
        for (Position position : paths) {
            // Include a wait:
            if (!hurricanePositions.contains(position)) {
                deeperPaths.add(position);
            }
            // Update other positions:
            for (Direction direction : DIR.values()) {
                Position newPosition = direction.translate(position);
                // Add specific exit-case
                if (newPosition.equals(exitPoint)) {
                    deeperPaths.add(exitPoint);
                }
                // Ignore borders:
                if (newPosition.getX() <= 0 || newPosition.getX() >= mapsize.getX() - 1 || newPosition.getY() <= 0
                    || newPosition.getY() >= mapsize.getY() - 1) {
                    continue;
                }
                if (!hurricanePositions.contains(newPosition)) {
                    deeperPaths.add(newPosition);
                }
            }
        }
        return deeperPaths;
    }

    private List<Position> doHurricanes(List<Position> hurricanePositions) {
        //Update the hurricane:
        List<Position> nextIteration = new ArrayList<>();
        for (int h = 0; h < hurricanePositions.size(); h++) {
            Position position = hurricanePositions.get(h);
            Direction direction = hurricaneDirections.get(h);

            Position newLocation = direction.translate(position);
            // Conserve energy:
            nextIteration.add(new Position(Math.floorMod(newLocation.getX() - 1, mapsize.getX() - 2) + 1,
                Math.floorMod(newLocation.getY() - 1, mapsize.getY() - 2) + 1));
        }
        return nextIteration;
    }
}
