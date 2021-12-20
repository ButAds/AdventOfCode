package com.dissi.adventofcode.version2021.day20;

import static com.dissi.adventofcode.version2021.day20.EnhanceNow.doDay;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class TrenchMapperENHANCE implements Answerable {

    private static final String LOCATION = "/2021/day20/example.txt";

    @Override
    public int getDay() {
        return 20;
    }

    @Override
    public int getSection() {
        return 2;
    }

    @Override
    public String getAnswer() throws IOException {
        return "" + doDay(LOCATION, 50);
    }


}
