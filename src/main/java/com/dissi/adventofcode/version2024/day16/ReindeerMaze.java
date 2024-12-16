package com.dissi.adventofcode.version2024.day16;

import static com.dissi.adventofcode.helpers.Direction.EAST;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toSet;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Grid;
import com.dissi.adventofcode.helpers.Position;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class ReindeerMaze {

    private final List<String> data;

    public ReindeerMaze() {
        this.data = BufferUtils.getInputAsStringList(2024, 16, false);
    }

    private Maze setupMaze() {
        int maxX = data.get(0).length();
        int maxY = data.size();
        Position start = null;
        Position end = null;
        Character[][] grid = new Character[maxY][maxX];
        for (int y = 0; y < maxY; y++) {
            char[] chars = data.get(y).toCharArray();
            for (int x = 0; x < maxX; x++) {
                if (chars[x] == 'S') {
                    start = new Position(x, y);
                } else if (chars[x] == 'E') {
                    end = new Position(x, y);
                } else {
                    grid[y][x] = chars[x];
                }
            }
        }

        return new Maze(start, end, new Grid<>(grid), 1000);
    }

    @SolutionAnnotation(year = 2024, day = 16, section = 1)
    public long findMyWay() {
        Maze maze = setupMaze();
        return score(calculateCosts(maze), maze);
    }

    @SolutionAnnotation(year = 2024, day = 16, section = 2)
    public long findBestWaySquares() {
        Maze maze = setupMaze();
        Map<Action, Long> state = calculateCosts(maze);
        long fastest = score(state, maze);
        Set<Action> minPath = state.keySet().stream()
            .filter(val -> val.position.equals(maze.end) && state.get(val) == fastest)
            .collect(toSet());
        return findBestTiles(maze, minPath, state).stream().map(Action::position).collect(toSet()).size();
    }


    private Set<Action> findBestTiles(Maze maze, Set<Action> minPath, Map<Action, Long> scoreMap) {
        LinkedList<Action> queue = new LinkedList<>(minPath);
        while (!queue.isEmpty()) {
            Action cycle = queue.pop();
            long currentScore = scoreMap.get(cycle);

            Direction traversal = cycle.direction;
            for (int i = 0; i < 3; i++) {
                traversal = traversal.turnSquare();
                Action prevAction = new Action(cycle.position, traversal, 0);
                if (scoreMap.getOrDefault(prevAction, Long.MAX_VALUE) == currentScore - maze.heuristic && minPath.add(prevAction)) {
                    queue.add(prevAction);
                }
            }

            Position prevLoc = cycle.direction.oneEighty().translate(cycle.position);
            if (maze.grid.at(prevLoc, '.') != '#') {
                Action prevAction = new Action(prevLoc, cycle.direction, 0);
                if (scoreMap.getOrDefault(prevAction, Long.MAX_VALUE) == currentScore - 1 && minPath.add(prevAction)) {
                    queue.add(prevAction);
                }
            }
        }
        return minPath;
    }

    private Map<Action, Long> calculateCosts(Maze maze) {
        PriorityQueue<Action> queue = new PriorityQueue<>(comparingLong(s -> s.score));
        queue.add(new Action(maze.start, EAST, 0));
        Map<Action, Long> scoreMap = new HashMap<>();
        while (!queue.isEmpty()) {
            Action cycle = queue.poll();
            if (scoreMap.getOrDefault(cycle, Long.MAX_VALUE) <= cycle.score) {
                continue;
            }

            scoreMap.put(cycle, cycle.score);
            Position nextLoc = cycle.direction.translate(cycle.position);
            if (maze.grid.at(nextLoc, '.') != '#') {
                queue.add(new Action(nextLoc, cycle.direction, cycle.score + 1));
            }
            Direction traversal = cycle.direction;
            for (int i = 0; i < 3; i++) {
                traversal = traversal.turnSquare();
                queue.add(new Action(cycle.position, traversal, cycle.score + maze.heuristic));
            }
        }

        return scoreMap;
    }

    private long score(Map<Action, Long> costMap, Maze maze) {
        return costMap.entrySet().stream()
            .filter(e -> e.getKey().position.equals(maze.end))
            .mapToLong(Map.Entry::getValue)
            .min()
            .orElseThrow();
    }

    record Maze(Position start, Position end, Grid<Character> grid, long heuristic) {

    }

    record Action(Position position, Direction direction, long score) {

        @Override
        public boolean equals(Object o) {
            Action action = (Action) o;
            if(action == null)
                return false;
            return Objects.equals(position, action.position) && direction == action.direction;
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, direction);
        }
    }
}