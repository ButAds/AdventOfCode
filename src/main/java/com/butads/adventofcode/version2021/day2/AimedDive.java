package com.butads.adventofcode.version2021.day2;

import static com.butads.adventofcode.BufferUtils.getInputAsStringList;

import com.butads.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AimedDive implements Answerable {

    private static final String LOCATION = "/2021/day2/input.txt";

    public String getAnswer() throws IOException {
        List<String> orders = getInputAsStringList(LOCATION);

        var horizontal = 0;
        var depth = 0;
        var aim = 0;

        for (String line : orders) {
            String[] directionAndSpeed = line.split(" ");
            String direction = directionAndSpeed[0];
            int value = Integer.parseInt(directionAndSpeed[1]);

            if ("down".equals(direction)) {
                aim += value;
            } else if ("up".equals(direction)) {
                aim -= value;
            } else if ("forward".equals(direction)) {
                horizontal += value;
                depth += (aim * value);
            }
        }

        return "" + (horizontal * depth);
    }

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
