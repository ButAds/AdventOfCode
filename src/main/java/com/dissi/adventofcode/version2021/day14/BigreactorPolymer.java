package com.dissi.adventofcode.version2021.day14;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BigreactorPolymer implements Answerable {

    private static final String LOCATION = "/2021/day14/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);
        long answer = Polymerization.simulateSteps(lines, 40);
        return "" + answer;
    }

    @Override
    public int getDay() {
        return 14;
    }

    @Override
    public int getSection() {
        return 1;
    }

}
