package com.dissi.adventofcode.version2021.day07;

import java.util.List;
import java.util.TreeSet;
import java.util.function.LongFunction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Fuel {

    public static Long getFuel(List<Integer> locations, LongFunction<Long> calculator) {
        TreeSet<Integer> tests = new TreeSet<>(locations);

        long fuelSpent = Long.MAX_VALUE;
        int min = tests.first();
        int max = tests.last();

        for (int location = min; location < max; location++) {
            long currentFuelSpent = 0;

            for (int destination : locations) {
                long distance = Math.abs(location - destination);
                currentFuelSpent += calculator.apply(distance);
            }

            if (currentFuelSpent < fuelSpent) {
                fuelSpent = currentFuelSpent;
            }
        }

        return fuelSpent;
    }
}
