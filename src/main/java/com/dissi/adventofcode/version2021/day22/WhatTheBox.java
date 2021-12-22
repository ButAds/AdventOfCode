package com.dissi.adventofcode.version2021.day22;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class WhatTheBox {

    private static final Pattern PATTERN = Pattern.compile(
        "(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");
    private final int x1;
    private final int x2;
    private final int y1;
    private final int y2;
    private final int z1;
    private final int z2;
    @Setter
    private boolean on;

    public WhatTheBox(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new RuntimeException("Error line " + line);
        }
        on = matcher.group(1).equals("on");
        this.x1 = Integer.parseInt(matcher.group(2));
        this.x2 = Integer.parseInt(matcher.group(3));
        this.y1 = Integer.parseInt(matcher.group(4));
        this.y2 = Integer.parseInt(matcher.group(5));
        this.z1 = Integer.parseInt(matcher.group(6));
        this.z2 = Integer.parseInt(matcher.group(7));

    }

    public WhatTheBox(boolean state, int x1, int x2, int y1, int y2, int z1, int z2) {
        this.on = state;
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    public boolean overlapsWith(WhatTheBox other) {
        return (this.x1 <= other.x2 && this.x2 >= other.x1) &&
            (this.y1 <= other.y2 && this.y2 >= other.y1) &&
            (this.z1 <= other.z2 && this.z2 >= other.z1);
    }

    public Optional<WhatTheBox> intersect(WhatTheBox c, boolean on) {
        if (x1 > c.x2 || x2 < c.x1 || y1 > c.y2 ||
            y2 < c.y1 || z1 > c.z2 || z2 < c.z1) {
            return Optional.empty();
        }

        return Optional.of(new WhatTheBox(on,
            Math.max(x1, c.x1), Math.min(x2, c.x2),
            Math.max(y1, c.y1), Math.min(y2, c.y2),
            Math.max(z1, c.z1), Math.min(z2, c.z2)));
    }

    public long volume() {
        return (x2 - x1 + 1L) * (y2 - y1 + 1L) * (z2 - z1 + 1L) * (on ? 1 : -1);
    }
}
