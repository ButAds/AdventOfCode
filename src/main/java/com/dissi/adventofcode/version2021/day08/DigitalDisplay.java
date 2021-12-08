package com.dissi.adventofcode.version2021.day08;

import java.util.Arrays;
import java.util.Objects;

public class DigitalDisplay {

    private final String onlineSegments;

    public DigitalDisplay(String display) {
        char[] chars = display.toCharArray();
        Arrays.sort(chars);
        onlineSegments = new String(chars);
    }

    public boolean matches(DigitalDisplay target) {
        for (char c : target.onlineSegments.toCharArray()) {
            if (onlineSegments.contains(c + "")) {
                continue;
            }
            return false;
        }
        return true;
    }

    public int getOnlineSegments() {
        return onlineSegments.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DigitalDisplay that = (DigitalDisplay) o;
        return Objects.equals(onlineSegments, that.onlineSegments);
    }

    @Override
    public int hashCode() {
        return onlineSegments.hashCode();
    }
}
