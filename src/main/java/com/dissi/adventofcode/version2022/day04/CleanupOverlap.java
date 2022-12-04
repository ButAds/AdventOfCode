package com.dissi.adventofcode.version2022.day04;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.version2022.day04.Job.Assignment;
import com.dissi.adventofcode.version2022.day04.Job.Pair;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class CleanupOverlap implements Answerable {


    private static final String LOCATION = "/2022/day4/input.txt";

    public String getAnswer() throws IOException {
        long result = BufferUtils.getInputAsStringList(LOCATION).stream()
            .map(Assignment::create)
            .filter(pair -> checkOverlap(pair.left(), pair.right()))
            .count();
        return "" + result;
    }

    private static boolean checkOverlap(Pair left, Pair right) {
        return (left.start() >= right.start() && left.start() <= right.end()) ||
            (right.start() >= left.start() && right.start() <= left.end());
    }

    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public int getSection() {
        return 2;
    }

    @Override
    public int getYear() {
        return 2022;
    }
}
