package com.dissi.adventofcode.version2021.day03;

import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public static int[] getMostOccurring(List<String> items) {

        int[] occurrences = null;
        for (String line : items) {
            if (occurrences == null) {
                occurrences = new int[line.length()];
            }
            for (int i = 0; i < occurrences.length; i++) {
                occurrences[i] += line.charAt(i) == '1' ? 1 : 0;
            }
        }
        return occurrences;
    }

}
