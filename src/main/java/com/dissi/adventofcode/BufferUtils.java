package com.dissi.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static String getInputAsStringNoJoining(String location) throws IOException {
        try {
            return Files.readString(Paths.get(Objects.requireNonNull(BufferUtils.class.getResource(location)).toURI()));
        } catch (URISyntaxException e) {
            throw new IOException("Does not exist");
        }
    }

    public static List<String> getInputAsStringList(int year, int day, boolean example) {
        try {
            return getInputAsStringList(format(year, day, example));
        } catch (IOException e) {
            throw new IllegalArgumentException("Does not exist: " + format(year, day, example));
        }
    }

    public static String getInputAsString(int year, int day, boolean example) {
        try {
            return getInputAsString(format(year, day, example));
        } catch (IOException e) {
            throw new IllegalArgumentException("Does not exist: " + format(year, day, example));
        }
    }

    private static String format(int year, int day, boolean example) {
        return String.format("/%s/day%s/%s.txt", year, day, example ? "example" : "input");
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

    public static List<Integer> getInputAsIntList(int year, int day, boolean example) {
        try {
            return getInputAsIntList(format(year, day, example));
        } catch (IOException e) {
            throw new IllegalArgumentException("Does not exist: " + format(year, day, example));
        }
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
