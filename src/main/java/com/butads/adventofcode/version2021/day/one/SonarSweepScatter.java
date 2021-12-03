package com.butads.adventofcode.version2021.day.one;

import static com.butads.adventofcode.BufferUtils.getInputAsStream;

import com.butads.adventofcode.version2021.Answerable;
import java.io.BufferedReader;
import java.io.IOException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class SonarSweepScatter implements Answerable {


    private static final String LOCATION = "/2021/day1/input.txt";

    public String getAnswer() throws IOException {
        int amount = 0;

        Integer previous = null;
        BufferedReader reader = getInputAsStream(LOCATION);
        String line;
        while ((line = reader.readLine()) != null) {
            int now = Integer.parseInt(line);

            if (previous != null && now > previous) {
                amount++;
            }

            previous = now;
        }

        return "" + amount;
    }

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
