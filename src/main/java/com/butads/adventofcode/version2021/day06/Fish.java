package com.butads.adventofcode.version2021.day06;

import java.util.Arrays;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Fish {

    private static final int MAX_LIFESPAN = 8;

    public static Long getAmountOfFish(int day, String startState) {
        long[] fish = new long[MAX_LIFESPAN + 1];
        Arrays.stream(startState.split(","))
            .map(Integer::parseInt)
            .forEach(i -> fish[i] += 1);
        return Arrays.stream(calculateDays(day, fish)).sum();
    }

    private static long[] calculateDays(int days, long[] start) {
        for (int day = 0; day < days; day++) {
            long fallOver = start[0];
            System.arraycopy(start, 1, start, 0, start.length - 1);
            start[6] += fallOver;
            start[start.length - 1] = fallOver;
        }

        return start;
    }
}
