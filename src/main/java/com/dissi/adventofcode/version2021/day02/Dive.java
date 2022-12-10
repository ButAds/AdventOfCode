package com.dissi.adventofcode.version2021.day02;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class Dive {

    private static final String LOCATION = "/2021/day2/input.txt";

    @SolutionAnnotation(day = 2, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> orders = getInputAsStringList(LOCATION);

        int horizontal = 0;
        int depth = 0;

        for (String line : orders) {
            String[] directionAndSpeed = line.split(" ");
            String direction = directionAndSpeed[0];
            int value = Integer.parseInt(directionAndSpeed[1]);

            if ("down".equals(direction)) {
                depth += value;
            } else if ("up".equals(direction)) {
                depth -= value;
            } else if ("forward".equals(direction)) {
                horizontal += value;
            }
        }

        return "" + (depth * horizontal);
    }
}
