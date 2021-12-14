package com.dissi.adventofcode.version2021.day14;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day14.Polymerization.simulateSteps;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ReactorPolymer implements Answerable {

    private static final String LOCATION = "/2021/day14/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);
        long answer = simulateSteps(lines, 10);
        return "" + answer;
    }

    @Override
    public int getDay() {
        return 14;
    }

    @Override
    public int getSection() {
        return 2;
    }

}
