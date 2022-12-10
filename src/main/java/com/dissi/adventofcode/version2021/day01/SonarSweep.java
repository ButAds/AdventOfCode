package com.dissi.adventofcode.version2021.day01;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class SonarSweep {

    private static final String LOCATION = "/2021/day1/input.txt";

    @SolutionAnnotation(day = 1, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        int amount = 0;

        List<String> lines = BufferUtils.getInputAsStringList(LOCATION);
        Integer previous = null;

        for (String line : lines) {
            int now = Integer.parseInt(line);
            if (previous != null && now > previous) {
                amount++;
            }

            previous = now;
        }
        return "" + amount;
    }

}
