package com.dissi.adventofcode.version2021.day24;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;
import static com.dissi.adventofcode.version2021.day24.ComputerSimulator.executeInstruction;
import static com.dissi.adventofcode.version2021.day24.ComputerSimulator.getRegisters;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NomadSmoll {

    private static final String LOCATION = "/2021/day24/input.txt";
    private final Map<String, String> memory = new HashMap<>(8192);
    private List<String> inputs;

    @SolutionAnnotation(day = 24, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        inputs = getInputAsStringList(LOCATION);
        Map<String, Long> registers = getRegisters();
        return getBest(registers, 0);
    }


    public String getBest(Map<String, Long> vals, int execPos) {
        if (execPos == inputs.size()) {
            long z = vals.get("z");
            if (z == 0) {
                return "";
            }
            return null;
        }

        String line = inputs.get(execPos);
        if (line.startsWith("inp")) {
            String reg = line.split(" ")[1];

            for (long i = 1; i <= 9; i++) {
                long e = i;
                // Wild guess that the first number has to be 9
                if (execPos < 5) {
                    e = 9;
                }
                Map<String, Long> v2 = new HashMap<>(vals);
                v2.put(reg, e);
                String sol = getBestMemo(v2, execPos + 1);
                if (sol != null) {
                    return "" + e + sol;
                }
            }
            return null;
        } else {
            executeInstruction(vals, line);
            return getBest(vals, execPos + 1);
        }
    }

    public String getBestMemo(Map<String, Long> vals, int execPos) {
        String state = "" + execPos + ":" + vals.toString();
        if (memory.containsKey(state)) {
            return memory.get(state);
        }
        String res = getBest(vals, execPos);
        memory.put(state, res);
        return res;
    }

}
