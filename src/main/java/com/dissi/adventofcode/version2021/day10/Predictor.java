package com.dissi.adventofcode.version2021.day10;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class Predictor implements Answerable {

    private static final String LOCATION = "/2021/day10/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> lines = getInputAsStringList(LOCATION);
        List<Long> scores = new ArrayList<>();
        for (String line : lines) {
            LinkedList<Chunk> chunks = new LinkedList<>();
            Chunk offender = null;

            char[] charArray = line.toCharArray();
            for (Character character : charArray) {
                Chunk newChunk = new Chunk(character);

                if (newChunk.isAdd()) {
                    chunks.add(newChunk);
                } else if (chunks.isEmpty() && newChunk.isRemove()) {
                    offender = newChunk;
                    break;
                } else {
                    Chunk lastOpen = chunks.getLast();
                    Chunk newClose = Chunk.getMatchedClose(newChunk);
                    if (!lastOpen.equals(newClose)) {
                        offender = newChunk;
                        break;
                    } else {
                        chunks.removeLast();
                    }
                }
            }
            if (offender == null) {
                long score = 0;
                while (!chunks.isEmpty()) {
                    Chunk c = chunks.getLast();
                    chunks.removeLast();
                    score *= 5;
                    score += c.getOpenScore();
                }
                scores.add(score);
            }
        }
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
