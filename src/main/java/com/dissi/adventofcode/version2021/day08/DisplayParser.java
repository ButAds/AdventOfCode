package com.dissi.adventofcode.version2021.day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DisplayParser {

    private static final Pattern PATTERN = Pattern.compile(
        "(\\w*) (\\w*) (\\w*) (\\w*) (\\w*) (\\w*) (\\w*) (\\w*) (\\w*) (\\w*) (\\|) (\\w*) (\\w*) (\\w*) (\\w*)");

    public static List<DigitalDisplay> parseLine(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.find()) {
            return Collections.emptyList();
        }

        List<DigitalDisplay> list = new ArrayList<>();
        for (int start = 0; start < 15; start++) {
            if (start == 10) {
                continue;
            }
            list.add(new DigitalDisplay(matcher.group(start + 1).trim()));
        }
        return list;
    }

}
