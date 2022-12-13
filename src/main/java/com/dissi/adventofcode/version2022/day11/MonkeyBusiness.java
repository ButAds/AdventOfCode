package com.dissi.adventofcode.version2022.day11;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MonkeyBusiness {

    private static final String LOCATION = "/2022/day11/input.txt";
    private final String[] data;

    public record Throw(long item, int monkey) {

    }

    public MonkeyBusiness() throws IOException {
        this.data = BufferUtils.getInputAsStringNoJoining(LOCATION).split("\n\n");
    }

    @SolutionAnnotation(day = 11, section = 1, year = 2022)
    public int section1() {
        Monkey[] monkeys = Arrays.stream(data).parallel().map(Monkey::new)
            .toArray(Monkey[]::new);
        doRounds(20, monkeys, true);
        return Arrays.stream(monkeys).mapToInt(Monkey::getInspections).boxed()
            .sorted(Comparator.reverseOrder()).limit(2).reduce(1, (a, b) -> a * b);
    }

    private void doRounds(final int nrOfRounds, final Monkey[] monkeys, final boolean useCalcOne) {
        for (int i = 1; i <= nrOfRounds; i++) {
            for (final Monkey monkey : monkeys) {
                final List<Throw> monkeyThrows = monkey.takeTurn(useCalcOne);
                for (final Throw t : monkeyThrows) {
                    monkeys[t.monkey()].getItems().add(t.item());
                }
            }
        }
    }

    @SolutionAnnotation(day = 11, section = 2, year = 2022)
    public long section2() {
        Monkey[] monkeys = Arrays.stream(data).parallel().map(Monkey::new)
            .toArray(Monkey[]::new);
        int lowest = 1;
        for (final Monkey monkey : monkeys) {
            lowest = calculateLeastCommonMultiple(lowest, monkey.getCanDivideBy());
        }
        for (final Monkey monkey : monkeys) {
            monkey.setLcm(lowest);
        }

        doRounds(10000, monkeys, false);
        return Arrays.stream(monkeys).parallel().mapToLong(Monkey::getInspections).boxed()
            .sorted(Comparator.reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b);
    }

    private int greatestCommonDenominator(final int a, final int b) {
        if (a == 0 || b == 0) {
            return a + b;
        } else {
            // We have to go smaller ...
            return greatestCommonDenominator(Math.max(a, b) % Math.min(a, b), Math.min(a, b));
        }
    }

    private int calculateLeastCommonMultiple(final int a, final int b) {
        return a * b / greatestCommonDenominator(a, b);
    }
}
