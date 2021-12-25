package com.dissi.adventofcode.version2021.day25;

import com.dissi.adventofcode.helpers.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloorMap {

    private final Map<Position, Character> seaCucumbers;
    private final int limitX;
    private final int limitY;

    private FloorMap(Map<Position, Character> seaCucumbers, int limitX, int limitY) {
        this.seaCucumbers = seaCucumbers;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    public static FloorMap fromString(List<String> lines) {
        var map = new HashMap<Position, Character>();
        for (int y = 0; y < lines.size(); ++y) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); ++x) {
                if (line.charAt(x) != '.') {
                    map.put(new Position(x, y), line.charAt(x));
                }
            }
        }
        return new FloorMap(map, lines.get(0).length(), lines.size());
    }

    public int tick() {
        return toMove('>') + toMove('v');
    }

    private int toMove(char direction) {
        List<Position> toMove = seaCucumbers.keySet().stream()
            .filter(k -> seaCucumbers.get(k) == direction)
            .filter(k -> !seaCucumbers.containsKey(getNext(k, limitX, limitY, direction)))
            .toList();
        toMove(toMove, direction);
        return toMove.size();
    }

    private void toMove(List<Position> toMove, char direction) {
        toMove.forEach(c -> {
            seaCucumbers.remove(c);
            seaCucumbers.put(getNext(c, limitX, limitY, direction), direction);
        });
    }

    private Position getNext(Position current, int maxX, int maxY, char direction) {
        if (direction == '>') {
            int nX = (current.getX() + 1 < maxX) ? current.getX() + 1 : 0;
            return new Position(nX, current.getY());
        }
        int nY = (current.getY() + 1 < maxY) ? current.getY() + 1 : 0;
        return new Position(current.getX(), nY);
    }
}
