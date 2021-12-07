package com.dissi.adventofcode;

import java.io.IOException;

public interface Answerable {

    default int getYear() {
        return 2021;
    }

    int getDay();

    int getSection();

    String getAnswer() throws IOException;
}
