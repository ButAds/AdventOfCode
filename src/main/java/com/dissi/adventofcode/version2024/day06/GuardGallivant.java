package com.dissi.adventofcode.version2024.day06;

import static com.dissi.adventofcode.helpers.Direction.EAST;
import static com.dissi.adventofcode.helpers.Direction.NORTH;
import static com.dissi.adventofcode.helpers.Direction.SOUTH;
import static com.dissi.adventofcode.helpers.Direction.WEST;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.BigGrid;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class GuardGallivant {

    private final List<String> data;

    public GuardGallivant() {
        this.data = BufferUtils.getInputAsStringList(2024, 6, false);
    }

    private MapInput getMap() {
        BigGrid<Character> grid = new BigGrid<>(new HashMap<>());
        Position startPosition = null;
        int y = 0;
        for (String s : data) {
            char[] tiles = s.toCharArray();
            for(int x = 0; x < tiles.length; x++){
                char c = tiles[x];
                if (c == '^') {
                    startPosition = new Position(x, y);
                    grid.add(new Position(x, y), c);
                } else if (c == '#') {
                    grid.add(new Position(x, y), c);
                }
            }
            y++;
        }

        return new MapInput(grid, startPosition);
    }

    @SolutionAnnotation(day = 6, section = 1, year = 2024)
    public long walkingInTheRiver() {
        var startMap = getMap();
        doWalk(startMap);
        return startMap.map.streamPositions()
            .filter(location -> location.getValue() == 'X')
            .count();
    }

    @SolutionAnnotation(day = 6, section = 2, year = 2024)
    public long withObstruction() {
        var initialMap = getMap();
        // Initial setup and modification of the map
        doWalk(initialMap);

        List<Position> positions = initialMap.map.streamPositions()
            .filter(location -> location.getValue() == 'X')
            .map(Entry::getKey)
            .toList();

        int answer = 0;

        for (Position dumpTypewriter : positions) {
            var mapNow = getMap();
            mapNow.map.add(dumpTypewriter, '#');
            // Loop!
            if (doWalk(mapNow)) {
                answer++;
            }
        }

        return answer;
    }

    static boolean doWalk(MapInput mapInput) {
        long maxX = mapInput.map.maxY();
        long maxY = mapInput.map.maxX();

        Direction dir = NORTH;
        Position current = mapInput.startPosition;
        Set<Turn> turns = new HashSet<>();
        while (true) {
            mapInput.map.add(current, 'X');
            Position nextPost = dir.translate(current);
            if (nextPost.getX() >= 0L && nextPost.getY() >= 0L && nextPost.getX() <= maxX && nextPost.getY() <= maxY) {
                if (mapInput.map.getOrDefault(nextPost, '.') == '#') {
                    Turn cur = new Turn(current, dir);
                    if (turns.contains(cur)) {
                        return true; // Loop detected!
                    }
                    dir = turnRight(dir);
                    turns.add(cur);
                } else {
                    current = nextPost;
                }
            } else {
                return false;
            }
        }
    }

    static Direction turnRight(Direction current) {
        return switch (current) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalArgumentException();
        };

    }

    record Turn(Position pos, Direction direction) {

    }

    record MapInput(BigGrid<Character> map, Position startPosition) {

    }

}
