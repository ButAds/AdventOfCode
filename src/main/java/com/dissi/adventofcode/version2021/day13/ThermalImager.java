package com.dissi.adventofcode.version2021.day13;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ThermalImager implements Answerable {

    private static final String LOCATION = "/2021/day13/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);
        Paper paper = new Paper(lines);

        while (!paper.fold()) {
        }

        return "\n" + paper;
    }

    @Override
    public int getDay() {
        return 13;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
