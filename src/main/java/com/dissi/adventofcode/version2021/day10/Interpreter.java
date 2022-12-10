package com.dissi.adventofcode.version2021.day10;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day10.ChunkCollector.runChecks;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Interpreter {

    private static final String LOCATION = "/2021/day10/input.txt";

    @SolutionAnnotation(day = 10, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);
        List<Chunk> failures = new ArrayList<>();
        runChecks(lines, failures);
        int out = failures.stream().mapToInt(Chunk::getScore).sum();
        return "" + out;
    }
}
