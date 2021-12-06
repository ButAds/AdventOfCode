package com.butads.adventofcode.version2021.day4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BingoSystemUtils {

    public static List<BingoCard> getCards(Queue<String> input) {
        List<BingoCard> cards = new ArrayList<>();
        int size = 5;
        int currentSize = 0;
        String[] lines = new String[size];
        while (!input.isEmpty()) {

            String line = input.poll();
            if (line.isEmpty()) {
                continue;
            }
            lines[currentSize] = line;
            currentSize++;
            if (currentSize == size) {
                cards.add(new BingoCard(cards.size(), lines));
                currentSize = 0;
            }
        }
        return cards;
    }

    public static LinkedList<BingoCard> getOrderOfWinning(BingoDraws answers, List<BingoCard> cards) {

        LinkedList<BingoCard> winnerOrder = new LinkedList<>();
        List<BingoCard> remainingCards = new ArrayList<>(cards);

        for (int i = 4; i < answers.getTotalAnswers(); i++) {
            answers.setAmount(i);
            List<BingoCard> winners = remainingCards.stream()
                .filter(x -> x.winsAfterNumber(answers))
                .toList();
            remainingCards.removeAll(winners);
            winnerOrder.addAll(winners);
        }
        return winnerOrder;
    }

}
