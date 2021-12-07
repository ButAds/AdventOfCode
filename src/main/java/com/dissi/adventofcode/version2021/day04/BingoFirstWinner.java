package com.dissi.adventofcode.version2021.day04;

import static java.util.Objects.requireNonNull;

import com.dissi.adventofcode.Answerable;
import com.dissi.adventofcode.BufferUtils;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class BingoFirstWinner implements Answerable {

    private static final String LOCATION = "/2021/day4/input.txt";

    @Override
    public String getAnswer() throws IOException {
        Queue<String> input = BufferUtils.getInputAsQueue(LOCATION);
        BingoDraws totalAnswer = new BingoDraws(requireNonNull(input.poll()));
        List<BingoCard> cards = BingoSystemUtils.getCards(input);

        LinkedList<BingoCard> orderOfWinning = BingoSystemUtils.getOrderOfWinning(totalAnswer, cards);
        return "" + orderOfWinning.getFirst().calculateWinner(totalAnswer);
    }

    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public int getSection() {
        return 1;
    }
}
