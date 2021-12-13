package com.dissi.adventofcode.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Position {

    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+)");

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Line looking like x,y
     */
    public Position(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.matches()) {
            throw new RuntimeException("Can not match " + line);
        }
        x = Integer.parseInt(matcher.group(1));
        y = Integer.parseInt(matcher.group(2));
    }
}
