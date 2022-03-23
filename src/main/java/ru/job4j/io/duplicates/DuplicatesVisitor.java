package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, List<Path>> listDoubles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.toFile().isDirectory()) {

            var fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
            var listPath = listDoubles.get(fileProperty);
            if (listPath == null) {
                listPath = new LinkedList<>();
                listPath.add(file);
                listDoubles.put(new FileProperty(file.toFile().length(), file.getFileName().toString()), listPath);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public HashMap<FileProperty, List<Path>> getDoubles() {
        return listDoubles;
    }
}
