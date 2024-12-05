package com.dissi.adventofcode.version2024.day01;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HistorianHysteria {

    private final List<String> data;

    public HistorianHysteria() {
        this.data = BufferUtils.getInputAsStringList(2024, 1, false);
    }

    @SolutionAnnotation(day = 1, section = 1, year = 2024)
    public int getAnswer() {
        int answer = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (String line : data) {
            var numbers = line.split("   ");
            left.add(Integer.parseInt(numbers[0]));
            right.add(Integer.parseInt(numbers[1]));
        }

        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);

        for (int i = 0; i < left.size(); i++) {
            answer += Math.abs(left.get(i) - right.get(i));
        }

        return answer;
    }

    @SolutionAnnotation(day = 1, section = 2, year = 2024)
    public int getAnswerTwo() {
        int answer = 0;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (String line : data) {
            var numbers = line.split("   ");
            left.add(Integer.parseInt(numbers[0]));
            right.add(Integer.parseInt(numbers[1]));
        }

        for (int leftNumber : left) {
            int amount = 0;
            for (int rightNumber : right) {
                if (rightNumber == leftNumber) {
                    amount++;
                }
            }
            answer += leftNumber * amount;
        }

        return answer;
    }
}
