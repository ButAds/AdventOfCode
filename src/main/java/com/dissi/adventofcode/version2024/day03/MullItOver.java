package com.dissi.adventofcode.version2024.day03;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {

    private final String data;

    public MullItOver() {
        this.data = BufferUtils.getInputAsString(2024, 3, false);
    }

    @SolutionAnnotation(day = 3, section = 1, year = 2024)
    public int doSolveMultiplication(){
        return  doMultiplication(data);
    }

    @SolutionAnnotation(day = 3, section = 3, year = 2024)
    public int multiplicationDoNot(){
        return  doMultiplicationButWithDontDoRemoved(data);
    }

    private int doMultiplicationButWithDontDoRemoved(String line) {
        String removeOps = line.replaceAll("don't\\(\\).*?do\\(\\)", "");
        removeOps = removeOps.replaceAll("don't\\(\\).*", "");
        return doMultiplication(removeOps);
    }

    private int doMultiplication(String line) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(line);

        int multiSum = 0;
        while (matcher.find()) {
            int left = Integer.parseInt(matcher.group(1));
            int right = Integer.parseInt(matcher.group(2));
            multiSum += left * right;
        }

        return multiSum;
    }
}
