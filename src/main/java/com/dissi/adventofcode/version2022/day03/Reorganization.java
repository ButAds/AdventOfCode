package com.dissi.adventofcode.version2022.day03;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class Reorganization {

    private static final String LOCATION = "/2022/day3/input.txt";

    @SolutionAnnotation(day = 3, section = 1, year = 2022)
    public String getAnswer() throws IOException {

        int result = BufferUtils.getInputAsStringList(LOCATION).stream()
            .map(data -> new String[] {data.substring(0, data.length() / 2), data.substring(data.length() / 2)})
            .map(halves -> {
                for (char c : halves[0].chars().mapToObj(c -> (char) c).toList()) {
                    if (halves[1].indexOf(c) != -1) {
                        return c;
                    }
                }
                return '-';
            })
            .mapToInt(c -> c > 96 ? c - 96 : c - 38)
            .sum();

        return "" + result;
    }

}
