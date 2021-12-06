package com.butads.adventofcode.version2021.day1;

import static com.butads.adventofcode.BufferUtils.getInputAsIntList;

import com.butads.adventofcode.version2021.Answerable;
import java.io.IOException;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class SonarSweep implements Answerable {

    private static final String LOCATION = "/2021/day1/input.txt";

    private static int getTotal(List<Integer> input, int index) {
        return getFromIndex(input, index) + getFromIndex(input, index + 1) + getFromIndex(input, index + 2);
    }

    private static int getFromIndex(List<Integer> input, int index) {
        if (index >= input.size() || index < 0) {
            return 0;
        }
        return input.get(index);
    }

    public String getAnswer() throws IOException {
        List<Integer> integers = getInputAsIntList(LOCATION);
        Integer previous = null;
        int amount = 0;
        for (int i = 0; i < integers.size(); i++) {
            int now = getTotal(integers, i);
            if (previous != null && now > previous) {
                amount++;
            }

            previous = now;
        }

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
}
