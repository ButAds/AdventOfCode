package com.dissi.adventofcode.version2024.day05;

import com.dissi.adventofcode.BufferUtils;
import com.dissi.adventofcode.SolutionAnnotation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PrintQueue {

    private final List<String> data;

    public PrintQueue() throws IOException {
        this.data = BufferUtils.getInputAsStringList(2024, 5, false);
    }

    @SolutionAnnotation(day = 5, section = 1, year = 2024)
    public int getAnswer() {
        ArrayList<AViertje> pages = new ArrayList<>();
        int answer = 0;
        for (String page : data) {
            answer += parselogicPartOne(page, pages);
        }

        return answer;
    }

    @SolutionAnnotation(day = 5, section = 2, year = 2024)
    public int getAnswerTwo() {
        ArrayList<AViertje> pages = new ArrayList<>();
        int answer = 0;
        for (String page : data) {
            answer += logicPartTwo(page, pages);
        }

        return answer;
    }

    private static boolean isAddPage(String line, ArrayList<AViertje> pagina) {
        if (line.isEmpty()) {
            return true;
        } else if (line.length() == 5) { // first half
            String[] split = line.split("\\|");
            AViertje page1 = findPage(Integer.parseInt(split[0]), pagina);
            AViertje page2 = findPage(Integer.parseInt(split[1]), pagina);
            page2.dependsOn().add(page1);
            return true;
        } else {
            return false;
        }
    }

    private static int parselogicPartOne(String line, ArrayList<AViertje> paginas) {
        if (isAddPage(line, paginas)) {
            return 0;
        }
        // Actual pages
        String[] split = line.split(",");
        ArrayList<AViertje> illegalPages = new ArrayList<>();
        for (String s : split) {
            AViertje page = findPage(Integer.parseInt(s), paginas);
            if (illegalPages.contains(page)) {
                return 0;
            }
            illegalPages.addAll(page.dependsOn());
        }

        return Integer.parseInt(split[split.length / 2]);
    }

    private static int logicPartTwo(String line, ArrayList<AViertje> paginas) {
        if (isAddPage(line, paginas)) {
            return 0;
        }

        // Actual pages
        String[] split = line.split(",");
        ArrayList<AViertje> printingPages = new ArrayList<>();
        for (String splitPage : split) {
            AViertje page = findPage(Integer.parseInt(splitPage), paginas);
            printingPages.add(page);
        }
        boolean changeOrder = false;
        for (int i = 0; i < printingPages.size(); i++) {
            AViertje printingPage = printingPages.get(i);
            for (int j = 0; j < printingPages.indexOf(printingPage); j++) {
                if (printingPages.get(j).dependsOn().contains(printingPage)) {
                    printingPages.remove(printingPage);
                    printingPages.add(j, printingPage);
                    changeOrder = true;
                }
            }
        }
        if (changeOrder) {
            return printingPages.get(printingPages.size() / 2).number();
        } else {
            return 0;
        }
    }


    private static AViertje findPage(int number, ArrayList<AViertje> pages) {
        AViertje newPage = new AViertje(number, new ArrayList<>());
        if (pages.contains(newPage)) {
            newPage = pages.get(pages.indexOf(newPage));
        } else {
            pages.add(newPage);
        }
        return newPage;
    }
}

record AViertje(int number, List<AViertje> dependsOn) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AViertje aViertje)) {
            return false;
        }

        return number == aViertje.number;
    }
}
