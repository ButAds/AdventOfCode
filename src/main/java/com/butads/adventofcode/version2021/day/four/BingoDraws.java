package com.butads.adventofcode.version2021.day.four;

import java.util.Arrays;
import java.util.List;

public class BingoDraws {

    private final List<Integer> draws;
    private List<Integer> currentDraws;

    public BingoDraws(String line) {
        draws = Arrays.stream(line.split(","))
            .map(Integer::parseInt)
            .toList();
    }

    public List<Integer> getCurrentDraws() {
        return currentDraws;
    }

    public int getTotalAnswers() {
        return draws.size();
    }

    public void setAmount(int max) {
        currentDraws = draws.stream().limit(max).toList();
    }

    public List<Integer> getListAt(int size) {
        return draws.stream().limit(size).toList();
    }
}
