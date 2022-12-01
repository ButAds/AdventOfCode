package com.dissi.adventofcode.version2022.day01;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CalorieCounterPartTwo implements Answerable {

    private static final String LOCATION = "/2022/day1/input.txt";

    public String getAnswer() throws IOException {
        String allLines = BufferUtils.getInputAsStringNoJoining(LOCATION);
        List<Integer> allElves = Arrays.stream(allLines.split("\\n\\n"))
            .map(s -> Arrays.stream(s.split("\\n"))
                .mapToInt(Integer::parseInt).sum())
            .sorted(Comparator.reverseOrder()).toList();
        return "" + (allElves.get(0) + allElves.get(1) + allElves.get(2));
    }

    @Override
    public int getDay() {
        return 1;
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
