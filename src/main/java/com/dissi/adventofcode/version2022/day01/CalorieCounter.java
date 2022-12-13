package com.dissi.adventofcode.version2022.day01;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class CalorieCounter {

    private static final String LOCATION = "/2022/day1/input.txt";
    private final String data;

    public CalorieCounter() throws IOException {
        this.data = BufferUtils.getInputAsStringNoJoining(LOCATION);
    }

    @SolutionAnnotation(day = 1, section = 1, year = 2022)
    public int getAnswer() {
        return Arrays.stream(data.split("\\n\\n")).parallel()
            .map(s -> Arrays.stream(s.split("\\n"))
                .mapToInt(Integer::parseInt).sum())
            .max(Comparator.naturalOrder()).orElse(Integer.MIN_VALUE);
    }

    @SolutionAnnotation(day = 1, section = 2, year = 2022)
    public int getTopUsers() {
        List<Integer> allElves = Arrays.stream(data.split("\\n\\n"))
            .map(s -> Arrays.stream(s.split("\\n"))
                .mapToInt(Integer::parseInt).sum())
            .sorted(Comparator.reverseOrder()).toList();
        return (allElves.get(0) + allElves.get(1) + allElves.get(2));
    }
}
