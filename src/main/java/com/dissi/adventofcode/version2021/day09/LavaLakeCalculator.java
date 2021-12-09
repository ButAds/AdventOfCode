package com.dissi.adventofcode.version2021.day09;

import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LavaLakeCalculator {

    private static final int MAX_HEIGHT = 10;

    public static boolean isLowestNeighbour(int x, int y, List<String> data) {
        int currentValue = getByte(x, y, data, 0);
        int top = getByte(x, y - 1, data, MAX_HEIGHT);
        if (currentValue >= top) {
            return false;
        }

        int bottom = getByte(x, y + 1, data, MAX_HEIGHT);
        if (currentValue >= bottom) {
            return false;
        }

        int left = getByte(x - 1, y, data, MAX_HEIGHT);
        if (currentValue >= left) {
            return false;
        }

        int right = getByte(x + 1, y, data, MAX_HEIGHT);
        return currentValue <= right;
    }

    public static int getByte(int x, int y, List<String> data, int defaultValue) {
        if (y < 0 || y >= data.size()) {
            return defaultValue;
        }
        if (x < 0 || x >= data.get(0).length()) {
            return defaultValue;
        }

        char c = data.get(y).toCharArray()[x];
        return Byte.parseByte(c + "");
    }
}
