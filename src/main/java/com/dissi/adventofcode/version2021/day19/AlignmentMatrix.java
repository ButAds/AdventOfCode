package com.dissi.adventofcode.version2021.day19;

import com.dissi.adventofcode.helpers.Position3D;
import java.util.List;
import lombok.Getter;

public class AlignmentMatrix {

    @Getter
    private final List<Position3D> rotatedPosition;

    public AlignmentMatrix(KeyScanner sc, int rotation, boolean flipX, boolean flipY, boolean flipZ) {
        rotatedPosition = sc.getBeacons()
            .stream().map(pos -> {
                if (rotation == 0) {
                    int currX = pos.getX();
                    int currY = pos.getY();
                    int currZ = pos.getZ();
                    return new Position3D(flipX ? -currX : currX, flipY ? -currY : currY, flipZ ? -currZ : currZ);
                } else if (rotation == 1) {
                    // swap xyz -> zyx
                    int px = pos.getX();
                    int py = pos.getZ();
                    int pz = pos.getY();
                    return new Position3D(flipX ? -px : px, flipY ? -py : py, flipZ ? -pz : pz);
                } else if (rotation == 2) {
                    int px = pos.getY();
                    int py = pos.getX();
                    int pz = pos.getZ();
                    return new Position3D(flipX ? -px : px, flipY ? -py : py, flipZ ? -pz : pz);
                } else if (rotation == 3) {
                    int px = pos.getY();
                    int py = pos.getZ();
                    int pz = pos.getX();
                    return new Position3D(flipX ? -px : px, flipY ? -py : py, flipZ ? -pz : pz);
                } else if (rotation == 4) {
                    int px = pos.getZ();
                    int py = pos.getX();
                    int pz = pos.getY();
                    return new Position3D(flipX ? -px : px, flipY ? -py : py, flipZ ? -pz : pz);
                } else {
                    int px = pos.getZ();
                    int py = pos.getY();
                    int pz = pos.getX();
                    return new Position3D(flipX ? -px : px, flipY ? -py : py, flipZ ? -pz : pz);
                }
            }).toList();
    }

    public int getCount() {
        return this.rotatedPosition.size();
    }

    public Position3D get(int n) {
        return this.rotatedPosition.get(n);
    }
}
