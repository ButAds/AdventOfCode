package com.butads.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BufferUtils {

    public static BufferedReader getInputAsStream(String location) {
        var is = BufferUtils.class.getResourceAsStream(location);
        var streamReader = new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }

    public static List<String> getInputAsStringList(String location) throws IOException {
        var br = getInputAsStream(location);
        List<String> allItems = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            allItems.add(line);
        }
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
        return items;
    }

    public static Queue<String> getInputAsQueue(String location) throws IOException {
        var br = getInputAsStream(location);
        Queue<String> allItems = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            allItems.add(line);
        }
        return allItems;
    }

}
