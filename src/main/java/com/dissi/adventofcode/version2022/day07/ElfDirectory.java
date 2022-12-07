package com.dissi.adventofcode.version2022.day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record ElfDirectory(String name, ElfDirectory up, HashSet<ElfDirectory> elfDirectories, Set<ElfFile> elfFiles) {

    public static ElfDirectory createFromInput(List<String> commands) {
        ElfDirectory root = new ElfDirectory("root", null, new HashSet<>(), new HashSet<>());
        ElfDirectory current = root;

        for (String command : commands) {
            if (command.startsWith("$ ls")) {
                continue;
            }
            if (command.startsWith("$ cd")) {
                current = current.getDirectoryByName(command.split(" ")[2]);
            } else if (command.startsWith("dir")) {
                current.getDirectoryByName(command.split(" ")[1]);
                // new dir
            } else {
                String[] fileData = command.split(" ");
                current.addFile(new ElfFile(fileData[1], Integer.parseInt(fileData[0])));
                // Existing file
            }
        }

        return root.getDirectoryByName("/");
    }

    long size() {
        return elfFiles.stream().mapToInt(ElfFile::size).sum() + elfDirectories.stream().mapToLong(ElfDirectory::size)
            .sum();
    }

    void addFile(ElfFile elfFile) {
        elfFiles.add(elfFile);
    }

    ElfDirectory getDirectoryByName(String name) {
        if (name.equals("..")) {
            return up;
        }
        return elfDirectories.stream().filter(f -> f.name().equals(name)).findAny().orElseGet(() -> {
            ElfDirectory file = new ElfDirectory(name, this, new HashSet<>(), new HashSet<>());
            elfDirectories.add(file);
            return file;
        });
    }

    List<ElfDirectory> getAllDirectories() {
        List<ElfDirectory> all = new ArrayList<>(elfDirectories);
        elfDirectories.stream().map(ElfDirectory::getAllDirectories).forEach(all::addAll);
        return all;
    }


    @Override
    public int hashCode() {
        // Omit UP.
        int result = name.hashCode();
        result = 31 * result + elfDirectories.hashCode();
        result = 31 * result + elfFiles.hashCode();
        return result;
    }

    public void printString(int depth) {
        for (ElfDirectory directory : elfDirectories) {
            System.out.println(
                Stream.generate(() -> "\t").limit(depth).collect(Collectors.joining()) + " - " + directory.name
                    + " (DIR, size = " + directory.size() + ")");
            directory.printString(depth + 1);
        }
        for (ElfFile file : elfFiles) {
            System.out.println(
                Stream.generate(() -> "\t").limit(depth).collect(Collectors.joining()) + " - " + file.name()
                    + " (File, "
                    + file.size() + ")");
        }
    }
}
