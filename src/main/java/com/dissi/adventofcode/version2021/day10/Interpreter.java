package com.dissi.adventofcode.version2021.day10;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day10.ChunkCollector.runChecks;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class Interpreter implements Answerable {

    private static final String LOCATION = "/2021/day10/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);
        List<Chunk> failures = new ArrayList<>();
        runChecks(lines, failures);
        int out = failures.stream().mapToInt(Chunk::getScore).sum();
        return "" + out;
    }

    @Override
    public int getDay() {
        return 10;
    }

    @Override
    public int getSection() {
        return 1;
    }

}
