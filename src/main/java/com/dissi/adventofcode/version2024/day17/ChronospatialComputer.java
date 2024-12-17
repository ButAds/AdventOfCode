package com.dissi.adventofcode.version2024.day17;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.OptionalLong;
import java.util.stream.LongStream;

public class ChronospatialComputer {


    private List<Long> opcodes;
    private final List<String> data;
    private Long start;

    public ChronospatialComputer() {
        this.data = BufferUtils.getInputAsStringList(2024, 17, false);
    }

    @SolutionAnnotation(year = 2024, day = 17, section = 1)
    public String chronotrigger() {
        initComputer();
        List<Long> longs = runProgram(opcodes, start);
        return String.join(",", longs.stream().map(s -> s + "").toList());
    }

    @SolutionAnnotation(year = 2024, day = 17, section = 2)
    public long loops() {
        initComputer();
        return findAMatchingOutput(opcodes, opcodes);
    }

    private long findAMatchingOutput(List<Long> program, List<Long> target) {
        System.out.printf("Finding A for %s%n", target);
        long aStart;
        if (target.size() == 1) {
            aStart = 0;
        } else {
            // aStart = findAMatchingOutput(program, target.subList(1, target.size())) * 8L;
            aStart = findAMatchingOutput(program, target.subList(1, target.size())) << 3L;
        }
        System.out.printf("Start at %s for program %s %n", aStart, target);
        while (!equalsProgram(runProgram(program, aStart), target)) {
             aStart++;
        }
        System.out.printf("Found A[%s] for target %s %n", aStart, target);
        return aStart;
    }

    private boolean equalsProgram(List<Long> original, List<Long> output) {
        if (original.size() != output.size()) {
            return false;
        }

        for (int i = 0; i < original.size(); i++) {
            if (!Objects.equals(original.get(i), output.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static List<Long> runProgram(List<Long> program, long start) {
        long a = start;
        long b = 0;
        long c = 0;

        List<Long> result = new ArrayList<>();
        int ptr = 0;

        while (ptr >= 0 && ptr < program.size() - 1) {
            long opcode = program.get(ptr);
            long value = program.get(ptr + 1);
            ptr += 2;

            // adv
            if (opcode == 0) {
                a = (long) (a / Math.pow(2L, getOther(value, a, b, c)));
            }
            // bdv
            if (opcode == 6) {
                b = (long) (a / Math.pow(2L, getOther(value, a, b, c)));
            }
            // cdv
            if (opcode == 7) {
                c = (long) (a / Math.pow(2L, getOther(value, a, b, c)));
            }
            //bxl
            if (opcode == 1) {
                b ^= value;
            }
            //bst
            if (opcode == 2) {
                b = (getOther(value, a, b, c) % 8);
            }
            //jnz
            if (opcode == 3 && a != 0) {
                // Set ptr
                ptr = (int) value;
            }
            //bxc
            if (opcode == 4) {
                b ^= c;
            }
            //out
            if (opcode == 5) {
                long out = getOther(value, a, b, c) % 8;
                result.add(out);
            }
        }
        return result;
    }

    public static Long getOther(long code, long a, long b, long c) {
        if (code >= 0 && code <= 3) {
            return code;
        }
        if (code == 4) {
            return a;
        }
        if (code == 5) {
            return b;
        }
        if (code == 6) {
            return c;
        }
        throw new IllegalStateException("Unexpected value: " + code);
    }

    private void initComputer() {
        this.opcodes = Arrays.stream(data.get(4).split(" ")[1].split(",")).map(Long::parseLong).toList();
        start = Long.parseLong(data.get(0).split(" ")[2]);
    }
}
