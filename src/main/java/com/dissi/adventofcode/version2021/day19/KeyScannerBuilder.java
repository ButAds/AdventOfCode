package com.dissi.adventofcode.version2021.day19;

import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class KeyScannerBuilder {

    public static List<KeyScanner> buildScanners(List<String> allData) {
        List<KeyScanner> scanners = new ArrayList<>();

        KeyScanner scanner = null;
        for (String line : allData) {
            if (line.contains("scanner")) {
                scanner = new KeyScanner(Integer.parseInt(line.split(" ")[2]));
                scanners.add(scanner);

            } else if (line.contains(",")) {
                scanner.addPoint(line);
            }
        }
        return scanners;
    }
}
