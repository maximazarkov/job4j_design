package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        checkArgs(args);
        Path start = Paths.get(args[0]);
        String endsWithParam = args[1];
        search(start, p -> p
                .toFile()
                .getName()
                .endsWith(endsWithParam))
                .forEach(System.out::println);
    }

    /**
     * Проверка входных параметров
     * @param args - входные параметры
     */
     private static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Error entering command parameters."
                    + " Usage java -jar dir.jar ROOT_FOLDER FIND_EXT.");
        }
        if (!Files.exists(Paths.get(args[0]))) {
            throw new IllegalArgumentException(
                    "Dir not exists. Usage java -jar dir.jar ROOT_FOLDER FIND_EXT.");
        }
        if (!args[1].matches("\\.[\\w]+")) {
            throw new IllegalArgumentException("Error entering the extension mask. Usage "
                    + "java -jar dir.jar ROOT_FOLDER FIND_EXT. "
                    + "Example FIND_EXT: .pdf or .txt etc.");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}