package com.dissi.adventofcode.version2022.day04;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.version2022.day04.Job.Assignment;
import com.dissi.adventofcode.version2022.day04.Job.Pair;
import java.io.IOException;


public class CleanupOverlap {


    private static final String LOCATION = "/2022/day4/input.txt";

    private static boolean checkOverlap(Pair left, Pair right) {
        return (left.start() >= right.start() && left.start() <= right.end()) ||
            (right.start() >= left.start() && right.start() <= left.end());
    }

    @SolutionAnnotation(day = 4, section = 2, year = 2022)
    public String getAnswer() throws IOException {
        long result = BufferUtils.getInputAsStringList(LOCATION).stream()
            .map(Assignment::create)
            .filter(pair -> checkOverlap(pair.left(), pair.right()))
            .count();
        return "" + result;
    }

}
