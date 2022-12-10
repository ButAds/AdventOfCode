package com.dissi.adventofcode.version2021.day19;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class HmmmBacons {

    private final KeyScannerSolver solver = new KeyScannerSolver();

    @SolutionAnnotation(day = 19, section = 1, year = 2021)
    public String dayOne() throws IOException {
        solver.doSolve();

        return "" + solver.getBeaconCount();
    }

    @SolutionAnnotation(day = 19, section = 2, year = 2021)
    public String dayTwo() throws IOException {

        solver.doSolve();
        return "" + solver.getMaxDistance();
    }

}
