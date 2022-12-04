package com.dissi.adventofcode.version2022.day03;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class Reorganization implements Answerable {

    private static final String LOCATION = "/2022/day3/input.txt";

    public String getAnswer() throws IOException {

        int result = BufferUtils.getInputAsStringList(LOCATION).stream()
            .map(data -> new String[] {data.substring(0, data.length() / 2), data.substring(data.length() / 2)})
            .map(halves -> {
                for (char c : halves[0].chars().mapToObj(c -> (char) c).toList()) {
                    if (halves[1].indexOf(c) != -1) {
                        return c;
                    }
                }
                return '-';
            })
            .mapToInt(c -> c > 96 ? c - 96 : c - 38)
            .sum();

        return "" + result;
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public int getSection() {
        return 1;
    }

    @Override
    public int getYear() {
        return 2022;
    }
}
