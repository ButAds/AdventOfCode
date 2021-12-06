package com.butads.adventofcode.version2021.day02;

import static com.butads.adventofcode.BufferUtils.getInputAsStringList;

import com.butads.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Dive implements Answerable {

    private static final String LOCATION = "/2021/day2/input.txt";

    public String getAnswer() throws IOException {
        List<String> orders = getInputAsStringList(LOCATION);

        int horizontal = 0;
        int depth = 0;

        for (String line : orders) {
            String[] directionAndSpeed = line.split(" ");
            String direction = directionAndSpeed[0];
            int value = Integer.parseInt(directionAndSpeed[1]);

            if ("down".equals(direction)) {
                depth += value;
            } else if ("up".equals(direction)) {
                depth -= value;
            } else if ("forward".equals(direction)) {
                horizontal += value;
            }
        }

        return "" + (depth * horizontal);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public int getSection() {
        return 1;
    }
}
