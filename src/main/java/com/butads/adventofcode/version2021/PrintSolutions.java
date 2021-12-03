package com.butads.adventofcode.version2021;

import static java.util.Comparator.comparing;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log
public class PrintSolutions {

    private final List<Answerable> answers;

    public static String padLeft(int item, int n) {
        return String.format(MessageFormat.format("%{0}s", n), item);
    }

    private void print(Answerable answerable) {
        try {
            log.info(String.format("Day [%s]  Section [%s] - Answer [%s]",
                answerable.getDay(),
                answerable.getSection(),
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
        answers.stream().sorted(comparing).forEach(this::print);

    }

}
