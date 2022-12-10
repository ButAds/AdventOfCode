package com.dissi.adventofcode.version2021.day21;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.List;
import lombok.extern.java.Log;

@Log

public class MultipleErgernissen {

    private static final String LOCATION = "/2021/day21/input.txt";

    @SolutionAnnotation(day = 21, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        List<String> inputAsStringList = getInputAsStringList(LOCATION);
        Player one = new Player(inputAsStringList.get(0));
        Player two = new Player(inputAsStringList.get(1));
        return "" + (splitUniverse(one, two, true));
    }

    public long splitUniverse(Player p1, Player p2, boolean p1Turn) {
        if (p1.score >= 21) {
            return 1;
        } else if (p2.score >= 21) {
            return 0;
        }

        Player currentPlayer = p1Turn ? p1 : p2;
        long sum = 0;
        for (int diceResult = 3; diceResult <= 9; diceResult++) {
            int oldPos = currentPlayer.pos;
            long oldScore = currentPlayer.score;
            doPlay(currentPlayer, diceResult);

            int multiplier = diceDistribution(diceResult);
            // Yo dawg.
            sum += multiplier * splitUniverse(p1, p2, !p1Turn);

            currentPlayer.pos = oldPos;
            currentPlayer.score = oldScore;
        }
        return sum;
    }

    private void doPlay(Player player, int diceThrow) {
        player.pos = ((player.pos - 1 + diceThrow) % 10) + 1;
        player.score += player.pos;
    }

    private int diceDistribution(int throwItem) {
        // predetermined FTW.
        return switch (throwItem) {
            case 3, 9 -> 1;
            case 4, 8 -> 3;
            case 5, 7 -> 6;
            case 6 -> 7;
            default -> throw new RuntimeException("Boom.");
        };
    }
}
