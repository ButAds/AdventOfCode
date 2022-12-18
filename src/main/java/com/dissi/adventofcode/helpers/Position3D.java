package com.dissi.adventofcode.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Position3D {

    private static final Pattern PATTERN = Pattern.compile("(-?\\d+),(-?\\d+),(-?\\d+)");

    private final int x;
    private final int y;
    private final int z;

    public Position3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Line looking like x,y
     */
    public Position3D(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new RuntimeException("Can not match " + line);
        }
        x = Integer.parseInt(matcher.group(1));
        y = Integer.parseInt(matcher.group(2));
        z = Integer.parseInt(matcher.group(3));
    }

    public Position3D translate(Position3D their) {
        return new Position3D(this.x + their.x, this.y + their.y, this.z + their.z);
    }

    public Position3D translate(int translateX, int translateY, int translateZ) {
        return new Position3D(this.x + translateX, this.y + translateY, this.z + translateZ);

    }

    public Position3D[] createNeighbours() {
        Position3D[] positions = new Position3D[6];
        //top
        positions[0] = new Position3D(this.x, y, z + 1);
        // down
        positions[1] = new Position3D(this.x, y, z - 1);
        //left
        positions[2] = new Position3D(x, y - 1, z);
        // right
        positions[3] = new Position3D(x, y + 1, z);
        //front
        positions[4] = new Position3D(x + 1, y, z);
        //center
        positions[5] = new Position3D(x - 1, y, z);
        return positions;
    }

    public int calculateDistance(Position3D o) {
        return Math.abs(o.x - x) + Math.abs(o.y - y) + Math.abs(o.z - z);
    }
}
