package com.dissi.adventofcode.version2021.day25;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class SeaCumcumbers {

    private static final String LOCATION = "/2021/day25/input.txt";

    @SolutionAnnotation(day = 25, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> inputs = getInputAsStringList(LOCATION);

        FloorMap map = FloorMap.fromString(inputs);
        int steps = 0;
        int moved;
        do {
            moved = map.tick();
            ++steps;
        } while (moved != 0);

        return "" + steps;
    }
}
