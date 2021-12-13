package com.dissi.adventofcode.version2021.day13;


import com.dissi.adventofcode.helpers.Position;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Paper {

    private final Queue<Instruction> instructions = new LinkedList<>();
    private Set<Position> positions;
    private int xSize = 0;
    private int ySize = 0;

    public Paper(List<String> lines) {
        positions = new HashSet<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            if (line.startsWith("fold")) {
                addFold(line);
            } else {
                addPoint(line);
            }
        }
    }

    private void addPoint(String line) {
        Position position = new Position(line);
        if (position.getX() > xSize) {
            xSize = position.getX();
        }

        if (position.getY() > ySize) {
            ySize = position.getY();
        }

        positions.add(position);
    }

    private void addFold(String line) {
        instructions.add(new Instruction(line));
    }

    public int getVisibleDots() {
        return positions.size();
    }

    public boolean fold() {
        Set<Position> newPositions = new HashSet<>();
        if (instructions.isEmpty()) {
            return true;
        }
        Instruction instruction = instructions.poll();
        int foldOn = instruction.location;
        if (instruction.line.equals("x")) {
            xSize = foldOn;
            for (Position pos : positions) {
                if (pos.getX() < foldOn) {
                    newPositions.add(pos);
                } else {
                    int distance = pos.getX() - foldOn;
                    int newX = foldOn - distance;
                    newPositions.add(new Position(newX, pos.getY()));
                }

            }
        } else {
            ySize = foldOn;
            for (Position pos : positions) {
                if (pos.getY() < foldOn) {
                    newPositions.add(pos);
                } else {
                    int distance = pos.getY() - foldOn;
                    int newY = foldOn - distance;
                    newPositions.add(new Position(pos.getX(), newY));
                }
            }
        }
        positions = newPositions;
        return this.instructions.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                sb.append(positions.contains(new Position(x, y)) ? '█' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private static class Instruction {

        String line;
        int location;

        Instruction(String line) {
            String[] instructions = line.split(" ")[2].split("=");
            this.line = instructions[0];
            location = Integer.parseInt(instructions[1]);
        }
    }
}
