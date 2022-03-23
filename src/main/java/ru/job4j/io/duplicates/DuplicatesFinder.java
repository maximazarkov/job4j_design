package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Map<FileProperty, List<Path>> listDoubles = new HashMap<>();
        List<Path> ld = new LinkedList<>();
        DuplicatesVisitor dv = new DuplicatesVisitor();
        ld.add(Files.walkFileTree(Path.of("./"), dv));
        listDoubles = dv.getDoubles();
        System.out.println("---");
        System.out.println(listDoubles);

        System.out.println(listDoubles.size());
    }
}