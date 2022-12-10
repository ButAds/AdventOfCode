package com.dissi.adventofcode.version2021.day10;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day10.ChunkCollector.runChecks;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Predictor {

    private static final String LOCATION = "/2021/day10/input.txt";

    @SolutionAnnotation(day = 10, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        List<String> lines = getInputAsStringList(LOCATION);

        List<LinkedList<Chunk>> noEnds = runChecks(lines, new ArrayList<>());
        List<Long> scores = new ArrayList<>();

        noEnds.forEach(chunks -> {
            long score = 0;
            while (!chunks.isEmpty()) {
                Chunk c = chunks.getLast();
                chunks.removeLast();
                score *= 5;
                score += c.getOpenScore();
            }
            scores.add(score);
        });

        Collections.sort(scores);

        return "" + scores.get(scores.size() / 2);
    }
}
