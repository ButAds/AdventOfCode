package com.dissi.adventofcode.version2022.day03;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;


public class ReorganizationDuplicate {

    private static final String LOCATION = "/2022/day3/input.txt";

    @SolutionAnnotation(day = 3, section = 2, year = 2022)
    public String getAnswer() throws IOException {

        List<String> data = BufferUtils.getInputAsStringList(LOCATION);
        List<Character> alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".chars()
            .mapToObj(c -> ((Character) (char) c))
            .toList();

        Function<String[], Character> afterAllMagicContainsThisStringAfter = strA -> (char) alphabet.stream()
            .filter(i -> strA[0].indexOf(i) != -1 && strA[1].indexOf(i) != -1 && strA[2].indexOf(i) != -1)
            .findFirst().orElse(' ');

        int result = IntStream.iterate(0, i -> i + 3)
            // Stop on time
            .limit(data.size() / 3)
            // Create substring part
            .mapToObj(i -> new String[] {data.get(i), data.get(i + 1), data.get(i + 2)})
            .map(afterAllMagicContainsThisStringAfter)
            .mapToInt(c -> c > 96 ? c - 96 : c - 38)
            .sum();

        return "" + result;
    }

}
