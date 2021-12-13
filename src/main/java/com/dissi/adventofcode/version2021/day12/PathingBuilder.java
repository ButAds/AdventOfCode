package com.dissi.adventofcode.version2021.day12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PathingBuilder {


    public static int countPaths(Map<String, List<String>> connections, boolean allowTwice) {
        // Always start with start
        return countPaths(new ArrayList<>(Collections.singletonList("start")), connections, allowTwice);
    }

    private static int countPaths(List<String> path, Map<String, List<String>> connections, boolean allowTwice) {
        if (path.get(path.size() - 1).equals("end")) {
            return 1;
        }
        int cnt = 0;
        for (String neighbour : connections.get(path.get(path.size() - 1))) {
            if (neighbour.equals(neighbour.toUpperCase()) || !path.contains(neighbour)) {
                path.add(neighbour);
                cnt += countPaths(path, connections, allowTwice);
                path.remove(path.size() - 1);
            } else if (allowTwice && !neighbour.equals("start") && !neighbour.equals("end")) {
                path.add(neighbour);
                cnt += countPaths(path, connections, false);
                path.remove(path.size() - 1);
            }
        }
        return cnt;
    }

    public Map<String, List<String>> getConnectionMap(List<String> lines) {
        Map<String, List<String>> connections = new HashMap<>(); // cave to list of caves
        for (String line : lines) {
            String[] cave = line.split("-");
            if (!connections.containsKey(cave[0])) {
                connections.put(cave[0], new ArrayList<>());
            }

            if (!connections.containsKey(cave[1])) {
                connections.put(cave[1], new ArrayList<>());
            }

            connections.get(cave[0]).add(cave[1]);
            connections.get(cave[1]).add(cave[0]);
        }

        return connections;
    }
}
