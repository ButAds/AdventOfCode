package com.dissi.adventofcode.version2022.day14;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.io.IOException;
import java.util.HashSet;

public class SandDungeon {

    private final HashSet<Position> lines = new HashSet<>();


    private static final String LOCATION = "/2022/day14/input.txt";

    public SandDungeon() throws IOException {
        BufferUtils.getInputAsStringList(LOCATION).forEach(s -> {
            String[] words = s.split(" -> ");
            for (int i = 1; i < words.length; i++) {
                String[] coord1 = words[i - 1].split(",");
                String[] coord2 = words[i].split(",");

                int startX = Integer.parseInt(coord1[0]);
                int endX = Integer.parseInt(coord2[0]);
                int startY = Integer.parseInt(coord1[1]);
                int endY = Integer.parseInt(coord2[1]);

                if (startX > endX) {
                    startX = startX + endX;
                    endX = startX - endX;
                    startX = startX - endX;
                }

                if (startY > endY) {
                    startY = startY + endY;
                    endY = startY - endY;
                    startY = startY - endY;
                }

                for (int x = startX; x <= endX; x++) {
                    for (int y = startY; y <= endY; y++) {
                        lines.add(new Position(x, y));
                    }
                }
            }
        });
    }


    @SolutionAnnotation(day = 14, section = 1, year = 2022)
    public int dumpingTime() {
        HashSet<Position> solids = new HashSet<>(lines);

        int lowestY = solids.stream().map(Position::getX).max(Integer::compare).orElse(Integer.MIN_VALUE);

        Position source = new Position(500, 0);
        return getAmountOfSand(source, lowestY, solids);
    }

    private int getAmountOfSand(Position origin, int lowestY, HashSet<Position> floorMap) {
        Position sandPosition = new Position(origin);
        int sandies = 0;
        do {
            if (!floorMap.contains(Direction.SOUTH.translate(sandPosition))) {
                sandPosition = Direction.SOUTH.translate(sandPosition);
            } else if (!floorMap.contains(Direction.SOUTHWEST.translate(sandPosition))) {
                sandPosition = Direction.SOUTHWEST.translate(sandPosition);
            } else if (!floorMap.contains(Direction.SOUTHEAST.translate(sandPosition))) {
                sandPosition = Direction.SOUTHEAST.translate(sandPosition);
            } else {
                sandies++;
                floorMap.add(sandPosition);
                sandPosition = new Position(origin);
            }
        } while (sandPosition.getY() < lowestY);

        return sandies;
    }


    @SolutionAnnotation(day = 14, section = 2, year = 2022)
    public int toSafety() {
        HashSet<Position> floorMap = new HashSet<>(lines);

        int lowestY = floorMap.stream().map(Position::getY).max(Integer::compare).orElse(Integer.MIN_VALUE);
        for (int x = 500 - (2 * lowestY); x <= 500 + (2 * lowestY); x++) {
            floorMap.add(new Position(x, lowestY + 2));
        }
        Position source = new Position(500, 0);

        return getAmountOfSand(source, floorMap);
    }

    private int getAmountOfSand(Position source, HashSet<Position> floorMap) {
        Position sandPosition = new Position(source);

        int sandies = 0;
        do {
            if (!floorMap.contains(Direction.SOUTH.translate(sandPosition))) {
                sandPosition = Direction.SOUTH.translate(sandPosition);
            } else if (!floorMap.contains(Direction.SOUTHWEST.translate(sandPosition))) {
                sandPosition = Direction.SOUTHWEST.translate(sandPosition);
            } else if (!floorMap.contains(Direction.SOUTHEAST.translate(sandPosition))) {
                sandPosition = Direction.SOUTHEAST.translate(sandPosition);
            } else {
                floorMap.add(sandPosition);
                sandPosition = new Position(source);
                sandies++;
            }
        } while (!floorMap.contains(source));
        return sandies;
    }
}
