package com.dissi.adventofcode.version2022.day01;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;


public class CalorieCounter {

    private static final String LOCATION = "/2022/day1/input.txt";

    @SolutionAnnotation(day = 1, section = 1, year = 2022)
    public String getAnswer() throws IOException {
        String allLines = BufferUtils.getInputAsStringNoJoining(LOCATION);
        var amount = Arrays.stream(allLines.split("\\n\\n"))
            .map(s -> Arrays.stream(s.split("\\n"))
                .mapToInt(Integer::parseInt).sum())
            .max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
        return "" + amount;
    }

}
