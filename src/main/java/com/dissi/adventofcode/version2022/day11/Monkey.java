package com.dissi.adventofcode.version2022.day11;

import com.dissi.adventofcode.version2022.day11.MonkeyBusiness.Throw;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@ToString
@Log
public class Monkey {

    @Getter
    private final List<Long> items;
    private final String operator;
    private final String operand;
    @Getter
    private final int canDivideBy;
    private final int trueMonkey;
    private final int falseMonkey;

    @Getter
    private int inspections;

    @Setter
    @Getter
    private long lcm;

    /**
     * Monkey 0:
     * Starting items: 79, 98
     * Operation: new = old * 19
     * Test: divisible by 23
     * If true: throw to monkey 2
     * If false: throw to monkey 3
     */
    public Monkey(String data) {
        String[] split = data.split("\n");
        final String startingItems = split[1].split(": ")[1];
        items = Arrays.stream(startingItems.split(", ")).map(Long::parseLong).collect(Collectors.toList());
        operator = split[2].split(" = ")[1].split(" ")[1];
        operand = split[2].split(" = ")[1].split(" ")[2];
        canDivideBy = Integer.parseInt(split[3].trim().split(" ")[3]);
        trueMonkey = Integer.parseInt(split[4].trim().split(" ")[5]);
        falseMonkey = Integer.parseInt(split[5].trim().split(" ")[5]);
    }

    private long calculateWorry(final long item) {
        final long op = operand.equals("old") ? item : Long.parseLong(operand);
        return switch (operator) {
            case "*" -> item * op;
            case "+" -> item + op;
            default -> throw new IllegalArgumentException("Unknown operator '" + operator + "'");
        };
    }

    private int shouldThrow(final long worryLevel) {
        return (worryLevel % canDivideBy == 0) ? trueMonkey : falseMonkey;
    }

    public List<Throw> takeTurn(boolean calcTypeOne) {
        final List<Throw> monkeyThrows = new ArrayList<>();
        final List<Long> itemsToRemove = new ArrayList<>();
        for (final long item : items) {
            long worryLevel = calculateWorry(item);
            if (calcTypeOne) {
                worryLevel = (int) Math.floor(worryLevel / 3.0);
            } else {
                worryLevel %= lcm;
            }
            final int nextMonkey = shouldThrow(worryLevel);
            itemsToRemove.add(item);
            monkeyThrows.add(new Throw(worryLevel, nextMonkey));
            inspections++;
        }
        items.removeAll(itemsToRemove);
        return monkeyThrows;
    }
}
