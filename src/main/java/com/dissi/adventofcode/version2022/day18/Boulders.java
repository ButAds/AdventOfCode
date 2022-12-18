package com.dissi.adventofcode.version2022.day18;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Position3D;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class Boulders {

    private static final String LOCATION = "/2022/day18/input.txt";
    private final Set<Position3D> droplets;

    public Boulders() throws IOException {
        droplets = BufferUtils.getInputAsStringList(LOCATION).stream().map(Position3D::new).collect(Collectors.toSet());
    }

    @SolutionAnnotation(day = 18, section = 1, year = 2022)
    public long doObsidian() {
        return droplets.stream()
            .flatMap(s -> Arrays.stream(s.createNeighbours()))
            .filter(loc -> !droplets.contains(loc))
            .count();
    }

    @SolutionAnnotation(day = 18, section = 2, year = 2022)
    public int watAreYouDoing() {
        Position3D minimum = droplets.stream()
            .min(Comparator.comparingInt(o -> o.getX() + o.getY() + o.getZ())).orElseThrow();

        Position3D firstAir =
            Arrays.stream(minimum.createNeighbours())
                .filter(s -> !droplets.contains(s))
                .findAny()
                .orElseThrow();

        LinkedList<Position3D> queue = new LinkedList<>();
        queue.add(firstAir);
        HashSet<Position3D> airBlocks = new HashSet<>();
        while (!queue.isEmpty()) {
            Position3D cur = queue.poll();
            airBlocks.add(cur);

            for (Position3D neighbor : cur.createNeighbours()) {
                if (airBlocks.contains(neighbor)
                    || droplets.contains(neighbor)
                    || queue.contains(neighbor)
                    || shortestToOthers(droplets, neighbor) > 2) {
                    continue;
                }
                queue.add(neighbor);
            }
        }

        int surfaceArea = 0;
        for (Position3D c : airBlocks) {
            for (Position3D d : c.createNeighbours()) {
                if (droplets.contains(d)) {
                    surfaceArea++;
                }
            }
        }
        return surfaceArea;
    }

    //calculate and return shortest distance to any droplet
    public int shortestToOthers(Set<Position3D> droplets, Position3D pos) {
        return droplets.stream().map(x -> x.calculateDistance(pos)).min(Integer::compare).orElseThrow();
    }


}
