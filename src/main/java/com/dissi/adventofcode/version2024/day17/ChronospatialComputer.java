package com.dissi.adventofcode.version2024.day17;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        System.out.printf("opcodes: %s %n", opcodes);
        return findAMatchingOutput(opcodes, opcodes).stream().mapToLong(value -> value).min().getAsLong();

    }

    private List<Long> findAMatchingOutput(List<Long> program, List<Long> target) {
        List<Long> possibleStarts = new ArrayList<>();
        if (target.size() == 1) {
            possibleStarts.add(0L);
        } else {
            possibleStarts.addAll(findAMatchingOutput(program, target.subList(1, target.size())));
        }

        List<Long> possibleStartsResult = new ArrayList<>();
        for(long possibleStart  : possibleStarts) {
            long aStart = possibleStart << 3;
            // only 3 bits, so 8 max
            for (long toTry = 0b000L; toTry <= 0b111L; toTry++) {
                long candidate =  aStart | toTry;
                if (equalsProgram(runProgram(program, candidate), target)) {
                    possibleStartsResult.add(candidate);
                }
            }
        }
        return possibleStartsResult;
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
