package com.dissi.adventofcode.version2022.day15;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Position;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class SavingPrivateBeacon {

    private static final String LOCATION = "/2022/day15/input.txt";

    private final HashMap<Position, Position> sensorAndBeacon;

    public SavingPrivateBeacon() throws IOException {
        this.sensorAndBeacon = new HashMap<>();
        BufferUtils.getInputAsStringList(LOCATION).forEach(s -> {
            String[] words = s.split(", |: | ");
            Position sensor = new Position(Integer.parseInt(words[2].split("=")[1]),
                Integer.parseInt(words[3].split("=")[1]));
            Position beacon = new Position(Integer.parseInt(words[8].split("=")[1]),
                Integer.parseInt(words[9].split("=")[1]));
            sensorAndBeacon.put(sensor, beacon);
        });
    }

    @SolutionAnnotation(day = 15, section = 1, year = 2022)
    public int beaconSensors() {
        List<Position> noBeaconLocations = noBeaconRanges(sensorAndBeacon, 2000000);
        List<Position> noFound = merge(noBeaconLocations);
        return noFound.get(0).getY() - noFound.get(0).getX();
    }

    private List<Position> merge(List<Position> noBeaconLocations) {
        if (noBeaconLocations.isEmpty()) {
            return Collections.emptyList();
        }

        LinkedList<Position> newRanges = new LinkedList<>();
        newRanges.add(noBeaconLocations.get(0));
        for (int i = 1; i < noBeaconLocations.size(); i++) {
            Position range = noBeaconLocations.get(i);
            Position end = newRanges.getLast();

            if (range.getY() >= end.getX() && range.getY() <= end.getY()) {
                end.setX(Math.min(range.getX(), end.getX()));
            }

            if (range.getX() >= end.getX() && range.getX() <= end.getY()) {
                end.setY(Math.max(range.getY(), end.getY()));
            }

            if (!(range.getX() >= end.getX() && range.getX() <= end.getY()) && !(range.getY() >= end.getX()
                && range.getY() <= end.getY())) {
                newRanges.addFirst(range);
            }
        }
        return newRanges;
    }

    public List<Position> noBeaconRanges(Map<Position, Position> map, int y) {
        return map.keySet().stream()
            .map(c -> {
                int dist = c.distanceTo(map.get(c));
                int xRange = dist - Math.abs(c.getY() - y);
                if (xRange > 0) {
                    return new Position(c.getX() - xRange, c.getX() + xRange);
                } else {
                    return null;
                }
            }).filter(Objects::nonNull)
            .sorted(Comparator.comparingInt(Position::getX))
            .toList();
    }

    private record Row(int y, List<Position> positions) {

    }

    @SolutionAnnotation(day = 15, section = 2, year = 2022)
    public long findTuningFrequency() {
        final AtomicInteger value = new AtomicInteger();
        final Row item = IntStream.range(0, 4000000).parallel()
            .mapToObj(y -> new Row(y, merge(noBeaconRanges(sensorAndBeacon, y))))
            .filter(row -> row.positions.size() > 1)
            .filter(row -> {
                OptionalInt first = IntStream.range(0, 4000000).parallel()
                    .filter(x -> row.positions.stream().noneMatch(c -> x >= c.getX() && x <= c.getY()))
                    .findFirst();
                if (first.isPresent()) {
                    value.set(first.getAsInt());
                }
                return first.isPresent();
            })
            .findFirst().orElseThrow();

        return value.get() * 4000000L + item.y;

    }
}
