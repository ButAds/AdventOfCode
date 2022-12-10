package com.dissi.adventofcode.version2021.day20;

import static com.dissi.adventofcode.version2021.day20.EnhanceNow.doDay;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;


public class TrenchMapperENHANCE {

    private static final String LOCATION = "/2021/day20/example.txt";

    @SolutionAnnotation(day = 20, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        return "" + doDay(LOCATION, 50);
    }


}
