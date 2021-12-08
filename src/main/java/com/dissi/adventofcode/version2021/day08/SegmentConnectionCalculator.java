package com.dissi.adventofcode.version2021.day08;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SegmentConnectionCalculator {


    public static Map<DigitalDisplay, Integer> getOutcomes(List<DigitalDisplay> displays) {
        Map<DigitalDisplay, Integer> pattern = new HashMap<>();
        displays = displays.subList(0, 10).stream().sorted(Comparator.comparingInt(DigitalDisplay::getOnlineSegments))
            .toList();

        pattern.put(displays.get(0), 1);
        pattern.put(displays.get(1), 7);
        pattern.put(displays.get(2), 4);
        pattern.put(displays.get(9), 8);

        int whatIsSixNow = 0;
        for (int i = 6; i <= 8; i++) {
            DigitalDisplay toCompare = displays.get(i);

            if (toCompare.matches(displays.get(0)) && toCompare.matches(displays.get(1)) && toCompare.matches(
                displays.get(2))) {
                pattern.put(toCompare, 9);
            } else if (toCompare.matches(displays.get(0)) && toCompare.matches(displays.get(1))) {
                pattern.put(toCompare, 0);
            } else {
                pattern.put(toCompare, 6);
                whatIsSixNow = i;
            }
        }
        for (int i = 3; i <= 5; i++) {
            DigitalDisplay toCompare = displays.get(i);
            if (toCompare.matches(displays.get(0)) && toCompare.matches(displays.get(1))) {
                pattern.put(toCompare, 3);
            } else if (displays.get(whatIsSixNow).matches(toCompare)) {
                pattern.put(toCompare, 5);
            } else {
                pattern.put(toCompare, 2);
            }
        }
        return pattern;
    }
}
