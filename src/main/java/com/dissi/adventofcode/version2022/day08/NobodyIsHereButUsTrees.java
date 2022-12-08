package com.dissi.adventofcode.version2022.day08;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class NobodyIsHereButUsTrees implements Answerable {

    private static final String LOCATION = "/2022/day8/input.txt";

    public String getAnswer() throws IOException {
        List<String> data = BufferUtils.getInputAsStringList(LOCATION);

        VisibilityMap visibilityMap = new VisibilityMap(data);

        return "" + visibilityMap.countVisible();
    }

    @Override
    public int getDay() {
        return 8;
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
