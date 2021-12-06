package com.butads.adventofcode.version2020.day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.java.Log;

@Log
public class Password {

    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w): (\\w*)");

    private final boolean valid;

    public Password(String input) {
        Matcher matcher = PATTERN.matcher(input);
        if (!matcher.find()) {
            valid = false;
            return;
        }
        String password = matcher.group(4);
        char toSearch = matcher.group(3).toCharArray()[0];

        int amount = 0;
        for (char c : password.toCharArray()) {
            if (c == toSearch) {
                amount++;
            }
        }
        int minAmount = Integer.parseInt(matcher.group(1));
        int maxAmount = Integer.parseInt(matcher.group(2));
        valid = minAmount <= amount && amount <= maxAmount;
    }

    public boolean valid() {
        return valid;
    }

}
