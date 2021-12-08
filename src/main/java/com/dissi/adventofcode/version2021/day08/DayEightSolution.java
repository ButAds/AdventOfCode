package com.dissi.adventofcode.version2021.day08;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class DayEightSolution implements Answerable {

    private static final String LOCATION = "/2021/day8/input.txt";

    @Override
    public String getAnswer() throws IOException {
        List<String> inputAsStringList = getInputAsStringList(LOCATION);
        int amount = 0;
        for (String s : inputAsStringList) {
            List<DigitalDisplay> displays = DisplayParser.parseLine(s);
            amount += displays.stream().skip(10)
                .filter(digitalDisplay ->
                    digitalDisplay.getOnlineSegments() == 2 ||
                        digitalDisplay.getOnlineSegments() == 3 ||
                        digitalDisplay.getOnlineSegments() == 4 ||
                        digitalDisplay.getOnlineSegments() == 7).count();
        }
        return "" + amount;
    }

    @Override
    public int getDay() {
        return 8;
    }

    @Override
    public int getSection() {
        return 1;
    }
}
