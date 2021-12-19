package com.dissi.adventofcode.version2021.day19;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HmmmBacons implements Answerable {

    public final KeyScannerSolver solver;

    @Override
    public int getDay() {
        return 19;
    }

    @Override
    public int getSection() {
        return 1;
    }

    @Override
    public String getAnswer() throws IOException {
        solver.doSolve();

        return "" + solver.getBeaconCount();
    }
}
