package com.dissi.adventofcode.version2021.day19;

import com.dissi.adventofcode.helpers.Position3D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.ToString;

@ToString
public class KeyScanner {

    @Getter
    private final int id;
    @Getter
    private final Set<Position3D> beacons = new HashSet<>();

    @Getter
    private final List<KeyScanner> relatesTo = new ArrayList<>();

    private final Map<KeyScanner, List<Position3D>> relativeList = new HashMap<>();

    public KeyScanner(int scannerId) {
        this.id = scannerId;
    }

    public void addPoint(String line) {
        this.beacons.add(new Position3D(line));
    }

    public Position3D extend(KeyScanner other, boolean doExtend) {
        Position3D res;
        for (int i = 0; i < 6; i++) {
            // Try all the matrixes on all rotations...
            res = match(doExtend, new AlignmentMatrix(other, i, false, false, false));
            if (res != null) {
                return res;
            }
            res = match(doExtend, new AlignmentMatrix(other, i, false, false, true));
            if (res != null) {
                return res;
            }
            res = match(doExtend, new AlignmentMatrix(other, i, false, true, false));
            if (res != null) {
                return res;
            }
            res = match(doExtend, new AlignmentMatrix(other, i, false, true, true));
            if (res != null) {
                return res;
            }
            res = match(doExtend, new AlignmentMatrix(other, i, true, false, false));
            if (res != null) {
                return res;
            }
            res = match(doExtend, new AlignmentMatrix(other, i, true, false, true));
            if (res != null) {
                return res;
            }
            res = match(doExtend, new AlignmentMatrix(other, i, true, true, false));
            if (res != null) {
                return res;
            }
            res = match(doExtend, new AlignmentMatrix(other, i, true, true, true));
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    public Position3D match(boolean doExtend, AlignmentMatrix other) {
        Set<Position3D> relativeCoordinates = new HashSet<>();
        for (Position3D beacon : beacons) {
            for (Position3D their : other.getRotatedPosition()) {
                relativeCoordinates.add(their.translate(-beacon.getX(), -beacon.getY(), -beacon.getZ()));
            }
        }
        for (Position3D rel : relativeCoordinates) {
            int relX = rel.getX();
            int relY = rel.getY();
            int relZ = rel.getZ();

            int count = 0;
            for (Position3D beacon : beacons) {
                for (Position3D their : other.getRotatedPosition()) {
                    if (relX + beacon.getX() == their.getX() &&
                        relY + beacon.getY() == their.getY() &&
                        relZ + beacon.getZ() == their.getZ()) {
                        count++;
                        if (count == 12) {
                            break;
                        }
                    }
                }
            }
            if (count >= 12) {
                // Found one!
                if (doExtend) {
                    for (int l = 0; l < other.getCount(); l++) {
                        Position3D n = other.get(l);
                        Position3D translatedPos = n.translate(-relX, -relY, -relZ);
                        beacons.add(translatedPos);
                    }
                }
                return new Position3D(relX, relY, relZ);
            }
        }
        return null;
    }

}
