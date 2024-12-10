package com.dissi.adventofcode.version2024.day10;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.BigGrid;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class HoofIt {

    private final BigGrid<Integer> grid;

    public HoofIt() {
        List<String> data = BufferUtils.getInputAsStringList(2024, 10, false);
        this.grid = new BigGrid<>(new HashMap<>());
        int maxY = data.size();
        int maxX = data.get(0).length();
        for (int y = 0; y < maxY; y++) {
            char[] line = data.get(y).toCharArray();
            for (int x = 0; x < maxX; x++) {
                Integer item = line[x] - 48;
                grid.add(new Position(x, y), item);
            }
        }
    }

    @SolutionAnnotation(year = 2024, day = 10, section = 1)
    public long somePeakInTheDistance() {
        return grid.streamPositions().map(positionObjectEntry ->
                positionObjectEntry.getValue() == 0 ?
                    findPath(positionObjectEntry.getKey(), 0, grid) :
                    null
            ).filter(Objects::nonNull)
            .mapToLong(TrailPos::score).sum();
    }

    @SolutionAnnotation(year = 2024, day = 10, section = 2)
    public long somePeakInTheDeDupedDistance() {
        return grid.streamPositions().map(positionObjectEntry ->
                positionObjectEntry.getValue() == 0 ?
                    findPath(positionObjectEntry.getKey(), 0, grid) :
                    null
            ).filter(Objects::nonNull)
            .mapToLong(TrailPos::scoreSecondPart).sum();
    }

    private TrailPos findPath(Position pos, int currentVal, BigGrid<Integer> mountain) {
        Integer height = mountain.getOrDefault(pos, null);
        if (height != null && height == currentVal) {
            List<TrailPos> adjacent = Stream.of(
                findPath(Direction.NORTH.translate(pos), currentVal + 1, mountain),
                findPath(Direction.EAST.translate(pos), currentVal + 1, mountain),
                findPath(Direction.SOUTH.translate(pos), currentVal + 1, mountain),
                findPath(Direction.WEST.translate(pos), currentVal + 1, mountain)
            ).filter(Objects::nonNull).toList();

            return new TrailPos(pos, currentVal, adjacent);
        }

        return null;
    }

    record TrailPos(Position pos, int height, List<TrailPos> adjacent) {

        Set<TrailPos> reachablePeaks() {
            if (height == 9) {
                return Set.of(this);
            }

            Set<TrailPos> peaks = new HashSet<>();
            for (TrailPos tp : adjacent()) {
                peaks.addAll(tp.reachablePeaks());
            }

            return peaks;
        }

        int score() {
            return reachablePeaks().size();
        }

        int scoreSecondPart() {
            if (height == 9) {
                return 1;
            }

            return adjacent.stream().mapToInt(TrailPos::scoreSecondPart).sum();
        }

        @Override
        public String toString() {
            return "[" + pos + "]: " + score() + (height == 0 ? "(" + reachablePeaks() + ")" : "");
        }
    }
}
