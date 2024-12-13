package com.dissi.adventofcode.version2024.day12;

import static com.dissi.adventofcode.helpers.Direction.EAST;
import static com.dissi.adventofcode.helpers.Direction.NORTH;
import static com.dissi.adventofcode.helpers.Direction.SOUTH;
import static com.dissi.adventofcode.helpers.Direction.WEST;
import static java.util.stream.Stream.concat;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.BigGrid;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class GardenGroups {

    private final BigGrid<Character> grid;

    public GardenGroups() {
        List<String> data = BufferUtils.getInputAsStringList(2024, 12, false);
        this.grid = new BigGrid<>(new HashMap<>());

        int maxY = data.size();
        int maxX = data.get(0).length();

        for (int y = 0; y < maxY; y++) {
            char[] row = data.get(y).toCharArray();
            for (int x = 0; x < maxX; x++) {
                grid.add(new Position(x, y), row[x]);
            }
        }
    }

    @SolutionAnnotation(year = 2024, day = 12, section = 1)
    public long whereMyFenceAt() {
        return solve((grid, fenceLocation, direction) -> Stream.of());
    }

    @SolutionAnnotation(year = 2024, day = 12, section = 2)
    public long whereMyDiscount() {
        // REDUCE
        return solve((grid, fenceLocation, direction) ->
            concat(explorePerimeter(grid, fenceLocation.start(), direction,
                    grid.getOrDefault(fenceLocation.start(), null), true),
                explorePerimeter(grid, fenceLocation.start(), direction,
                    grid.getOrDefault(fenceLocation.start(), null), false)));
    }

    public long solve(Triple<BigGrid<Character>, FenceLocation, Direction> lambda) {
        Set<Position> visited = new HashSet<>();
        var stack = new LinkedList<Position>();

        return grid.streamPositions().filter(value -> !visited.contains(value.getKey())).mapToLong(value -> {
            Set<FenceLocation> visitedFenceLocations = new HashSet<>();
            stack.clear();
            stack.push(value.getKey());
            AtomicLong area = new AtomicLong();
            AtomicLong perimeter = new AtomicLong();

            while (!stack.isEmpty()) {
                Position current = stack.pop();
                Character currentItem = grid.getOrDefault(current, '-');
                if (visited.add(current)) {
                    area.incrementAndGet();
                    Arrays.stream(Direction.NON_DIAGONAL).forEach(dir -> {
                        Position next = dir.translate(current);
                        if (currentItem.equals(grid.getOrDefault(next, null))) {
                            stack.push(next);
                        } else {
                            var edge = new FenceLocation(current, next);
                            if (visitedFenceLocations.add(edge)) {
                                perimeter.incrementAndGet();
                                lambda.apply(grid, edge, dir).forEach(visitedFenceLocations::add);
                            }
                        }
                    });
                }
            }

            return area.get() * perimeter.get();
        }).sum();
    }

    private Stream<FenceLocation> explorePerimeter(BigGrid<Character> grid, Position current, Direction direction,
        char plant,
        boolean turnRight) {
        Direction newDirection;
        if (turnRight) {
            newDirection = switch (direction) {
                case SOUTH -> WEST;
                case WEST -> NORTH;
                case NORTH -> EAST;
                case EAST -> SOUTH;
                default -> throw new RuntimeException("Direction not recognized");
            };
        } else {
            newDirection = switch (direction) {
                case WEST -> SOUTH;
                case NORTH -> WEST;
                case EAST -> NORTH;
                case SOUTH -> EAST;
                default -> throw new RuntimeException("Direction not recognized");
            };
        }

        return appendWhile(
            fenceLocation -> new FenceLocation(newDirection.translate(fenceLocation.start),
                direction.translate(newDirection.translate(fenceLocation.start))),
            fenceLocation -> grid.has(fenceLocation.start())
                && grid.getOrDefault(fenceLocation.start(), null) == plant && (!grid.has(
                fenceLocation.end())
                || grid.getOrDefault(fenceLocation.end(), null) != plant),
            new FenceLocation(newDirection.translate(current), direction.translate(newDirection.translate(current))));
    }

    record FenceLocation(Position start, Position end) { }

    @FunctionalInterface
    private interface Triple<A, B, C> {
        Stream<FenceLocation> apply(A a, B b, C c);
    }

    public static <A> Stream<A> appendWhile(UnaryOperator<A> func, Predicate<A> pred, A start) {
        return Stream.iterate(start, func).takeWhile(pred);
    }

}



