package com.dissi.adventofcode.version2021.day11;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class OneHundredDaysLater {

    private static final String LOCATION = "/2021/day11/input.txt";

    @SolutionAnnotation(day = 11, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);
        SquidGame squidGame = new SquidGame(lines);
        for (int i = 0; i < 100; i++) {
            squidGame.simulateDay();
        }

        return "" + squidGame.getFlashes();

    }
}
