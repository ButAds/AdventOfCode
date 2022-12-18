package com.dissi.adventofcode.version2022.day17;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pyroclastic {

    private static final String LOCATION = "/2022/day17/input.txt";
    private final String data;

    public Pyroclastic() throws IOException {
        this.data = BufferUtils.getInputAsString(LOCATION);
    }

    @SolutionAnnotation(day = 17, section = 1, year = 2022)
    public long rockPaperCave() {
        return doSimulation(2022);
    }

    @SolutionAnnotation(day = 17, section = 2, year = 2022)
    public long makeElephantsHappy() {
        return doSimulation(1000000000000L);
    }

    public long doSimulation(long rockTarget) {
        Set<Position> rocksInCave = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            rocksInCave.add(new Position(i, -1));
        }

        boolean[] jetPattern = new boolean[data.length()];
        IntStream.range(0, data.length()).forEach(i ->
            jetPattern[i] = data.charAt(i) == '>'
        );

        List<Set<Position>> rocks = new ArrayList<>();
        addShapes(rocks);

        int jetCounter = 0;

        Map<Set<Position>, Position> cache = new HashMap<>();

        boolean cycleFound = false;
        long heightFromCycleRepeat = 0;

        long rockCount = 0;
        while (rockCount < rockTarget) {
            Set<Position> now = new HashSet<>(rocks.get((int) (rockCount % 5)));

            long maxY = rocksInCave.stream().map(Position::getY).max(Long::compare).orElseThrow();

            now = now.stream().map(x -> x.add(2, maxY + 4)).collect(Collectors.toSet());

            boolean solutionFound = false;
            while (!solutionFound) {
                if (jetPattern[jetCounter % jetPattern.length]) {
                    long highestX = now.stream().map(Position::getX).max(Long::compare).orElseThrow();
                    Set<Position> tentativeRight = now.stream().map(Direction.EAST::translate)
                        .collect(Collectors.toSet());
                    if (highestX < 6 && tentativeRight.stream().noneMatch(rocksInCave::contains)) {
                        now = tentativeRight;
                    }
                } else {
                    long lowestX = now.stream().map(Position::getX).min(Long::compare).orElseThrow();
                    Set<Position> tentativeLeft = now.stream().map(Direction.WEST::translate)
                        .collect(Collectors.toSet());
                    if (lowestX > 0 && tentativeLeft.stream().noneMatch(rocksInCave::contains)) {
                        now = tentativeLeft;
                    }
                }
                jetCounter++;

                for (Position c : now) {
                    if (rocksInCave.contains(Direction.NORTH.translate(c))) {
                        rocksInCave.addAll(now);
                        long curHeight = rocksInCave.stream().map(Position::getY).max(Long::compare).orElseThrow();
                        long currMaxY = rocksInCave.stream().map(Position::getY).max(Long::compare).orElseThrow();
                        Set<Position> cacheKey = rocksInCave.stream()
                            .filter(x -> currMaxY - x.getY() <= 30)
                            .map(x -> new Position(x.getX(), currMaxY - x.getY()))
                            .collect(Collectors.toSet());
                        if (!cycleFound && cache.containsKey(cacheKey)) {
                            Position info = cache.get(cacheKey);
                            long oldTime = info.getX();
                            long oldHeight = info.getY();
                            long cycleLength = (rockCount - oldTime);
                            long cycleHeightChange = curHeight - oldHeight;
                            long numCycles = (rockTarget - rockCount) / cycleLength;
                            heightFromCycleRepeat = cycleHeightChange * numCycles;
                            rockCount += numCycles * cycleLength;
                            cycleFound = true;
                        } else {
                            Position info = new Position(rockCount, curHeight);
                            cache.put(cacheKey, info);
                        }

                        solutionFound = true;
                        break;
                    }
                }

                now = now.stream().map(Direction.NORTH::translate).collect(Collectors.toSet());
            }

            rockCount++;
        }

        return (rocksInCave.stream().map(Position::getY).max(Long::compare).orElseThrow() + heightFromCycleRepeat + 1);
    }

    private void addShapes(List<Set<Position>> rocks) {
        Set<Position> line = new HashSet<>();
        line.add(new Position(0, 0));
        line.add(new Position(1, 0));
        line.add(new Position(2, 0));
        line.add(new Position(3, 0));

        rocks.add(line);

        Set<Position> plus = new HashSet<>();
        plus.add(new Position(0, 1));
        plus.add(new Position(1, 1));
        plus.add(new Position(2, 1));
        plus.add(new Position(1, 2));
        plus.add(new Position(1, 0));
        rocks.add(plus);

        Set<Position> l = new HashSet<>();
        l.add(new Position(0, 0));
        l.add(new Position(1, 0));
        l.add(new Position(2, 0));
        l.add(new Position(2, 1));
        l.add(new Position(2, 2));
        rocks.add(l);

        HashSet<Position> vertical = new HashSet<>();
        vertical.add(new Position(0, 0));
        vertical.add(new Position(0, 1));
        vertical.add(new Position(0, 2));
        vertical.add(new Position(0, 3));
        rocks.add(vertical);

        HashSet<Position> square = new HashSet<>();
        square.add(new Position(0, 0));
        square.add(new Position(0, 1));
        square.add(new Position(1, 1));
        square.add(new Position(1, 0));
        rocks.add(square);
    }
}
