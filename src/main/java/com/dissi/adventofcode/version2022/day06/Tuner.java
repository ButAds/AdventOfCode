package com.dissi.adventofcode.version2022.day06;

import java.util.HashSet;
import java.util.Set;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Tuner {

    public static int getTunePosition(final String buffer, int markerSize) {
        for (int i = 0; i < buffer.length() - markerSize; i++) {
            final Set<Character> letters = new HashSet<>();
            for (int j = i; j < i + markerSize; j++) {
                letters.add(buffer.charAt(j));
            }
            if (letters.size() == markerSize) {
                return i + markerSize;
            }
        }
        return Integer.MIN_VALUE;
    }

}
