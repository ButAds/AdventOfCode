package com.butads.adventofcode.version2021.day6;

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
        long[] currentFish = start;
        for (int day = 1; day <= days; day++) {
            long[] nextArray = new long[MAX_LIFESPAN + 1];
            for (int i = 0; i < nextArray.length; i++) {
                nextArray[i] = currentFish[(i + 1) % nextArray.length];
            }
            nextArray[6] = currentFish[7] + currentFish[0];
            currentFish = nextArray;
        }
        return currentFish;
    }

}
