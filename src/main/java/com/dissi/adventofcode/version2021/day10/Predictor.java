package com.dissi.adventofcode.version2021.day10;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day10.ChunkCollector.runChecks;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Predictor implements Answerable {

    private static final String LOCATION = "/2021/day10/input.txt";

    @Override
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

    @Override
    public int getDay() {
        return 10;
    }

    @Override
    public int getSection() {
        return 2;
    }

}
