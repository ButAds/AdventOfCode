package com.dissi.adventofcode.version2021.day20;

import static com.dissi.adventofcode.helpers.Direction.EAST;
import static com.dissi.adventofcode.helpers.Direction.NORTH;
import static com.dissi.adventofcode.helpers.Direction.NORTHEAST;
import static com.dissi.adventofcode.helpers.Direction.NORTHWEST;
import static com.dissi.adventofcode.helpers.Direction.SELF;
import static com.dissi.adventofcode.helpers.Direction.SOUTH;
import static com.dissi.adventofcode.helpers.Direction.SOUTHEAST;
import static com.dissi.adventofcode.helpers.Direction.SOUTHWEST;
import static com.dissi.adventofcode.helpers.Direction.WEST;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.helpers.Coordinates;
import com.dissi.adventofcode.helpers.Grid;
import com.dissi.adventofcode.helpers.Position;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

@Log
@UtilityClass
public class EnhanceNow {

    public static int doDay(String location, int times) throws IOException {
        List<String> in = BufferUtils.getInputAsStringList(location);
        char[] replace = in.get(0).toCharArray();
        in.remove(0);
        in.remove(0);

        Grid<Character> inputImage = new Grid<>(
            in.stream().map(String::toCharArray).map(EnhanceNow::toCharArray).toArray(Character[][]::new));

        Coordinates inputCoords = new Coordinates();
        inputCoords.fill(inputImage.grid, '#');

        Coordinates outputImage = new Coordinates();
        for (int it = 0; it < times; it++) {
            int iteration = it;
            log.info("Start of day " + it);
            Grid<Integer> n = getIntegerGrid(inputCoords);
            for (int i = -times; i <= n.sizeX() + times; i++) {
                for (int j = -times; j <= n.sizeY() + times; j++) {
                    Position thisPoint = new Position(i, j);
                    String bin = Stream.of(NORTHWEST, NORTH, NORTHEAST, WEST, SELF, EAST, SOUTHWEST, SOUTH, SOUTHEAST)
                        .map(dir -> Long.toString(
                            n.at(dir.translate(thisPoint), (iteration % 2 == 1 && replace[0] == '#' ? 1 : 0))))
                        .collect(Collectors.joining());
                    if (replace[getAsDecimal(bin)] == '#') {
                        outputImage.add(thisPoint);
                    }
                }

            }
            inputCoords = outputImage;
            outputImage = new Coordinates();
        }
        return inputCoords.size();
    }

    private static Character[] toCharArray(char[] chars) {
        Character[] toreturn = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) {
            toreturn[i] = chars[i];
        }
        return toreturn;
    }

    private static Grid<Integer> getIntegerGrid(Coordinates coords) {
        Grid<Integer> g = new Grid<>(new Integer[coords.sizeY() + 1][coords.sizeX() + 1]);
        int minX = coords.minX();
        int minY = coords.minY();
        coords.getCoordinates().stream()
            .map(e -> new Position(e.getX() - minX, e.getY() - minY))
            .forEach(e -> g.set(e, 1));
        return g;
    }

    private static int getAsDecimal(String s) {
        return Integer.parseInt(new BigInteger(s, 2).toString(10));
    }
}
