package com.dissi.adventofcode.version2021.day09;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day09.LavaLakeCalculator.getByte;
import static com.dissi.adventofcode.version2021.day09.LavaLakeCalculator.isLowestNeighbour;
import static java.util.Comparator.comparingInt;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
public class BasinLava implements Answerable {

    private static final String LOCATION = "/2021/day9/input.txt";

    private static void checkNearbyCoord(Coord c, Set<Coord> checked, List<String> data) {
        int gridVal = getByte(c.x, c.y, data, 9);
        if (gridVal == 9) {
            return;
        }

        if (checked.contains(c)) {
            return;
        }

        // Part of this fill.
        checked.add(c);

        if (c.y - 1 >= 0) {
            checkNearbyCoord(new Coord(c.x, c.y - 1), checked, data);
        }
        if (c.x - 1 >= 0) {
            checkNearbyCoord(new Coord(c.x - 1, c.y), checked, data);
        }
        if (c.y + 1 < data.size()) {
            checkNearbyCoord(new Coord(c.x, c.y + 1), checked, data);
        }
        if (c.x + 1 < data.get(0).length()) {
            checkNearbyCoord(new Coord(c.x + 1, c.y), checked, data);
        }
    }

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);

        List<Coord> lowestCoords = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(0).length(); x++) {
                if (isLowestNeighbour(x, y, lines)) {
                    lowestCoords.add(new Coord(x, y));
                }
            }
        }

        Map<Coord, Integer> basinSize = new HashMap<>();
        for (Coord coord : lowestCoords) {
            Set<Coord> checked = new HashSet<>();
            checkNearbyCoord(coord, checked, lines);
            basinSize.put(coord, checked.size());
        }

        // Sort the map on the values.
        List<Map.Entry<Coord, Integer>> sorted = basinSize.entrySet().stream()
            .sorted(comparingInt(Map.Entry<Coord, Integer>::getValue).reversed())
            .toList();

        return "" + sorted.get(0).getValue() * sorted.get(1).getValue() * sorted.get(2).getValue();
    }


    @Override
    public int getDay() {
        return 9;
    }

    @Override
    public int getSection() {
        return 2;
    }

    @EqualsAndHashCode
    private static class Coord {

        int x;
        int y;

        public Coord(int x, int y) {
            this.y = y;
            this.x = x;
        }

    }
}
