package com.dissi.adventofcode.version2020.day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexPassword {

    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w): (\\w*)");

    private final boolean valid;

    public ComplexPassword(String input) {
        Matcher matcher = PATTERN.matcher(input);
        if (!matcher.find()) {
            valid = false;
            return;
        }

        String password = matcher.group(4);
        char toSearch = matcher.group(3).toCharArray()[0];

        int minAmount = Integer.parseInt(matcher.group(1));
        int maxAmount = Integer.parseInt(matcher.group(2));
        char first = password.charAt(minAmount - 1);
        char second = password.charAt(maxAmount - 1);

        if (first != toSearch) {
            valid = second == toSearch;
        } else {
            valid = second != toSearch;
        }
    }

    public boolean valid() {
        return valid;
    }

}
