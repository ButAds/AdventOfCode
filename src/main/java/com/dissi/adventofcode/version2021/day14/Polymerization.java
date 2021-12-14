package com.dissi.adventofcode.version2021.day14;

import com.dissi.adventofcode.helpers.CountMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Polymerization {

    public static long simulateSteps(List<String> lines, int steps) {
        String init = lines.get(0);
        lines.remove(0); // remove first
        lines.remove(0); // remove empty

        Map<String, PolymerRules> rules = lines.stream()
            .map(PolymerRules::new)
            .collect(Collectors.toMap(poly -> poly.key, poly -> poly));

        CountMap<String> pairs = new CountMap<>();

        // Length -1 to prevent OOB error.
        for (int i = 0; i < init.length() - 1; i++) {
            pairs.increment(init.substring(i, i + 2));
        }

        // Count start state
        CountMap<Character> counts = new CountMap<>();
        init.chars().forEach(e -> counts.increment((char) e));

        // Simulation of the steps
        for (int step = 0; step < steps; step++) {
            pairs = doStep(rules, pairs, counts);
        }

        // Answer generation
        List<Long> items = counts.getOccurances().values().stream()
            .sorted(Comparator.comparing(Long::longValue))
            .toList();

        return items.get(items.size() - 1) - items.get(0);
    }

    private static CountMap<String> doStep(Map<String, PolymerRules> rules, CountMap<String> pairs,
        CountMap<Character> existingCounts) {
        CountMap<String> newCount = new CountMap<>();
        for (String pair : pairs.getOccurances().keySet()) {

            PolymerRules appliedRule = rules.get(pair);
            // Get current count value for a pair
            long increment = pairs.getValue(pair);

            // Add additional left/right pairs
            String keyLeft = pair.charAt(0) + appliedRule.newChar;
            String keyRight = appliedRule.newChar + pair.charAt(1);

            // Increment counters
            newCount.increment(keyLeft, increment);
            newCount.increment(keyRight, increment);

            // Finally, increment current existing count
            existingCounts.increment(appliedRule.newChar.charAt(0), increment);
        }
        return newCount;
    }

    private static class PolymerRules {

        final String key;
        final String newChar;

        PolymerRules(String ruleSet) {
            String[] input = ruleSet.split(" -> ");
            key = input[0];
            newChar = input[1];
        }
    }

}
