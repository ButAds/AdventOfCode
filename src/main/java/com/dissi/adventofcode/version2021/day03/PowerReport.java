package com.dissi.adventofcode.version2021.day03;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;


public class PowerReport {

    private static final String LOCATION = "/2021/day3/input.txt";

    @SolutionAnnotation(day = 3, section = 1, year = 2021)
    public String getAnswer() throws IOException {

        List<String> reports = BufferUtils.getInputAsStringList(LOCATION);
        int[] occurrences = new int[12];

        for (String line : reports) {
            for (int i = 0; i < occurrences.length; i++) {
                occurrences[i] += line.charAt(i) == '1' ? 1 : 0;
            }
        }

        int middle = reports.size() / 2;
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for (int occurrence : occurrences) {
            boolean isGamma = occurrence > middle;
            if (isGamma) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }
        int total = Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2);
        return "" + total;
    }

}
