package com.dissi.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BufferUtils {

    private static BufferedReader getInputAsStream(String location) {
        var is = BufferUtils.class.getResourceAsStream(location);
        var streamReader = new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }

    public static String getInputAsString(String location) throws IOException {
        var br = getInputAsStream(location);
        String toReturn = br.lines().collect(Collectors.joining());
        br.close();
        return toReturn;
    }

    public static List<String> getInputAsStringList(String location) throws IOException {
        var br = getInputAsStream(location);
        List<String> allItems = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            allItems.add(line);
        }
        br.close();
        return allItems;
    }

    public static List<Integer> getInputAsIntList(String location) throws IOException {
        ArrayList<Integer> items = new ArrayList<>();
        var reader = getInputAsStream(location);
        String line;
        while ((line = reader.readLine()) != null) {
            int now = Integer.parseInt(line);
            items.add(now);
        }
        reader.close();
        return items;
    }

    public static List<Integer> getSingleLineAsInt(String location) throws IOException {
        ArrayList<Integer> items = new ArrayList<>();
        var reader = getInputAsStream(location);
        String line;
        while ((line = reader.readLine()) != null) {
            Arrays.stream(line.split(",")).map(Integer::parseInt).forEach(items::add);

        }
        reader.close();
        return items;
    }


    public static Queue<String> getInputAsQueue(String location) throws IOException {
        var br = getInputAsStream(location);
        Queue<String> allItems = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            allItems.add(line);
        }
        br.close();
        return allItems;
    }

}
