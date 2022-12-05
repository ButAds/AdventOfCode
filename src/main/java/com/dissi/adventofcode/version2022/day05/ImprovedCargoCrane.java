package com.dissi.adventofcode.version2022.day05;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.version2022.day05.CargoDock.DockInstruction;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ImprovedCargoCrane implements Answerable {


    private static final String LOCATION = "/2022/day5/input.txt";

    public String getAnswer() throws IOException {
        String data = BufferUtils.getInputAsStringNoJoining(LOCATION);

        String[] movement = data.split("\n\n");
        CargoDock dock = CargoDock.create(movement[0]);
        List<DockInstruction> instructions = CargoDock.createInstructions(movement[1]);
        dock.doImprovedInstructions(instructions);

        return "" + dock.getAnswer();
    }

    @Override
    public int getDay() {
        return 5;
    }

    @Override
    public int getSection() {
        return 2;
    }

    @Override
    public int getYear() {
        return 2022;
    }

}
