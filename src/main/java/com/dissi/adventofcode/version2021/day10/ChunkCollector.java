package com.dissi.adventofcode.version2021.day10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChunkCollector {


    public static List<LinkedList<Chunk>> runChecks(List<String> lines, List<Chunk> offenders) {
        List<LinkedList<Chunk>> remainingCharacters = new ArrayList<>();
        for (String line : lines) {
            LinkedList<Chunk> chunks = new LinkedList<>();
            Chunk offender = null;

            char[] charArray = line.toCharArray();

            for (Character character : charArray) {
                Chunk newChunk = new Chunk(character);
                if (newChunk.isAdd()) {
                    chunks.add(newChunk);
                } else if (chunks.isEmpty() && newChunk.isCloseSection()) {
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
            if (offender != null) {
                offenders.add(offender);
            } else {
                remainingCharacters.add(chunks);
            }
        }
        return remainingCharacters;
    }
}
