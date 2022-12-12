package com.dissi.adventofcode.version2022.day12;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Position;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IceClimbers {

    private static final String LOCATION = "/2022/day12/input.txt";
    private final Map<Position, Integer> mountain;
    private Position start;
    private Position end;

    public IceClimbers() throws IOException {
        List<String> data = BufferUtils.getInputAsStringList(LOCATION);
        mountain = new HashMap<>();
        for (int y = 0; y < data.size(); y++) {
            String line = data.get(y);
            for (int x = 0; x < line.length(); x++) {
                var pos = new Position(x, y);
                int linePathValue = line.charAt(x);
                if (line.charAt(x) == 'S') {
                    start = pos;
                    linePathValue = 'a' - 1;
                } else if (line.charAt(x) == 'E') {
                    end = pos;
                    linePathValue = 'z' + 1;
                }
                mountain.put(pos, linePathValue);
            }
        }
    }

    @SolutionAnnotation(day = 12, section = 1, year = 2022)
    public int takeAHike() {
        return soEarlyTimeForBreadfastSearch(start, end, mountain);
    }

    @SolutionAnnotation(day = 12, section = 2, year = 2022)
    public long takeAScenicHike() {

        return mountain.keySet()
            .parallelStream()
            .filter(c -> mountain.get(c) == 'a')
            .mapToInt(c -> soEarlyTimeForBreadfastSearch(c, end, mountain))
            .min()
            .orElse(Integer.MAX_VALUE);
    }

    private static int soEarlyTimeForBreadfastSearch(Position start, Position end, Map<Position, Integer> map) {
        HashMap<Position, Integer> visited = new HashMap<>();
        HashMap<Position, Position> parent = new HashMap<>();
        LinkedList<Position> queue = new LinkedList<>();
        visited.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Position now = queue.poll();
            if (now.equals(end)) {
                ArrayList<Position> path = new ArrayList<>();
                while (parent.containsKey(now)) {
                    path.add(now);
                    now = parent.get(now);
                }
                return path.size();
            }

            for (Position c : now.getCardinals()) {
                if (!map.containsKey(c) || map.get(c) > map.get(now) + 1) {
                    continue;
                }
                int tentativeG = visited.get(now) + 1;
                if (tentativeG < visited.getOrDefault(c, Integer.MAX_VALUE)) {
                    visited.put(c, tentativeG);
                    parent.put(c, now);
                    queue.add(c);
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
