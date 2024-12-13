package com.dissi.adventofcode.version2024.day13;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClawContraption {

    private final List<GameOn> games;

    public ClawContraption() {
        this.games = Arrays.stream(BufferUtils.getInputAsStringNoJoining(2024, 13, false).split("\n\n"))
            .map(ClawContraption::parse).toList();
    }

    private static GameOn parse(String section) {
        Pattern pattern = Pattern.compile(
            "Button A: X([+-]?\\d+\\.?\\d), Y([+-]?\\d+\\.?\\d)\\nButton B: X([+-]?\\d+\\.?\\d), Y([+-]?\\d+\\.?\\d)\\nPrize: X=([+-]?\\d+\\.?\\d), Y=([+-]?\\d+\\.?\\d)");

        Matcher theGame = pattern.matcher(section);
        theGame.find();
        return new GameOn(
            Long.parseLong(theGame.group(1)),
            Long.parseLong(theGame.group(2)),
            Long.parseLong(theGame.group(3)),
            Long.parseLong(theGame.group(4)),
            Long.parseLong(theGame.group(5)),
            Long.parseLong(theGame.group(6)));
    }

    @SolutionAnnotation(year = 2024, day = 13, section = 1)
    public long theClaw() {
        return runClawGame(0);
    }

    @SolutionAnnotation(year = 2024, day = 13, section = 2)
    public Object theLargeClaw() {
        return runClawGame(10_000_000_000_000L);
    }

    private long runClawGame(long offset) {
        return games.stream()
            .map(m -> m.withPrize(m.prizeX() + offset, m.prizeY() + offset))
            .mapToLong(GameOn::leastTokenCount)
            .sum();
    }

    public record GameOn(long aX, long aY, long bX, long bY, long prizeX, long prizeY) {
        public GameOn withPrize(long prizeX, long prizeY) {
            return new GameOn(aX, aY, bX, bY, prizeX, prizeY);
        }

        public long leastTokenCount() {
            long numerator = prizeX * aY - prizeY * aX;
            long b = numerator / (bX * aY - bY * aX);
            long remainingX = prizeX - b * bX;
            long l = aX == 0 ? prizeY : remainingX;
            long r = aX == 0 ? aY : aX;
            long a = l / r;
            return (a * aY + b * bY == prizeY && l % r == 0) ? 3 * a + b : 0;
        }
    }
}
