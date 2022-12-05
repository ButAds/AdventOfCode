package com.dissi.adventofcode.version2022.day05;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CargoDock {

    public void doInstructions(List<DockInstruction> instructions) {
        instructions.forEach(dockInstruction -> {
                for (int i = 0; i < dockInstruction.amount; i++) {
                    docks.get(dockInstruction.to).add(docks.get(dockInstruction.from).pollLast());
                }
            }
        );
    }

    public void doImprovedInstructions(List<DockInstruction> instructions) {
        instructions.forEach(dockInstruction -> {
                LinkedList<String> toDump = new LinkedList<>();
                for (int i = 0; i < dockInstruction.amount; i++) {
                    toDump.addFirst(docks.get(dockInstruction.from).pollLast());
                }
                docks.get(dockInstruction.to).addAll(toDump);
            }
        );
    }

    public String getAnswer() {
        return docks.stream().map(LinkedList::getLast).collect(Collectors.joining());
    }

    record DockInstruction(int amount, int from, int to) {

        static DockInstruction create(String data) {
            String[] split = data.split(" ");
            return new DockInstruction(parseInt(split[1]), parseInt(split[3]) - 1, parseInt(split[5]) - 1);
        }
    }

    private final List<LinkedList<String>> docks;

    static CargoDock create(String cargoString) {
        String[] cargo = cargoString.split("\n");
        String amount = cargo[cargo.length - 1];
        List<LinkedList<String>> docks = Arrays.stream(amount.split(" {2}"))
            .map(s -> new LinkedList<String>())
            .toList();
        for (int i = cargo.length - 2; i >= 0; i--) {
            String cargoLine = cargo[i];
            for (int dock = 0; dock < docks.size(); dock++) {
                int stringLoc = 2 + (dock * 4);
                if (cargoLine.length() < stringLoc) {
                    break;
                }
                String atLot = cargoLine.substring(stringLoc - 1, stringLoc);
                if (!atLot.equals(" ")) {
                    docks.get(dock).add(atLot);
                }
            }
        }
        return new CargoDock(docks);
    }

    static List<DockInstruction> createInstructions(String instructionBlob) {
        return Arrays.stream(instructionBlob.split("\n")).map(DockInstruction::create).toList();
    }

}
