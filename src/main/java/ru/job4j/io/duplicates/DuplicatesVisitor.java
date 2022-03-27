package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    HashMap<FileProperty, List<Path>> fullList = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        var listPath = fullList.computeIfAbsent(
                new FileProperty(file.toFile().length(),
                        file.getFileName().toString()), k -> new LinkedList<>());
        listPath.add(file);
        fullList.put(new FileProperty(file.toFile().length(), file.getFileName().toString()), listPath);
        return FileVisitResult.CONTINUE;
    }

    /**
     * Выводит список дубликатов на экран
     */
    public void printDoubles() {
        for (Map.Entry<FileProperty, List<Path>> entry : fullList.entrySet()) {
            if (entry.getValue().size() > 1) {
                for (Path path : entry.getValue()) {
                    System.out.println(path);
                }
            }
        }
    }

    /**
     * возвращает список дубликатов для дальнейшей обработки
     * @return - список дубликатов
     */
    public Map<FileProperty, List<Path>> getDoubles() {
        Map<FileProperty, List<Path>> listDoubles = new HashMap<>();
        for (Map.Entry<FileProperty, List<Path>> entry : fullList.entrySet()) {
            if (entry.getValue().size() > 1) {
                listDoubles.put(entry.getKey(), entry.getValue());
            }
        }
        return listDoubles;
    }
}
