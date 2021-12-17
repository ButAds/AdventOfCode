package com.dissi.adventofcode.version2021.day17;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class KillerShots implements Answerable {

    private static final String LOCATION = "/2021/day17/input.txt";

    @Override
    public String getAnswer() throws IOException {
        String target = BufferUtils.getInputAsString(LOCATION);

        PoolBar poolBar = new PoolBar(target);
        poolBar.solve();

        return "" + poolBar.getHits();
    }

    @Override
    public int getDay() {
        return 17;
    }

    @Override
    public int getSection() {
        return 1;
    }
}
