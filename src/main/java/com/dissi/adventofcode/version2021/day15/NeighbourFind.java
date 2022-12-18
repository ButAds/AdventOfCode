package com.dissi.adventofcode.version2021.day15;

import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.Arrays;
import java.util.List;

public class NeighbourFind {

    private final int[][] minimumrisk;
    private final int[][] risk;

    public NeighbourFind(List<String> lines, int replicationFactor) {
        risk = lines.stream()
            .map(line -> line.chars()
                .map(Character::getNumericValue).toArray())
            .toArray(size -> new int[size][0]);

        minimumrisk = calculateMinRisk(replicationFactor);
    }

    public int getMinimumRisk() {
        return minimumrisk[minimumrisk.length - 1][minimumrisk[0].length - 1];
    }

    private int[][] calculateMinRisk(int replicationFactor) {
        int[][] minrisk = new int[risk.length * replicationFactor][risk[0].length * replicationFactor];
        for (int[] ints : minrisk) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        minrisk[0][0] = 0;
        boolean done = false;

        while (!done) {
            done = true;
            for (int currY = 0; currY < minrisk.length; currY++) {
                for (int currX = 0; currX < minrisk[currY].length; currX++) {
                    int currentRisk = minrisk[currY][currX];
                    for (Direction dir : Direction.NON_DIAGONAL) {
                        Position toCheck = dir.translate(new Position(currX, currY));
                        if (toCheck.getY() >= 0 && toCheck.getY() < minrisk.length && toCheck.getX() >= 0
                            && toCheck.getX() < minrisk[currY].length) {
                            int calculatedRisk = currentRisk + calcRisk((int)toCheck.getX(), (int)toCheck.getY());
                            if (minrisk[(int)toCheck.getY()][(int)toCheck.getX()] > calculatedRisk) {
                                done = false;
                                minrisk[(int)toCheck.getY()][(int)toCheck.getX()] = calculatedRisk;
                            }
                        }
                    }
                }
            }
        }
        return minrisk;
    }

    private int calcRisk(int x, int y) {
        if (x < risk.length && y < risk[x].length) {
            return risk[x][y];
        }
        int adjRisk = 0;
        if (y >= risk[0].length) { //  repeat size Y
            adjRisk = calcRisk(x, y - risk[0].length) + 1;
        } else if (x >= risk.length) {  //  repeat size x
            adjRisk = calcRisk(x - risk.length, y) + 1;
        }
        adjRisk = adjRisk % 10;
        if (adjRisk == 0) {
            adjRisk = 1;
        }
        return adjRisk;
    }

}
