package com.butads.adventofcode.version2021;

import java.io.IOException;

public interface Answerable {

    int getDay();

    int getSection();

    String getAnswer() throws IOException;
}
