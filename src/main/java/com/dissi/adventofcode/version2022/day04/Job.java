package com.dissi.adventofcode.version2022.day04;

import static java.lang.Integer.parseInt;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Job {

    record Assignment(Pair left, Pair right) {

        static Assignment create(String line) {
            String[] pairs = line.split(",");
            String[] left = pairs[0].split("-");
            String[] right = pairs[1].split("-");

            return new Assignment(new Pair(parseInt(left[0]), parseInt(left[1])),
                new Pair(parseInt(right[0]), parseInt(right[1])));
        }
    }

    record Pair(int start, int end) {

    }

}
