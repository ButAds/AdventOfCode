package com.dissi.adventofcode.version2022.day01;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import org.springframework.stereotype.Component;

@Component
public class CalorieCounter implements Answerable {

    private static final String LOCATION = "/2022/day1/input.txt";

    public String getAnswer() throws IOException {
        String allLines = BufferUtils.getInputAsStringNoJoining(LOCATION);
        var amount = Arrays.stream(allLines.split("\\n\\n"))
            .map(s -> Arrays.stream(s.split("\\n"))
                .mapToInt(Integer::parseInt).sum())
            .max(Comparator.naturalOrder()).get();
        return "" + amount;
    }

    @Override
    public int getDay() {
        return 1;
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
