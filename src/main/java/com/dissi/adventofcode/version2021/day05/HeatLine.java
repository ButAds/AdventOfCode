package com.dissi.adventofcode.version2021.day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HeatLine {

    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

    private final List<HeatPoint> heatPoints;
    private final String input;
    private boolean diagonal;

    public HeatLine(String input) {
        this.input = input;
        Matcher match = PATTERN.matcher(input);
        if (!match.find()) {
            heatPoints = Collections.emptyList();
            return;
        }

        int fromX = Integer.parseInt(match.group(1));
        int fromY = Integer.parseInt(match.group(2));
        int toX = Integer.parseInt(match.group(3));
        int toY = Integer.parseInt(match.group(4));

        this.heatPoints = generatePoints(fromX, fromY, toX, toY);
    }

    public static List<HeatLine> createHeatlines(List<String> lines) {
        return lines.stream().map(HeatLine::new).toList();
    }

    private List<HeatPoint> generatePoints(int fromX, int fromY, int toX, int toY) {
        ArrayList<HeatPoint> points = new ArrayList<>();

        int stepSizeX = Integer.signum(toX - fromX);
        int stepSizeY = Integer.signum(toY - fromY);
        this.diagonal = !(fromX == toX || fromY == toY);

        while (fromX != (toX + stepSizeX) || fromY != (toY + stepSizeY)) {
            HeatPoint point = new HeatPoint(fromX, fromY);
            points.add(point);
            fromX += stepSizeX;
            fromY += stepSizeY;
        }
        return points;
    }

}
