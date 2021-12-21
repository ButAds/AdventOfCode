package com.dissi.adventofcode.version2021.day21;

import static com.dissi.adventofcode.BufferUtils.getInputAsStringList;

import com.dissi.adventofcode.Answerable;
import java.io.IOException;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class MensErgerJeNiet implements Answerable {

    private static final String LOCATION = "/2021/day21/example.txt";

    private Player one;
    private Player two;
    private int diceThrows = 0;

    @Override
    public int getDay() {
        return 21;
    }

    @Override
    public int getSection() {
        return 1;
    }

    @Override
    public String getAnswer() throws IOException {
        List<String> inputAsStringList = getInputAsStringList(LOCATION);

        one = new Player(inputAsStringList.get(0));
        two = new Player(inputAsStringList.get(1));

        Player loser = simulate(1000);

        return "" + (diceThrows * loser.score);
    }

    private Player simulate(int maxScore) {

        while (true) {
            doPlay(one);
            if (one.score >= maxScore) {
                return two;
            }
            doPlay(two);
            if (two.score >= maxScore) {
                return one;
            }
        }
    }

    private void doPlay(Player player) {
        int steps = doThrow();
        player.pos = ((player.pos - 1 + steps) % 10) + 1;
        player.score += player.pos;
    }

    private int doThrow() {
        return diceThrow() + diceThrow() + diceThrow();
    }

    private int diceThrow() {
        int toReturn = (diceThrows % 100) + 1;
        diceThrows++;
        return toReturn;
    }
}
