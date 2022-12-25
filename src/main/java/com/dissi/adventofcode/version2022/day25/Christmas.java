package com.dissi.adventofcode.version2022.day25;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.List;

public class Christmas {

    private final List<String> data;

    public Christmas() {
        this.data = BufferUtils.getInputAsStringList(2022, 25, false);
    }

    @SolutionAnnotation(day = 25, section = 1, year = 2022)
    public String doSnafu() {
        long sum = data.stream().mapToLong(s -> {
            long r = 0;
            for (int i = 0; i < s.length(); i++) {
                int x = "=-012".indexOf(s.substring(s.length() - 1 - i, s.length() - i)) - 2;
                r += (x * Math.pow(5, (i)));
            }
            return r;
        }).sum();

        StringBuilder sb = new StringBuilder();
        while (sum > 0) {
            sb.insert(0, "012=-".charAt((int) (sum % 5)));
            sum -= (((sum + 2) % 5) - 2);
            sum /= 5;
        }

        return sb.toString();
    }
}
