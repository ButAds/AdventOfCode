package com.dissi.adventofcode.version2024.day08;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResonantCollinearity {

    private final int maxX;
    private final int maxY;
    private final HashMap<Character, ArrayList<Position>> antennas = new HashMap<>();

    public ResonantCollinearity() {
        List<String> data = BufferUtils.getInputAsStringList(2024, 8, false);

        this.maxY = data.size();
        this.maxX = data.get(0).length();

        for (int y = 0; y < maxY; y++) {
            char[] row = data.get(y).toCharArray();
            for (int x = 0; x < maxX; x++) {
                if (row[x] != '.') {
                    ArrayList<Position> coords = antennas.getOrDefault(row[x], new ArrayList<>());
                    coords.add(new Position(x, y));
                    antennas.put(row[x], coords);
                }
            }
        }
    }

    @SolutionAnnotation(day = 8, section = 1, year = 2024)
    public long uniqueInBounds() {
        return calculateAntinodePositions(false).size();
    }

    @SolutionAnnotation(day = 8, section = 2, year = 2024)
    public long doCrossReferenceCheck() {
        return calculateAntinodePositions(true).size();
    }

    private Set<Position> calculateAntinodePositions(boolean resonantColiniarityThingyMajingEnabled) {
        Set<Position> antiNodes = new HashSet<>();

        for (List<Position> value : antennas.values()) {
            if (value.size() > 1) {
                for (int i = 0; i < value.size(); i++) {
                    long y = value.get(i).getY();
                    long x = value.get(i).getX();
                    for (int j = i + 1; j < value.size(); j++) {
                        long compareY = value.get(j).getY();
                        long compareX = value.get(j).getX();

                        long yDiff = y - compareY;
                        long xDiff = x - compareX;

                        long antinode1Y = y + yDiff;
                        long antinode1X = x + xDiff;

                        long antinode2Y = compareY - yDiff;
                        long antinode2X = compareX - xDiff;

                        while (antinode1Y < this.maxY && antinode1Y >= 0 && antinode1X < this.maxX
                            && antinode1X >= 0) {
                            antiNodes.add(new Position(antinode1X, antinode1Y));

                            if (!resonantColiniarityThingyMajingEnabled) {
                                break;
                            }

                            antinode1Y += yDiff;
                            antinode1X += xDiff;
                        }

                        while (antinode2Y < this.maxY && antinode2Y >= 0 && antinode2X < this.maxX
                            && antinode2X >= 0) {
                            antiNodes.add(new Position(antinode2X, antinode2Y));

                            if (!resonantColiniarityThingyMajingEnabled) {
                                break;
                            }

                            antinode2Y -= yDiff;
                            antinode2X -= xDiff;
                        }

                        if (resonantColiniarityThingyMajingEnabled) {
                            antiNodes.add(new Position(x, y));
                            antiNodes.add(new Position(compareX, compareY));
                        }
                    }
                }
            }
        }

        return antiNodes;
    }
}
