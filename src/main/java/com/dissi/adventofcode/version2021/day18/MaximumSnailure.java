package com.dissi.adventofcode.version2021.day18;

import static com.dissi.adventofcode.version2021.day18.SnailForm.calculateMaximumMagnitude;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MaximumSnailure implements Answerable {

    private static final String LOCATION = "/2021/day18/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = BufferUtils.getInputAsStringList(LOCATION);
        return "" + calculateMaximumMagnitude(lines);
    }

    @Override
    public int getDay() {
        return 18;
    }

    @Override
    public int getSection() {
        return 2;
    }

}
