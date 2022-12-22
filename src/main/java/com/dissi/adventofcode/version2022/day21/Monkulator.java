package com.dissi.adventofcode.version2022.day21;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.function.LongFunction;

/**
 *
 */
public class Monkulator {

    private final Map<String, Long> monkeyMap = new HashMap<>();
    private final Map<String, Sum> calcMap = new HashMap<>();

    public record Sum(String left, char op, String right) {

    }

    public Monkulator() {
        BufferUtils.getInputAsStringList(2022, 21, false).stream().map(s -> s.split(" ")).forEach(e -> {
            if (e.length > 3) {
                calcMap.put(e[0].substring(0, 4), new Sum(e[1], e[2].charAt(0), e[3]));
            } else {
                monkeyMap.put(e[0].substring(0, 4), Long.parseLong(e[1]));
            }
        });
    }

    @SolutionAnnotation(day = 21, section = 1, year = 2022)
    public long shoutiNator() {
        Pair<Long, Long> response = calculateResponse(new HashMap<>(this.monkeyMap));
        return doSum((this.calcMap.get("root")).op, response.a(), response.b());
    }

    @SolutionAnnotation(day = 21, section = 2, year = 2022)
    public long hummInator() {
        return binarySearch(i -> {
            Map<String, Long> labelMap = new HashMap<>(this.monkeyMap);
            labelMap.put("humn", i);
            var res = calculateResponse(labelMap);
            return res.a() - res.b();
        }, 0, 10000000000000L) - 1;
    }

    public Pair<Long, Long> calculateResponse(Map<String, Long> monkeyToSolutionMap) {
        while (true) {
            for (Map.Entry<String, Sum> entry : this.calcMap.entrySet()) {
                Sum val = entry.getValue();
                if (monkeyToSolutionMap.containsKey(val.left) && monkeyToSolutionMap.containsKey(val.right)) {
                    long answer1 = monkeyToSolutionMap.get(val.left);
                    long answer2 = monkeyToSolutionMap.get(val.right);
                    if (entry.getKey().equals("root")) {
                        return new Pair<>(answer1, answer2);
                    }
                    monkeyToSolutionMap.put(entry.getKey(), doSum(val.op, answer1, answer2));
                }
            }
        }
    }

    private static long doSum(char op, Long m1, Long m2) {
        return switch (op) {
            case '+' -> m1 + m2;
            case '-' -> m1 - m2;
            case '/' -> m1 / m2;
            case '*' -> m1 * m2;
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }

    public static long binarySearch(LongFunction<Long> testFunction, long low, long high) {
        while (true) {
            if (low == high) {
                return low;
            }
            long size = (high - low) / 3;
            long l1 = low + size;
            long res1 = testFunction.apply(l1);
            long diff1 = Math.abs(res1);
            if (diff1 == 0) {
                return l1;
            }
            long l2 = l1 + size;
            long res2 = testFunction.apply(l2);
            long diff2 = Math.abs(res2);
            if (diff2 == 0) {
                return l2;
            }
            if (diff1 <= diff2) {
                high = l2 - 1;
            }
            if (diff1 >= diff2) {
                low = l1 + 1;
            }
        }
    }
}
