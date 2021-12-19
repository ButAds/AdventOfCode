package com.dissi.adventofcode.version2021.day19;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.helpers.Position3D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class KeyScannerSolver {

    private static final String LOCATION = "/2021/day19/input.txt";
    private int maxDist = Integer.MIN_VALUE;
    private Set<Position3D> beacons;

    public void doSolve() throws IOException {
        if (beacons != null) {
            return;
        }
        List<KeyScanner> keyScanners = KeyScannerBuilder.buildScanners(BufferUtils.getInputAsStringList(LOCATION));

        Map<KeyScanner, List<KeyScanner>> pairs = new HashMap<>();
        for (KeyScanner sc : keyScanners) {
            pairs.put(sc, new ArrayList<>());
        }
        for (int i = 0, scannersSize = keyScanners.size(); i < scannersSize; i++) {
            KeyScanner sc1 = keyScanners.get(i);
            for (int j = i + 1, size = keyScanners.size(); j < size; j++) {
                Position3D res = sc1.extend(keyScanners.get(j), false);
                if (res != null) {
                    pairs.get(sc1).add(keyScanners.get(j));
                    pairs.get(keyScanners.get(j)).add(sc1);
                }
            }
        }

        Set<KeyScanner> remain = new HashSet<>(keyScanners);
        Queue<KeyScanner> matchingQueue = new LinkedList<>();
        matchingQueue.add(keyScanners.get(0));
        Set<Position3D> allLocations = new HashSet<>();

        KeyScanner result = keyScanners.get(0);
        while (!matchingQueue.isEmpty()) {
            KeyScanner o = matchingQueue.poll();
            for (KeyScanner s : pairs.get(o)) {
                if (!remain.contains(s)) {
                    continue;
                }
                Position3D res = result.extend(s, true);
                if (res != null) {
                    allLocations.add(res);
                    remain.remove(s);
                    matchingQueue.add(s);
                } else {
                    throw new RuntimeException("Could not mach " + o.getId());
                }
            }
        }
        for (Position3D one : allLocations) {
            for (Position3D loc : allLocations) {
                int d = Math.abs(one.getX() - loc.getX()) +
                    Math.abs(one.getY() - loc.getY()) +
                    Math.abs(one.getZ() - loc.getZ());
                maxDist = Math.max(maxDist, d);
            }
        }
        this.beacons = result.getBeacons();
    }

    public int getBeaconCount() {
        return beacons.size();
    }

    public int getMaxDistance() {
        return this.maxDist;
    }
}
