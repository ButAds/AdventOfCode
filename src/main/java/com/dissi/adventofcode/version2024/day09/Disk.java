package com.dissi.adventofcode.version2024.day09;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Disk {

    public static XmasDiskSystem defragment(String input, boolean blockFileMove) {
        XmasDiskSystem xmasDiskSystem = parse(input);

        for (int i = xmasDiskSystem.files.size() - 1; i >= 0; i--) {
            FileItem file = xmasDiskSystem.getFile(i);

            int blockMoveIterations = file.size();
            int blockSizeToSearch = blockFileMove ? file.size() : 1;
            FileItem blankFileItem = null;
            while (blockMoveIterations-- > 0) {
                blankFileItem = blankFileItem == null ? xmasDiskSystem.getBlankItem(blockSizeToSearch) : blankFileItem;

                if (blankFileItem == null || blankFileItem.start > file.start) {
                    break;
                }

                file.removeBlockFromEnd();
                int blankBlock = blankFileItem.removeBlockFromStart();
                file.addBlock(blankBlock);
                if (blankFileItem.blocks.isEmpty()) {
                    blankFileItem = null;
                }
            }
            if (blankFileItem != null) {
                xmasDiskSystem.emptyList.get(blankFileItem.size()).add(blankFileItem);
            }
        }
        return xmasDiskSystem;
    }

    private static XmasDiskSystem parse(String line) {
        List<FileItem> files = new ArrayList<>();
        LinkedHashMap<Integer, FileItem> blankSpaces = new LinkedHashMap<>();
        int index = 0;
        int id = 0;
        for (int i = 0; i < line.length(); i++) {
            int val = line.charAt(i) - '0';
            if (i % 2 == 0) {
                files.add(new FileItem(id, index, index + val - 1));
                id++;
                index += val;
            } else if (val > 0) {
                blankSpaces.put(id, new FileItem(id, index, index + val - 1));
                index += val;
            }
        }
        List<PriorityQueue<FileItem>> emptySpaceList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            emptySpaceList.add(new PriorityQueue<>(Comparator.comparingInt(i2 -> i2.start)));
        }

        blankSpaces.values().forEach(blank -> emptySpaceList.get(blank.size()).add(blank));
        return new XmasDiskSystem(files, emptySpaceList);
    }

    public record XmasDiskSystem(List<FileItem> files, List<PriorityQueue<FileItem>> emptyList) {

        FileItem getFile(int index) {
            return files.get(index);
        }

        long checkSum() {
            return files.stream().map(FileItem::checkSum).mapToLong(l -> l).sum();
        }

        FileItem getBlankItem(int size) {
            int start = Integer.MAX_VALUE;
            int blankItemLocation = -1;

            for (int i = emptyList.size() - 1; i >= 0; i--) {
                var heap = emptyList.get(i);
                if (i >= size && !heap.isEmpty() && heap.peek().start < start) {
                    start = heap.peek().start;
                    blankItemLocation = i;
                }
            }
            return blankItemLocation != -1 ? emptyList.get(blankItemLocation).poll() : null;
        }
    }

    static class FileItem {
        private final int id;
        private int start;
        private final ArrayDeque<Integer> blocks;

        FileItem(int id, int s, int e) {
            this.id = id;
            this.start = s;
            this.blocks = new ArrayDeque<>();
            for (int i = s; i <= e; i++) {
                blocks.add(i);
            }
        }

        long checkSum() {
            return blocks.stream()
                .mapToInt(blockId -> blockId)
                .mapToLong(blockId -> ((long) blockId * id))
                .sum();
        }

        int size() {
            return blocks.size();
        }

        void removeBlockFromEnd() {
            blocks.removeLast();
        }

        int removeBlockFromStart() {
            start++;
            return blocks.removeFirst();
        }

        void addBlock(int id) {
            blocks.addFirst(id);
        }
    }
}
