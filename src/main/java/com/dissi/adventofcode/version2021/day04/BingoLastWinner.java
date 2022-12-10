package com.dissi.adventofcode.version2021.day04;

import static java.util.Objects.requireNonNull;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BingoLastWinner {

    private static final String LOCATION = "/2021/day4/input.txt";

    @SolutionAnnotation(day = 4, section = 2, year = 2021)
    public String getAnswer() throws IOException {

        Queue<String> input = BufferUtils.getInputAsQueue(LOCATION);
        BingoDraws totalAnswer = new BingoDraws(requireNonNull(input.poll()));
        List<BingoCard> cards = BingoSystemUtils.getCards(input);

        LinkedList<BingoCard> orderOfWinning = BingoSystemUtils.getOrderOfWinning(totalAnswer, cards);
        return "" + orderOfWinning.getLast().calculateWinner(totalAnswer);
    }

}
