package com.butads.adventofcode.version2021.day.four;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public class BingoCard {

    private static final int MAX_WIDTH = 5;
    private final ArrayList<List<Integer>> lines;
    private final int customer;
    private int wonAt = -1;
    private int winningNumber = -1;

    public BingoCard(int customerId, String[] lines) {
        customer = customerId;
        this.lines = new ArrayList<>(lines.length);
        for (String line : lines) {
            this.lines.add(
                Arrays.stream(line.split(" "))
                    .filter(not(String::isEmpty))
                    .map(Integer::parseInt).toList()
            );
        }
    }

    public int calculateWinner(BingoDraws answer) {
        List<Integer> listAtWin = answer.getListAt(wonAt);
        return lines.stream()
            .flatMap(Collection::stream)
            .filter(value -> !listAtWin.contains(value))
            .mapToInt(value -> value)
            .sum() * winningNumber;
    }

    public boolean winsAfterNumber(BingoDraws answer) {
        List<Integer> winningNumbers = answer.getCurrentDraws();
        winningNumber = winningNumbers.get(winningNumbers.size() - 1);

        // left right
        boolean leftRight = lines.stream().anyMatch(winningNumbers::containsAll);
        if (leftRight) {
            wonAt = winningNumbers.size();
            return true;
        }

        // top down
        for (int width = 0; width < MAX_WIDTH; width++) {
            boolean winner = true;
            for (List<Integer> currentLine : lines) {
                if (!winningNumbers.contains(currentLine.get(width))) {
                    winner = false;
                    break;
                }
            }
            if (winner) {
                wonAt = winningNumbers.size();
                return true;
            }
        }
        // diagonals
        // Do not count!
        // Nope
        return false;
    }
}
