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
        if (!file.toFile().isDirectory()) {

            var fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
            var listPath = fullList.get(fileProperty);
            if (listPath == null) {
                listPath = new LinkedList<>();
            }
            listPath.add(file);
            fullList.put(new FileProperty(file.toFile().length(), file.getFileName().toString()), listPath);

            if (listPath.size() > 1) {
                System.out.println(listPath);
            }

        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * возвращает список дубликатов для дальнейшей обработки
     * @return - список дубликатов
     */
    public Map<FileProperty, List<Path>> getDoubles() {
        Map<FileProperty, List<Path>> listDoubles = new HashMap<>();
        Iterator<Map.Entry<FileProperty, List<Path>>> entries = fullList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<FileProperty, List<Path>> entry = entries.next();
            if (entry.getValue().size() > 1) {
                listDoubles.put(entry.getKey(), entry.getValue());
            }
        }
        return listDoubles;
    }
}
