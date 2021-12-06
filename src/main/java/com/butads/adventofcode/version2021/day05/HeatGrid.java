package com.butads.adventofcode.version2021.day05;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.java.Log;

@Log
public class HeatGrid {

    private final List<HeatLine> heatLines;

    public HeatGrid(List<HeatLine> heatLines) {
        this.heatLines = heatLines;
    }

    public Set<HeatPoint> getPointsOverTwo(boolean includeDiagonal) {
        HashMap<HeatPoint, Integer> heatMap = new HashMap<>();

        heatLines.stream()
            .filter(line -> !line.isDiagonal() || line.isDiagonal() && includeDiagonal) // DOH
            .map(HeatLine::getHeatPoints)
            .flatMap(Collection::stream)
            .forEach(point -> heatMap.put(point, heatMap.getOrDefault(point, 0) + 1));

        return heatMap.entrySet().stream().filter(set -> set.getValue() >= 2)
            .map(Entry::getKey).collect(Collectors.toSet());
    }
}
