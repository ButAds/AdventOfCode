package com.dissi.adventofcode.version2022.day04;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.version2022.day04.Job.Assignment;
import com.dissi.adventofcode.version2022.day04.Job.Pair;
import java.io.IOException;


public class Cleanup {


    private static final String LOCATION = "/2022/day4/input.txt";

    private static boolean doesOneContainTheOther(Pair first, Pair second) {
        return (first.start() >= second.start() && first.end() <= second.end()) ||
            (second.start() >= first.start() && second.end() <= first.end());
    }

    @SolutionAnnotation(day = 4, section = 1, year = 2022)
    public String getAnswer() throws IOException {
        long result = BufferUtils.getInputAsStringList(LOCATION).stream()
            .map(Assignment::create)
            .filter(pair -> doesOneContainTheOther(pair.left(), pair.right()))
            .count();

        return "" + result;
    }

}
