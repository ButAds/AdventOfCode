package com.dissi.adventofcode.version2022.day05;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.version2022.day05.CargoDock.DockInstruction;
import java.io.IOException;
import java.util.List;

public class ImprovedCargoCrane {

    private static final String LOCATION = "/2022/day5/input.txt";
    private final String data;

    public ImprovedCargoCrane() throws IOException {
        data = BufferUtils.getInputAsStringNoJoining(LOCATION);
    }

    @SolutionAnnotation(day = 5, section = 1, year = 2022)
    public String getAnswer() {
        String[] movement = data.split("\n\n");
        CargoDock dock = CargoDock.create(movement[0]);
        List<DockInstruction> instructions = CargoDock.createInstructions(movement[1]);
        dock.doInstructions(instructions);

        return dock.getAnswer();
    }


    @SolutionAnnotation(day = 5, section = 2, year = 2022)
    public String doVersion9001() {
        String[] movement = data.split("\n\n");

        CargoDock dock = CargoDock.create(movement[0]);
        List<DockInstruction> instructions = CargoDock.createInstructions(movement[1]);
        dock.doImprovedInstructions(instructions);

        return "" + dock.getAnswer();
    }
}
