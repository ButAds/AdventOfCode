package com.dissi.adventofcode.version2022.day03;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;


public class Reorganization {

    private static final String LOCATION = "/2022/day3/input.txt";
    private final List<String> data;

    public Reorganization() throws IOException {
        this.data = BufferUtils.getInputAsStringList(LOCATION);
    }

    @SolutionAnnotation(day = 3, section = 1, year = 2022)
    public long getReorganized() {
        return data.parallelStream()
            .map(line -> new String[] {line.substring(0, line.length() / 2), line.substring(line.length() / 2)})
            .map(halves -> {
                for (char c : halves[0].chars().mapToObj(c -> (char) c).toList()) {
                    if (halves[1].indexOf(c) != -1) {
                        return c;
                    }
                }
                return '-';
            })
            .mapToInt(c -> c > 96 ? c - 96 : c - 38)
            .sum();
    }

    @SolutionAnnotation(day = 3, section = 2, year = 2022)
    public long getWithoutDupes() {
        List<Character> alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".chars().parallel()
            .mapToObj(c -> ((Character) (char) c))
            .toList();

        Function<String[], Character> afterAllMagicContainsThisStringAfter = strA -> (char) alphabet.stream()
            .filter(i -> strA[0].indexOf(i) != -1 && strA[1].indexOf(i) != -1 && strA[2].indexOf(i) != -1)
            .findFirst().orElse(' ');

        return IntStream.iterate(0, i -> i + 3)
            .parallel()
            // Stop on time
            .limit(data.size() / 3)
            // Create substring part
            .mapToObj(i -> new String[] {data.get(i), data.get(i + 1), data.get(i + 2)})
            .map(afterAllMagicContainsThisStringAfter)
            .mapToInt(c -> c > 96 ? c - 96 : c - 38)
            .sum();
    }
}
