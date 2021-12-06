package com.butads.adventofcode;

import static java.util.Comparator.comparing;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log
public class PrintSolutions {

    private final List<Answerable> answers;

    @Value("${config.performance.loops:1}")
    private int performanceLoopCount;

    @Value("${config.year:-1}")
    private int whatYear;

    @Value("${config.day:-1}")
    private int fromDay;

    private void print(Answerable answerable) {
        try {
            Instant start = Instant.now();
            for (int i = 0; i < performanceLoopCount; i++) {
                answerable.getAnswer();
            }
            Instant end = Instant.now();
            log.info(String.format("Day [%s] Section [%s] - took [%sms] - Answer [%s]",
                answerable.getDay(),
                answerable.getSection(),
                (Duration.between(start, end).dividedBy(performanceLoopCount).toNanos() / 1_000_000F),
                answerable.getAnswer()));

        } catch (IOException e) {
            log.warning(String.format("Day [%s]  Section [%s] - ERROR: [%s]", answerable.getDay(),
                answerable.getSection(),
                e.getMessage()));
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void doPrint() {
        Comparator<Answerable> comparing = comparing(Answerable::getDay)
            .thenComparing(Answerable::getSection).reversed();
        answers.stream()
            .filter(answerable -> whatYear == -1 || whatYear == answerable.getYear())
            .filter(answerable -> answerable.getDay() >= fromDay)
            .sorted(comparing)
            .forEach(this::print);

    }

}
