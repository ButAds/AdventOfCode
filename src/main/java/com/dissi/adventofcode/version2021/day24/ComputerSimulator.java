package com.dissi.adventofcode.version2021.day24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ComputerSimulator {

    public static boolean executeInstruction(Map<String, Long> values, String instructions) {
        List<String> commandAndArgument = Arrays.stream(instructions.split(" ")).toList();
        String cmd = commandAndArgument.get(0);
        String a = commandAndArgument.get(1);
        long b = 0;
        if (commandAndArgument.size() == 3) {
            String registerIndex = commandAndArgument.get(2);
            if (values.containsKey(registerIndex)) {
                b = values.get(registerIndex);
            } else {
                b = Long.parseLong(registerIndex);
            }
        }

        switch (cmd) {
            case "add" -> values.put(a, values.get(a) + b);
            case "mul" -> values.put(a, values.get(a) * b);
            case "div" -> {
                if (b == 0) {
                    return false;
                }
                values.put(a, values.get(a) / b);
            }
            case "mod" -> {
                if (b <= 0) {
                    return false;
                }
                if (values.get(a) < 0) {
                    return false;
                }
                values.put(a, values.get(a) % b);
            }
            case "eql" -> {
                long res = 0;
                long av = values.get(a);
                if (av == b) {
                    res = 1;
                }
                values.put(a, res);
            }
            default -> {
                return false;
            }

        }
        return true;
    }

    public static Map<String, Long> getRegisters() {
        Map<String, Long> registers = new HashMap<>(4);
        registers.put("w", 0L);
        registers.put("x", 0L);
        registers.put("y", 0L);
        registers.put("z", 0L);
        return registers;
    }
}
