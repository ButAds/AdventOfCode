package com.dissi.adventofcode.version2022.day04;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.version2022.day04.Job.Assignment;
import com.dissi.adventofcode.version2022.day04.Job.Pair;
import java.io.IOException;
import java.util.List;


public class CleanupOverlap {

    private static final String LOCATION = "/2022/day4/input.txt";
    private final List<String> data;

    public CleanupOverlap() throws IOException {
        this.data = BufferUtils.getInputAsStringList(LOCATION);
    }

    @SolutionAnnotation(day = 4, section = 1, year = 2022)
    public long getContainer() {
        return data.parallelStream()
            .map(Assignment::create)
            .filter(pair -> doesOneContainTheOther(pair.left(), pair.right()))
            .count();
    }

    private static boolean doesOneContainTheOther(Pair first, Pair second) {
        return (first.start() >= second.start() && first.end() <= second.end()) ||
            (second.start() >= first.start() && second.end() <= first.end());
    }

    @SolutionAnnotation(day = 4, section = 2, year = 2022)
    public long getOverlap() {
        return data.parallelStream()
            .map(Assignment::create)
            .filter(pair -> checkOverlap(pair.left(), pair.right()))
            .count();

    }

    private static boolean checkOverlap(Pair left, Pair right) {
        return (left.start() >= right.start() && left.start() <= right.end()) ||
            (right.start() >= left.start() && right.start() <= left.end());
    }

}
