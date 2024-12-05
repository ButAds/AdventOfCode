package com.dissi.adventofcode.version2024.day02;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.ArrayList;
import java.util.List;

public class RedNosedReports {

    private final List<String> data;

    public RedNosedReports() {
        this.data = BufferUtils.getInputAsStringList(2024, 2, false);
    }

    @SolutionAnnotation(day = 2, section = 1, year = 2024)
    public int getAnswer() {
        int answer = 0;
        for (String line : data) {
            if (isSafeWithSkippedNumber(line, -1)) {
                answer++;
            }
        }
        return answer;
    }

    @SolutionAnnotation(day = 2, section = 2, year = 2024)
    public int getAnswerLessProblems() {
        int answer = 0;
        for (String line : data) {
            if (isSafeIfOneRemoved(line)) {
                answer++;
            }
        }

        return answer;
    }

    public boolean isSafeIfOneRemoved(String line) {
        for (int i = -1; i < data.size(); i++) {
            if (isSafeWithSkippedNumber(line, i)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSafeWithSkippedNumber(String line, int toSkip) {
        return isSafe(getAndSkip(line, toSkip));
    }

    public boolean isSafe(List<Integer> numbers) {
        boolean isDecreasing = numbers.get(0) - numbers.get(1) > 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            int difference = numbers.get(i) - numbers.get(i + 1);
            if (difference > 3 || difference < -3 || difference == 0) {
                return false;
            }

            if (isDecreasing && difference < 0 || !isDecreasing && difference > 0) {
                return false;
            }
        }

        return true;
    }

    public static List<Integer> getAndSkip(String line, int number) {
        ArrayList<Integer> numbers = new ArrayList<>();
        String[] lineSplit = line.split(" ");
        for (int i = 0; i < lineSplit.length; i++) {
            if (i != number) {
                numbers.add(Integer.parseInt(lineSplit[i]));
            }
        }

        return numbers;
    }
}
