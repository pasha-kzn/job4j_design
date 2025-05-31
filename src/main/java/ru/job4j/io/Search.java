package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArguments(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArguments(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Параметры не переданы. Укажите условия поиска");
        }
        if (args.length < 2) {
            throw new IllegalArgumentException("Не указан путь или расширение файла");
        }
        if (args.length > 2) {
            throw new IllegalArgumentException("Удалите лишние параметры");
        }
    }
}