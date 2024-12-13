package com.dissi.adventofcode.version2024.day11;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Pair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlutonianPebbles {

    private final Map<Pair<Long, Integer>, Long> memoization = new HashMap<>(4096);

    private final String data;

    public PlutonianPebbles() {
        this.data = BufferUtils.getInputAsString(2024, 11, false);
    }

    @SolutionAnnotation(year = 2024, day = 11, section = 1)
    public long blinkyBlink() {
        return calculateStones(25);
    }

    @SolutionAnnotation(year = 2024, day = 11, section = 2)
    public long blinkyBlinkBlinkMoreBlink() {
        return calculateStones(75);
    }

    private long calculateStones(int totalBlinks) {
        return Arrays.stream(data.split(" ")).mapToLong(Long::parseLong).map(stone -> countStones(stone, totalBlinks))
            .sum();
    }

    private long countStones(long stone, int remaining) {
        if (remaining == 0) {
            return 1;
        }
        Pair<Long, Integer> key = Pair.of(stone, remaining);

        if (memoization.containsKey(key)) {
            return memoization.get(key);
        }
        long blinkAmount = amount(stone, remaining);
        memoization.put(key, blinkAmount);
        return blinkAmount;
    }

    private long amount(long s, int blinksLeft) {
        if (s != 0) {
            long n = (long) Math.log10(Math.abs(s)) + 1;
            if (n % 2 == 0) {
                long pow = (long) Math.pow(10, (double) n / 2);
                return countStones(s / pow, blinksLeft - 1) + countStones(s % pow, blinksLeft - 1);
            }
            return countStones(s * 2024, blinksLeft - 1);
        }
        return countStones(1L, blinksLeft - 1);
    }


}
