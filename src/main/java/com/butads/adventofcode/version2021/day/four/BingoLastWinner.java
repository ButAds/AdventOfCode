package com.butads.adventofcode.version2021.day.four;

import static com.butads.adventofcode.version2021.day.four.BingoSystemUtils.getCards;
import static com.butads.adventofcode.version2021.day.four.BingoSystemUtils.getOrderOfWinning;
import static java.util.Objects.requireNonNull;

import com.butads.adventofcode.BufferUtils;
import com.butads.adventofcode.version2021.Answerable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class BingoLastWinner implements Answerable {

    private static final String LOCATION = "/2021/day4/input.txt";


    @Override
    public String getAnswer() throws IOException {
        Queue<String> input = BufferUtils.getInputAsQueue(LOCATION);
        BingoDraws totalAnswer = new BingoDraws(requireNonNull(input.poll()));
        List<BingoCard> cards = getCards(input);

        LinkedList<BingoCard> orderOfWinning = getOrderOfWinning(totalAnswer, cards);
        return "" + orderOfWinning.getLast().calculateWinner(totalAnswer);
    }

    @Override
    public int getDay() {
        return 4;
    }

    @Override
    public int getSection() {
        return 2;
    }
}
