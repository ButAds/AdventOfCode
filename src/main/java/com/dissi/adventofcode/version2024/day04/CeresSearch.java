package com.dissi.adventofcode.version2024.day04;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import com.dissi.adventofcode.helpers.BigGrid;
import com.dissi.adventofcode.helpers.Direction;
import com.dissi.adventofcode.helpers.Position;
import java.util.HashMap;
import java.util.List;

public class CeresSearch {

    private final List<String> data;
    private final BigGrid<Character> grid;

    public CeresSearch() {
        this.data = BufferUtils.getInputAsStringList(2024, 4, false);
        this.grid = doParse();
    }

    public BigGrid<Character> doParse() {
        BigGrid<Character> grid = new BigGrid<>(new HashMap<>());
        int y = 0;
        for (String s : data) {
            char[] tiles = s.toCharArray();
            for (int x = 0; x < tiles.length; x++) {
                char c = tiles[x];
                grid.add(new Position(x, y), c);
            }
            y++;
        }

        return grid;
    }

    @SolutionAnnotation(day = 4, section = 1, year = 2024)
    public int whereXmasAt() {
        int amount = 0;
        long xMax = grid.maxX();
        long yMax = grid.maxY();

        for (int y = 0; y <= yMax; y++) {
            for (int x = 0; x <= xMax; x++) {
                Position checkPos = new Position(x, y);
                if (grid.getOrDefault(checkPos, '0') == 'X') {
                    for (Direction d : Direction.DIAGONAL) {
                        if (isMas(grid, checkPos, d)) {
                            amount++;
                        }
                    }
                }
            }
        }

        return amount;
    }

    @SolutionAnnotation(day = 4, section = 2, year = 2024)
    public int whereXAt() {
        int amount = 0;
        long xMax = grid.maxX();
        long yMax = grid.maxY();

        for (int y = 1; y < yMax; y++) {
            for (int x = 1; x < xMax; x++) {
                Position checkPos = new Position(x, y);
                if (grid.getOrDefault(checkPos, '0') == 'A' && isX(grid, checkPos)) {
                    amount++;
                }
            }
        }

        return amount;
    }

    private boolean isX(BigGrid<Character> grid, Position checkPos) {
        char topLeft = grid.getOrDefault(Direction.NORTHEAST.translate(checkPos), '0');
        char topRight = grid.getOrDefault(Direction.NORTHWEST.translate(checkPos), '0');
        char bottomLeft = grid.getOrDefault(Direction.SOUTHEAST.translate(checkPos), '0');
        char bottomRight = grid.getOrDefault(Direction.SOUTHWEST.translate(checkPos), '0');

        String diagonalTop = topLeft + "A" + bottomRight;
        String diagonalBottom = bottomLeft + "A" + topRight;

        if (!diagonalTop.equals("MAS") && !diagonalTop.equals("SAM")) {
            return false;
        }

        return diagonalBottom.equals("SAM") || diagonalBottom.equals("MAS");
    }

    private boolean isMas(BigGrid<Character> grid, Position checkPos, Direction direction) {
        Position next = direction.translate(checkPos);
        if (grid.getOrDefault(next, '0') != 'M') {
            return false;
        }

        next = direction.translate(next);
        if (grid.getOrDefault(next, '0') != 'A') {
            return false;
        }

        next = direction.translate(next);
        return grid.getOrDefault(next, '0') == 'S';
    }
}
