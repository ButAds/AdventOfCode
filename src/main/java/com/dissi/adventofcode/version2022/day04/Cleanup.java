package com.dissi.adventofcode.version2022.day04;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.version2022.day04.Job.Assignment;
import com.dissi.adventofcode.version2022.day04.Job.Pair;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class Cleanup implements Answerable {


    private static final String LOCATION = "/2022/day4/input.txt";

    public String getAnswer() throws IOException {
        long result = BufferUtils.getInputAsStringList(LOCATION).stream()
            .map(Assignment::create)
            .filter(pair -> doesOneContainTheOther(pair.left(), pair.right()))
            .count();

        return "" + result;
    }

    private static boolean doesOneContainTheOther(Pair first, Pair second) {
        return (first.start() >= second.start() && first.end() <= second.end()) ||
            (second.start() >= first.start() && second.end() <= first.end());
    }

    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public int getSection() {
        return 1;
    }

    @Override
    public int getYear() {
        return 2022;
    }
}
